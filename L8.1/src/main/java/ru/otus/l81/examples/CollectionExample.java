package ru.otus.l81.examples;

import java.util.ArrayList;

public class CollectionExample {
    public String id = "2.7";

    public ArrayList<BaseExample> collection = new ArrayList();

    public CollectionExample() {
        collection.add(new BaseExample());
        collection.add(new BaseExample());
    }
}
