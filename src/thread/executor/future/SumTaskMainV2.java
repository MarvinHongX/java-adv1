package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;

public class SumTaskMainV2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = es.submit(task1);
        Future<Integer> future2 = es.submit(task2);

        int sum1 = future1.get(); // 여기는 블로킹
        int sum2 = future2.get(); // 여기는 블로킹
        int result = sum1 + sum2;
        System.out.println(result);

        es.close();
    }

    private static class SumTask implements Callable<Integer> {
        private int startNum;
        private int endNum;

        public SumTask(int startNum, int endNum) {
            this.startNum = startNum;
            this.endNum = endNum;
        }

        @Override
        public Integer call() throws Exception {
            log("작업시작");
            Thread.sleep(2000); // Callable 인터페이스는 "throws Exception" 이 있어서 모든 체크예외를 던질 수 있다.
            int sum = 0;
            for (int i = startNum; i <= endNum; i++) {
                sum += i;
            }
            log("작업종료");
            return sum;
        }
    }
}
