package autostudy.section5ArraysAndLoops;

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

    public LocalDate getBestBefore() {
        return bestBefore;
    }
}
