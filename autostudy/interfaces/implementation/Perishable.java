package autostudy.interfaces.implementation;

import java.time.Period;

public interface Perishable {
    // Interfaces may contain constants but not variables
    public static final Period MAX_PERIOD = Period.ofDays(5);

    //Instance methods are by default public and abstract
    void perish();
    boolean isPerished();

    //They can contain concrete methods only if they are either default, private or static
    public default boolean verifyPeriod(Period p){
        return comparePeriod(p) < 0;
    }
    private int comparePeriod(Period p){
        return p.getDays() - MAX_PERIOD.getDays();
    }
    public static int getMaxPeriodDays(){
        return MAX_PERIOD.getDays();
    }
}
