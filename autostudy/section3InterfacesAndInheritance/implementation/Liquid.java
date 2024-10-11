package autostudy.section3InterfacesAndInheritance.implementation;

public interface Liquid {
    public default void prepare(){
        // pour to a cup
    }
    int measure();
}
