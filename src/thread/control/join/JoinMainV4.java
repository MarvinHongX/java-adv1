package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV4 {
    public static void main(String[] args) throws InterruptedException {
        log("start");
        SumTask task1 = new SumTask(1, 50);

        Thread thread1 = new Thread(task1, "thread-1");

        thread1.start();

        // thread1가 종료될 때까지 xx초간 대기
        log("thread1 1초간 대기");
        thread1.join(1000); // TIMED_WAITING
        log("thread1 1초간 대기 완료");

        log("result1 = " + task1.getResult());
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
