package ru.otus.l121.orm.database;

import ru.otus.l121.orm.cache.Cache;
import ru.otus.l121.orm.cache.CacheElement;
import ru.otus.l121.orm.cache.CacheImpl;
import ru.otus.l121.orm.data.UserDataSet;

import java.util.ArrayList;
import java.util.List;

public class DBServiceDecorator implements DBService {

    private final DBService service = new DBHibernateServiceImpl();
    private final Cache<Long, UserDataSet> cache = new CacheImpl<>(10, 300000, 300000, false);

    @Override
    public String getLocalStatus() {
        return service.getLocalStatus();
    }

    @Override
    public long save(UserDataSet dataSet) {
        long result = service.save(dataSet);
        cache.put(new CacheElement<>(result, dataSet));
        return result;
    }

    @Override
    public UserDataSet read(long id) {
        return getCached(id);
    }

    @Override
    public UserDataSet read(String name, String pass) {
        if(name == null || pass == null) return  null;
        List<UserDataSet> users = getCached(name);
        System.out.println("cached users : " + users);
        for (UserDataSet user : users) {
            if(user.getPass().equals(pass)){
                return  user;
            }
        }
        return service.read(name, pass);
    }

    private UserDataSet getCached(long id){
        CacheElement<Long, UserDataSet> cached = cache.get(id);
        if(cached != null){
            return cached.getValue();
        }
        return service.read(id);
    }

    private List<UserDataSet> getCached(String name){
        ArrayList<UserDataSet> results = new ArrayList();
        long id = -1;
        for (CacheElement<Long, UserDataSet> element : cache.allElements()) {
            UserDataSet userDataSet = element.getValue();
            if(userDataSet.getName().equals(name)){
                id = userDataSet.getId();
                results.add(service.read(id));
            }
        }
        return results;
    }

    @Override
    public List<UserDataSet> readAll() {
        return service.readAll();
    }

    @Override
    public void shutdown() {
        service.shutdown();
    }

    @Override
    public Cache<Long, UserDataSet> getUserCache() {
        return cache;
    }
}
