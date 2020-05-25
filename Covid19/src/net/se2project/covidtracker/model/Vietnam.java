package net.se2project.covidtracker.model;

public class Vietnam extends Statistics{
    public Vietnam(int id, String country_name, int total_cases, int active_cases, int total_recovered, int total_death) {
        super(id, country_name, total_cases, total_death, total_recovered, active_cases);
    }

    public Vietnam(String country_name, int total_cases, int active_cases, int total_recovered, int total_death) {
        super(country_name, total_cases, total_death, total_recovered, active_cases);
    }
}
