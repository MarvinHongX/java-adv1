package thread.collection.simple;

import java.util.ArrayList;
import java.util.List;

public class SimpleListMainV0 {
    public static void main(String[] args) {
        // ArrayList 는 Thread Safe 일까???
        List<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        System.out.println(list);
    }
}
