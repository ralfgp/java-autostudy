package autostudy.classesAndObjects;

import java.math.BigDecimal;
import java.time.Period;

public class Shop {

    // The main method is static
    public static void main(String[] args){
        /*
        // All Math class operations are static
        Math.round(1.99);

        // Static import enables referencing static variables and methods of another class as if they are in this class
        double value = random();
        System.out.println(value);

        // Factory methods are static methods that create and return a new instance
        BigDecimal.valueOf(1.99);
        LocalDateTime.now();
        ZoneId.of("Europe/London");
        ResourceBundle.getBundle("messages", Locale.UK);
        NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println("Hello World");
        /* */

        // The equals function can be create
        equalsFunctions();
    }

    public static void equalsFunctions(){
        Product p1 = new Product(42,"Cake", 2.99);
        Product p2 = new Product(42,"Cake", 2.99);
        Product p3 = p1;
        System.out.println((p1==p2));
        System.out.println((p1==p3));
        System.out.println(p1.equals(p2));
        System.out.println(p1.equals(p3));
    }

    /*
    public void order(Product p){
        BigDecimal price = p.getPrice();
        BigDecimal discount = BigDecimal.ZERO;
        if(p instanceof Food){
            discount = (((Food)p).getBestBefore().isEqual(LocalDate.now().plusDays(1)))
                                        ? price.multiply(BigDecimal.valueOf(0.1))
                                        : BigDecimal.ZERO;
        }
        if(p instanceof Drink){
            LocalTime now = LocalTime.now();
            discount = (now.isAfter(LocalTime.of(17,30)) && now.isBefore(LocalTime.of(18,30)))
                                    ? price.multiply(BigDecimal.valueOf(0.2))
                                    : BigDecimal.ZERO;
        }
        price = price.subtract(discount);
    } */

    public void order(Product p){
        BigDecimal price = p.getPrice();
        System.out.println(price);
        p.serve();
    }

    public void enumFunction(){
        Product tea = new Product("Tea", Condition.HOT);
        Person joe = new Person("Joe");
        joe.consume(tea.serve());
    }

    public void function(){
        new Product();
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = p2;
        // This can no longer be used as name has become final
        p1.setName("Tea");
        p2.setName("Cake");
        System.out.println(p1.getName()+" in a cup");
        System.out.println(p2.getName()+" on a plate");
        System.out.println(p3.getName()+" to share");
        // p1.name = "Coffee" this wont work since the variables are private

        // This can no longer be used as name has become final
        p1.setPrice(1.99);
        BigDecimal price = p1.getPrice();
        System.out.println("Precio: " + price);

        // Define constants
        Product p = new Product();
        BigDecimal percentage = BigDecimal.valueOf(0.2);
        final BigDecimal amount = p.getDiscount(percentage);
        System.out.println("Precio con descuento: " + amount);
    }

    public void staticMethods(){
        Product.setExpiryPeriod(4);
        System.out.println(Product.getExpiryPeriod().getDays());
        Product p1 = new Product();
        //Product p2 = new Product();
        // the next 2 lines are permited but not recommended because the static variable belongs to the class not the instance/object
        // p1.setExpiryPeriod(2);
        // System.out.println(p2.getExpiryPeriod().getDays());
        //Product p3 = new Product();
        System.out.println(Product.getExpiryPeriod().getDays());
        p1.getName();
        // Instance variables and methods cant be accessed through the static context
        // Product.getName();

        Period expiry = Period.ofDays(Product.MAX_EXPIRY_PERIOD);
        System.out.print(expiry.getDays());
    }
}
