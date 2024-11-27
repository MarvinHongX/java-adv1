package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task);
        thread.start();

        sleep(100);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    private static class MyTask implements Runnable {

        @Override
        public void run() {
            // isInterrupted()는 인터럽트 상태를 확인 후에 while문을 빠져나온 뒤에는 인터럽트 상태를 다시 정상(false)로 돌려주지 않는다.
            // while 문을 탈출하기 위해 인터럽트를 사용했음에도 계속 인터럽트 상태(true)라면 의도치 않은 곳에서 인터럽트가 또 발생할 수 있다.
            while (!Thread.currentThread().isInterrupted()) {
                log("running...");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted()); // true

            try {
                log("자원 정리");
                Thread.sleep(1000);
                log("자원 종료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 인터럽트가 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted()); // false
            }
        }
    }
}