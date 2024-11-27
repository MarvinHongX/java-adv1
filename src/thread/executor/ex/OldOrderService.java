package thread.executor.ex;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class OldOrderService {
    public void order(String orderNo) {
        InventoryWork inventoryWork = new InventoryWork(orderNo); // 재고반영
        ShippingWork shippingWork = new ShippingWork(orderNo); // 배송반영
        AccountingWork accountingWork = new AccountingWork(orderNo); // 회계반영

        // 작업 요청
        Boolean inventoryResult = inventoryWork.call();
        Boolean shippingResult = shippingWork.call();
        Boolean accountingResult = accountingWork.call();

        // 결과 확인
        if (inventoryResult && shippingResult && accountingResult) {
            log("모든 주문 처리가 성공적으로 완료되었습니다.");
        } else {
            log("일부 작업이 실패했습니다.");
        }
    }

    private static class InventoryWork {
        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {
            log("재고 반영: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    private static class ShippingWork {
        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {
            log("배송 반영: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    private static class AccountingWork {
        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {
            log("회계 반영: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}
