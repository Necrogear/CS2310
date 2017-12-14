package mtr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
		//Initialise hashmaps
		trainLines = new HashMap<String, TrainLine>();
		stationLinks = new HashMap<String, HashSet<TrainLine>>();
		adjHashMap = new HashMap<String, HashSet<TrainLine>>();
		
		//Read input file.
		try {
			processCsv(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setupAdjacentLines();
		tui = new TUI(this);
	}

	public static void main(String[] args) throws FileNotFoundException {
		// new InfoCenter();
		new InfoCenter(args[0]);

	}

	public void processCsv(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("src/" + file));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] lineArray = line.split(",");
			String lineName = lineArray[0];
			TrainLine stations = new TrainLine();
			stations.setLineName(lineName);
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
			trainLines.put(lineName, stations);
		}
		scanner.close();

	}

	@Override
	public String listAllTermini() {

		String output = "The termini in the MTR network are:\n\n";
		for (TrainLine line : trainLines.values()) {
			output = output + line.getLineName() + ": " + line.getFirstStation() + " <-> " + line.getLastStation()
					+ "\n";
		}

		return output;

	}

	@Override
	public String listStationsInLine(String line) {
		if (trainLines.get(line) != null) {
			String output = "The stations in line " + line + " are:\n";
			for (String station : trainLines.get(line)) {
				output = output + station + "\n";
			}
			return output;
		} else {
			return "Train Line not found, please try retyping";
		}

	}

	@Override
	public String listAllDirectlyConnectedLines(String line) {
		if (line != null) {
			String output = "";
			for (String line2 : adjHashMap.keySet()) {
				if (line.equals(line2)) {
					adjHashMap.get(line2).remove(trainLines.get(line2));
					output += adjHashMap.get(line2).toString();
				}
			}
			return output;
		} else {
			return "Train Line not found, please try retyping";
		}
	}

	private void setupAdjacentLines() {
		for (TrainLine line : trainLines.values()) {
			TrainLine line1 = trainLines.get(line.getLineName());
			HashSet<TrainLine> tls = new HashSet<TrainLine>();
			if (line1 != null) {
				String output = "The direct connected lines to " + line1.getLineName() + " are:\n";
				for (String stationName : line1) {
					for (TrainLine tl : stationLinks.get(stationName)) {
						tls.add(tl);
					}
				}
				tls.remove(trainLines.get(line));
				adjHashMap.put(line1.getLineName(), tls);
			}
		}
	}

	@Override
	public String showPathBetween(String stationA, String stationB) {

		return "PATH BETWEEN";
	}

}
