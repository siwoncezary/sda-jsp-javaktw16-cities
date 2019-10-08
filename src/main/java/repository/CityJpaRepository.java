package repository;

import com.sun.istack.Nullable;
import dao.CityDao;
import dao.DaoJpa;
import entity.CityBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Stream;

public class CityJpaRepository extends DaoJpa<CityBean, Long> implements CityDao {

    public CityJpaRepository(EntityManagerFactory managerFactory) {
        super(managerFactory);
    }

    @Override
    public Stream<CityBean> findByCountryCode(String code) {
        return null;
    }

    @Override
    public Stream<CityBean> findAll() {
        return null;
    }

    @Override
    public List<String> findCountryCodes() {
        return null;
    }

    @Override
    public CityBean select(Long index) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        CityBean bean = em.find(CityBean.class, index);
        em.getTransaction().commit();
        return bean;
    }
}
