package ru.otus.l11.orm.executor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.otus.l11.orm.dao.UserDataSetDAO;
import ru.otus.l11.orm.data.UserDataSet;

import java.util.function.Function;

public class Executor {
    private SessionFactory sessionFactory;

    public Executor(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long save(UserDataSet user){
        try (Session session = sessionFactory.openSession()) {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.save(user);
        }catch (Error e){
            e.printStackTrace();
        }
        return -1;
    }

    public UserDataSet read(long id) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.read(id);
        });
    }

    public String getLocalStatus() {
        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }

    public void dispose() {
        sessionFactory = null;
    }

    private <R> R runInSession(Function<Session, R> function) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }


}
