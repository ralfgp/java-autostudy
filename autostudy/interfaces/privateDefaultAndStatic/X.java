package autostudy.interfaces.privateDefaultAndStatic;

public interface X {
    // private interface methods do not cause conflicts, because they are not visible outside of that interface
    private void c() {}
    // static interface methods do not cause conflicts, because they are invoked via specific parent types and do not rely on the super reference
    public static void d(){}
    // if there is a conflict between default methods, it must be resolved by overriding this default method within the implementation class
    public default void b(){c();}
    // otherwise the default method can be inherited
    void a();
}
