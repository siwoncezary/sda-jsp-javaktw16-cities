package dao;
import entity.CityBean;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CityDaoList extends DaoList<CityBean> {

    public CityDaoList(List<CityBean> list) {
        super(list);
    }

    public Stream<CityBean> findAll(){
        return list.stream();
    }

    public Stream<CityBean> findByCountryCode(String countryCode){
        return list.stream().filter(city-> city.getCountryCode().equals(countryCode));
    }

    public List<String> findCountryCodes(){
        return list.stream()
                .map(city -> city.getCountryCode())
                .distinct()
                .collect(Collectors.toList());
    }

}
