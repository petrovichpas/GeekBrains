package JavaCoreProfesionalLevel.Task1;

import java.util.*;

public class SomeClass {
    int id;
    String name;

    public SomeClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {
        List<SomeClass> list = new ArrayList<>();
        HashMap<String, List<Integer>> hashMap = new LinkedHashMap<>();

        list.add(new SomeClass(1, "Test1"));
        list.add(new SomeClass(2, "Test1"));
        list.add(new SomeClass(3, "Test1"));
        list.add(new SomeClass(4, "Test2"));
        list.add(new SomeClass(5, "Test2"));
        list.add(new SomeClass(6, "Test3"));
        list.add(new SomeClass(7, "Test3"));
        list.add(new SomeClass(8, "Test4"));
        
        list.forEach(ls ->  hashMap.computeIfAbsent(ls.name, k -> new ArrayList<>()).add(ls.id));
        System.out.println(hashMap);
    }
}
