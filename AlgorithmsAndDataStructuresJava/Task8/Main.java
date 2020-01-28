package Task8;

public class Main {
    public static void main(String[] args) {
        ChainingHashMap<Integer, String> map = new ChainingHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(9, "nine");
        System.out.println(map);
        map.delete(9);
        System.out.println(map);
    }
}
