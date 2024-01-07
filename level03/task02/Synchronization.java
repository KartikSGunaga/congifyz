package task02;

class Counter{
    int count;
     public synchronized void increment(){
         count ++;
     }
}
public class Synchronization {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1001; i++){
                    //System.out.println("Inside loop Thread1: " + (i+1));
                    c.increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1001; i++){
                    //System.out.println("Inside loop thread2: " + (i+1));
                    c.increment();
                }
            }
        });
        thread1.start();
        thread1.join();

        thread2.start();
        thread1.join();

        System.out.println("Count: "+ c.count);
    }
}
