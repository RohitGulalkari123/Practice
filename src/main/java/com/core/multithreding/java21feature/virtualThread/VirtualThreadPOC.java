package com.core.multithreding.java21feature.virtualThread;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ============================================================
 *   PROOF OF CONCEPT: Virtual Threads in Java 21
 *   Use Case: Simulating a Concurrent API Test Execution Engine
 *   (QA Context: 100 test cases each making a "blocking network call")
 * ============================================================
 *
 *  RUN  : javac VirtualThreadPOC.java && java VirtualThreadPOC
 *  NEEDS: Java 21+
 *
 *  STRUCTURE:
 *    Section 1 → Background & Thread Model (in comments)
 *    Section 2 → Platform Threads (old way)
 *    Section 3 → Virtual Threads  (Java 21 way)
 *    Section 4 → Performance Benchmark (10,000 tasks)
 *    Section 5 → API Styles Reference
 *    Section 6 → Traps & Pitfalls Demo
 *    Section 7 → Summary + Mental Model
 */
public class VirtualThreadPOC {

    // ─────────────────────────────────────────────────────────────────────────
    //  CONSTANTS
    // ─────────────────────────────────────────────────────────────────────────
    static final int TOTAL_TASKS        = 100;    // 100 concurrent test cases
    static final int BLOCKING_DELAY_MS  = 200;    // each "API call" blocks 200ms
    static final int PLATFORM_POOL_SIZE = 20;     // fixed thread pool
    static final int BENCHMARK_TASKS    = 5_000;  // stress test count

    // ─────────────────────────────────────────────────────────────────────────
    //  SHARED TASK — Simulates one API test case
    //  Each test makes a blocking I/O call (REST endpoint under test)
    // ─────────────────────────────────────────────────────────────────────────
    static String executeTestCase(int testId) throws InterruptedException {
        String threadType = Thread.currentThread().isVirtual() ? "[VIRTUAL ]" : "[PLATFORM]";
        Thread.sleep(BLOCKING_DELAY_MS);  // simulate HTTP response wait
        return String.format("%s TC-%03d PASSED (carrier=%s)",
                threadType, testId, Thread.currentThread().getName());
    }

    static final AtomicInteger completedCount = new AtomicInteger(0);

    // ═════════════════════════════════════════════════════════════════════════
    //  SECTION 2 — PLATFORM THREADS (Traditional Multithreading)
    // ═════════════════════════════════════════════════════════════════════════
    /*
     * HOW IT WORKS:
     *   Platform thread → maps 1:1 with an OS thread
     *   Fixed pool of 20 → only 20 tasks run at a time
     *   When a thread calls Thread.sleep(200) → OS thread is PARKED
     *   That OS thread cannot serve another task while parked
     *
     * MATH:
     *   100 tasks ÷ 20 pool = 5 batches × 200ms = ~1000ms total
     *
     * COST:
     *   Each OS thread ≈ 1MB stack memory
     *   20 threads ≈ 20MB just for stack allocation
     *   Can't easily scale to 1000s of threads (OutOfMemoryError)
     */
    static void runWithPlatformThreads() throws InterruptedException {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  SECTION 2 — PLATFORM THREADS  (Fixed Pool = " + PLATFORM_POOL_SIZE + " OS threads)");
        System.out.println("═".repeat(65));
        System.out.println("  Flow: Submit 100 tasks → only 20 run at once → rest QUEUE UP");
        System.out.println("        Thread.sleep() → OS thread PARKED → cannot serve others");

        completedCount.set(0);
        List<String> results = new ArrayList<>();

        // Fixed pool — bounded by PLATFORM_POOL_SIZE
        ExecutorService executor = Executors.newFixedThreadPool(PLATFORM_POOL_SIZE);
        List<Future<String>> futures = new ArrayList<>();
        Instant start = Instant.now();

        for (int i = 1; i <= TOTAL_TASKS; i++) {
            final int testId = i;
            futures.add(executor.submit(() -> {
                String result = executeTestCase(testId);
                completedCount.incrementAndGet();
                return result;
            }));
        }

        for (Future<String> f : futures) {
            try {
                results.add(f.get());
            } catch (ExecutionException e) {
                results.add("ERROR: " + e.getMessage());
            }
        }

        long elapsed = Duration.between(start, Instant.now()).toMillis();
        executor.shutdown();

        System.out.println("\n  Sample Results (first 3):");
        results.stream().limit(3).forEach(r -> System.out.println("    " + r));

        System.out.println("\n  ┌──────────────────────────────────────────────────┐");
        System.out.printf ("  │  Tasks Completed   : %-5d                        │%n", completedCount.get());
        System.out.printf ("  │  Pool Size         : %-5d OS threads              │%n", PLATFORM_POOL_SIZE);
        System.out.printf ("  │  Total Time        : %-6d ms                     │%n", elapsed);
        System.out.printf ("  │  Expected Formula  : ceil(100/20) × 200 = ~1000ms │%n");
        System.out.printf ("  │  Bottleneck        : Tasks queued, OS threads idle │%n");
        System.out.println("  └──────────────────────────────────────────────────┘");
    }

    // ═════════════════════════════════════════════════════════════════════════
    //  SECTION 3 — VIRTUAL THREADS (Java 21 — Project Loom)
    // ═════════════════════════════════════════════════════════════════════════
    /*
     * HOW IT WORKS:
     *   Virtual thread → managed by JVM, NOT the OS
     *   JVM uses a small pool of "carrier" OS threads (≈ CPU core count)
     *   Each task gets its OWN virtual thread (no pool limit needed)
     *
     *   MOUNT  : virtual thread assigned to carrier OS thread → RUNNING
     *   UNMOUNT: virtual thread blocks (sleep/I/O) → detached from carrier
     *            carrier OS thread is FREE to run another virtual thread
     *   REMOUNT: when blocking finishes → virtual thread remounts on any carrier
     *
     * MATH:
     *   100 tasks → 100 virtual threads start IMMEDIATELY
     *   All 100 call sleep(200) → all UNMOUNT → all "wait" in parallel
     *   Total time ≈ 200ms (single batch)
     *
     * COST:
     *   Virtual thread ≈ few KB heap (continuation object)
     *   Can create 100,000+ virtual threads comfortably
     */
    static void runWithVirtualThreads() throws InterruptedException {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  SECTION 3 — VIRTUAL THREADS  (1 Virtual Thread per Task)");
        System.out.println("═".repeat(65));
        System.out.println("  Flow: Submit 100 tasks → 100 virtual threads start IMMEDIATELY");
        System.out.println("        Thread.sleep() → virtual thread UNMOUNTS → carrier is FREE");

        completedCount.set(0);
        List<String> results = new ArrayList<>();

        // No pool needed — JVM creates virtual threads on demand
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<Future<String>> futures = new ArrayList<>();
        Instant start = Instant.now();

        for (int i = 1; i <= TOTAL_TASKS; i++) {
            final int testId = i;
            futures.add(executor.submit(() -> {
                String result = executeTestCase(testId);
                completedCount.incrementAndGet();
                return result;
            }));
        }

        for (Future<String> f : futures) {
            try {
                results.add(f.get());
            } catch (ExecutionException e) {
                results.add("ERROR: " + e.getMessage());
            }
        }

        long elapsed = Duration.between(start, Instant.now()).toMillis();
        executor.shutdown();

        System.out.println("\n  Sample Results (first 3):");
        results.stream().limit(3).forEach(r -> System.out.println("    " + r));

        System.out.println("\n  ┌──────────────────────────────────────────────────┐");
        System.out.printf ("  │  Tasks Completed   : %-5d                        │%n", completedCount.get());
        System.out.printf ("  │  Virtual Threads   : 1 per task (no pool limit)  │%n");
        System.out.printf ("  │  Total Time        : %-6d ms                     │%n", elapsed);
        System.out.printf ("  │  Expected Formula  : 1 batch × 200ms = ~200ms    │%n");
        System.out.printf ("  │  Advantage         : All 100 run concurrently!   │%n");
        System.out.println("  └──────────────────────────────────────────────────┘");
    }

    // ═════════════════════════════════════════════════════════════════════════
    //  SECTION 4 — PERFORMANCE BENCHMARK (5,000 tasks)
    // ═════════════════════════════════════════════════════════════════════════
    static void runBenchmark() throws InterruptedException {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  SECTION 4 — BENCHMARK: " + BENCHMARK_TASKS + " tasks × " + BLOCKING_DELAY_MS + "ms blocking I/O");
        System.out.println("═".repeat(65));

        int ptPool = 200; // generous pool for platform threads

        // ── Platform Threads ──────────────────────────────────────────────
        System.out.println("\n  Running Platform Threads (pool=" + ptPool + ")...");
        ExecutorService ptExec = Executors.newFixedThreadPool(ptPool);
        List<Future<?>> ptFutures = new ArrayList<>();
        Instant ptStart = Instant.now();

        for (int i = 0; i < BENCHMARK_TASKS; i++) {
            ptFutures.add(ptExec.submit(() -> {
                try { Thread.sleep(BLOCKING_DELAY_MS); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }));
        }
        for (Future<?> f : ptFutures) {
            try { f.get(); } catch (ExecutionException e) { /* ignore */ }
        }
        long ptTime = Duration.between(ptStart, Instant.now()).toMillis();
        ptExec.shutdown();

        // ── Virtual Threads ───────────────────────────────────────────────
        System.out.println("  Running Virtual Threads (unbounded)...");
        ExecutorService vtExec = Executors.newVirtualThreadPerTaskExecutor();
        List<Future<?>> vtFutures = new ArrayList<>();
        Instant vtStart = Instant.now();

        for (int i = 0; i < BENCHMARK_TASKS; i++) {
            vtFutures.add(vtExec.submit(() -> {
                try { Thread.sleep(BLOCKING_DELAY_MS); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }));
        }
        for (Future<?> f : vtFutures) {
            try { f.get(); } catch (ExecutionException e) { /* ignore */ }
        }
        long vtTime = Duration.between(vtStart, Instant.now()).toMillis();
        vtExec.shutdown();

        // ── Results ───────────────────────────────────────────────────────
        double speedup = ptTime > 0 ? (double) ptTime / vtTime : 0;
        long ptExpected = (long) Math.ceil((double) BENCHMARK_TASKS / ptPool) * BLOCKING_DELAY_MS;

        System.out.println("\n  ┌──────────────────────────────────────────────────┐");
        System.out.printf ("  │  Platform Threads (pool=%-3d) : %,8d ms        │%n", ptPool, ptTime);
        System.out.printf ("  │  Virtual Threads  (unbounded) : %,8d ms        │%n", vtTime);
        System.out.printf ("  │  Expected PT time             : ~%,6d ms        │%n", ptExpected);
        System.out.printf ("  │  Speedup Factor               : %.1fx faster    │%n", speedup);
        System.out.printf ("  │  Memory saved (est.)          : ~%,d MB         │%n", (BENCHMARK_TASKS * 1024) / 1024 / 1024);
        System.out.println("  │                                                  │");
        System.out.printf ("  │  %-48s │%n", speedup > 2 ? "✅  Virtual threads WIN by significant margin" : "✅  Virtual threads faster!");
        System.out.println("  └──────────────────────────────────────────────────┘");
    }

    // ═════════════════════════════════════════════════════════════════════════
    //  SECTION 5 — API STYLES REFERENCE
    // ═════════════════════════════════════════════════════════════════════════
    static void showThreadCreationStyles() throws InterruptedException {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  SECTION 5 — ALL WAYS TO CREATE VIRTUAL THREADS (API Reference)");
        System.out.println("═".repeat(65));

        // Style 1: Thread.ofVirtual().start() — simplest
        Thread.ofVirtual().start(() ->
            System.out.println("  Style 1 ofVirtual().start()              → isVirtual=" + Thread.currentThread().isVirtual())
        ).join();

        // Style 2: Named virtual thread, started manually
        Thread vt2 = Thread.ofVirtual().name("api-test-thread").unstarted(() ->
            System.out.println("  Style 2 ofVirtual().name().unstarted()   → name=" + Thread.currentThread().getName())
        );
        vt2.start(); vt2.join();

        // Style 3: Static shorthand
        Thread.startVirtualThread(() ->
            System.out.println("  Style 3 Thread.startVirtualThread()      → daemon=" + Thread.currentThread().isDaemon())
        ).join();

        // Style 4: Executor (recommended for production)
        try (ExecutorService ex = Executors.newVirtualThreadPerTaskExecutor()) {
            ex.submit(() -> System.out.println("  Style 4 newVirtualThreadPerTaskExecutor() → isVirtual=" + Thread.currentThread().isVirtual())).get();
        } catch (ExecutionException e) { /* ignore */ }

        // Style 5: ThreadFactory (inject into frameworks like Spring)
        ThreadFactory factory = Thread.ofVirtual().name("worker-", 1).factory();
        Thread vt5 = factory.newThread(() ->
            System.out.println("  Style 5 ThreadFactory                    → name=" + Thread.currentThread().getName())
        );
        vt5.start(); vt5.join();

        System.out.println("\n  KEY FACTS:");
        System.out.println("    - Virtual threads are ALWAYS daemon threads (isDaemon=true)");
        System.out.println("    - Thread.setPriority() on virtual thread → IGNORED by JVM");
        System.out.println("    - Thread.currentThread().isVirtual() → detection at runtime");
    }

    // ═════════════════════════════════════════════════════════════════════════
    //  SECTION 6 — TRAPS & PITFALLS DEMO
    // ═════════════════════════════════════════════════════════════════════════
    /*
     * TRAP 1 — synchronized PINS virtual thread to carrier (MOST CRITICAL)
     *   synchronized block prevents unmounting → carrier OS thread BLOCKED
     *   Fix: Replace synchronized with ReentrantLock
     *
     * TRAP 2 — ThreadLocal memory leak with millions of virtual threads
     *   Each virtual thread gets its own ThreadLocal copy → memory bloat
     *   Fix: Use ScopedValue (Java 21 incubator) or minimize ThreadLocal use
     *
     * TRAP 3 — CPU-bound tasks do NOT benefit from virtual threads
     *   Virtual threads help ONLY when blocking on I/O / sleep
     *   Fix: For CPU work, use platform threads with parallelism = cores
     *
     * TRAP 4 — Don't wrap virtual threads in a fixed thread pool
     *   Semaphore/rate-limiting is fine, but newFixedThreadPool defeats purpose
     *   Fix: Use Semaphore for rate limiting, not pool size
     *
     * TRAP 5 — JNI / native calls pin the carrier thread
     *   Cannot be fixed in user code — minimize native code in hot paths
     */
    static void demonstrateTraps() throws InterruptedException {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  SECTION 6 — TRAPS & PITFALLS");
        System.out.println("═".repeat(65));

        // ── Trap 1: synchronized pins carrier (BAD) ───────────────────────
        System.out.println("\n  TRAP 1: synchronized vs ReentrantLock");
        Object monitor = new Object();

        Thread pinned = Thread.ofVirtual().start(() -> {
            synchronized (monitor) {       // ⚠️  PINS virtual thread
                try { Thread.sleep(10); }  // carrier BLOCKED here — cannot serve others
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            System.out.println("    ⚠️  synchronized block: carrier was PINNED (bad for thousands of threads)");
        });
        pinned.join();

        // Fix: ReentrantLock allows proper unmounting (GOOD)
        ReentrantLock rl = new ReentrantLock();
        Thread proper = Thread.ofVirtual().start(() -> {
            rl.lock();
            try {
                Thread.sleep(10); // virtual thread UNMOUNTS → carrier is FREE
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                rl.unlock();
            }
            System.out.println("    ✅  ReentrantLock: virtual thread UNMOUNTED correctly (use this)");
        });
        proper.join();

        // ── Trap 2: CPU-bound — virtual threads don't help ────────────────
        System.out.println("\n  TRAP 2: CPU-bound task comparison");
        int cpuIterations = 5_000_000;

        // CPU task in virtual thread
        Instant vtStart = Instant.now();
        List<Thread> vtCpuThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = Thread.ofVirtual().start(() -> {
                long sum = 0;
                for (int j = 0; j < cpuIterations; j++) sum += j;
            });
            vtCpuThreads.add(t);
        }
        for (Thread t : vtCpuThreads) t.join();
        long vtCpuTime = Duration.between(vtStart, Instant.now()).toMillis();

        // CPU task in platform thread
        Instant ptStart = Instant.now();
        List<Thread> ptCpuThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = Thread.ofPlatform().start(() -> {
                long sum = 0;
                for (int j = 0; j < cpuIterations; j++) sum += j;
            });
            ptCpuThreads.add(t);
        }
        for (Thread t : ptCpuThreads) t.join();
        long ptCpuTime = Duration.between(ptStart, Instant.now()).toMillis();

        System.out.printf("    VT CPU-bound: %dms  |  PT CPU-bound: %dms%n", vtCpuTime, ptCpuTime);
        System.out.println("    ❌  For CPU-bound work: virtual threads offer NO advantage");
        System.out.println("    ✅  Use platform threads (ForkJoinPool) for CPU-bound tasks");

        // ── Trap 3: Rate limiting — use Semaphore not pool size ───────────
        System.out.println("\n  TRAP 3: Rate limiting with Semaphore (correct pattern)");
        Semaphore rateLimiter = new Semaphore(5); // max 5 concurrent requests

        List<Thread> rateLimitedThreads = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            final int id = i;
            Thread t = Thread.ofVirtual().start(() -> {
                try {
                    rateLimiter.acquire(); // block if 5 already active
                    Thread.sleep(50);      // simulate API call
                    rateLimiter.release();
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            rateLimitedThreads.add(t);
        }
        for (Thread t : rateLimitedThreads) t.join();
        System.out.println("    ✅  Semaphore limits concurrency without restricting virtual threads");

        // ── Thread state inspection ───────────────────────────────────────
        System.out.println("\n  IDENTITY CHECKS:");
        Thread vt = Thread.ofVirtual().name("demo-vt").start(() -> {
            Thread t = Thread.currentThread();
            System.out.println("    isVirtual()         → " + t.isVirtual());
            System.out.println("    isDaemon()          → " + t.isDaemon());
            System.out.println("    getName()           → " + t.getName());
            System.out.println("    getClass().getName()→ " + t.getClass().getName());
        });
        vt.join();
    }

    // ═════════════════════════════════════════════════════════════════════════
    //  MAIN
    // ═════════════════════════════════════════════════════════════════════════
    public static void main(String[] args) throws InterruptedException {
        printBanner();

        System.out.println("\n  USE CASE: 100 API test cases — each makes a blocking REST call (200ms)");
        System.out.println("  QUESTION: How do we run all 100 concurrently and efficiently?");

        runWithPlatformThreads();   // old approach
        runWithVirtualThreads();    // Java 21 approach
        runBenchmark();             // 5000-task stress test
        showThreadCreationStyles(); // API reference
        demonstrateTraps();         // pitfalls
        printMentalModel();         // summary
    }

    static void printBanner() {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║      Java 21 Virtual Threads — Full POC                      ║");
        System.out.println("║      Use Case: Concurrent API Test Execution Engine           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println("  Java  : " + System.getProperty("java.version"));
        System.out.println("  Cores : " + Runtime.getRuntime().availableProcessors() + " (= max carrier threads)");
        System.out.println("  Heap  : " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + " MB");
    }

    static void printMentalModel() {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  SECTION 7 — MENTAL MODEL & SUMMARY");
        System.out.println("═".repeat(65));
        System.out.println("""
  ┌─────────────────────────────────────────────────────────────┐
  │  TERM            │  MEANING                                 │
  ├─────────────────────────────────────────────────────────────┤
  │  Platform Thread │  1:1 with OS thread │ ~1MB stack         │
  │  Virtual Thread  │  M:N with OS thread │ ~few KB heap       │
  │  Carrier Thread  │  OS thread that runs virtual threads     │
  │  Mount           │  VT assigned to carrier = RUNNING        │
  │  Unmount         │  VT detached from carrier = WAITING      │
  │  Pinning         │  VT stuck to carrier = BAD (synchronized)│
  └─────────────────────────────────────────────────────────────┘

  USE VIRTUAL THREADS WHEN:
    ✅  REST / HTTP calls (blocked on network I/O)
    ✅  Database queries (JDBC, blocked on DB response)
    ✅  File reads/writes (blocked on disk I/O)
    ✅  High-concurrency servers (Spring Boot 3.2+, Tomcat 10.1+)
    ✅  Parallel test execution with HTTP assertions (QA context!)

  DO NOT USE VIRTUAL THREADS WHEN:
    ❌  CPU-bound tasks (image processing, encryption, sorting)
    ❌  Tasks with heavy synchronized blocks (causes pinning)
    ❌  When wrapping in a fixed thread pool defeats the purpose

  ENABLE IN SPRING BOOT 3.2+ (1 line in application.properties):
    spring.threads.virtual.enabled=true

  ENABLE IN TOMCAT (web.xml or programmatic):
    tomcat.executor = VirtualThreadPerTaskExecutor
        """);
    }
}