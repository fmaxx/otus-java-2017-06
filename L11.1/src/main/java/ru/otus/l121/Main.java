package ru.otus.l121;



import ru.otus.l121.orm.data.AddressDataSet;
import ru.otus.l121.orm.data.PhoneDataSet;
import ru.otus.l121.orm.data.UserDataSet;
import ru.otus.l121.orm.database.DBService;
import ru.otus.l121.orm.database.DBServiceDecorator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by maximfirsov on 06/07/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("run");
        example();
    }

    static private void example(){

        DBService service = new DBServiceDecorator();

        String status = service.getLocalStatus();
        System.out.println("Status: " + status);

//        user_1
        UserDataSet user_1 = new UserDataSet("Max", 36, new AddressDataSet("Lenina", "Snezhinsk"));
        user_1.setPhones( new ArrayList<>(Arrays.asList(
                new PhoneDataSet("0123456789", user_1),
                new PhoneDataSet("9876543210", user_1)
        )));
        service.save(user_1);

//        user_2
        UserDataSet user_2 = new UserDataSet("Dan", 24, new AddressDataSet("Mira", "Yaroslavl"));
        user_2.setPhones( new ArrayList<>(Arrays.asList(
                new PhoneDataSet("33333333333", user_2),
                new PhoneDataSet("33333333333", user_2)
        )));
        service.save(user_2);

        // Read
        UserDataSet restored_1 = service.read(1);
        UserDataSet restored_2 = service.read(2);

        System.out.println("~~~ restored_1 : " + restored_1);
        System.out.println("~~~ restored_2 : " + restored_2);

        service.shutdown();
    }





}
