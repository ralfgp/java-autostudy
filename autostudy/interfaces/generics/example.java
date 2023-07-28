package autostudy.interfaces.generics;

public class example {
    public static void main(String[] args) {
        Some<Product> some = new Some<>();
        some.setValue(new Product("Tea",1.99));
        // This dont work due to some being of Product type and not accepting String as the type, just uncomment to see the error
        // some.setValue("asdlkmasd");
        Product p = some.getValue();
        System.out.println(p);
    }
}
