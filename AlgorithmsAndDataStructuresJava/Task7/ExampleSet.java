package Task7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExampleSet {
    public static void main(String[] args) {
        Set<Integer> setA = new HashSet<>();
        setA.addAll(Arrays.asList(0, 2, 4, 6, 8, 10, 12, 14));
        System.out.println(setA);

        Set<Integer> setB = new HashSet<>();
        setB.addAll(Arrays.asList(4, 8, 12, 16, 20));
        System.out.println(setB);

//        System.out.println(setA.containsAll(setB));

//        setA.removeAll(setB); //удаление общих элементов.
//        System.out.println(setA);

//        setA.retainAll(setB); //  пересечение
//        System.out.println(setA);

        setA.addAll(setB);  //объединение
        System.out.println(setA);
    }
}
