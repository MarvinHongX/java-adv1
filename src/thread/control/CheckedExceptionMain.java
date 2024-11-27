package thread.control;

import static util.ThreadUtils.*;

public class CheckedExceptionMain {
    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {

        // Runnbale의 run() 메서드를 재정의 하는 경우, 체크예외는 던질 수 없고 반드시 try catch로 잡아야 한다.
        @Override
        public void run() /* throws Exceptions */ {
//            throw new Exception();
            sleep(1000);
        }
    }
}
