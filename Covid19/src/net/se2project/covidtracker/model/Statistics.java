package net.se2project.covidtracker.model;

public class Statistics {
    protected int id;
    protected String country_name;
    protected int total_cases;
    protected int new_cases;
    protected int total_death;
    protected int new_death;
    protected int total_recovered;
    protected int active_cases;
    protected int critical_cases;

    public Statistics() {
    }


    public Statistics(String country_name, int total_cases, int new_cases, int total_death,
                      int new_death, int total_recovered, int active_cases, int critical_cases ) {
        super();
        this.country_name = country_name;
        this.total_cases = total_cases;
        this.new_cases = new_cases;
        this.total_death = total_death;
        this.new_death = new_death;
        this.total_recovered = total_recovered;
        this.active_cases = active_cases;
        this.critical_cases = critical_cases;

    }

    public Statistics(int id, String country_name, int total_cases , int new_cases, int total_death,
                      int new_death, int total_recovered, int active_cases, int critical_cases ) {
        super();
        this.id = id;
        this.country_name = country_name;
        this.total_cases = total_cases;
        this.new_cases = new_cases;
        this.total_death = total_death;
        this.new_death = new_death;
        this.total_recovered = total_recovered;
        this.active_cases = active_cases;
        this.critical_cases = critical_cases;

    }

    public Statistics(int id, String country_name, int total_cases, int total_death, int total_recovered, int active_cases) {
        this.id = id;
        this.country_name = country_name;
        this.total_cases = total_cases;
        this.total_death = total_death;
        this.total_recovered = total_recovered;
        this.active_cases = active_cases;
    }

    public Statistics(String country_name, int total_cases, int total_death, int total_recovered, int active_cases) {
        this.country_name = country_name;
        this.total_cases = total_cases;
        this.total_death = total_death;
        this.total_recovered = total_recovered;
        this.active_cases = active_cases;
    }

    public int getId() {
        return id;
    }

    public void setCountry_id(int id) {
        this.id = id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(int total_cases) {
        this.total_cases = total_cases;
    }

    public int getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(int new_cases) {
        this.new_cases = new_cases;
    }

    public int getTotal_death() {
        return total_death;
    }

    public void setTotal_death(int total_death) {
        this.total_death = total_death;
    }

    public int getNew_death() {
        return new_death;
    }

    public void setNew_death(int new_death) {
        this.new_death = new_death;
    }

    public int getTotal_recovered() {
        return total_recovered;
    }

    public void setTotal_recovered(int total_recovered) {
        this.total_recovered = total_recovered;
    }

    public int getActive_cases() {
        return active_cases;
    }

    public void setActive_cases(int active_cases) {
        this.active_cases = active_cases;
    }

    public int getCritical_cases() {
        return critical_cases;
    }

    public void setCritical_cases(int critical_cases) {
        this.critical_cases = critical_cases;
    }


}

