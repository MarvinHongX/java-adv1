package thread.start;

public class BadThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread thread1 = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        thread1.run(); // start대신 run을 직접 실행한다면?: main 스레드가 사용하는 스택위에 run() 스택 프레임이 올라간다.
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
