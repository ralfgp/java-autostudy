package autostudy.section7NestedClassesLambdaExp;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends Product{
    private LocalDate bestBefore;

    public Food(){ this("no name"); }
    public Food(String name) { this(name,0); }
    public Food(String name, double price) { this(name, price, LocalDate.now().plusDays(3)); }
    public Food(String name, double price, LocalDate bestBefore) {
        super(name, price);
        this.bestBefore = bestBefore;
    }
    public Food(String name, BigDecimal price) {
        super(name, price);
        this.bestBefore = LocalDate.now().plusDays(3);
    }
    public LocalDate getBestBefore() {
        return bestBefore;
    }
}
