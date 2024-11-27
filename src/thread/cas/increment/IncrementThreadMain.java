package thread.cas.increment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.ThreadUtils.sleep;

public class IncrementThreadMain {
    public static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger()); // 단순히 CPU 캐시 메모리를 무시하고 메인 메모리를 직접 사용할 뿐, 연산자체를 원자적으로 묶어주는 기능은 아님.
        test(new SyncInteger()); // synchronized 키워드를 통해 안전한 임계 영역을 만듬.
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sleep(10); // 너무 빨리 실행되기 때문에, 다른 스레드와 동시 실행하기 위해 잠깐 쉰다.
                incrementInteger.increment();
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable, "thread-" + i);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + ", result: " + result);
    }
}
