package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {
    public static void main(String[] args) throws InterruptedException {
        log("start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // thread1,2가 종료될 때까지 무기한 대기
        log("thread1,2 대기"); // WAITING
        thread1.join();
        thread2.join();
        log("thread1,2 대기 완료");

        int result1 = task1.getResult();
        int result2 = task2.getResult();
        int sum = result1 + result2;
        log("sum = " + sum);
        log("end");
    }

    private static class SumTask implements Runnable {
        private int startValue;
        private int endValue;
        private int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        public int getResult() {
            return result;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000); // 2초 걸리는 복잡한 계산이라고 가정
            for (int i = startValue; i <= endValue; i++) {
                result += i;
            }
            log("작업 완료: " + result);
        }
    }
}
