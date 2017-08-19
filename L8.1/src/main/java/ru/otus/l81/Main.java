package ru.otus.l81;

import ru.otus.l81.examples.ArrayExample;
import ru.otus.l81.examples.BaseExample;
import ru.otus.l81.examples.CollectionExample;

import java.io.IOException;

/**
 * Created by maximfirsov on 06/07/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("BaseExample : " + JsonHelper.toJSONString(new BaseExample()));
        System.out.println("ArrayExample : " + JsonHelper.toJSONString(new ArrayExample()));
        System.out.println("CollectionExample : " + JsonHelper.toJSONString(new CollectionExample()));
    }





}
