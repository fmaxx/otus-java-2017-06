package ru.otus.l121.orm.database;

import ru.otus.l121.orm.cache.Cache;
import ru.otus.l121.orm.cache.CacheElement;
import ru.otus.l121.orm.cache.CacheImpl;
import ru.otus.l121.orm.data.UserDataSet;

import java.util.List;

public class DBServiceDecorator implements DBService {

    private final DBService service = new DBHibernateServiceImpl();
    private final Cache<Long, UserDataSet> cache = new CacheImpl<>(10, 10000, 5000, false);

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
        CacheElement<Long, UserDataSet> cached = cache.get(id);
        if(cached != null){
            System.out.println("!!!!EBA!!!!");
            return cached.getValue();
        }
        return service.read(id);
    }

    @Override
    public UserDataSet readByName(String name) {
        return service.readByName(name);
    }

    @Override
    public List<UserDataSet> readAll() {
        return service.readAll();
    }

    @Override
    public void shutdown() {
        service.shutdown();
    }
}
