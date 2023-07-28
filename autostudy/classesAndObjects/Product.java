package autostudy.classesAndObjects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Product {
    // All obj use this variable, pointing to this memory space, theres only this one
    //private static Period defaultExpiryPeriod = Period.ofDays(3);

    // private final String name = "Tea";
    // private final BigDecimal price = BigDecimal.ZERO;
    // private final int id;
    // private final String name;
    // private final BigDecimal price;

    public static int maxId = 0;
    public static final int MAX_EXPIRY_PERIOD = 5;
    private static Period defaultExpiryPeriod;
    private int id;
    private String name;
    private String caution;
    private BigDecimal price;
    // these two are public just to show the use of super and this on another class, in this case Food
    public BigDecimal publicPrice;
    public BigDecimal publicDiscount;
    private BigDecimal discount = BigDecimal.ZERO;
    private BigDecimal tax;
    private LocalDate bestBefore = LocalDate.now().plusDays(3);
    private Condition condition;
    static { defaultExpiryPeriod = Period.ofDays(3); }
    { id = ++maxId; }

    // To be able to use an empty constructor with a declared constructor, you have to add it explicitly
    public Product(){ this("no name",0); }

    public Product(String name){ this(name,0); }

    public Product(String name, Condition condition){
        this(name,0);
        this.condition = condition;
    }

    public Product(String name, double price){
        this.name =  name;
        this.price = BigDecimal.valueOf(price);
        publicPrice = this.price;
    }

    public Product(int id, String name, double price){
        this(name, price);
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("\nNew verification");
        if(this == obj){
            System.out.println("They are from the same memory space, ergo the same object");
            return true;
        }
        if(!(obj instanceof Product)){
            System.out.println("Is not an instance of Product");
            return false;
        }
        Product other = (Product)obj;
        System.out.println("Checking if id equals the obj id with a casting to Product");
        return  this.id == other.id;
    }
    /*
     * Assume equals compares product ids
     *//*
    @Override
    public int hashCode() {
        return this.id;
    }
    
    /*
     * Assume equals method compares product names and prices
     *//*
    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
    /* */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        if(name == null){
            return "Unknown";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price  = BigDecimal.valueOf(price);
        publicPrice = this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price  = price;
        publicPrice = this.price;
    }

    public void setPrice(BigDecimal price, BigDecimal discount) {
        this.price  = price;
        publicPrice = this.price;
        this.discount = discount;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    public String consume(){
        return "Good!";
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getDiscount(){
        return price.multiply(discount);
    }

    public BigDecimal getDiscount(final BigDecimal discount){
        return price.multiply(discount);
    }

    public static void setExpiryPeriod(int days){
        defaultExpiryPeriod = Period.ofDays(days);
        System.out.println(defaultExpiryPeriod.getDays());
        // Cannot use keyword 'this' in static context
        // String name = this.name;
    }

    public static Period getExpiryPeriod() {
        return defaultExpiryPeriod;
    }

    public void setFiscalDetails(double... values){
        switch(values.length){
            case 3:
                tax = BigDecimal.valueOf(values[2]);
            case 2:
                discount = BigDecimal.valueOf(values[1]);
            case 1:
                // this line cant work no more due to price being final now
                price = BigDecimal.valueOf(values[0]);
        }
    }

    public void addCaution(String caution){
        this.caution = caution;
    }

    public String getCaution(){
        return caution;
    }

    public Product serve(){
        System.out.println(this.condition.getCaution());
        // With the use of a variable and method in the enum we can avoid the use of the switch
        // switch(condition){
        //     case COLD:
        //         this.addCaution("This is HOT!");
        //     case WARM:
        //         this.addCaution("Just right");
        //     case HOT:
        //         this.addCaution("Warning HOT!");
        // }
        return this;
    } /* */
}
