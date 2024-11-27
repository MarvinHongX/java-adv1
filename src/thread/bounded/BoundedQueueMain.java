package thread.bounded;

import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueMain {
    public static void main(String[] args) {
        // BoundedQueue 선택
//        BoundedQueue queue = new BoundedQueueV1(2);
//        BoundedQueue queue = new BoundedQueueV2(2);
//        BoundedQueue queue = new BoundedQueueV3(2);
//        BoundedQueue queue = new BoundedQueueV4(2);
//        BoundedQueue queue = new BoundedQueueV5(2);
        BoundedQueue queue = new BoundedQueueV6_1(2); // BoundedQueueV5 와 같은 코드
//        BoundedQueue queue = new BoundedQueueV6_2(2); // BoundedQueueV1 와 같은 코드
//        BoundedQueue queue = new BoundedQueueV6_3(2);
//        BoundedQueue queue = new BoundedQueueV6_4(2);

        // 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!
        producerFirst(queue); // 생산자 먼저 실행하는 경우
//        consumerFirst(queue); // 소비자 먼저 실행하는 경우

    }

    private static void producerFirst(BoundedQueue queue) {
        log("== [생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
        List<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);
        printAllState(queue, threads);
    }

    private static void startProducer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("생산자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
            threads.add(producer);
            producer.start();
            sleep(100);
        }
    }

    private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("소비자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void consumerFirst(BoundedQueue queue) {
        log("== [소비자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
        List<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);
    }

    private static void printAllState(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("현재 상태 및 대기열 출력: " + queue);
        for (Thread thread : threads) {
            log(thread.getName() + ": " + thread.getState());
        }
    }
}
