package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        int result1 = incrementAndGet(atomicInteger);
        System.out.println("result1 = " + result1);

        int result2 = incrementAndGet(atomicInteger);
        System.out.println("result2 = " + result2);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            log("getValue = " + getValue);

            // 락을 걸지 않고 CAS 연산을 사용해서 값을 증가함.
            result = atomicInteger.compareAndSet(getValue, getValue + 1);

            log("result = " + result);
        } while(!result);
        return getValue + 1;
    }
}