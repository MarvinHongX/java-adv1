package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        log("main start");
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main end");
    }

    private static class MyTask implements Runnable {
        volatile boolean runFlag = true; // 메인 메모리 직접 사용
//        boolean runFlag = true; // cpu 캐쉬 메모리 사용

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                // runFlag가 false 이면 탈출
            }
            log("task 종료");
        }
    }
}
