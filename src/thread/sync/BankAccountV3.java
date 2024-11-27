package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount {
    private int balance;

    public BankAccountV3(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("어서 오세요. 거래를 선택하세요. " + getClass().getSimpleName());
        log(amount + "원을 출금 하시겠습니까?");

        synchronized (this) { // 락을 획득하지 못하면 BLOCKED 상태가 된다.
            // 잔고가 출금액보다 적으면
            if (balance < amount) {
                log("잔액이 부족합니다. (" + balance + "원)");
                return false;
            }

            // 출금 진행
            log("처리 중입니다. 잠시만 기다리세요...");
            sleep(1000);
            balance -= amount;
            log("요청하신 거래가 완료되었습니다. 거래금액: " + amount + "원, 거래 후 잔액: " + balance + "원");
        }

        log("정상 처리되었습니다. 안녕히 가세요.");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
