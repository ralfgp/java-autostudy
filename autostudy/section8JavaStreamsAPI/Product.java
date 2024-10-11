package autostudy.section8JavaStreamsAPI;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

public class Product implements Comparable<Product>, Comparator<Product>{
    private static int latestId = 0;
    {
        id = ++latestId;
    }
    private int id;
    private String name;
    private BigDecimal price;
    private Double discount;
    private LocalDate bestBefore;

    public Product(){ this("no name"); }
    public Product(String name){ this(name, BigDecimal.valueOf(0)); }
    public Product(String name, Double price) { this(name, BigDecimal.valueOf(price)); }
    public Product(String name, BigDecimal price){ this(name, price, Double.valueOf(0)); }
    public Product(String name, BigDecimal price, Double discount){ this(name, price, discount, LocalDate.now()); }
    public Product(String name, BigDecimal price, Double discount, LocalDate bestBefore){
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.bestBefore = bestBefore;
    }

    @Override
    public String toString() {
        //return name+" @ "+super.hashCode();
        return name+"\t"+price;
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
    public Double getDiscount() {
        return discount;
    }
    public LocalDate getBestBefore(){
        return bestBefore;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal applyDiscount(double d) {
        return price.multiply(BigDecimal.valueOf(1-d));
    }

    @Override
    public int compareTo(Product o) {
        return getName().length()-o.getName().length();
    }
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }
    public void printProduct(){
        System.out.println("Name: "+this.getName()+"\tPrice: "+this.getPrice().toString());
    }
    public void setBestBefore(LocalDate now) {
        this.bestBefore = now;
    }
}
