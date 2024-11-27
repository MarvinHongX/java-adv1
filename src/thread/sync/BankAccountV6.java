package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount {
    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("어서 오세요. 거래를 선택하세요. " + getClass().getSimpleName());
        log(amount + "원을 출금 하시겠습니까?");

        // ======= lock start =======
        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) { // 특정 시간동안 대기해도 락을 획득하지 못하면 return
                log("이미 처리 중인 거래가 있습니다.");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            // 잔고가 출금액보다 적으면
            if (balance < amount) {
                log("잔액이 부족합니다. (" + balance + "원)");
                return false;
            }

            // 출금 진행
            log("처리 중입니다. 잠시만 기다리세요...");
            sleep(1000); // TIMED_WAITING
            balance -= amount;
            log("요청하신 거래가 완료되었습니다. 거래금액: " + amount + "원, 거래 후 잔액: " + balance + "원");
        } finally {
            lock.unlock();
        }
        // ======= lock end =======


        log("정상 처리되었습니다. 안녕히 가세요.");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
