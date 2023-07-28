package autostudy.interfaces.privateDefaultAndStatic;

public class example {

    public static void main(String[] args) {
        Z z = new Z();
        z.a();
        z.b();
        z.e();
        X.d();
        Y.d();
        // d can not be inherited due to being static
        // z.d();
        // And c cannot be inherited and doesnt have conflicts due to it being private in their respective interfaces
        // z.c();
    }
}
