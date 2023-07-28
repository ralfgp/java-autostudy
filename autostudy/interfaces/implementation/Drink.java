package autostudy.interfaces.implementation;

public class Drink extends Product
                   // By using implements, in the case of a conflict due to inheriting, the function thats in conflict becomes abstract and asks to be overriden
                   implements Consumable, Liquid{
    @Override
    public void consume(int quantity) {}

    @Override
    public int measure() { return 0; }
    
}
