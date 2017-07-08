package ru.otus.l51.framework;

import ru.otus.l51.framework.annotations.After;
import ru.otus.l51.framework.annotations.Before;
import ru.otus.l51.framework.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by maximfirsov on 06/07/2017.
 */
public final class Runner {
    private Runner(){

    }

    public static void run(Class<?>[] classes) {
        for (Class<?> cl : classes){
            System.out.println("run: " + cl.getName());

            List<Method> tests = ReflectionHelper.getMethodsByAnnotation(cl, Test.class);

            if(tests.size() == 0){
                System.out.println("    tests not found");
                continue;
            }

            List<Method> before = ReflectionHelper.getMethodsByAnnotation(cl, Before.class);
            List<Method> after = ReflectionHelper.getMethodsByAnnotation(cl, After.class);

            System.out.println("    before: " + before.size());
            System.out.println("    tests: " + tests.size());
            System.out.println("    after: " + after.size());

            for(Method test : tests){

                Object instance = ReflectionHelper.instantiate(cl);

                // run before
                for(Method beforeMethod : before){
                    ReflectionHelper.callMethod(instance, beforeMethod.getName());
                }

                // run the test
                ReflectionHelper.callMethod(instance, test.getName());

                // run after
                for(Method afterMethod : after){
                    ReflectionHelper.callMethod(instance, afterMethod.getName());
                }
            }
        }
    }

    public static void run(String packageName) {
        Class[] classes = ReflectionHelper.getAllClassesInPackage(packageName);
        run(classes);
    }
}
