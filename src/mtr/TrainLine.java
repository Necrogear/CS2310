package mtr;

import java.util.LinkedHashSet;
import java.util.HashSet;


public class TrainLine extends LinkedHashSet<String> {

	private String lineName;
	private String firstStation;
	private String lastStation;
	private HashSet<String> adjacentStations;

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
	
	public void addAdjacentStation(String station) {
		adjacentStations.add(station);
	}
	
	public String getAdjacentStations() {
		String stations = null;
		for (String station : adjacentStations)
			stations = station + " ";
		return stations;
	}
	
	public String toString() {
		return getLineName();
	}
	

}
