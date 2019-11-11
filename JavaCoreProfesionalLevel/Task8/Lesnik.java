package Task8;

import java.util.HashMap;

public class Lesnik {
    public static void main(String[] args) {
        int[] wood = new int[1000];
        for (int i = 0; i < wood.length; i++) {
            wood[i] = (int) (Math.random() * 20) + 1;
        }
        countTree(wood);
    }

    public static void countTree(int[] wood){
        HashMap<Integer, Integer> numTrees = new HashMap<>();
        for (int w : wood) {
            if (numTrees.containsKey(w)){
                numTrees.replace(w, numTrees.get(w) + 1);
            }
            else numTrees.put(w, 1);
        }
        numTrees.forEach((k, v)-> System.out.println(k + " встречается " + v));
    }
}
