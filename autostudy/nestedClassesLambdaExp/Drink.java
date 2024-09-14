package autostudy.nestedClassesLambdaExp;

import java.math.BigDecimal;
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
    public Drink(String name, BigDecimal price) {
        super(name, price);
        this.bestBefore = LocalDate.now().plusDays(3);
    }
    
    public LocalDate getBestBefore() {
        return bestBefore;
    }
}
