package dao;

public interface Dao<T, I> {
    I save(T obj);
    T update(T obj, I index);
    T delete(T obj);
    T select(I index);
}
