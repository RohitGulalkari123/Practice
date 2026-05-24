package practice.corejava.racecondition;

public class RaceConditionSynchronization {
    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                Counter.increment();
                System.out.println("ThreadName :" + Thread.currentThread().getName()
                        + " and counter is :" + Counter.getCount());
            }
        });

        Thread th2 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                Counter.increment();
                System.out.println("ThreadName :" + Thread.currentThread().getName()
                        + " and counter is :" + Counter.getCount());
            }
        });

        /**Random incorrect results.**/
        th.start();
        th.join();

        th2.start();
        th2.join();
        System.out.println("Counter is :" + Counter.getCount());
    }
}
