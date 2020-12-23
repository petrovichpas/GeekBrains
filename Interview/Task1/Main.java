package Task2;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> mll = new MyLinkedList<>();
        mll.insertFirst("1");
        mll.insertFirst("2");
        mll.insertLast("3");
        System.out.println(mll);
        mll.remove("1");
        System.out.println(mll);

        MyArrayList<String> ab = new MyArrayList<>();
        ab.add("A");
        ab.add("B");
        System.out.println(ab.get(0) + ab.get(1));
        ab.remove("A");
        System.out.println(ab.get(0));
    }
}
