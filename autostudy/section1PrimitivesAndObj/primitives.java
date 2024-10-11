package autostudy.section1PrimitivesAndObj;

public class primitives {

    public void primVar(){
        int a = 0b101010;   // bynary
        short b = 052;      // octal
        byte c  = 42;       // decimal
        long d = 0x24;      // hex
        float e = 1.99E2F;
        double f = 1.99;
        long g = 5, h = c;
        float i = g;
        char j = 'A';
        char k = '\u0041', l = '\101';
        int s;
        s = 77;
    }

    public void casting(){
        byte a = 127, b = 5;

        float h = (float)(a/b); // h is 25.0F
        float i = (float)a/b;   // i is 25.4F
        float j = a/(float)b;   // j is 25.4F
    }

    public void mathOperations(){
        double a = 1.99, b = 2.99, c = 0;
        c = Math.cos(a);    // cosine
        c = Math.acos(a);   // arc cosine
        c = Math.exp(a);    // e^a
        c = Math.max(a,b);  // greater of two values
        c = Math.min(a,b);  // smaller of two values
        c = Math.pow(a,b);  // a^b
        c = Math.sqrt(a);   // square root
        c = Math.random();  // random number 0.0 >= c < 1.0
    }

    public void roundingMath(){
        int a = 11, b = 3;
        long c = Math.round(a/b);                       // c is 3
        double d = Math.round(a/b);                     // d is 3.0
        double e = Math.round((double)a/b*100)/100.0;   // e is 3.67
    }

    public void bitwiseOperations(){
        Boolean andBitwise = true & true; // true
        Boolean orBitwise = true | false; // true
        Boolean xorBitwise = true ^ false; // true
        byte a = 5;             // 0101
        byte b = 3;             // 0011
        byte c = (byte)(a & b); // 0001 (c is 1)
        byte d = (byte)(a | b); // 0111 (d is 7)
        byte e = (byte)(a ^ b); // 0110 (e is 6)
    }

    public void bitShift(){
        int a = 5;
        int b = -5;
        int c = a << 2;     // signed left shift, fills lower positions with 0 bit values
        int d = a >> 2;     // signed right shift
        int e = a >>> 2;    // unsigned right shift, fill higher positions with 0 bit values
    }

    public void useOfInstanceOf(){
        Perro perro = new Perro();
        System.out.print(perro instanceof Animal);    // true
    }
}

class Animal {}
class Perro extends Animal {}
class Robot {}
