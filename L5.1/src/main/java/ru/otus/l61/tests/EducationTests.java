package ru.otus.l61.tests;
import ru.otus.l61.framework.annotations.After;
import ru.otus.l61.framework.annotations.Before;
import ru.otus.l61.framework.annotations.Test;


/**
 * Created by maximfirsov on 06/07/2017.
 */
public class EducationTests {


    @Before
    public void setUp(){
        System.out.println("        @Before: setUp");
    }

    @After
    public void tearDown(){
        System.out.println("        @After: tearDown");
    }

    @Test
    public void test1(){
        System.out.println("            @Test: test1");
    }

    @Test
    public void test2(){
        System.out.println("            @Test: test2");
    }
}
