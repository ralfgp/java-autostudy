package autostudy.ArraysAndLoops;

import java.util.Arrays;

public class example {
    public static void main(String[] args) {
        intArray();
        objArray();
        initIntArray();
        initObjArray();
        multDimArray();
        copyArrays();
        arrayFunctions();
        processArrayUsingLoops();
        complexLoops();
        continuesAndBreaks();
    }

    private static void continuesAndBreaks(){
        char[][] matrix = {{'A', 'B', 'C', 'D', 'E'},
                           {'F', 'G', 'H', 'I', 'K'},
                           {'L', 'M', 'N', 'O', 'P'},
                           {'Q', 'R', 'S', 'T', 'U'},
                           {'V', 'W', 'X', 'Y', 'Z'}};
        StringBuilder txt = new StringBuilder();
        outerLoopLabel:
        for (char[] row : matrix) {
            for (char value : row) {
                // skips the current loop (row)
                if(value == 'C'){continue;}
                // skips to the level of the label, skipping even the new line
                if(value == 'H'){continue outerLoopLabel;}
                // breaks the loop (row)
                if(value == 'N'){break;}
                // breaks the loop up to the level of the label
                if(value == 'S'){break outerLoopLabel;}
                txt.append(value);
            }
            txt.append('\n');
        }
        System.out.println(txt);
    }

    private static void complexLoops() {
        int[] values = {1,2,3,4,5,6,7,8,9};
        int sum = 0;
        for(int i = 0; i < values.length; sum+=i++);
        System.out.println(sum);
        // sum result is 36

        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        for(int i = 0,j = 2; !(i==3|| j==-1);i++,j--){
            System.out.print(matrix[i][j]+" ");
        }
        System.out.println("\n");
    }

    private static void processArrayUsingLoops() {
        int[] values = {1,2,3};
        StringBuilder txt = new StringBuilder();
        for(int i = 0;i<values.length;i++){
            txt.append(values[i]);
        }
        // This kind of for is called forEach where the values are auto extracted
        for(int value: values){
            txt.append(value);
        }
    }

    private static void arrayFunctions() {
        // The Arrays class provides convenient methods for handling Arrays
        String[] values = new String[5];
        Arrays.fill(values, 2, 5, "aaa");
        System.out.println(values[2]);
        int x = Arrays.binarySearch(values,"aaa");
        System.out.println("aaa is in "+x);

        String[] names1 = {"Mary","Ann","Jane","Tom"};
        String[] names2 = {"Mary","Ann","John","Tom"};
        boolean isTheSame = Arrays.equals(names1,names2);
        System.out.println("The answer to the question names1 is equals to names2 is "+isTheSame);
        Arrays.sort(names2);
        Arrays.sort(names2,new LenghtCompare());
    }

    private static void copyArrays() {
        char[] a1 = {'a','c','m','e'};
        char[] a2 = {'t','o',' ',' ',' '};
        // System.arraycopy(<source array>, <source position>,
        //                  <destination array>, <destination position>,
        //                  <lenght of content to copy from source>);
        // The next line would result in a1 being "acme" and a2 being "to me"
        System.arraycopy(a1, 2, a2, 3, 2);

        // The other way in which and array can be copied is
        char[] b1 = {'a','c','m','e'};
        // Arrays.copyOf(<source of array>, <new array lenght>)
        // Arrays.copyOfRange(<source of array>, <start position>, <end position>)
        char[] b2 = Arrays.copyOf(b1,5);
        System.out.println(b2);
    }

    private static void multDimArray() {
        // Can use normal or short-hand initializations
        int[][] matrix1 = new int[2][3];
        matrix1[0][1] = 5;
        matrix1[1][2] = 7;

        // Can be of non-square shape
        int[][] matrix2 = {{4,1},{2,0,5}};
        System.out.println(matrix2[0][0]);

        // Example with objects
        Product[][] products = {{new Food("Cake"),null, new Drink("Tea")},
                                {null, new Food("Cookie")}};
        System.out.println(products[0][0]);
    }

    private static void initObjArray() {
        // Combine declaration and creation of the array object
        Product[] p1 = new Product[3];
        p1[0] = new Food("Cake");
        p1[1] = new Drink("Tea");
        p1[2] = new Food("Cookie");

        // Combine creation of the array object and initialization of the array content
        Product[] p2;
        p2 = new Product[]{new Food("Cake"),
                           new Drink("Tea"),
                           new Food("Cookie")};
        System.out.println(p2[0]);

        // Combine declaration and creation of the array object as well initialization of the array content
        Product[] p3 = {new Food("Cake"),
                        new Drink("Tea"),
                        new Food("Cookie")};
        System.out.println(p3[0]);

    }

    private static void initIntArray() {
        // Combine declaration and creation of the array object
        int[] p1 = new int[3];
        p1[0] = 2;
        p1[1] = 3;
        p1[2] = 5;

        // Combine creation of the array object and initialization of the array content
        int[] p2;
        p2 = new int[]{2,3,5};
        System.out.println(p2[0]);

        // Combine declaration and creation of the array object as well initialization of the array content
        int[] p3 = {2,3,5};
        System.out.println(p3[0]);
    }

    private static void objArray() {
        Product[] products;
        // Product products[]
        products = new Product[3];
        products[0] = new Food("Cake");
        products[2] = new Drink("Tea");
        products[2].getPrice();
        // Cant access or declare objs outside of the declared bounds
        // products[3];
    }

    private static void intArray() {
        // the brackest [] in both lines function the same and are the same
        int[] primes;
        //int primes[];
        primes = new int[4];
        primes[1] = 3;
        primes[2] = 7;
        primes[0] = primes[1]-1;
        // Cant declare values outside of the declared bounds
        // primes[4] = 0;
    }

    
}
