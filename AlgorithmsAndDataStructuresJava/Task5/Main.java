package AlgorithmsAndDataStructuresJava.Task5;

public class Main {
    public static void main(String[] args) {
        System.out.println("Максимальная стоимость всех предметов " + findBestCost(items.length - 1, 7) + " долларов.");
        System.out.println(degree(3, 3));
    }

    static Item[] items = new Item[]{
            new Item("Book", 100, 1),
            new Item("First aid kit", 700, 3),
            new Item("Laptop", 4700, 3),
            new Item("Canister", 550, 4),
            new Item("Binoculars", 2200, 2),
            new Item("Clothing", 300, 1),
            new Item("Dumbbell", 200, 5)
    };

    // 2 задача
    static int findBestCost(int numItems, int maxBackpackWeight) {
        if (numItems < 0) return 0;
        if (items[numItems].weight > maxBackpackWeight) return findBestCost(numItems - 1, maxBackpackWeight);
        else return Math.max(findBestCost(numItems - 1, maxBackpackWeight),
                findBestCost(numItems - 1, maxBackpackWeight - items[numItems].weight) + items[numItems].price);
    }
    // 1 задача
    static long degree(int num, int deg){
        if (deg < 1) return 1;
        return degree(num, deg -1) * num;
    }
}
