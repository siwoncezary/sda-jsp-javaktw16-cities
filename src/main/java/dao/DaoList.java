package dao;

import java.util.List;
import java.lang.Integer;

public class DaoList<T> implements Dao<T, Integer> {
    protected List<T> list;

    public DaoList(List<T> list) {
        this.list = list;
    }

    @Override
    public Integer save(T obj) {
        list.add(obj);
        return  list.indexOf(obj);
    }

    @Override
    public T update(T obj, Integer index) {
        list.set(index, obj);
        return obj;
    }

    @Override
    public T delete(T obj) {
        T returnedObj = list.get(list.indexOf(obj));
        list.remove(obj);
        return returnedObj;
    }

    @Override
    public T select(Integer index) {
        return list.get(index);
    }
}
