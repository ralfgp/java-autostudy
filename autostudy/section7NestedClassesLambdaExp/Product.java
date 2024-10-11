package autostudy.section7NestedClassesLambdaExp;

import java.math.BigDecimal;
import java.util.Comparator;

public class Product implements Comparable<Product>, Comparator<Product>{
    private static int latestId = 0;
    {
        id = ++latestId;
    }
    private int id;
    private String name;
    private BigDecimal price;

    public Product(){ this("no name"); }
    public Product(String name){ this(name,0); }
    public Product(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }
    public Product(String name, double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    @Override
    public String toString() {
        return name+" @ "+super.hashCode();
    }

    public void setName(String name) {
        this.name = name;
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
    @Override
    public int compareTo(Product o) {
        return getName().length()-o.getName().length();
    }
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
