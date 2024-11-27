package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // CAS 연산: 메모리에 있는 값이 기대하는 값이라면 원하는 값으로 변경한다. (CPU 하드웨어에서 지원)
        boolean result1 = atomicInteger.compareAndSet(0, 1);
        System.out.println("result1 = " + result1 + ", value = " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 200);
        System.out.println("result2 = " + result2 + ", value = " + atomicInteger.get());
    }
}