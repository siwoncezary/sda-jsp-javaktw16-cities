package dao;

public interface Dao<T, I> {
    void save(T obj);
    void update(T obj, I index);
    T delete(T obj);
    T select(I index);
}
