package autostudy.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    }

    private static void manageHashMapObject() {
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
        printList(menu,"Original List");
        menu.add(2, p1);
        printList(menu, "Added in the middle of the list");
        menu.set(2, p2);
        printList(menu, "Updated the third value");
        menu.remove(0);
        printList(menu, "Removed element in index 0");
        menu.remove(p2);
        printList(menu, "Removed first "+p2.getName()+" in list");
        System.out.println("Does the list contain p2 still? "+menu.contains(p2));
        System.out.println("Whats the index of p1? "+menu.indexOf(p1));
        menu.get(menu.indexOf(p1)).setName("Cookie");
        System.out.println("");
        printList(menu, "p2 was accessed by index and the name was replaced to cookie");
        try{menu.add(4, p2);}catch(Exception e){ System.out.println(e.toString()+"\n"); }
    }

    private static <T> void printList(List<T> o, String reason) {
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
