package task02;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MultiThreading thread1 = new MultiThreading();

        MyRunnable runnable1 = new MyRunnable();
        Thread thread2 = new Thread(runnable1);

//        thread1.start();
//        thread2.start();

        //if we want to start a thread after a thread finishes:
        thread1.start();
        thread1.join(3000);
        thread2.start();
    }
}
