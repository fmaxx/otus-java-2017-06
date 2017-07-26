package ru.otus.l61;

import ru.otus.l61.framework.Runner;
import ru.otus.l61.tests.EducationTests;

import java.io.IOException;

/**
 * Created by maximfirsov on 06/07/2017.
 *
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("--- run classes ---");
        Runner.run(new Class[]{EducationTests.class});

        System.out.println("--- run package ---");
        Runner.run("ru.otus.l61.tests");

    }
}
