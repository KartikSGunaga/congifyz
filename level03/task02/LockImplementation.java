package task02;

class Counter2 {
    int count;

    public synchronized void increment() {
        count++;
    }
}

public class LockImplementation {
    public static void main(String[] args) throws InterruptedException {
        Counter2 c = new Counter2();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1001; i++) {
                    c.increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1001; i++) {
                    c.increment();
                }
            }
        });

        thread1.start();
        thread1.join(); // Wait for thread1 to finish before starting thread2

        thread2.start();
        thread2.join(); // Wait for thread2 to finish before proceeding

        System.out.println("Count: " + c.count);
    }
}
