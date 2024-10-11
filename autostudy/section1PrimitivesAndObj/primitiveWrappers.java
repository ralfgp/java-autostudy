package autostudy.section1PrimitivesAndObj;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class primitiveWrappers {

    /*
     *    byte => Byte
     *   short => Short
     *     int => Integer
     *    long => Long
     *   float => Float
     *  double => Double
     *    char => Character
     * boolean => Boolean
     */
    public void wrapperSimpleExplanation(){
        int a =42;
        Integer b = Integer.valueOf(a); // Construct wrapper object out of primitive or string
        int c = b.intValue();           // Extract primitive values out of wrapper usin xxxValue()
        b = a; // auto-boxing, avoid too many for performance reasons
        c = b; // auto-unboxing, avoid too many for performance reasons

        //Create wrapper or primite out of the string using parseXXX()
        String d = "12.25";
        Float e = Float.valueOf(d);
        float f = Float.parseFloat(d);

        // You may convert a primitive to a string using String.valueOf()
        String g = String.valueOf(a);

        // Wrapper calsses provide constants
        int h = Short.MIN_VALUE;
        int i = Short.MAX_VALUE;
    }

    /*
     * The BigDecimal is useful in handling decimal numbers that require exact precision.
     * All prim wrappers and BigDecimals are immutable and signed
     * It provides arithmetic operations as methods such as add, subtract, divide, multiply, remainder.
     * It is typically used to represent decimal numbers that require exact precision, such as fiscal values.
     */
    public void bigWrappers(){
        // Method chaining is possible with any operation that returns an object, as it is exemplified with s2 and price
        String s1 = "Hello";
        String s2 = s1.concat("World").substring(3,6); // s2 is "loW"

        BigDecimal price = BigDecimal.valueOf(12.99);
        BigDecimal taxRate = BigDecimal.valueOf(0.2);
        BigDecimal tax = price.multiply(taxRate);                           // tax is 2.598
        price = price.add(tax).setScale(2,RoundingMode.HALF_UP);   // price is 15.59
    }
}
