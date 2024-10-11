package autostudy.section8JavaStreamsAPI;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class example {
    public static void main(String[] args) throws IOException {
        // charOfStreams();
        // createStreams();
        // usingFunctionalInterfaces();
        // mapStringToInt();
        // biArgumentVariantFunctionalInterfaces();
        // actionswithStreamPipeline();
        // filteringOfStreamPipeline();
        // mappingOfStreamPipeline();
        // joinStreamWithFlatMap();
        // otherIntermediateStreamOperations();
        shortCircuitTerminalOperations();
    }
    
    // Short-circuit terminal operations produce finite result even if presented with infinite input.
    // All short-circuit operations terminate stream pipeline processing as soon as result is computed.
    private static void shortCircuitTerminalOperations() {
        String[] values = {"RED", "GREEN", "BLUE"};
        // Operation allMatch(Predicate p) returns true if all elements in the stream match the predicate.
        boolean allGreen = Arrays.stream(values).allMatch(s -> s.equals("GREEN"));
        // Operation anyMatch(Predicate p) returns true if any elements in the stream match the predicate.
        boolean anyGreen = Arrays.stream(values).anyMatch(s -> s.equals("GREEN"));
        // Operation noneMatch(Predicate p) returns true if no elements in the stream match the predicate.
        boolean noneGreen = Arrays.stream(values).noneMatch(s -> s.equals("GREEN"));
        // Operation findAny() returns an element from the stream wrapped in an Optional object.
        Optional<String> anyColour = Arrays.stream(values).findAny();
        // Operation findFirst() returns the first element from the stream wrapped in an Optional object.
        Optional<String> firstColour = Arrays.stream(values).findFirst();
    }
    
    // Intermediate operations rearranging or reducing stream content:
    private static void otherIntermediateStreamOperations() {
        Stream.of("A", "B", "C", "D", "B", "D")
            // Operation distinct() returns a stream with no duplicates.
              .distinct()
            // Operations sorted() and sorted(Comparator<T>) rearrange the order of elements.
              .sorted()
            // Operation skip(long n) skips a number of elements in the stream.
              .skip(2)
              .forEach(s -> System.out.println(s.toLowerCase()));

        Stream.of("B", "C", "D", "A", "B", "D")
            // Operation takeWhile(Predicate<T>) takes elements from the stream while they match the predicate.
              .takeWhile(s -> !s.equals("D"))
            // Operation dropWhile(Predicate<T>) removes elements from the stream while they match the predicate.
              .dropWhile(s -> !s.equals("C"))
            // Operation limit(long n) returns a stream of elements limited to a given size.
              .limit(2)
              .forEach(s -> System.out.println(s.toLowerCase()));
    }


    // Flatten a number of streams into a single stream:
    private static void joinStreamWithFlatMap() {
        // Operation Stream<R> flatMap(Function<T, Stream<R>> f) merges streams.
        // Primitive variants are: flatMapToInt, flatMapToLong, and flatMapToDouble (see notes).
        List<Product> prodList1 = createProductList();
        List<Product> prodList2 = createProductList();
        prodList2.remove(1);
        prodList2.add(new Product("Chocolate",4.0));
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(prodList1));
        orders.add(new Order(prodList2));
        double x = orders.stream()
                        .flatMap(order -> order.items())
                        .filter(item -> item.getName().equals("Tea"))
                        .mapToDouble(item -> item.getPrice().doubleValue())
                        .sum();

    }

    /*
    Map stream elements to a new stream of different content type using map operation.
    Method map accepts a Function<T, R> interface and returns a new stream
        comprising elements produced by this function based on the original stream content.
     */
    private static void mappingOfStreamPipeline() {
        List<Product> list = createProductList();

        // Lambda expression must implement abstract R apply(T t) method.
        Function<Product, String> nameMapper = p -> p.getName();
        // Static methods provided by the Function interface:
        // - identity returns a function that always returns its input argument (equivalent of t -> t function).
        // Interface UnaryOperator<T> is a variant of a Function that maps values without changing the type.
        UnaryOperator<String> trimMapper = n -> n.trim();
        ToIntFunction<String> lengthMapper = n -> n.length();

        // Default methods provided by the Function:
        // - andThen and compose combine functions together.
        list.stream().map(nameMapper.andThen(trimMapper))
            // Primitive variants of map are: mapToInt, mapToLong and mapToDouble.
            .mapToInt(lengthMapper)
            .sum();
    }

    // Filtering of the pipeline content is performed by the filter operation.
    // Method filter accepts Predicate<T> interface and returns a stream comprising only elements that satisfy the filter criteria.
    private static void filteringOfStreamPipeline() {
        List<Product> list = createProductList();

        // Lambda expression must implement abstract boolean test(T t) method.
        Predicate<Product> foodFilter = p -> p instanceof Food;
        Predicate<Product> priceFilter = p -> p.getPrice().compareTo(BigDecimal.valueOf(2)) < 0;

        // Default methods provided by the Predicate:
        // - and combines predicates like the && operator.
        // - or combines predicates like the || operator.
        // - negate returns a predicate that represents the logical negation of this predicate.
        list.stream().filter(foodFilter.negate().or(priceFilter))
            .forEach(p -> p.setDiscount(0.1));

        // Static methods provided by the Predicate interface:
        // - not returns a predicate that is the negation of the supplied predicate.
        // - isEqual returns a predicate that compares the supplied object with the contents of the collection.
        list.stream().filter(Predicate.isEqual(new Food("Cake", BigDecimal.valueOf(1.99))))
            .forEach(p -> p.setDiscount(0.1));

    }

    /*
    Intermediate or terminal actions are handled by peek and forEach operations.
     */
    private static void actionswithStreamPipeline() {
        List<Product> list = createProductList();
        Consumer<Product> expireProduct = (p) -> p.setBestBefore(LocalDate.now());
        Consumer<Product> discountProduct = (p) -> p.setDiscount(0.1);

        // Operations peek, forEach, and forEachOrdered accept the Consumer<T> interface.
        // Lambda expressions must implement the abstract void accept(T t) method.
        // The default andThen method provided by the Consumer interface combines consumers together.
        list.stream().forEach(expireProduct.andThen(discountProduct));

        // The difference between forEachOrdered and forEach is that forEachOrdered guarantees
        // respecting the order of elements, which is beneficial for parallel stream processing.
        list.stream()
            .peek(expireProduct)
            .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(10)) > 0)
            .forEach(discountProduct);

        list.stream().forEach(p -> {
            p.setBestBefore(LocalDate.now());
            p.setDiscount(0.1);
        });
    }

    /*
    Process more than one value at a time.
    Extra parameter is provided compared to basic function interfaces:
        BiPredicate<T, U> defines method boolean test(T t, U u) to apply conditions.
        BiFunction<T, U, R> defines method R apply(T t, U u) to convert two inputs into a single result.
        BinaryOperator<T> (variant of BiFunction) defines method T apply(T t1, T t2) to combine two values.
        BiConsumer<T, U> defines method void accept(T t, U u) to process a pair of elements.
                +-------------------+-------------------------+--------------------+----------------------+
                |     Predicate     |       Function          |  UnaryOperator     |     Consumer         |
                +-------------------+-------------------------+--------------------+----------------------+
                | BiPredicate<T, U> | BiFunction<T, U, R>     | BinaryOperator     | BiConsumer<T, U>     |
    +-----------+-------------------+-------------------------+--------------------+----------------------+
    | primitive |                   | ToIntBiFunction<T,U>    | IntUnaryOperator   | ObjIntConsumer<T>    |
    |  variants |                   | ToLongBiFunction<T,U>   | LongBinaryOperator | ObjLongConsumer<T>   |
    |           |                   | ToDoubleBiFunction<T,U> | BinaryOperator     | ObjDoubleConsumer<T> |
    +-----------+-------------------+-------------------------+--------------------+----------------------+
     */
    private static void biArgumentVariantFunctionalInterfaces() {
        Product p1 = new Food("Cake", BigDecimal.valueOf(3.10));
        Product p2 = new Drink("Tea", BigDecimal.valueOf(2.00));
        Map<Product, Integer> items = new HashMap<>();
        items.put(p1, Integer.valueOf(1));
        items.put(p2, Integer.valueOf(3));
        items.forEach((k, q) -> k.getPrice().multiply(BigDecimal.valueOf(q.intValue())));
    }

    /**
    +------------------+-----------------+----------------------+---------------------+----------------+----------------+
    |                  |    Predicate    |      Function<T>     |  UnaryOperator      |    Consumer    |    Supplier    |
    +------------------+-----------------+----------------------+---------------------+----------------+----------------+
    |    primitive     |                 | ToIntFunction<T>     |                     |                |  IntSupplier   |
    |     output       |                 | ToLongFunction<T>    |                     |                | LongSupplier   |
    |                  |                 | ToDoubleFunction<T>  |                     |                | DoubleSupplier |
    +------------------+-----------------+----------------------+---------------------+----------------+----------------+
    |                  | IntPredicate    | IntFunction<R>       |                     |  IntConsumer   |                |
    |    primitive     | LongPredicate   | LongFunction<R>      |                     | LongConsumer   |                |
    |      input       | DoublePredicate | DoubleFunction<R>    |                     | DoubleConsumer |                |
    +------------------+-----------------+----------------------+---------------------+----------------+----------------+
    |                  |                 | IntToLongFunction    | IntUnaryOperator    |                |                |
    |    primitive     |                 | IntToDoubleFunction  | LongUnaryOperator   |                |                |
    |   input-output   |                 | LongToIntFunction    | DoubleUnaryOperator |                |                |
    |                  |                 | LongToDoubleFunction |                     |                |                |
    |                  |                 | DoubleToIntFunction  |                     |                |                |
    |                  |                 | DoubleToLongFunction |                     |                |                |
    +------------------+-----------------+----------------------+---------------------+----------------+----------------+
    */
    private static void mapStringToInt() {
        Stream.of("ONE","TWO","THREE","FOUR")
              .mapToInt(s->s.length())          //ToIntFunction<T>
              .peek(i->System.out.println(i))   //IntConsumer
              .filter(i->i>3)                   //IntPredicate
              .sum();

        //Other example
        int x = DoubleStream.of(1.234, 1.0, 3.987, -0.321, 4.0)
            .filter(n -> n > 0) // remove negative numbers
            .boxed() // convert primitive stream to Double
            .map(n -> BigDecimal.valueOf(n)) // convert Double to BigDecimal
            .map(n -> n.round(new MathContext(0, RoundingMode.HALF_UP))) // round BigDecimal value using MathContext
            .mapToInt(n -> n.intValue()) // convert object stream to primitive stream using ToIntFunction
            .sum(); // compute sum of values
    }
    
    // Using Functional Interfaces
    private static void usingFunctionalInterfaces() {
        List<Product> list = createProductList();


        // Stream operations use functional interfaces located in java.util.function package
        // Can be implemented using lambda expressions
        // Basic function shapes are:
        list.stream()                                   // Produce stream of products from the list
                // - Supplier<T> defines method T get () to produce elements
            .filter (p -> p.getDiscount() == 0)         // Retain only products with no discount
                // - Predicate<T> defines method boolean test (T t) to apply conditions to filter elements
            .peek(p -> p.applyDiscount(0.1))          // Apply discount of 10 percent
                // - Consumer<T> defines method void accept (T t) to process elements
            .map (p -> p.getBestBefore())               // Produce a stream of LocalDate objects
                // - Function<T, R> defines method R apply (T t) to convert types of elements
                // - UnaryOperator<T> (variant of Function) defines method T apply (T t) to convert values
            .forEach (d -> {d.plusDays(1);});    // Calculate the next day
                // - Consumer<T> defines method void accept (T t) to process elements
        //list.forEach(p->p.printProduct());

        // Example of Supplier and Consumer
        Supplier<String> textGenerator = () -> {
            Random random = new Random();
            StringBuilder txt = new StringBuilder(10);
            for(int i = 0;i<10;i++){
                txt.append((char)(random.nextInt(26)+'a'));
            }
            return txt.toString();
        };
        UnaryOperator<String> textConverter = s -> s.toUpperCase();
        Consumer<String> textPrinter = s -> System.out.println(s);
        Stream.generate(textGenerator)
              .limit(20)
              .map(textConverter)
              .forEach(textPrinter);
    }

    // Stream Pipeline Processing Operations
    @SuppressWarnings("unused")
    private static void pipelineProcessingOperations() {
        /*
        Stream handling operation categories:
        +--------------------+-----------------+    - Intermediate: perform action and produce another stream
        | Intermediate       | Terminal        |    - Terminal: traverse stream pipeline and end the stream processing
        +--------------------+-----------------+
        | filter             | forEach         |
        | map                | forEachOrdered  |
        | flatMap            | count           |
        | peek               | min             |
        | distinct           | max             |
        | sorted             | sum             |
        | dropWhile          | average         |
        | skip               | collect         |
        |--------------------+-----------------|
        |        ↓↓↓   Short-Circuit  ↓↓↓      |    - Short-circuit: produce finite result, even if presented with infinite input
        |--------------------+-----------------|
        |                    | reduce          |
        | limit              | allMatch        |
        | takeWhile          | anyMatch        |
        |                    | noneMatch       |
        |                    | findAny         |
        |                    | findFirst       |
        +--------------------+-----------------+


        Basic function purposes:
        Use <functional interfaces> from java.util.function package
        Can be implemented using lambda expressions
        Stream.generate(<Supplier>)
                    - Supplier produces elements
              .filter(<Predicate>)
                    - Predicate performs tests
              .peek(<Consumer>)
                    - Consumer processes elements
              .map(<Function>/<UnaryOperator>)
                    - Function converts types
                    - UnaryOperator (a variant of Function) converts values
              .forEach(<Consumer>);
                    - Consumer processes elements
         */
    }

    // Create Streams Using Stream API
    private static void createStreams() throws IOException {
        // Streams handling is described by the following interfaces:
        // - BaseStream - defines core stream behaviors, such as managing the stream in a parallel or sequential mode.
        // - Stream, DoubleStream, IntStream, LongStream interfaces extend the BaseStream and provide stream processing operations.
        IntStream.generate(() -> (int) (Math.random() * 10)).takeWhile(n -> n != 3).sum();
        Stream.of(new Food(), new Drink()).forEach(p -> p.setPrice(BigDecimal.valueOf(1)));
        // - To avoid excessive boxing and unboxing, primitive stream variants are also provided.
        
        List<Product> list = new ArrayList<>();
        Product[] array = {new Drink(), new Food()};
        
        // - Stream can be obtained from any collection and array or by using static methods of the Stream class.
        list.stream().parallel().mapToDouble(p -> p.getPrice().doubleValue()).sum();
        Arrays.stream(array).filter(p -> p.getPrice().doubleValue() > 2).forEach(p -> p.setDiscount(0.1));
        // - Many other Java APIs can create and use streams (see notes).

        // Other examples of Stream creations

        String s = "some text";
        IntStream charCodes1 = s.chars();
        IntStream charCodes2 = s.codePoints(); // handles unicode correctly

        Stream<String> symbols = charCodes2.mapToObj(c -> String.valueOf((char)c)); // convert int values from the stream into String objects

        Random random = new Random();
        DoubleStream randomNumbers = random.doubles(10);

        Stream<String> textFileContent = Files.lines(Paths.get("some.txt"));
    }

    // Characteristics of Streams
    // - Stream is an immutable flow of elements.
    private static void charOfStreams() {
        List<Product> list = new ArrayList<>();

        // Stream example
        // Stream processing can be sequential (default) or parallel.
        // Once an element is processed, it is no longer available from the stream.
        list.stream()
            .parallel()
            // Stream pipeline traversal uses method chaining - intermediate operations return streams.
            .filter(p -> p.getPrice().doubleValue() > 10)
            // Stream operations use functional interfaces and can be implemented as lambda expressions.
            // Stream may represent both finite and infinite flows of elements.
            .forEach(p -> p.setDiscount(0.2));
            // Pipeline traversal is lazy:
            // - Intermediate actions are deferred until stream is traversed by the terminal operation.
            // - The chain of activities could be fused into a single pass on data.
            // - Stream processing ends as soon as the result is determined; remaining stream data can be ignored.

        // Loop example
        for (Product p : list) {
            if (p.getPrice().doubleValue() > 10) {
                p.setDiscount(0.2);
            }
        }
    }

    private static List<Product> createProductList(){
        List<Product> list = new ArrayList<>();
        list.add(new Product("Coffee", BigDecimal.valueOf(2.5)));
        list.add(new Product("Cake", BigDecimal.valueOf(3.54)));
        list.add(new Product("Coke", BigDecimal.valueOf(1.9),Double.valueOf(0.2)));
        list.add(new Product("Tea", BigDecimal.valueOf(2)));
        return list;
    }
}
