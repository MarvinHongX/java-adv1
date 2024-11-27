package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount {
    private int balance;

    public BankAccountV1(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        // 잔고가 출금액보다 적으면
        log("[잔고 검증 시작]");
        if (balance < amount) {
            log("[잔고 검증 실패]: " + amount + ", 잔액: " + balance);
            return false;
        }
        log("[잔고 검증 완료]: " + amount + ", 잔액: " + balance);

        // 출금 진행
        sleep(1000);
        balance -= amount;

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
