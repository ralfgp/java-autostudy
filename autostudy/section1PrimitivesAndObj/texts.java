package autostudy.section1PrimitivesAndObj;

public class texts {

    public void initStrings(){
        // Its better to initialize a string by using the simple form of declaration due to it using
        // the same memory space, it keeps it as the same text in memory
        String a1 = "Hello";
        String b1 = a1;
        String c1 = "Hello";
        // These three use the same memory space to access the single Hello in memory

        // This type of declaration with new creates different instances of the same text occupying more memory
        char[] text = {'H','e','l','l','o'};
        String a2 = new String(text);
        String b2 = new String("Hello");
        String c2 = "Hello";
    }

    public void stringOperations(){
        String a3 = "  Hello  ";            // "  Hello  "
        a3 = a3.trim();                     // "Hello"
        String b3 = a3.concat("World"); // "HelloWorld"
        String c3 = a3 + "World";           // "HelloWorld"
        String d3 = c3.toLowerCase();       // "helloworld"
        boolean e3 = d3.contains("W");    // false

        String s  = "u";
        s = 1+1+"u";    // "2u"
        s = "u"+1+1;    // "u11"
        s = "u"+(1+1);  // "u2"
    }

    public void stringIndexing(){
        String a = "HelloWorld";                            
        String b = a.substring(0,5);    // b is "Hello"
        int c = a.indexOf('o');                          // c is 4
        int d = a.indexOf('o',5);              // d is 6
        int e = a.lastIndexOf('l');                      // e is 8
        int f = a.indexOf('a');                          // f is -1
        char g = a.charAt(0);                         // g is H
        int h = a.length();                                 // h is 10
        char i = a.charAt(10);                        // throw StringIndexOutOfBoundsException
    }

    /**
     * String is immutable whereas StringBuffer and StringBuilder are mutable classes.
     * StringBuffer is thread-safe and synchronized whereas StringBuilder is not.
     * That's why StringBuilder is faster than StringBuffer
     * Default capacity is 16 and iot auto-expands as required.
     */
    public void stringTypes(){
        new StringBuilder();
        new StringBuilder("text");
        new StringBuilder(100);
        StringBuilder a = new StringBuilder();
        a.append("tea");            // "tea"
        a.append('s');                // "teas"
        a.insert(3,'m');       // "teams"
        a.delete(2,4);        // "tes"
        a.reverse();                    // "set"
        int lenght = a.length();        // 3
        int capacity = a.capacity();    // 16
        a.insert(4,'s');       // throw StringIndexOutOfBoundsException
    }
}
