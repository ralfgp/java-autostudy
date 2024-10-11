package autostudy.section4AbstractClasses;

import java.time.LocalDate;

public class Shop {
    public static void main(String[] args) {
        // This cannot be instatiated due to Product being abstract
        // order(new Product("???",0));
        order(new Food("Cake",2.99));
        order(new Drink("Tea",2.99));
        toStringOverrideMethod();
        compareStrings();
    }

    private static void compareStrings() {
        String a = "Hello";
        String b = "Hello";
        String c = new String("Hello");
        String d = "heLLo";
        System.out.println("Comparing a==b is "+(a==b));
        System.out.println("Comparing a==c is "+(a==c));
        System.out.println("Comparing a.equals(b) is "+(a.equals(b)));
        System.out.println("Comparing a.equals(c) is "+(a.equals(c)));
        System.out.println("Comparing a.equals(d) is "+(a.equals(d)));
        System.out.println("Comparing a.equalsIgnoreCase(d) is "+(a.equalsIgnoreCase(d)));
    }

    public static void toStringOverrideMethod(){
        Product p = new Food("Cake",2.99,LocalDate.now().plusDays(1));
        System.out.println(p);
    }

    public static void order(Product p){
        p.serve();
    }
}
