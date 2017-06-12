package ru.otus.l21;

import java.io.IOException;
import java.util.HashSet;
import java.util.function.Supplier;

/**
 * Created by maximfirsov on 05/06/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Stand stand = new Stand();

        stand.calculate(new Supplier<Object>() {
            @Override
            public Object get() {
                return new Object();
            }
        });

        stand.calculate(new Supplier<Object>() {
            @Override
            public Object get() {
                return new String("");
            }
        });

        stand.calculate(new Supplier<Object>() {
            @Override
            public Object get() {
                return 0;
            }
        });

        stand.calculate(new Supplier<Object>() {
            @Override
            public Object get() {
                return new int[0];
            }
        });

        stand.calculate(new Supplier<Object>() {
            @Override
            public Object get() {
                return new HashSet();
            }
        });


        /*
        results (MacBookPro/10.12.5/Java 1.8.0_131):

            java.lang.Object, avg size: 8.0 bytes
            java.lang.String, avg size: 27.0 bytes
            java.lang.Integer, avg size: 3.0 bytes
            int[], avg size: 19.0 bytes
            java.util.HashSet, avg size: 67.0 bytes

        * */


    }
}
