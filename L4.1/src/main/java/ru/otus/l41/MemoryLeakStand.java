package ru.otus.l41;

import java.util.ArrayList;

/**
 * Created by maximfirsov on 28/06/2017.
 */
public class MemoryLeakStand {
    void run(int size) {

        int halfSize = size / 2;
        ArrayList<String> list = new ArrayList<>(size);

        while(true) {
            for (int i = 0; i < size; i++) {
                list.add(new String(new char[0]));
            }


            // remove
            for (int i = 0; i < halfSize; i++) {
                list.remove(i);
            }

            System.out.println("Created: " + size);
            System.out.println("Removed: " + halfSize);
            System.out.println("ArrayList: " + list.size());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
