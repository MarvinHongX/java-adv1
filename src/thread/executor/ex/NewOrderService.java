package thread.executor.ex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class NewOrderService {
    private final ExecutorService es = Executors.newFixedThreadPool(10);

    public void order(String orderNo) throws InterruptedException, ExecutionException {
        Callable<Boolean> inventoryWork = new InventoryWork(orderNo); // 재고반영
        Callable<Boolean> shippingWork = new ShippingWork(orderNo); // 배송반영
        Callable<Boolean> accountingWork = new AccountingWork(orderNo); // 회계반영
        List<Callable<Boolean>> works = List.of(inventoryWork, shippingWork, accountingWork);

        try {
            // 작업 요청
            List<Future<Boolean>> futures = es.invokeAll(works);

            boolean result = true;
            for (Future<Boolean> future : futures) {
                result = (result && future.get());
            }

            // 결과 확인
            if (result) {
                log("모든 주문 처리가 성공적으로 완료되었습니다.");
            } else {
                log("일부 작업이 실패했습니다.");
            }
        } finally {
            es.close();
        }
    }

    private static class InventoryWork implements Callable<Boolean> {
        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("재고 반영: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    private static class ShippingWork implements Callable<Boolean> {
        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("배송 반영: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    private static class AccountingWork implements Callable<Boolean> {
        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("회계 반영: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}
