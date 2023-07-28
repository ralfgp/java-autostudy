package autostudy.classesAndObjects;

public class Person {
    private String name;

    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String consume(Product product){
        return "Mmmm such a nice "+ product.getName();
    }
}
