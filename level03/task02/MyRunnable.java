package task02;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            System.out.println("Thread 2: " + (i+1));
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Thread 2 is complete.");
    }
}
