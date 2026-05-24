package practice.corejava.racecondition;

class Counter {

    static int count = 0;

    /**
     * 1. read
     * 2. increment
     * 3. write
     **/
    static synchronized void increment() {
        count++;
    }

    static synchronized int getCount() {
        return count;
    }
}