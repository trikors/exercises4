package exercise4;

public class Threads {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            while(true){
                try{
                    System.out.println("1");
                    Thread.currentThread().sleep(200);
                } catch (InterruptedException ex){
                    ex.printStackTrace();;
                }
            }
        };
        Runnable task2 = () -> {
            while(true){
                try{
                    System.out.println("2");
                    Thread.currentThread().sleep(200);
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        try{
            Thread.currentThread().sleep(100);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
        thread2.start();
    }
}
