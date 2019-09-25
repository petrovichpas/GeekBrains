package Task3;
import java.util.*;

public class MyArray {

    public static void main(String[] args) {
        removeDuplicate(new String[] {"Qw", "Qwe", "LL", "Qwt", "P", "XXX", "Mkj", "FFF", "XXX", "111", "-333", "111"});
    }

    public static void removeDuplicate(String[] a){
        Map<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            Integer value = hm.get(a[i]);
            hm.put(a[i], value == null ? 1 : value + 1);
        }
        System.out.println(hm);
    }
}

