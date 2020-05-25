package net.se2project.covidtracker.model;

public class Chart {
    protected int id;
    protected String name;
    protected String date;
    protected String year;
    protected int cases;

    public Chart(String name, String date, String year, int cases) {
        this.name = name;
        this.date = date;
        this.year = year;
        this.cases = cases;
    }

    public Chart(int id, String name, String date, String year, int cases) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.year = year;
        this.cases = cases;
    }

    public Chart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }
}
