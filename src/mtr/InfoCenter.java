package mtr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Represents an information center for a railway network, handling reading and
 * processing and output of data about the network.
 * 
 * @author Jawwad Choudhury
 * @author Hassan Hussein
 * @author Joseph Rolli
 * 
 */
public class InfoCenter implements Controller {

	/**
	 * The {@link TUI} that holds all this {@link Game}'s states.
	 */
	private TUI tui;

	/**
	 * The {@link HashMap} mapping the names of trainlines to their respective train
	 * line.
	 */
	private HashMap<String, TrainLine> trainLines;

	/**
	 * The {@link HashMap} mapping the stations in the network to the train lines
	 * they are found in, stored in a {@link HashSet}.
	 */
	private HashMap<String, HashSet<TrainLine>> stationLinks;

	/**
	 * The {@link HashMap} mapping a given line in the network to every line
	 * connected directly to it, stored in a {@link HashSet}.
	 */
	private HashMap<String, HashSet<TrainLine>> adjHashMap;

	private InfoCenter(String file) {
		// Initialise hashmaps
		trainLines = new HashMap<String, TrainLine>();
		stationLinks = new HashMap<String, HashSet<TrainLine>>();
		adjHashMap = new HashMap<String, HashSet<TrainLine>>();

		// Read input file.
		try {
			processCsv(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Calculate adjacent lines
		setupAdjacentLines();
		// Initialise console interface
		tui = new TUI(this);
	}

	/**
	 * The main for starting up this application. This application expects a single
	 * argument as a string representing the name of the input file. This .csv data
	 * file stores the lines and stations in the train network.
	 * 
	 * @param args
	 *            input argument(s)
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// new InfoCenter();
		new InfoCenter(args[0]);

	}

	/**
	 * Makes use of a {@link Scanner} to process the csv file representing the rail
	 * network.
	 * 
	 * 
	 * @param file
	 *            <code>String</code> the input file.
	 */
	public void processCsv(String file) throws FileNotFoundException {
		// Initialise scanner and parse file line by line.
		Scanner scanner = new Scanner(new File("src/" + file));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			// Input line stored as String array, split by commas.
			String[] lineArray = line.split(",");
			String lineName = lineArray[0];
			TrainLine stations = new TrainLine();
			stations.setLineName(lineName);
			// Seperates stations in parsed array from the line name, and adds to trainline.
			// Also sets first and last station.
			for (int i = 1; i < lineArray.length; i++) {
				HashSet<TrainLine> linesSet = new HashSet<TrainLine>();
				stations.add(lineArray[i]);

				if (i == 1) {
					stations.setFirstStation(lineArray[i]);
				}
				if (i == lineArray.length - 1) {
					stations.setLastStation(lineArray[i]);
				}

				if (!stationLinks.containsKey(lineArray[i])) {
					stationLinks.put(lineArray[i], new HashSet<TrainLine>());
					stationLinks.get(lineArray[i]).add(stations);
				} else {
					stationLinks.get(lineArray[i]).add(stations);
				}

			}
			// Adds train lines to trainLine hashmap.
			trainLines.put(lineName, stations);
		}
		scanner.close();

	}

	/**
	 * Processes the current network to identify the lines that directly connect to each line. The method
	 * then stores these in a {@link HashMap}, where the key is the {@link TrainLine}, and the value
	 * is a {@link hashSet} of the {@link TrainLine}s that connect to it.
	 * 
	 */
	private void setupAdjacentLines() {
		for (TrainLine line : trainLines.values()) {
			TrainLine line1 = trainLines.get(line.getLineName());
			HashSet<TrainLine> trainLineSet = new HashSet<TrainLine>();
			if (line1 != null) {
				String output = "The direct connected lines to " + line1.getLineName() + " are:\n";
				for (String stationName : line1) {
					for (TrainLine tl : stationLinks.get(stationName)) {
						trainLineSet.add(tl);
					}
				}
				trainLineSet.remove(trainLines.get(line));
				adjHashMap.put(line1.getLineName(), trainLineSet);
			}
		}
	}

	
	/**
	 * Retrives as a string the stations that are termini of the {@link TrainLine}s in the network.
	 * Formatted for output to console. 
	 * 
	 * @return <code>String</code>
	 */
	@Override
	public String listAllTermini() {

		String output = "The termini in the MTR network are:\n\n";
		for (TrainLine line : trainLines.values()) {
			output = output + line.getLineName() + ": " + line.getFirstStation() + " <-> " + line.getLastStation()
					+ "\n";
		}

		return output;

	}

	/**
	 * Retrives as a string the stations in a given line, in their physical order.
	 * Formatted for output to console. 
	 * 
	 * @return <code>String</code>
	 * @param line
	 */
	@Override
	public String listStationsInLine(String line) {
		if (trainLines.get(line) != null) {
			String output = "The stations in line " + line + " are:\n";
			for (String station : trainLines.get(line)) {
				output = output + station + "\n";
			}
			return output;
		} else {
			return "Train Line not found, please try retyping.";
		}

	}

	/**
	 * Retrives as a string the {@link TrainLine}s that are directly connected to the specified {@link TrainLine}.
	 * Formatted for output to console. 
	 * 
	 * @return <code>String</code>
	 * @param line
	 */
	@Override
	public String listAllDirectlyConnectedLines(String line) {
		if (adjHashMap.get(line) != null) {
			String output = "The lines connected to " + line + " are:\n";
			adjHashMap.get(line).remove(trainLines.get(line));
			output += adjHashMap.get(line).toString();

			return output;
		} else {
			return "Train Line not found, please try retyping.";
		}
	}


	@Override
	public String showPathBetween(String stationA, String stationB) {

		return "PATH BETWEEN";
	}

}
