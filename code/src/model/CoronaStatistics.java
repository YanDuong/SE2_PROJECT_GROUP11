package model;

public class CoronaStatistics {
	protected int id;
	protected String name;
	protected int totalOfInfected;
	protected int totalOfRecovered;
	protected int totalOfDeaths;
	protected int updatedInfected;
	protected int updatedRecovered;
	protected int updatedDeaths;
	private final static double RATE = 0;
	

	public CoronaStatistics(int id, String name, int totalOfInfected, int totalOfRecovered, int totalOfDeaths,
			int updatedInfected, int updatedRecovered,
			int updatedDeaths) {
		this.id = id;
		this.name = name;
		this.totalOfInfected = totalOfInfected;
		this.totalOfRecovered = totalOfRecovered;
		this.totalOfDeaths = totalOfDeaths;
		this.updatedInfected = updatedInfected;
		this.updatedRecovered= updatedRecovered;
		this.updatedDeaths = updatedDeaths;
		
	}
	
	public CoronaStatistics(String name, int totalOfInfected, int totalOfRecovered, int totalOfDeaths,
			int updatedInfected, int updatedRecovered,
			int updatedDeaths) {
		this.name = name;
		this.totalOfInfected = totalOfInfected;
		this.totalOfRecovered = totalOfRecovered;
		this.totalOfDeaths = totalOfDeaths;
		this.updatedInfected = updatedInfected;
		this.updatedRecovered= updatedRecovered;
		this.updatedDeaths = updatedDeaths;
		
	}
	
	public CoronaStatistics(int totalOfInfected, int totalOfRecovered, int totalOfDeaths,
			int updatedInfected, int updatedRecovered,
			int updatedDeaths) {
		this.totalOfInfected = totalOfInfected;
		this.totalOfRecovered = totalOfRecovered;
		this.totalOfDeaths = totalOfDeaths;
		this.updatedInfected = updatedInfected;
		this.updatedRecovered= updatedRecovered;
		this.updatedDeaths = updatedDeaths;
		
	}
public void setId(int id) {
	this.id = id;
}

public void setName(String name) {
	this.name = name;
}
	
	public void setTotalOfInfected(int totalOfInfected) {
		this.totalOfInfected = totalOfInfected;
	}
	
	public void setTotalOfRecovered(int totalOfRecovered) {
		this.totalOfRecovered = totalOfRecovered;
	}
	
	public void setTotalOfDeaths(int totalOfDeaths) {
		this.totalOfDeaths = totalOfDeaths;
	}
	
	public void setUpdatedRecovered(int updatedRecovered) {
		this.updatedRecovered = updatedRecovered;
	}
	
	public void setUpdatedInfected(int updatedInfected) {
		this.updatedInfected = updatedInfected;
	}
	
	public void setUpdatedDeaths(int updatedDeaths) {
		this.updatedDeaths = updatedDeaths;
	}
	
	
	
	public int getTotalOfInfected() {
		return totalOfInfected;
	}

	public int getTotalOfRecovered() {
		return totalOfRecovered;
	}

	public int getTotalOfDeaths() {
		return totalOfDeaths;
	}

	public int getUpdatedInfected() {
		return updatedInfected;
	}

	public int getUpdatedRecovered() {
		return updatedRecovered;
	}

	public int getUpdatedDeaths() {
		return updatedDeaths;
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	
	 
	
	  public double getInfectedRate() {
		  
		if(totalOfInfected <= updatedInfected) 
			return RATE;
		else 
			return Math.floor((updatedInfected * 100/ (totalOfInfected - updatedInfected)) * 100) / 100;
		}
	  
	  public double getRecoveredRate() {
		  if(totalOfRecovered <= updatedRecovered) 
			  return RATE;
		  else  
	  return Math.floor((updatedRecovered * 100/ (totalOfRecovered - updatedRecovered)) *
	  100) / 100;
	  }
	  
	  public double getDeathRate() { 
		  if(totalOfDeaths <= updatedDeaths) 
			  return RATE;
		  else  
	  return Math.floor((updatedDeaths * 100/ (totalOfDeaths- updatedDeaths)) *
	  100) / 100;
	  }
	 
	 
	
		
		
	}
	
	
	

