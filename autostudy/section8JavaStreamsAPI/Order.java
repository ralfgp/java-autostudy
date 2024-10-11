package autostudy.section8JavaStreamsAPI;

import java.util.List;
import java.util.stream.Stream;

public class Order {
    private List<Product> items;

    public Order(List<Product> items) {
        this.items = items;
    }
    
    public Stream<Product> items() {
        return items.stream();
    }
}

