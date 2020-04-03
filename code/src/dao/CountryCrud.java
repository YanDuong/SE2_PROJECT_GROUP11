package dao;

import java.util.List;
import model.Country;
public interface CountryCrud {
void addCountry(Country c);
boolean deleteCountry(int countryId);

boolean updateCountry(Country c);
public List<Country> getAllCountries();

}
