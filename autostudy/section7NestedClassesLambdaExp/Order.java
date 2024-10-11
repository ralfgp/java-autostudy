package autostudy.section7NestedClassesLambdaExp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Order {
    /*
     * Static nested class is associated with the static context of the outer class
     * To create an instance of a static nested class, you do not need to create instances of outer class.
     * Can access private variables and methods of the outer class
     * Can only access static variables and methods of the outer class
     */
    public static void createShippingMode(String description){
        new ShippingMode(description);
    }
    private static class ShippingMode{
        private String description;
        public ShippingMode(String description){
            this.description = description;
        }
        // other methods and variables of the ShippingMode class
    }

    /*
     * Member inner class is associated with the instance context of the outer class
     * To create an instance of a member inner class, you must create an instance of outer class first
     * Can access private variables and methods of the outer class
     * Can access both static and instance variables and methods of the outer class
     */
    private Set<Item> items = new HashSet<>();
    public void addItem(Product product, int quantity){
        items.add(new Item(product,quantity));
    }
    class Item{
        private Product product;
        private int quantity;
        public Item(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
        // other methods of the Item class
    }
    
    /*
     * Local inner class is associated with the context of the specified method
     * Instances of the local inner class can only be created within the outer method context.
     * Contains logic complex enough to require the algorithms be wrapped up as a class
     * Outer method local varibles and parameters can only be accessed if they are final or effectively final
     */
    private Map<Integer, Item> items2 = new HashMap<>();
    public void manageTax(final String saleLocation){
        class OrderTaxManager{
            private BigDecimal findRate(Product product){
                // use SaleLocation and product to find the tax rate
                return product.getPrice();
            }
            BigDecimal calculateTax(){
                // find tax rate in a given sale location for each product
                // calculate tax value
                return findRate(new Drink("pepsi",2.9));
            }
        }
        OrderTaxManager taxManager = new OrderTaxManager();
        BigDecimal taxTotal = taxManager.calculateTax();
    }

    /*
     * Anonymous inner class is an implementation of an interface or extension of a class
     * Extend a parent class of implement an interface to override operations
     * Implemented inline and instantiated immediately
     * Outer method local variables and parameters can only be accessed if they are final or effectively final.
     */
    public BigDecimal getDiscount(){
        return BigDecimal.ZERO;
    }
}
