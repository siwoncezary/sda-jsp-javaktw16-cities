package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class DaoJpa<T, I> implements Dao<T, I> {
    EntityManagerFactory managerFactory;

    public DaoJpa(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    @Override
    public void save(T obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        em.find(obj.getClass(),obj);
    }

    @Override
    public void update(T obj, I index) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    @Override
    public T delete(T obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        T returned = (T) em.find(obj.getClass(), obj);
        em.remove(obj);
        return returned;
    }

    @Override
    public abstract T select(I index);

    public EntityManager getEntityManager(){
        return managerFactory.createEntityManager();
    }
}
