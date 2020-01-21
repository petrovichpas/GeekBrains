package Task66;

public class Main {
    public static void main(String[] args) {
        MyTreeMap[] trees = new MyTreeMap[20];
        int goodTrees = 0;

        for (int i = 0; i < trees.length; i++) {
            trees[i] = new MyTreeMap();
            while (trees[i].level() < 7){
                int random = (int) (Math.random()*(200+1)) - 100;
                trees[i].put(random, String.valueOf(random));
            }
            goodTrees = trees[i].isBalanced() ? goodTrees + 1 : goodTrees;
        }
        System.out.println((float) goodTrees / trees.length * 100 + " %");
    }
}
