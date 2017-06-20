package ru.otus.l31;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.function.Supplier;

/**
 * Created by maximfirsov on 05/06/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        // addAll tests
        System.out.println("addAll: ");
        MyArrayList<Integer> myList = new MyArrayList<>();
        myList.addAll(new ArrayList<>(Arrays.asList(1,2,3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,14)));
        System.out.println("    " + myList);

        myList.addAll(new ArrayList<>(Arrays.asList(15, 16)));
        System.out.println("    " + myList);

        myList.addAll(new ArrayList<>(Arrays.asList(17, 18, 19, 20, 21, 22,23)));
        System.out.println("    " + myList);
        System.out.println("    ");

        // copy tests
        System.out.println("copy: ");
        MyArrayList<Integer> destination = new MyArrayList<>();
        MyArrayList<Integer> source = new MyArrayList<>();
        source.addAll(Arrays.asList(1,2,3, 4, 5));
        System.out.println("    input: ");
        System.out.println("        destination:    " + Arrays.toString(destination.toArray()));
        System.out.println("        source:         " + Arrays.toString(source.toArray()));
        MyArrayList.copy(destination, source);
        System.out.println("    ouptut: ");
        System.out.println("        destination:    " + Arrays.toString(destination.toArray()));
        System.out.println("        source:         " + Arrays.toString(source.toArray()));
        System.out.println("    ");

        // sort tests
        System.out.println("sort: ");
        MyArrayList<Integer> sortList = new MyArrayList<>();
        sortList.addAll(new ArrayList<>(Arrays.asList(22, 1, 3, 2, 7, 5, 10, 4, 13, 9, 6, 11, 12, 8, 14, 14, 0)));
        System.out.println("    unsorted:   " + sortList);
        MyArrayList.sort(sortList, (o1, o2) -> o1.compareTo(o2));
        System.out.println("    sorted:     " + sortList);
        System.out.println("    ");


        // for output strings
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
