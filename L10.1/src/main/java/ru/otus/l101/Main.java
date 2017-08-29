package ru.otus.l101;
import ru.otus.l101.orm.data.PhoneDataSet;
import ru.otus.l101.orm.data.UserDataSet;
import ru.otus.l101.orm.database.DBService;
import ru.otus.l101.orm.database.DBServiceImpl;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by maximfirsov on 06/07/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("run");
        example();
    }

    static private void example(){

        DBService service = new DBServiceImpl();

        String status = service.getLocalStatus();
        System.out.println("Status: " + status);

        service.save(new UserDataSet("Max", 36, new ArrayList<PhoneDataSet>(){{
            new PhoneDataSet("0123456789");
            new PhoneDataSet("9876543210");
        }}));

       /* service.save(new UserDataSet("Dan", 24, new ArrayList<PhoneDataSet>(){{
            new PhoneDataSet("123123132");
            new PhoneDataSet("456456465");
        }}));*/


//        service.save(new UserDataSet("sully", new PhoneDataSet("67890")));

       /* Connection connection = ConnectionHelper.open();
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
        System.out.println("~ loaded : " + forLoadUDS);*/
    }





}
