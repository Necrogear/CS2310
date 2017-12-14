package mtr;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashSet;

public class InfoCenter implements Controller {

	private TUI tui;

	// Maps a Train Line name as a string to it's stations as a string array
	private HashMap<String, TrainLine> trainLines;

	// Maps a Station to the TrainLines it can be found in,
	private HashMap<String, HashSet<TrainLine>> stationLinks;

	private InfoCenter() {
		trainLines = new HashMap<String, TrainLine>();
		stationLinks = new HashMap<String, HashSet<TrainLine>>();
		try {
			processCsv();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tui = new TUI(this);

	}

	public static void main(String[] args) throws FileNotFoundException {
		// new InfoCenter();
		new InfoCenter();

	}

	public void processCsv() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("src/MTRsystem_partial.csv"));
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
		
		TrainLine line1 = trainLines.get(line);
		if (trainLines.get(line) != null) {
			String output = "The direct connected lines to line:2" + line + " are:\n";
			for (String stationName : trainLines.get(line)) {
				output = output + stationLinks.get(stationName);
				}
			return output;
		} else {
			return "Train Line not found, please try retyping";
		}
	}

	@Override
	public String showPathBetween(String stationA, String stationB) {

		return "PATH BETWEEN";
	}

}
