package autostudy.section2ClassesAndObjects;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends Product{
    private LocalDate bestBefore;
    private BigDecimal publicDiscount;

    public Food(String name, LocalDate bestBefore){
        super(name);
        this.bestBefore = bestBefore;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public BigDecimal getDiscount() {
        return publicPrice.subtract(this.publicDiscount.add(super.publicDiscount));
    }

    @Override
    public BigDecimal getPrice(){
        BigDecimal discount = (bestBefore.isEqual(LocalDate.now().plusDays(1)))
                                        ? super.getPrice().multiply(BigDecimal.valueOf(0.1))
                                        : BigDecimal.ZERO;
        return super.getPrice().subtract(discount);
    }
}
