package thread.control.printer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV1 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("프린트할 문서를 입력하세요. (종료는 q): ");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                printer.running = false;
                break;
            }
            printer.add(input);
        }
    }
    private static class Printer implements Runnable {
        volatile boolean running = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        public void add(String job) {
            jobQueue.offer(job);
        }
        @Override
        public void run() {
            while (running) {
                if (jobQueue.isEmpty()) {
                    continue;
                }
                String job = jobQueue.poll();
                log("출력 시작: " + job + ", 대기문서: " + jobQueue);
                sleep(3000);
                log("출력 완료: " +  job);
            }
            log("프린터 종료");
        }
    }
}
