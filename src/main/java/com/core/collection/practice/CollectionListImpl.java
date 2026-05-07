package com.core.collection.practice;

import java.util.*;

public class CollectionListImpl {
    public static void main(String[] args) {

        List<String> al = new ArrayList<>();
        al.add("Rohit");
        al.add(1, "Diliprao");
        al.add(2, "Gulalkari");
        System.out.println(" Original Al Is :" + al);

        Collections.sort(al);
        System.out.println(" After Sorting  Al Is :" + al);

        al.sort(Comparator.reverseOrder());
        System.out.println(" Reversed Sorting  Al Is :" + al);

        al.remove(0);
        al.remove("Gulalkari");

        System.out.println(" After operations  Al Is :" + al);


        //LinkedList  ->Frequest add/Remove
        LinkedList<String> ll = new LinkedList<>();

        ll.add("Rohit");
        ll.add("Diliprao");
        ll.add("Gulalkari");
        ll.addFirst("Mr");
        ll.addLast("Jain");
        System.out.println(" Original LinkedList  Al Is :" + ll);

        String pf = ll.peekFirst();
        System.out.println(" After peekFirst  Al Is :" + pf +" List is "+ll);

        String pollf = ll.pollFirst();
        System.out.println(" After pollFirst  Al Is :" + pollf+" List is "+ll);

        String pollLast = ll.pollLast();
        System.out.println(" After pollLast  Al Is :" + pollLast+" List is "+ll);




    }

}
