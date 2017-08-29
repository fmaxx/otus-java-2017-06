package ru.otus.l101.orm;
import org.hibernate.cfg.Configuration;
import ru.otus.l101.orm.data.AddressDataSet;
import ru.otus.l101.orm.data.PhoneDataSet;
import ru.otus.l101.orm.data.UserDataSet;

public class ORMConfig {

    static public class Database{
        public static final String NAME = "homework_10_1";

        public static final Configuration hibernateConfiguration;

        static {
            hibernateConfiguration = new Configuration();

//            hibernateConfiguration.addAnnotatedClass(AddressDataSet.class);
//            hibernateConfiguration.addAnnotatedClass(PhoneDataSet.class);
            hibernateConfiguration.addAnnotatedClass(UserDataSet.class);

            hibernateConfiguration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            hibernateConfiguration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            hibernateConfiguration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + NAME);
            hibernateConfiguration.setProperty("hibernate.connection.username", "maxim");
            hibernateConfiguration.setProperty("hibernate.connection.password", "maxim");
            hibernateConfiguration.setProperty("hibernate.show_sql", "true");
            hibernateConfiguration.setProperty("hibernate.hbm2ddl.auto", "create");
            hibernateConfiguration.setProperty("hibernate.connection.useSSL", "false");
//            hibernateConfiguration.setProperty("hibernate.connection.autocommit", "true");
            hibernateConfiguration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
            hibernateConfiguration.setProperty("hibernate.jdbc.time_zone", "UTC");
        }
    }

    static public class Tables{
        public static final String USERS = "users";
        public static final String ADDRESSES = "addresses";
        public static final String PHONES = "phones";
    }


}
