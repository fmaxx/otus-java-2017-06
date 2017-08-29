package ru.otus.l101.orm.dao;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;
import ru.otus.l101.orm.data.UserDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class UserDataSetDAO {

    private Session session;

    public UserDataSetDAO (Session session) {
        this.session = session;
    }

    public void save(UserDataSet userDataSet){
        Serializable id = session.save(userDataSet);
//        SessionImpl impl = (SessionImpl) session;
        System.out.println("save id : " + id + ", "+((SessionImpl) session).isClosed());
    }

}
