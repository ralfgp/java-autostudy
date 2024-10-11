package autostudy.section3InterfacesAndInheritance.generics;

public class Some<T>{
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
