package thread.start.ex;

import static util.MyLogger.log;

public class StartTest4Main {
    public static void main(String[] args) {
        Thread threadA = new Thread(new Printer(1000, "A"), "Thread-A");
        Thread threadB = new Thread(new Printer(500, "B"), "Thread-B");
        threadA.start();
        threadB.start();
    }

    static class Printer implements Runnable{
        private int sleepMs;
        private String word;

        public Printer(int sleepMs, String word) {
            this.sleepMs = sleepMs;
            this.word = word;
        }

        @Override
        public void run() {
            while (true){
                log(word);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
