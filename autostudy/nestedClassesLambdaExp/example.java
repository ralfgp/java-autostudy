package autostudy.nestedClassesLambdaExp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class example {
    public static void main(String[] args) {
        nestedClasses();
        staticNestedClass();
        memberInnerClass();
        anonymousInnerClass();
        anonInnerClassesAndFuncInterf();
        lambdaExpressions();
        lambdaExpParaAndBody();
        lambdaMethodReference();
        defaultAndStaticMethCompInterface();
    }


    private static void defaultAndStaticMethCompInterface() {
        List<Product> menu = new ArrayList<>();
        menu.add(new Food("Cake",1.99));
        menu.add(new Food("Cookie",2.99));
        menu.add(new Drink("Tea", 2.99));
        menu.add(new Drink("Coffee", 2.99));
        Comparator<Product> sortNames = (p1, p2) -> p1.getName().compareTo(p2.getName());
        Comparator<Product> sortPrices = (p1, p2) -> p1.getPrice().compareTo(p2.getPrice());

        // thenComparing ass additional comparators
        // reversed reveses sorting order
        Collections.sort(menu, sortNames.thenComparing(sortPrices).reversed());
        menu.add(null);
        // nullsFirst and nullsLast return comparators that enable sorting collections with null values
        Collections.sort(menu, Comparator.nullsFirst(sortNames));
    }


    private static void lambdaMethodReference() {
        TextFilter filter =  new TextFilter();
        List<String> list = new ArrayList<>();

        // Reference by <Class>::<staticMethod> - reference a static method
        list.removeIf(s -> TextFilter.removeA(s));
        list.removeIf(TextFilter::removeA);         //same as the line above

        Collections.sort(list, (s1, s2) -> filter.sortText(s1, s2));
        // Reference by <object>::<instanceMethod> - reference an instance method of a particular object
        Collections.sort(list, filter::sortText); //same as the line above

        Collections.sort(list, (s1, s2) -> s1.compareToIgnoreCase(s2));
        // Reference by <Class>::<instanceMethod> - reference an instance method of an arbitrary object of a particular type
        Collections.sort(list, String::compareToIgnoreCase); //same as the line above

        // The last type of eference is <Class>::new - reference a constructor
    }


    private static void lambdaExpParaAndBody() {
        List <String> list = new ArrayList<>();

        // To apply modifiers like final to parameters, define them using specific types or locally infered types
        list.removeIf((final String s) -> s.equals("remove me"));
        list.removeIf((final var s) -> s.equals("remove me"));

        // When no modifier is required you may just infer types from the context
        // Formal body {} and return statements are optional when using a simple expression
        list.sort((s1,s2) -> {return s1.compareTo(s2);});

        //Expression can be predefined and reused
        Comparator<String> sortText = (s1,s2) -> s1.compareTo(s2);
        Collections.sort(list, sortText);

    }

    /*
     * Lambda expression is an inline implementation of a functional interface
     * Lambda expression infers its properties from the context:
     */
    private static void lambdaExpressions() {
        List<Product> products = new ArrayList<>();
        // -Context of invocation infers which functional interface is implemented ...sort(obj,<Comparator>)
        Collections.sort(products,new Product());
        // -Functional interfaces only abstract method infers the method that has to be overriden
        // -Generics infer which parameters the method should have
        // -Return type infers a return statement.
        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product p1, Product p2){
                return p1.getName().compareTo(p2.getName());
            }
        });
        /*
         * Lambda expression may just contain parameter names and the desired algorithm
         * Lambda token -> separates parameter list from the method body
         */
        Collections.sort(products, (p1,p2) -> p1.getPrice().compareTo(p2.getPrice()));
    }

    /*
     * Anonymous inner classes are typically used to provide inline interface implementations
     * Anonymous inner class can implement an interface inline and override as many methods as required
     * Functional interfaces define only one abstract method that must be overridden
     * Anonymous inner class that implements functional interface will only have to override one method
     */
    private static void anonInnerClassesAndFuncInterf() {
        //It could be more convenient to:
        //  Use a regular class to override many mehthos
        //  Use anonymous inner class to override a few methods(just one in case of a functional interface)

        List<Product> products = new ArrayList<>();
        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product p1, Product p2){
                return p1.getName().compareTo(p2.getName());
            }
        });

        Collections.sort(products, new Comparator<Product>(){
            public int compare(Product p1, Product p2){
                return p1.getPrice().compareTo(p2.getPrice());
            }
        });
    }

    private static void anonymousInnerClass() {
        //Separate class impolementation, check OnlineOrder, using extend Order
        //Better than creating a whole new class
        //Anonymous inner class implementation
        Order order = new Order(){
            @Override
            public BigDecimal getDiscount(){
                return BigDecimal.valueOf(0.1);
            }
        };
    }

    private static void memberInnerClass() {
        Order order1 = new Order();
        Order order2 = new Order();
        order1.addItem(new Drink("Tea"),2);
        order1.addItem(new Food("Cake"),1);
        order2.addItem(new Drink("Tea"),1);
    }

    private static void staticNestedClass() {
        Order.createShippingMode("Fast");
        Order.createShippingMode("Normal");
        Order order1 = new Order();
        Order order2 = new Order();
    }

    /*
     * Static nested class is associated with the static context of the outer class
     * Member (not static) inner class is associated with the instance context of the outer class
     * Local (private) inner class is associated with the context of specific method
     * Anonymous inner class is an inline implementation or extension of an interface or a class.
     */
    private static void nestedClasses(){
        Outer.StaticNested x = new Outer.StaticNested();
        x.getClass();

        OtherOuter.createInstance();
    }
}
