package F21SF_Assignment1;

/**
 * @author Tom Feraud
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a list of HotelRoom objects
 */
public class HotelRoomList {
	/**
	 * The number of views available in hour hotel
	 */
	private static final int numberView = 3;

	/**
	 * Our hotel rooms list
	 */
	private ArrayList<HotelRoom> hotelRoomList;

	/**
	 * Creates a new HotelRoomList
	 */
	public HotelRoomList() {
		hotelRoomList = new ArrayList<HotelRoom>();
	}

	/**
	 * Adds a HotelRoom object to our HotelRoomList It gets the room number of
	 * the object to be add Checks if a HotelRoom with this room number is
	 * already in the list Adds the HotelRoom if it is not in the list
	 * 
	 * @param hr
	 *            This is the HotelRoom that we want to add to the list
	 * 
	 * @return true if the object has been added
	 * @return false if the object has not been added
	 */
	public boolean addOneHotelRoom(HotelRoom hr) {
		String roomNumber = hr.getRoomNumber(); // Gets the room number
		HotelRoom inList = this.findByRoom(roomNumber);// Checks

		if (inList == null) {
			hotelRoomList.add(hr);
			return true;
		}
		return false;
	}

	/**
	 * Displays the details of all the HotelRoom of our HotelRoomList
	 * 
	 * @return text consisting of details of all the hotel rooms, formatted into
	 *         a table
	 */
	public String getTableOfHotelRooms() {
		String table = "N°	TYPE		VIEW  		 BEDS	 COST		COMMENTS\n";
		for (HotelRoom hr : hotelRoomList) {

			// If the type of the room is wrong
			if (hr.getTypeRoom().equals("error")) {
				System.err.println("type of the room mistaped for room number:" + hr.getRoomNumber()
						+ ". Please choice between : \"single\", \"double\", \"family\", \"presidential\"");
			} else {
				table += String.format("%-8s", hr.getRoomNumber());
				table += String.format("%-16s", hr.getTypeRoom());
				table += String.format("%-18s", hr.getRoomView());
				table += String.format("%-7d" + "£", hr.getTotalNumberBeds());
				table += String.format("%.2f", hr.getCost());
				if (hr.getInfoJacuzzi()) {
					table += String.format("%-10s", "	Jacuzzi");
				}
				table += "\n";
			}
		}
		return table;
	}

	/**
	 * Finds the HotelRoom corresponding to its number
	 * 
	 * @param roomNumber
	 *            is the number of the wanted HotelRoom
	 * @return the hotel room corresponding to the number in parameter
	 */
	public HotelRoom findByRoom(String roomNumber) {
		for (HotelRoom hr : hotelRoomList) {
			if (hr.getRoomNumber().equals(roomNumber)) {
				return hr;
			}
		}
		return null;
	}

	/**
	 * Gets the details of the HotelRoom corresponding to its number
	 * 
	 * @param roomNumber
	 *            is the number of the wanted HotelRoom
	 * @return a string giving full details of the HotelRoom
	 */
	String getFullDetailsSingleRoom(String roomNumber) {
		String fullDetailsSR;
		HotelRoom hr;
		hr = findByRoom(roomNumber);
		fullDetailsSR = hr.getFullDetails();
		return fullDetailsSR;
	}

	/**
	 * Calculates sum of the cost of each HotelRoom
	 * 
	 * @return the maximum income that the hotel could get per night
	 */
	public double getMaxIncomePerNight() {
		double income = 0;
		for (HotelRoom hr : hotelRoomList) {
			income += hr.getCost();
		}
		return income;
	}

	/**
	 * 
	 * @return the cost of the cheapest cabin
	 */
	public double getCheapestCost() {
		// We initialise a variable with a huge price in comparison to our room
		// so we are able to find the cheapest
		double cheapestCost = 999999;
		for (HotelRoom hr : hotelRoomList) {
			if (hr.getTypeRoom().equals("error")) {

			} else {
				double price = hr.getCost();
				if (price < cheapestCost) {
					cheapestCost = price;
				}
			}
		}
		return cheapestCost;
	}

	/**
	 * 
	 * @return the cost of the most expensive cabin
	 */
	public double getExpensiveCost() {
		double expensiveCost = 0;
		for (HotelRoom hr : hotelRoomList) {
			double price = hr.getCost();
			if (price > expensiveCost) {
				expensiveCost = price;
			}
		}
		return expensiveCost;
	}

	/**
	 * 
	 * @return the number of hotel rooms in the list
	 */
	public int getNumberOfHotelRooms() {
		return hotelRoomList.size();
	}

	/**
	 * Creates a frequency report of how much HotelRoom possesses each view
	 * 
	 * @return the frequency report
	 */
	public String getRoomViewFrequencyReport() {
		int[] freqView = new int[numberView];
		String[] views = { "street view", "garden view", "sea view" };
		for (HotelRoom hr : hotelRoomList) {
			String view = hr.getRoomView();
			if (view.equals(views[0])) { // if it is a street view
				freqView[0]++; // +1 room has a street view
			} else if (view.equals(views[1])) {
				freqView[1]++;
			} else {
				freqView[2]++;
			}
		}
		// create a report
		String report = "NUMBER OF ROOMS WITH EACH VIEW\n";

		for (int i = 0; i < freqView.length; i++) {
			report += String.format("%-11s :", views[i]);
			report += String.format("%d", freqView[i]);
			report += "\n";
		}
		return report;
	}

	/**
	 * Processes line, extracts data, creates HotelRoom object and adds it to
	 * the list Checks for missing items and incomplete data
	 * 
	 * @param line
	 *            the line to be processed
	 */
	private void processLine(String line) {
		try {
			String parts[] = line.split(",");
			HotelRoomOwner owner = new HotelRoomOwner(parts[1]);
			String room = parts[0];
			//If the room number is missing 
			if (room.equals("")) {
				String error = "The number of the room on the line:" + line + " is missing";
				System.err.println(error);
			} else {
				String typeRoom = parts[2];
				// create HotelRoom object and add it to the list
				HotelRoom hr = new HotelRoom(room, typeRoom, owner);
				this.addOneHotelRoom(hr);
			}

		}
		// for these two formatting errors, ignore lines in error and try and
		// carry on
		// this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '" + line + "'  - " + nfe.getMessage();
			System.err.println(error);
		}
		// this catches missing items if only one or two items
		// other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in line:" + line + " index position : " + air.getMessage();
			System.err.println(error);
		}
		// if the name is incomplete or absent in our .csv an error message is
		// displayed
		catch (StringIndexOutOfBoundsException e) {
			String error = "The name part of the line: " + line + " is incomplete. Please add the missing information";
			System.err.println(error);

		}

	}

	/**
	 * Reads file with a given name, extracting HotelRoom data, creating
	 * HotelRoom objects and adding them to the list of HotelRoom Blank lines
	 * are skipped Validation for missing items, data
	 * 
	 * @param filename
	 *            the name of the input file
	 */
	public void readFile(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				// read first line and process it
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {// ignored if blank line
					processLine(inputLine);
				}
			}
		}
		// if the file is not found, stop with system exit
		catch (FileNotFoundException fnf) {
			System.out.println(filename + " not found ");
			System.exit(0);
		}
	}

	/**
	 * writes supplied text to file
	 * 
	 * @param filename
	 *            the name of the file to be written to
	 * @param hrlist
	 *            the HotelRoom list used to get the statistics
	 */

	public void writeToFile(String filename, HotelRoomList hrlist) {

		FileWriter fWriter;
		try {
			fWriter = new FileWriter(filename);

			fWriter.write("-------------------------------------------------- \n");
			fWriter.write(hrlist.getRoomViewFrequencyReport());
			fWriter.write("-------------------------------------------------- \n");
			fWriter.write("Max income that the site could get per night : \n");
			fWriter.write(hrlist.getMaxIncomePerNight() + "\n");
			fWriter.write("-------------------------------------------------- \n");
			fWriter.write("Cost of the cheapest cabin : \n");
			fWriter.write(hrlist.getCheapestCost() + "\n");
			fWriter.write("-------------------------------------------------- \n");
			fWriter.write("Cost of the most expensive cabin : \n");
			fWriter.write(hrlist.getExpensiveCost() + "\n");
			fWriter.write("-------------------------------------------------- \n");
			fWriter.write("Number of hotel room : \n");
			fWriter.write(hrlist.getNumberOfHotelRooms() + "\n");

			fWriter.close();
		}
		// message and stop if file not found
		catch (FileNotFoundException fnf) {
			System.out.println(filename + " not found ");
			System.exit(0);
		}
		// stack trace here because we don't expect to come here
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}

}
