package com.core.collection;

import java.util.*;

public class App01 {
    public static void main(String[] args) {

        /**
         * ArrayList is backed by a dynamic array. It provides O(1) random access and O(1) amortized append.
         * Inserting or removing in the middle is O(n) because elements must shift. Default initial capacity is 10; it
         * grows by 50% when full.
         * **/

        List lst = new ArrayList();
        lst.add(1);
        lst.add(2);
        lst.add(3);
        lst.add(4);
        lst.add(null);
        System.out.println(lst);
        lst.remove(2); // from index it will remove the content
        System.out.println(lst);

        /**LinkedList is a doubly-linked list. Each node stores data, a previous pointer, and a next pointer. It
         implements both List and**/

        LinkedList<Integer> ll = new LinkedList<>();
        ll.addFirst(10); // [10]
        ll.addLast(20); // [10, 20]
        ll.addFirst(5); // [5, 10, 20]
        System.out.println(ll.peekFirst()); // 5 (no remove)
        System.out.println(ll.pollFirst()); // 5 (removes)
        System.out.println(ll.pollLast()); // 20 (removes)
        System.out.println(ll); // [10]

        //Part 3 — Queue, Deque & PriorityQueue

        /**
         * Queue = FIFO. Deque = double-ended (supports both stack and queue). PriorityQueue = heap-based,
         always polls the smallest element (min-heap by default). Never use the legacy Stack class — use
         ArrayDeque instead.
         **/
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{3, 101}); // priority 3
        pq.offer(new int[]{1, 202}); // priority 1
        pq.offer(new int[]{2, 303}); // priority 2
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            System.out.println("Task " + t[1] + " P=" + t[0]);
        }

        //ArrayDeque as Stack (Undo System)
        Deque<String> undo = new ArrayDeque<>();
        undo.push("typed hello");
        undo.push("bold text");
        undo.push("inserted image");
        System.out.println(undo.pop()); // LIFO → inserted image
        System.out.println(undo.pop()); // bold text

        Queue<String> q = new ArrayDeque<>();
        q.offer("doc1.pdf");
        q.offer("report.pdf");
        q.offer("invoice.pdf");
        while (!q.isEmpty())
            System.out.println("Printing: " + q.poll()); // FIFO

/**A Set stores unique elements. Adding a duplicate simply returns false and the collection is unchanged.
 The three main implementations differ only in ordering and null support.**/

        List<String> raw = Arrays.asList(
                "apple", "banana", "apple", "cherry", "banana");
        Set<String> unique = new HashSet<>(raw);
        System.out.println(unique);
        System.out.println("Size: " + unique.size());
        System.out.println(unique.contains("apple"));

        //  TreeSet — Sorted Scores (with navigation)
        TreeSet<Integer> s = new TreeSet<>();
        s.add(85);
        s.add(92);
        s.add(78);
        s.add(92); // dup ignored
        System.out.println(s); // [78, 85, 92]
        System.out.println(s.first()); // 78
        System.out.println(s.last()); // 92
        System.out.println(s.headSet(90)); // [78, 85] (below 90)
        System.out.println(s.tailSet(85)); // [85, 92] (from 85 incl)
        System.out.println(s.subSet(80, 92)); // [85]
        System.out.println(s.floor(88)); // 85 (greatest <= 88)


        //  Set Operations — Union, Intersection, Difference
        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> b = new HashSet<>(Arrays.asList(3, 4, 5, 6));
        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        Set<Integer> inter = new HashSet<>(a);
        inter.retainAll(b);
        Set<Integer> diff = new HashSet<>(a);
        diff.removeAll(b);
        System.out.println("Union: " + union); // [1,2,3,4,5,6]
        System.out.println("Intersection: " + inter); // [3,4]
        System.out.println("Difference: " + diff); // [1,2]


        //HashMap

        String[] words = {"the", "cat", "sat", "on", "the", "mat", "the"};
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        System.out.print("freq is :" + freq);

        //Iterating hashmap
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        //  TreeMap — Sorted Phonebook with Range Query
        TreeMap<String, String> pb = new TreeMap<>();
        pb.put("Zainab", "9876");
        pb.put("Arjun", "1234");
        pb.put("Maya", "5555");

        System.out.println(pb); // {Arjun=1234, Maya=5555, Zainab=9876}  --A-M-Z
        System.out.println(pb.firstKey()); // Arjun
        System.out.println(pb.tailMap("M")); // {Maya=5555, Zainab=9876}
        System.out.println(pb.floorKey("N")); // Maya (greatest key <= N)

    }

}
