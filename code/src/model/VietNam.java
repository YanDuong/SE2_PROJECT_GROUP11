package model;
import java.util.List;



import java.util.ArrayList;

public class VietNam extends Country {

private List<City> cities;

public VietNam(String name, int totalOfInfected, int totalOfRecovered, int totalOfDeaths,
		int updatedInfected, int updatedRecovered, int updatedDeaths) {
	super(name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
	this.cities = new ArrayList<>();
}

@Override 
public int getTotalOfInfected() {
	for(int i = 0; i < cities.size(); i++) {
		totalOfInfected += cities.get(i).getTotalOfInfected();
	}
	return totalOfInfected;
}

@Override
public int getTotalOfRecovered() {
	for(int i = 0; i < cities.size(); i++) {
		totalOfRecovered += cities.get(i).getTotalOfRecovered();
	}
	return totalOfRecovered;
}

@Override
public int getTotalOfDeaths() {
	for(int i = 0; i < cities.size(); i++) {
		totalOfDeaths += cities.get(i).getTotalOfDeaths();
	}
	return totalOfDeaths;
}

@Override
public int getUpdatedInfected() {
	for(int i = 0; i < cities.size(); i++) {
		updatedInfected += cities.get(i).getUpdatedRecovered();
	}
	return updatedInfected;
}

@Override
public int getUpdatedRecovered() {
	for(int i = 0; i < cities.size(); i++) {
		updatedRecovered += cities.get(i).getUpdatedRecovered();
	}
	return updatedRecovered;
}

@Override
public int getUpdatedDeaths() {
	for(int i = 0; i < cities.size(); i++) {
		updatedDeaths += cities.get(i).getUpdatedDeaths();
	}
	return totalOfRecovered;
}


	







}
