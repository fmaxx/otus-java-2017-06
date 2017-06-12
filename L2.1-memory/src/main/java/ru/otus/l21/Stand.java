package ru.otus.l21;

import java.util.function.Supplier;

/**
 * Created by maximfirsov on 12/06/2017.
 */
public class Stand {

    public void calculate(Supplier<Object> supplier) {

        int numCycles = 0;
        int maxCycles = 1_000;

        clean();

        long memory = currentMemory();
        Object[] objects = new Object[maxCycles];

        while (numCycles < maxCycles) {
            objects[numCycles] = supplier.get();
            numCycles++;
        }

        clean();

        float average = (currentMemory() - memory)/maxCycles;
        String className = supplier.get().getClass().getCanonicalName();
        System.out.println(className + ", avg size: " + average + " bytes");
    }

    private long currentMemory(){
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private void clean() {
        System.gc();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
