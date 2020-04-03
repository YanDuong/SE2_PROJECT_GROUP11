package model;

public class Country extends CoronaStatistics {
	
public Country(int id, String name,  int totalOfInfected, int totalOfRecovered, int totalOfDeaths,
		int updatedInfected, int updatedRecovered, int updatedDeaths) {
	super(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
	
}

public Country(String name, int totalOfInfected, int totalOfRecovered, int totalOfDeaths,
		int updatedInfected, int updatedRecovered, int updatedDeaths) {
	super(name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
	
}
}