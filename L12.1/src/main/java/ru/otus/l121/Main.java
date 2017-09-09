package ru.otus.l121;



import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import ru.otus.l121.orm.data.AddressDataSet;
import ru.otus.l121.orm.data.PhoneDataSet;
import ru.otus.l121.orm.data.UserDataSet;
import ru.otus.l121.orm.database.DBService;
import ru.otus.l121.orm.database.DBServiceDecorator;
import ru.otus.l121.web.servlets.DataServlet;
import ru.otus.l121.web.servlets.LoginServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws IOException {

        // credentials: admin / admin
        System.out.println("run");
        DBService service = setupDataBase();

        setupWebServer(service);
    }

    private static DBService setupDataBase() {
        // create a test admin (admin/admin) account in the runtime
        DBService service = new DBServiceDecorator();

        String status = service.getLocalStatus();
        System.out.println("Database status: " + status);

        UserDataSet admin = new UserDataSet("admin", "admin", 36,
                new AddressDataSet("Lenina", "Snezhinsk"));
                admin.setPhones( new ArrayList<>(Arrays.asList(
                    new PhoneDataSet("0123456789", admin),
                    new PhoneDataSet("9876543210", admin)
        )));
        service.save(admin);
        return  service;
    }

    private static void setupWebServer(DBService service) {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(new LoginServlet("login", service)), "/login");
        contextHandler.addServlet(DataServlet.class, "/data");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, contextHandler));

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
