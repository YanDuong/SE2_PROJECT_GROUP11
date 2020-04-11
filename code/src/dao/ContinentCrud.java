package dao;
import java.util.List;
import model.Continent;
public interface ContinentCrud {
boolean updateContinent(Continent c);
Continent getContinent(int id);
List<Continent> getAllContinents();
int getTotalOfInfected();
int getTotalOfRecovered();
int getTotalOfDeaths();
int getUpdatedInfected();
int getUpdatedRecovered();
int getUpdatedDeaths();
}
