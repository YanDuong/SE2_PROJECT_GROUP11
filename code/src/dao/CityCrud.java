package dao;
import java.util.List;
import model.City;
public interface CityCrud {
void addCity(City c);
boolean deleteCity(int cityId);
boolean updateCity(City city);
List<City> getAllCities();
City getCity(int id);
}
