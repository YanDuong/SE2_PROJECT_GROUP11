package net.se2project.covidtracker.model;

public class Vietnam extends Statistics{
    public Vietnam(int id, String country_name, String total_cases, String active_cases, String total_recovered, String total_death) {
        super(id, country_name, total_cases, total_death, total_recovered, active_cases);
    }

    public Vietnam(String country_name, String total_cases, String active_cases, String total_recovered, String total_death) {
        super(country_name, total_cases, total_death, total_recovered, active_cases);
    }
}
