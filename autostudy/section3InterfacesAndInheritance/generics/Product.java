package autostudy.section3InterfacesAndInheritance.generics;

import java.math.BigDecimal;

public class Product {
    private static int latestId = 0;
    {
        id = ++latestId;
    }
    private int id;
    private String name;
    private BigDecimal price;

    public Product(){ this("no name"); }
    public Product(String name){ this(name,0); }
    public Product(String name, double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    @Override
    public String toString() {
        return id+" "+name+" "+price;
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
