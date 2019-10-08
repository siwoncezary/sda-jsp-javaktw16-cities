package dao;

import java.util.List;
import java.lang.Integer;

public abstract class DaoList<T> implements Dao<T, Long> {
    protected List<T> list;

    public DaoList(List<T> list) {
        this.list = list;
    }

    @Override
    public void save(T obj) {
        list.add(obj);
    }

    @Override
    public void update(T obj, Long index) {
        list.set(Math.toIntExact(index), obj);
    }

    @Override
    public T delete(T obj) {
        T returnedObj = list.get(list.indexOf(obj));
        list.remove(obj);
        return returnedObj;
    }

    @Override
    public T select(Long index) {
        return list.get(Math.toIntExact(index));
    }
}
