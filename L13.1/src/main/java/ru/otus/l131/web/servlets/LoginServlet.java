package ru.otus.l131.web.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.otus.l131.orm.cache.Cache;
import ru.otus.l131.orm.data.UserDataSet;
import ru.otus.l131.orm.database.DBService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 */
public class LoginServlet extends HttpServlet {

    public static final String LOGIN_PARAMETER_NAME = "login";
    public static final String PASS_PARAMETER_NAME = "pass";
    public static final String SERVICE_LABEL_PARAMETER_NAME = "serviceLabel";
    public static final String HINT_COUNT_PARAMETER_NAME = "hintCount";
    public static final String MISS_COUNT_PARAMETER_NAME = "missCount";



    private static final String LOGIN_VARIABLE_NAME = "login";
    private static final String PASS_VARIABLE_NAME = "pass";
    private static final String LOGIN_PAGE_TEMPLATE = "login.html";

    private String login;
    private DBService service;

    public LoginServlet(String login) {
        this.login = login;
    }

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        initAuto();
    }

    private void initAuto() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void init() {
        //TODO: Create one context for the application. Inject beans.
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        service = (DBService) context.getBean("databaseService");
    }

    private static String getLoginPage(String login) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(LOGIN_VARIABLE_NAME, login == null ? "" : login);
        return TemplateProcessor.instance().getPage(LOGIN_PAGE_TEMPLATE, pageVariables);
    }

    private static String getDataPage(String login,
                                      String pass,
                                      DBService service) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(LOGIN_VARIABLE_NAME, login == null ? "" : login);
        pageVariables.put(PASS_VARIABLE_NAME, pass == null ? "" : pass);

        // write cache data
        Cache<Long, UserDataSet> cache = service.getUserCache();
        System.out.println("cache.getHintCount() : " + cache.getHintCount());
        System.out.println("cache.getMissCount() : " + cache.getMissCount());
        pageVariables.put(HINT_COUNT_PARAMETER_NAME, cache.getHintCount());
        pageVariables.put(MISS_COUNT_PARAMETER_NAME, cache.getMissCount());
        pageVariables.put(SERVICE_LABEL_PARAMETER_NAME, service.getLabel());

        return TemplateProcessor.instance().getPage(DataServlet.PAGE_TEMPLATE, pageVariables);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String requestLogin = request.getParameter(LOGIN_PARAMETER_NAME);
        String requestPass = request.getParameter(PASS_PARAMETER_NAME);

        String page;
        if (requestLogin != null && requestPass != null) {

            // check user in the database
//            System.out.println("requestLogin : " + requestLogin);
//            System.out.println("requestPass : " + requestPass);
            UserDataSet userDataSet = service.read(requestLogin, requestPass);
//            System.out.println("userDataSet : " + userDataSet);

            if(userDataSet != null){
                saveToVariable(requestLogin);
                saveToSession(request, requestLogin); //request.getSession().getAttribute("login");
                saveToServlet(request, requestLogin); //request.getAttribute("login");
                saveToCookie(response, requestLogin); //request.getCookies();

                // go to the data page
                page = getDataPage(requestLogin, requestPass, service);

            }else{
                page = getLoginPage(requestLogin);
            }
        }else{
            page = getLoginPage(requestLogin);
        }

        response.getWriter().println(page);
        setOK(response);
    }

    private void saveToCookie(HttpServletResponse response, String requestLogin) {
        response.addCookie(new Cookie("L12.1-login", requestLogin));
    }

    private void saveToServlet(HttpServletRequest request, String requestLogin) {
        request.getServletContext().setAttribute("login", requestLogin);
    }

    private void saveToSession(HttpServletRequest request, String requestLogin) {
        request.getSession().setAttribute("login", requestLogin);
    }

    private void saveToVariable(String requestLogin) {
        login = requestLogin != null ? requestLogin : login;
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
