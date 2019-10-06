package repository;

import dao.CityDaoList;
import entity.CityBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class CityRepository {
    public static CityDaoList instance;
    private CityRepository(){

    }

    public static List<CityBean> loadCities(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        return reader.lines().map(line -> {
            CityBean city = new CityBean();
            String[] arr = line.split("\t");
            city.setId(Long.valueOf(arr[0]));
            city.setName(arr[1]);
            city.setLatitude(Double.valueOf(arr[4]));
            city.setLongitude(Double.valueOf(arr[5]));
            city.setCountryCode(arr[8]);
            city.setPopulation(Integer.valueOf(arr[14]));
            city.setTimeZone(TimeZone.getTimeZone(arr[17]));
            return city;
        }).collect(Collectors.toList());
    }
}
