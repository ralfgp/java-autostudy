package autostudy.section4AbstractClasses;

import java.time.LocalDate;

public final class Food extends Product{
    // Class can not be extended due to it being final
    private LocalDate bestBefore;
    {
        bestBefore = LocalDate.now().plusDays(3);
    }

    public Food(){ super(); }

    public Food(String name, double price){ super(name,price); }

    public Food(String name, double price, LocalDate bestBefore){
        this(name, price);
        this.bestBefore = bestBefore;
    }

    @Override
    public String toString() {
        return super.toString()+" "+bestBefore;
    }

    @Override
    public void serve() {
        System.out.println("Serving a " + getName() + " on a plate");
    }

    // This method cannot be overriden due to the parent method being final
    // public void processPayment(){}
}