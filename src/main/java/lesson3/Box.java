package lesson3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private final ArrayList<T> fruitList = new ArrayList<>();

    public void addFruit(T fruit){
        fruitList.add(fruit);
    }

    public double getWeight(){
        int count = fruitList.size();
        if (count==0)
            return 0;
        return fruitList.get(0).getWeight()*count;
    }

    public boolean compare(Box box){
        return this.getWeight()==box.getWeight();
    }

    public void pourOverTo(Box<T> destBox){
        for (T fruit : fruitList){
            destBox.addFruit(fruit);
        }
        fruitList.clear();
    }
}