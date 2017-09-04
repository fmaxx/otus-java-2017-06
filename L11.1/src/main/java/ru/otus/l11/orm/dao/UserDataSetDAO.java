package ru.otus.l11.orm.dao;
import org.hibernate.Session;
import ru.otus.l11.orm.data.UserDataSet;

public class UserDataSetDAO {

    private Session session;

    public UserDataSetDAO (Session session) {
        this.session = session;
    }

    public long save(UserDataSet userDataSet){
        return (long) session.save(userDataSet);
    }

    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }

}
