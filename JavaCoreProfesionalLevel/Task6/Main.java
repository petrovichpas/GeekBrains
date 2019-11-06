package Task6;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public  int[] afterFour(int[] arr){
        int[] arrAfterFour = null;
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] == 4){
                arrAfterFour = new int[arr.length -1 - i];
               System.arraycopy(arr, i + 1, arrAfterFour, 0, arrAfterFour.length);
               break;
            }
        }
        if (arrAfterFour == null) throw new RuntimeException("Нет числа 4");
        return arrAfterFour;
    }

     public boolean checkArray(int[] arr) {
    return IntStream.of(arr).anyMatch(x -> x == 1) || IntStream.of(arr).anyMatch(x -> x == 4);
    }
}
