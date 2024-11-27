package thread.collection.java;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DequeMain {
    public static void main(String[] args) {
        Deque<String> deque = new ConcurrentLinkedDeque<>(); // Deque + 동시성
        deque.add("data1");
        deque.add("data2");
        deque.add("data3");
        System.out.println(deque.getClass());
        System.out.println(deque);
    }
}
