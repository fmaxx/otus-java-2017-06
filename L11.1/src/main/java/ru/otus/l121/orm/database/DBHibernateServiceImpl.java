package ru.otus.l121.orm.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.l121.orm.ORMConfig;
import ru.otus.l121.orm.data.UserDataSet;
import ru.otus.l121.orm.executor.Executor;


import java.util.List;

public class DBHibernateServiceImpl implements DBService {

    private SessionFactory sessionFactory;
    private Executor executor;

    public DBHibernateServiceImpl() {
        sessionFactory = createSessionFactory(ORMConfig.Database.hibernateConfiguration);
        executor = new Executor(sessionFactory);
    }

    @Override
    public String getLocalStatus() {
        return executor.getLocalStatus();
    }

    @Override
    public long save(UserDataSet dataSet) {
        return executor.save(dataSet);
    }

    public UserDataSet read(long id) {
        return executor.read(id);
    }

    @Override
    public UserDataSet readByName(String name) {
        return null;
    }

    @Override
    public List<UserDataSet> readAll() {
        return null;
    }

    @Override
    public void shutdown() {
        if(sessionFactory != null && !sessionFactory.isClosed()){
            sessionFactory.close();
            sessionFactory = null;
        }

        if(executor != null){
            executor.dispose();
            executor = null;
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
