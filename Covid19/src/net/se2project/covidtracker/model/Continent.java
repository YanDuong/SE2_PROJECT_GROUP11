package net.se2project.covidtracker.model;

public class Continent extends Statistics{
    public Continent(String country_name, int total_cases, int new_cases, int total_death, int new_death, int total_recovered, int active_cases, int critical_cases) {
        super(country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases);
    }

    public Continent() {
    }

    public Continent(int id, String country_name, int total_cases, int new_cases, int total_death, int new_death, int total_recovered, int active_cases, int critical_cases) {
        super(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases);
    }
}
