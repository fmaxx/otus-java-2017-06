package ru.otus.l131.orm.executor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.otus.l131.orm.dao.UserDataSetDAO;
import ru.otus.l131.orm.data.UserDataSet;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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


    public UserDataSet readByName(String name) {
        return runInSession(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<UserDataSet> criteriaQuery = criteriaBuilder.createQuery(UserDataSet.class);
            Root<UserDataSet> from = criteriaQuery.from(UserDataSet.class);
            criteriaQuery.where(criteriaBuilder.equal(from.get("name"), name));
            Query<UserDataSet> query = session.createQuery(criteriaQuery);
            return query.uniqueResult();
        });
    }

    public UserDataSet read(String name, String pass) {
        return runInSession(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<UserDataSet> criteriaQuery = criteriaBuilder.createQuery(UserDataSet.class);
            Root<UserDataSet> from = criteriaQuery.from(UserDataSet.class);
            criteriaQuery.where(criteriaBuilder.equal(from.get("name"), name))
                    .having(criteriaBuilder.equal(from.get("pass"), pass));

            Query<UserDataSet> query = session.createQuery(criteriaQuery);
            return query.uniqueResult();
        });
    }
}
