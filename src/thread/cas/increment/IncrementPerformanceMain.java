package thread.cas.increment;

import java.util.ArrayList;
import java.util.List;

import static util.ThreadUtils.sleep;

public class IncrementPerformanceMain {
    public static final long COUNT = 100_000_000;

    public static void main(String[] args) {
        test(new BasicInteger()); // 가장 빠름, CPU 캐시를 적극 활용, 멀티스레드 상황에서는 사용할 수 없음.
        test(new VolatileInteger()); // CPU 캐시를 사용하지 않고 메인 메모리 사용, 멀티스레드 상황에서도 안전하지 않음.
        test(new SyncInteger()); // synchronized 사용, 멀티스레드 상황에서 안전함.
        test(new MyAtomicInteger()); // synchronized, Lock(ReentrantLock) 보다 빠른 AtomicInteger 사용, 멀티스레드 상황에서 안전함.
    }

    private static void test(IncrementInteger incrementInteger) {
        long startMillis = System.currentTimeMillis();
        for (long i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }
        long endMillis = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + ": ms=" + (endMillis - startMillis));
    }
}
