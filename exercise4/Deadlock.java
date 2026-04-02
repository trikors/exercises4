
package exercise4;

public class Deadlock {
    private static final Object object1 = new Object();
    private static final Object object2 = new Object();

    public static void main(String[] args) {
        Runnable task1 = () -> {
            synchronized (object1) {
                System.out.println("Thread 1 on object1");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("Thread 1 on object2");
                }
            }
        };

        Runnable task2 = () -> {
            synchronized (object2) {
                System.out.println("Thread 2 on object2");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("Thread 2 on object 1");
                }
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
