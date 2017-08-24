package ru.otus.l91.orm.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    // reusable instance
    private static Connection sharedConnection = null;

    public static Connection open() {
        if(isClosed()){
            sharedConnection = null;
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

                String url = "jdbc:mysql://" +       //db type
                        "localhost:" +               //host name
                        "3306/" +                    //port
                        "howework91?" +              //db name
                        "useSSL=false&" +            //do not use ssl
                        "user=maxim&" +              //login
                        "password=maxim";            //password
                sharedConnection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return sharedConnection;
    }

    public static boolean isClosed() {
        if(sharedConnection == null) return true;
        try {
            return  sharedConnection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void close(){
        if(sharedConnection != null){
            try {
                sharedConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sharedConnection = null;
        }
    }
}
