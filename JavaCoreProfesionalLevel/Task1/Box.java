package JavaCoreProfesionalLevel.Task1;

import JavaCoreProfesionalLevel.Task1.fruits.Apple;
import JavaCoreProfesionalLevel.Task1.fruits.Fruit;
import JavaCoreProfesionalLevel.Task1.fruits.Orange;

import java.util.ArrayList;

public class Box <T extends Fruit> {
    float weight;
    private ArrayList<Fruit> fruitBox;

    public Box() {
        this.weight = 0f;
        this.fruitBox =  new ArrayList<>();
    }

    public float getWeight(){
        weight = fruitBox.size() == 0 ? 0 : fruitBox.size() * fruitBox.get(0).getWeight();
        return weight;
    }

    public boolean compare(Box<?> box){
        return getWeight() == box.getWeight();
    }

    public void intersperseFruits(Box<T> box){
        if (!fruitBox.equals(box)) {
            box.fruitBox.addAll(fruitBox);
            fruitBox.clear();
        }
    }
    // как создать новый фрукт исходя из типа первого аргумента метода? например new T()
    // можно конечно класть в коробку один и тот же объект, но если вес у яблок будет разный, то не сработает
    public void addFruits(T fruit, int quantity){
        for (int i = 0; i < quantity; i++) {
            if (fruit.getClass().getSimpleName().equals("Apple"))
                fruitBox.add(new Apple());
            else fruitBox.add(new Orange());
        }
    }
}
