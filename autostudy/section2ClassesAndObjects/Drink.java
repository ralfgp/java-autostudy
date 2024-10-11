package autostudy.section2ClassesAndObjects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Drink extends Product{
    private LocalDate bestBefore;

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    @Override
    public BigDecimal getPrice(){
        LocalTime now = LocalTime.now();
        BigDecimal discount = (now.isAfter(LocalTime.of(17,30)) && now.isBefore(LocalTime.of(18,30)))
                                    ? super.getPrice().multiply(BigDecimal.valueOf(0.2))
                                    : BigDecimal.ZERO;
        return super.getPrice().subtract(discount);
    }
}
