package mtr;

import java.util.LinkedHashSet;

/**
 * Represents a specific trainline in a rail network. Extends
 * {@linkLinkedHashSet}
 * 
 * 
 * @author Jawwad Choudhury
 * @author Hassan Hussain
 * @author Joseph Rolli
 * 
 */
public class TrainLine extends LinkedHashSet<String> {

	/**
	 * The {@link String} representing the name of the line.
	 */
	private String lineName;

	/**
	 * The {@link String} representing the name of the first station in the line.
	 */
	private String firstStation;

	/**
	 * The {@link String} representing the name of the last station in the line.
	 */
	private String lastStation;

	public TrainLine() {

	}

	/**
	 * Sets the name of the train line.
	 * 
	 * @param lineName
	 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
	 * Sets the name of the first station in the line.
	 * 
	 * @param firstStation
	 */
	public void setFirstStation(String firstStation) {
		this.firstStation = firstStation;
	}

	/**
	 * Sets the name of the last station in the line.
	 * 
	 * @param lastStation
	 */
	public void setLastStation(String lastStation) {
		this.lastStation = lastStation;
	}

	/**
	 * Retrieves the line name.
	 * 
	 * @return <code>String</code>
	 */
	public String getLineName() {
		return lineName;
	}

	/**
	 * Retrieves the name of the first station.
	 * 
	 * @return <code>String</code>
	 */
	public String getFirstStation() {
		return firstStation;
	}

	/**
	 * Retrieves the name of the last station.
	 * 
	 * @return <code>String</code>
	 */
	public String getLastStation() {
		return lastStation;
	}

	/**
	 * Retrieves the name representing this object..
	 * 
	 * @return <code>String</code>
	 */
	public String toString() {
		return getLineName();
	}

}
