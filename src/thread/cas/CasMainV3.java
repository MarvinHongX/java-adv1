package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CasMainV3 {
    private static int THREAD_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(atomicInteger.getClass().getSimpleName() + ", value = " + atomicInteger.get());
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            sleep(100); // 스레드 동시 실행 상황을 연출하기 위한 대기
            log("getValue = " + getValue);

            // 락을 걸지 않고 CAS 연산을 사용해서 값을 증가함.
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            
            log("result = " + result);
        } while(!result);
        // 다른 스레드가 값을 변경했을 수 있으므로 atomicInteger.get()을 사용하지 않음.
        return getValue + 1;
    }
}