package ru.otus.l91;
import ru.otus.l91.orm.data.UserDataSet;
import ru.otus.l91.orm.executor.Executor;
import ru.otus.l91.orm.helpers.ConnectionHelper;

import java.io.IOException;
import java.sql.Connection;

/**
 * Created by maximfirsov on 06/07/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("run");
        example();
    }

    static private void example(){
        Connection connection = ConnectionHelper.open();
        Executor executor = new Executor(connection);

        // SAVE example
        UserDataSet forSaveUDS = new UserDataSet("Vasya", 25);
        boolean result = executor.save(forSaveUDS);
        System.out.println("~ saved with result : " + result);

        // LOAD example
        UserDataSet forLoadUDS = executor.load(1, UserDataSet.class);
        System.out.println("~ loaded : " + forLoadUDS);

        // LOAD non-exist
        forLoadUDS = executor.load(-1, UserDataSet.class);
        System.out.println("~ loaded : " + forLoadUDS);
    }





}
