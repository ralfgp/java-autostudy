package autostudy.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class example {
    public static void main(String[] args) {
        createListObject();
        manageListObject();
        createSetObject();
        manageSetObject();
        createDequeObject();
        manageDequeObject();
        createHashMapObject();
        manageHashMapObject();
        iterations();
        otherCollectionBehaviors();
        useCollections();
        concurrentInteraction();
    }

    // A collection can be corrupted if accessed concurrently from multiple threads
    // Making a collection thread safe doesnt guarantee the thread safety of the objects it contains.
    // To prevent memory corruption in concurrently accessed collections, you can make a collection:
    private static void concurrentInteraction() {
        // Unmodifiable (fast, but read-only) can be a Set (unmodifiableSet), List (unmodifiableList), etc
        Set<Product> reandOnlySet = Collections.unmodifiableSet(new HashSet<>());
        reandOnlySet.contains(null);
        // Synchronizzed (slow and unscalable) can be a Map (synchronizedMap), List (synchronizedList), etc
        Map<Product, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.size();
        //Copy-on-write (fast, but consumes memory)
        List<Product> copyOnWriteList = new CopyOnWriteArrayList<>(new ArrayList<>());
        copyOnWriteList.size();
    }

    // Use the class Collections to interact with a collection
    private static void useCollections() {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Product p3 = new Food("Cookie");
        List<Product> menu = new ArrayList<>();
        menu.add(p1);
        menu.add(p2);
        menu.add(p3);

        // Reordering collection content using
        //  - Comparable (as implemented by objects within the array)
        Collections.sort(menu);
        //  - Comparator interface
        Collections.sort(menu, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().length() - p2.getName().length();
            }
        });
        // This method changes the order to opposite
        Collections.reverse(menu);
        // This method randomly reorders collection
        Collections.shuffle(menu);
        // Searching through the collection of Comparable objects or using Comparator
        Product x = menu.get(Collections.binarySearch(menu,p2));
        System.out.println(x);
        // You can also fill the whole List with the function Collections.fill
        Collections.fill(menu, new Food("Pie"));
    }

    // Some other exmaples of generic Collection behaviors include
    private static void otherCollectionBehaviors() {
        List<Product> menu = new ArrayList<>();
        menu.add(new Food("Cake"));
        menu.add(new Drink("Tea"));
        menu.add(new Food("Cookie"));

        // Convert collection to an array using toArray method
        // Use array provided if collection fits into it
        // Otherwise create new array with the matching size
        Product[] array = new Product[2];
        array = menu.toArray(array);

        // Remove elements from collection base on a condition
        // Implement interface Predicate<T> overriding abstract method boolean test(T);
        // Use removeIf(Predicate<T> p) method to remove all matching elements from the collection
        menu.removeIf(new LongProductNames());
    }

    // Iterate through collections:
    // Collections implement Iterable interface allowing them to be used in a forEach loop
    // Any List, Set or Deque can be directly can be directly used within a forEach loop
    // A more manual approach is to get an Iterator object from collection to step throught the content
    // Get Set of keys or List of values from the HashMap to iterate through the content
    // Iterators also allow to remove content from the collection
    private static void iterations() {
        Map<Product, Integer> items = new HashMap<>();
        Set<Product> keys = items.keySet();
        Collection<Integer> values = items.values();
        for(Product product: keys){
            Integer quantity = items.get(product);
            System.out.println(quantity);
            // use product and quantity objects
        }
        for(Integer quantity : values){
            System.out.println(quantity);
            // use quantity object
        }

        List<Product> menu = new ArrayList<>();
        menu.add(new Food("Cake"));
        menu.add(new Drink("Tea"));
        for(Product product : menu){
            System.out.println(product);
            // use product object
        }
        // less automated alternative
        Iterator<Product> iter = menu.iterator();
        while(iter.hasNext()){
            Product product = iter.next();
            System.out.println(product);
            // use product object
            iter.remove();
        }
    }

    private static void manageHashMapObject() {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Map<Product, Integer> items = new HashMap<>();
        // V put(K, V) insert or update a key-value pair
        items.put(p1,Integer.valueOf(2));
        items.put(p2,Integer.valueOf(2));
        Integer n1 = items.put(p1,Integer.valueOf(5));
        System.out.println("The function returns the value= "+n1);
        // V remove(K) delete a key-value pair
        System.out.println("The function remove(K) returns the value= "+items.remove(p2));
        //V get(K) return the value for a given key
        System.out.println("The function containsKey returns the boolean = "+items.containsKey(p2));
        System.out.println("The function containsValue returns the boolean = "+items.containsValue(n1));
        System.out.println("The function get returns the value of K = "+items.get(p1));
    }

    // A mapo is a composition of a set of keys and a collection of values
    private static void createHashMapObject() {
        Map<Product, Integer> items1 = new HashMap<>();
        Map<Product, Integer> items2 = new HashMap<>(20);
        Map<Product, Integer> items3 = new HashMap<>(items1);
        // A read-only instance of Map can be created using Map.of() or ofEntries()
        Map<Product, Integer> items4 = Map.of(new Food("Cake"), Integer.valueOf(2),
                                              new Drink("Tea"), Integer.valueOf(3));
        System.out.println(items1+"\n"+items2+"\n"+items3+"\n"+items4);
    }

    // Functions of Deque only interact with the first and last element on the list
    private static void manageDequeObject() {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Product p3 = new Food("Cookie");
        Deque<Product> menu = new ArrayDeque<>();
        // T pollFirst() and T pollLast() get and remove elements at the head and tail of the deque
        Product nullProduct = menu.pollFirst();
        // if Deque is empty, poll and peek return null
        System.out.println(nullProduct);
        // offerFirst(T) and offerLast(T) insert elements at the head and the tail of the deque
        menu.offerFirst(p1);
        menu.offerFirst(p2);
        Product tea = menu.pollFirst();
        System.out.println(tea);
        // T peekFirst() and T peekLast() get elements at the head and the tail of the deque
        Product cake1 = menu.peekFirst();
        System.out.println(cake1);
        menu.offerLast(p3);
        menu.offerLast(p1);
        Product cake2 = menu.pollLast();
        System.out.println(cake2);
        Product cookie = menu.peekLast();
        System.out.println(cookie);
        // Null values arent allowed in Deques
        try{menu.offerLast(null);}catch(Exception e){ System.out.println(e.toString()+"\n"); }
    }

    private static void createDequeObject() {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        List<Product> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        // Without constructor it creates a set of initial capacity of 16 elements
        Deque<Product> productDeque1 = new ArrayDeque<>();
        Deque<Product> productDeque2 = new ArrayDeque<>(20);
        Deque<Product> productDeque3 = new ArrayDeque<>(list);
        System.out.println(productDeque1+"\n"+productDeque2+"\n"+productDeque3);
    }

    private static void manageSetObject() {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Product p3 = new Food("Cookie");
        Set<Product> menu = new HashSet<>();
        menu.add(p1);       // insert element
        menu.add(p2);       // insert element
        menu.add(p2);       // insert nothing
        menu.add(p3);       // insert element
        menu.remove(p1);    // remove element
        menu.remove(p1);    // remove nothing
        System.out.println("Menu contains p2? "+menu.contains(p2));
    }

    private static void createSetObject() {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        List<Product> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        Set<Product> productSet1 = new HashSet<>();
        Set<Product> productSet2 = new HashSet<>(20);
        Set<Product> productSet3 = new HashSet<>(20, 0.85f);
        Set<Product> productSet4 = new HashSet<>(list);
        // Creation of a read-only instance, using Set.of(<T> ...)
        Set<Product> productSet5 = Set.of(p1, p2);
        System.out.println(productSet1+"\n"+productSet2+"\n"+productSet3+"\n"+productSet4+"\n"+productSet5+"\n");
    }

    private static void manageListObject() {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        List<Product> menu = new ArrayList<>(4);
        menu.add(p1);
        menu.add(p2);
        menu.add(2, null);
        menu.add(3, p1);
        listPrint(menu,"Original List");
        menu.add(2, p1);
        listPrint(menu, "Added in the middle of the list");
        menu.set(2, p2);
        listPrint(menu, "Updated the third value");
        menu.remove(0);
        listPrint(menu, "Removed element in index 0");
        menu.remove(p2);
        listPrint(menu, "Removed first "+p2.getName()+" in list");
        System.out.println("Does the list contain p2 still? "+menu.contains(p2));
        System.out.println("Whats the index of p1? "+menu.indexOf(p1));
        menu.get(menu.indexOf(p1)).setName("Cookie");
        System.out.println("");
        listPrint(menu, "p2 was accessed by index and the name was replaced to cookie");
        try{menu.add(4, p2);}catch(Exception e){ System.out.println(e.toString()+"\n"); }
    }

    private static <T> void listPrint(List<T> o, String reason) {
        System.out.println(reason);
        int i = 0;
        for (T product : o) {
            System.out.println(i+" "+product);
            i++;
        }
        System.out.println("");
    }

    private static void createListObject() {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Set<Product> set1 = new HashSet<>();
        set1.add(p1);
        set1.add(p2);

        // ArrayList can be created using no-arg constructor, constructor with specific initial capacity or any other Collection to populate this list with initial values
        // And ArrayList can auto-expand its internal storage, when more elements are added to it.
        List<Product> list1 = new ArrayList<>();
        List<Product> list2 = new ArrayList<>(20);
        List<Product> list3 = new ArrayList<>(set1);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        // a fixed-size list can be created from the array using Arrays.asList(<T>...)
        List<Product> list4 = Arrays.asList(p1,p2);
        System.out.println(list4);
        // A read-only instance of List can be created using List.of(<T> ...)
        List<Product> list5 = List.of(p1,p2);
        System.out.println(list5);
    }

}
