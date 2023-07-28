package autostudy.ArraysAndLoops;

import java.time.LocalDate;

public class Drink extends Product{
    private LocalDate bestBefore;

    public Drink(){ this("no name"); }
    public Drink(String name) { this(name,0); }
    public Drink(String name, double price) { this(name, price, LocalDate.now().plusDays(3)); }
    public Drink(String name, double price, LocalDate bestBefore) {
        super(name, price);
        this.bestBefore = bestBefore;
    }
    
    public LocalDate getBestBefore() {
        return bestBefore;
    }
}
