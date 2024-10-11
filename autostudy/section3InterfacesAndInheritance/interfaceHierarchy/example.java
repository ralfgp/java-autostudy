package autostudy.section3InterfacesAndInheritance.interfaceHierarchy;

public class example {
    public static void main(String[] args) {
        Z z = new Z();
        z.toString();
        z.a();
        z.b();
        if(z instanceof Y){
            Y y = (Y)z;
            y.toString();
            y.a();
            y.b();
        }
    }
}
