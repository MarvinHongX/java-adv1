package thread.collection.simple;

import thread.collection.simple.list.BasicList;
import thread.collection.simple.list.SimpleList;

import java.util.ArrayList;
import java.util.List;

public class SimpleListMainV1 {
    public static void main(String[] args) {
        // ArrayList 는 Thread Safe 일까???
        SimpleList list = new BasicList();

        list.add("A");
        list.add("B");
        System.out.println(list);
    }
}
