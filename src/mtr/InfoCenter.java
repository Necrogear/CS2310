package mtr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Collections;

public class InfoCenter implements Controller {

	private TUI tui;

	// Maps a Train Line name as a string to it's stations as a string array
	private HashMap<String, LinkedHashSet<String>> trainLine;

	private InfoCenter() {
		trainLine = new HashMap<String, LinkedHashSet<String>>();
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
			System.out.println(line);
			String[] lineArray = line.split(",");
			String lineName = lineArray[0];
			LinkedHashSet<String> stations = new LinkedHashSet<String>();

			for (int i = 1; i < lineArray.length; i++) {
				stations.add(lineArray[i]);

			}
			trainLine.put(lineName, stations);
		}
	}

	@Override
	public String listAllTermini() {

		String output = "The termini in the MTR network are:\n";

		for (LinkedHashSet<String> value : trainLine.values()) {
			output = output + value.;
			output = output + "<>";
			output = output + value[value.length - 1];
			output = output + "\n";

		}
		return output;

	}

	@Override
	public String listStationsInLine(String line) {
		if (trainLine.get(line) != null) {
			String output = "The stations in line " + line + " are:\n";
			for (String station : trainLine.get(line)) {
				output = output + station + "\n";
			}
			return output;
		} else {
			return "Train Line not found, please try retyping";
		}

	}

	@Override
	public String listAllDirectlyConnectedLines(String line) {
		if (trainLine.get(line) != null) {
			String output = "The direct connected lines to line:2" + line + " are:\n";
			String [] line1 = trainLine.get(line);
			String [] line2 = null;
			HashSet<String> hash = new HashSet<String>();
			for (String station : trainLine.get(line)) {
				hash.add(station);
			}
					
			for (String[] value : trainLine.values()) {
				line2 = value; 
				
			}
			return output;
		} else {
			return "Train Line not found, please try retyping";
		}
	}

	@Override
	public String showPathBetween(String stationA, String stationB) {
		// TODO Auto-generated method stub
		return "PATH BETWEEN";
	}
	
	public boolean linesConnect(String[] line1, String[] line2) {
		return false;
	}

}
