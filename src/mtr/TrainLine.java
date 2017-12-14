package mtr;

import java.util.LinkedHashSet;

public class TrainLine extends LinkedHashSet<String>{

	private String lineName;
	private String firstStation;
	private String lastStation;
	
	public TrainLine() {
		
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getFirstStation() {
		return firstStation;
	}

	public void setFirstStation(String firstStation) {
		this.firstStation = firstStation;
	}

	public String getLastStation() {
		return lastStation;
	}

	public void setLastStation(String lastStation) {
		this.lastStation = lastStation;
	}
	

}
