package thread.start;

public class DaemonThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        DaemonThread thread1 = new DaemonThread();
        thread1.setDaemon(true); // Daemon Thread 여부
        thread1.start();
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
