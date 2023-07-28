package autostudy.abstractClasses;

public class Drink extends Product{

    public Drink(){ super(); }

    public Drink(String name, double price){ super(name,price); }

    @Override
    public void serve() {
        System.out.println("Serving a " + getName() + " on a cup");
    }
}
