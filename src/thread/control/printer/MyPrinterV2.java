package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;

public class MyPrinterV2 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("프린트할 문서를 입력하세요. (종료는 q): ");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                printerThread.interrupt();
                break;
            }
            printer.add(input);
        }
    }
    private static class Printer implements Runnable {
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        public void add(String job) {
            jobQueue.offer(job);
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                if (jobQueue.isEmpty()) {
                    continue;
                }
                try {
                    String job = jobQueue.poll();
                    log("출력 시작: " + job + ", 대기문서: " + jobQueue);
                    Thread.sleep(3000);
                    log("출력 완료: " +  job);
                } catch (InterruptedException e) {
                    log("인터럽트!");
                    break;
                }
            }
            log("프린터 종료");
        }
    }
}
