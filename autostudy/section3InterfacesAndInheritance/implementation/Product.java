package autostudy.section3InterfacesAndInheritance.implementation;

import java.math.BigDecimal;

public class Product {
    private static int latestId = 0;
    // initializer, in this case an instance initializer, if it has the word static before the curly braces its an static class initializer
    {
        id = ++latestId;
    }
    private int id;
    private String name;
    private BigDecimal price;

    public Product(){ this("no name",0); }

    public Product(String name, double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
