package thread.collection.java;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueMain {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>(); // Queue + 동시성
        queue.add("data1");
        queue.add("data2");
        queue.add("data3");
        System.out.println(queue.getClass());
        System.out.println(queue);
    }
}
