package autostudy.section6Collections;

import java.util.function.Predicate;

public class LongProductNames implements Predicate<Product>{

    @Override
    public boolean test(Product t) {
        return t.getName().length() > 3;
    }

}
