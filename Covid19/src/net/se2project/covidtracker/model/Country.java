package net.se2project.covidtracker.model;


public class Country extends Statistics {
    public Country(String country_name, String total_cases, String new_cases, String total_death, String new_death, String total_recovered, String active_cases, String critical_cases) {
        super(country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases);
    }

    public Country(int id, String country_name, String total_cases, String new_cases, String total_death, String new_death, String total_recovered, String active_cases, String critical_cases) {
        super(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases);
    }

    public Country() {

    }
}
