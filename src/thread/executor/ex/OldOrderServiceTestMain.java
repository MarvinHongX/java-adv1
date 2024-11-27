package thread.executor.ex;

public class OldOrderServiceTestMain {
    public static void main(String[] args) {
        String orderNo = "Order#1234";
        OldOrderService orderService = new OldOrderService();
        orderService.order(orderNo);
    }
}
