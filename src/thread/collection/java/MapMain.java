package thread.collection.java;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class MapMain {
    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap(); // HashMap + 동시성, 입력순서 보장하지 않음.
        map.put("data1", 1);
        map.put("data2", 2);
        map.put("data3", 3);
        System.out.println(map.getClass());
        System.out.println(map);

        Map<String, Integer> skipMap = new ConcurrentHashMap(); // TreeMap + 동시성, comparator에 의해 정렬 가능.
        skipMap.put("data1", 1);
        skipMap.put("data2", 2);
        skipMap.put("data3", 3);
        System.out.println(skipMap.getClass());
        System.out.println(skipMap);
    }
}
