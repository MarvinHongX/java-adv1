package thread.collection.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>(); // HashSet + 동시성, 입력순서 보장하지 않음.
        set.add("data1");
        set.add("data2");
        set.add("data3");
        System.out.println(set.getClass());
        System.out.println(set);

        Set<String> skipSet = new ConcurrentSkipListSet<>(); // TreeSet + 동시성, comparator에 의해 정렬 가능.
        skipSet.add("data1");
        skipSet.add("data2");
        skipSet.add("data3");
        System.out.println(skipSet.getClass());
        System.out.println(skipSet);
    }
}
