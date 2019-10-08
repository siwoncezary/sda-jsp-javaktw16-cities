package dao;

import entity.CityBean;

import java.util.List;
import java.util.stream.Stream;

public interface  CityDao extends Dao<CityBean, Long>{
    Stream<CityBean> findByCountryCode(String code);
    Stream<CityBean> findAll();
    List<String> findCountryCodes();
}
