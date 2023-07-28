package autostudy.abstractClasses;

import java.math.BigDecimal;

public abstract class Product {
    private static int latestId = 0;
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

    @Override
    public String toString() {
        return id+" "+name+" "+price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public abstract void serve();

    public final void processPayment(){
        // Method can not be overwritten by subclasses
    }
}
