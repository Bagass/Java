package F21SF_Assignment1;

public class HotelRoomManager {
	private HotelRoomList allHotelRooms;

	// Constructor of HotelRoomManager, create a HotelRoomList object
	public HotelRoomManager() {
		allHotelRooms = new HotelRoomList();
	}

	public void run() {
		// Reads the file in parameter and fulfill our list with the data
		allHotelRooms.readFile("HotelRoomList.csv");
		String report = allHotelRooms.getTableOfHotelRooms();
		// Prints the details of all the hotel rooms of our list formatted into a
		// table
		System.out.println(report);
		//Writes into a text file the report and statistics of our HotelRoomList
		allHotelRooms.writeToFile("HotelRoomOut.txt", allHotelRooms);
	}
}
