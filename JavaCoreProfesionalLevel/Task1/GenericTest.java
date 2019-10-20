package JavaCoreProfesionalLevel.Task1;

import JavaCoreProfesionalLevel.Task1.fruits.Apple;
import JavaCoreProfesionalLevel.Task1.fruits.Orange;
import java.util.ArrayList;
import java.util.Arrays;

public class GenericTest {
    //Task1
    public Object[] swapElements(Object[] arr, int index1, int index2){
        Object donor = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = donor;
        return arr;
    }
    //Task2
    public <T>ArrayList toArrayList(T[] arr){
        return new ArrayList(Arrays.asList(arr));
    }

    public static void main(String[] args) {
        GenericTest genericTest = new GenericTest();
        System.out.println(Arrays.toString(genericTest.swapElements(new String[]{"wew", "gg", "hhh"}, 0, 2)));
        System.out.println(genericTest.toArrayList(new Integer[]{1, 2, 3}));

        Box <Apple> appleBox = new Box<>();
        appleBox.addFruits(new Apple(), 75);

        Box <Apple> appleBox2 = new Box<>();
        appleBox2.addFruits(new Apple(), 60);

        Box <Orange> orangeBox = new Box<>();
        orangeBox.addFruits(new Orange(), 50);

        System.out.println(appleBox2.getWeight());

    }
}

