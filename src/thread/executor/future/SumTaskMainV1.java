package thread.executor.future;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SumTaskMainV1 {
    public static void main(String[] args) throws InterruptedException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-1");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        int result = task1.getResult() + task2.getResult();
        System.out.println(result);
    }

    private static class SumTask implements Runnable {
        private int startNum;
        private int endNum;
        private int result;

        public SumTask(int startNum, int endNum) {
            this.startNum = startNum;
            this.endNum = endNum;
        }

        @Override
        public void run() {
            log("작업시작");

            try {
                Thread.sleep(2000); // Runnable 인터페이스는 throws 가 없어서 무조건 try catch 로 잡아야 한다.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int sum = 0;
            for (int i = startNum; i <= endNum; i++) {
                sum += i;
            }
            result = sum;
            log("작업종료");
        }

        public int getResult() {
            return result;
        }
    }
}
