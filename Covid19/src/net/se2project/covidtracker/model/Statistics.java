package net.se2project.covidtracker.model;

public class Statistics {
    protected int id;
    protected String country_name;
    protected String total_cases;
    protected String new_cases;
    protected String total_death;
    protected String new_death;
    protected String total_recovered;
    protected String active_cases;
    protected String critical_cases;
    private final static double RATE = 0;


    public Statistics() {
    }
    public Statistics(String country_name, String total_cases, String new_cases, String total_death,
                   String new_death, String total_recovered, String active_cases, String critical_cases ) {
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

    public Statistics(int id, String country_name, String total_cases ,String new_cases, String total_death,
                   String new_death, String total_recovered, String active_cases, String critical_cases ) {
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

    public Statistics(int id, String country_name, String total_cases, String total_death, String total_recovered, String active_cases) {
        this.id = id;
        this.country_name = country_name;
        this.total_cases = total_cases;
        this.total_death = total_death;
        this.total_recovered = total_recovered;
        this.active_cases = active_cases;
    }

    public Statistics(String country_name, String total_cases, String total_death, String total_recovered, String active_cases) {
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

    public String getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(String total_cases) {
        this.total_cases = total_cases;
    }

    public String getNew_cases() {
        return new_cases;
    }

    public void setNew_cases(String new_cases) {
        this.new_cases = new_cases;
    }

    public String getTotal_death() {
        return total_death;
    }

    public void setTotal_death(String total_death) {
        this.total_death = total_death;
    }

    public String getNew_death() {
        return new_death;
    }

    public void setNew_death(String new_death) {
        this.new_death = new_death;
    }

    public String getTotal_recovered() {
        return total_recovered;
    }

    public void setTotal_recovered(String total_recovered) {
        this.total_recovered = total_recovered;
    }

    public String getActive_cases() {
        return active_cases;
    }

    public void setActive_cases(String active_cases) {
        this.active_cases = active_cases;
    }

    public String getCritical_cases() {
        return critical_cases;
    }

    public void setCritical_cases(String critical_cases) {
        this.critical_cases = critical_cases;
    }



    public double getInfectedRate() {

        total_cases = total_cases.replaceAll("[^\\d.]", "");
        int a = Integer.parseInt(total_cases);

        new_cases = new_cases.replaceAll("[^\\d.]", "");
        int b = Integer.parseInt(new_cases);

        if(a <= b)
            return RATE;
        else
            return Math.floor((b * 100/ (a - b)) * 100) / 100;
    }


    public double getDeathRate() {

        total_death = total_death.replaceAll("[^\\d.]", "");
        int a = Integer.parseInt(total_death);

        total_death = total_death.replaceAll("[^\\d.]", "");
        int b = Integer.parseInt(total_death);

        if(a <= b)
            return RATE;
        else
            return Math.floor((b * 100/ (a- b)) *
                    100) / 100;
    }
}

