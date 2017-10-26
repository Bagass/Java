package F21SF_Assignment1;

public class HotelRoom {

	// Constants used to check the type of our HotelRoom
	static final String TYPEsingle = "single";
	static final String TYPEdouble = "double";
	static final String TYPEfamily = "family";
	static final String TYPEpresidential = "presidential";

	// Cnstance variables
	private String roomNumber;
	private String typeRoom;
	private HotelRoomOwner nameOwner;
	private int[] nbrOfEachBed;

	// Constructor of HotelRoom
	public HotelRoom(String room, String typeRoom, HotelRoomOwner nameOwner) {
		this.roomNumber = room;
		this.typeRoom = typeRoom.toLowerCase(); // to ensure the syntax
		this.nameOwner = nameOwner;
	}

	// Return the number of the room
	public String getRoomNumber() {
		return roomNumber;
	}

	// Sets the room's number
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	// Return the name of the owner of the room
	public String getNameOwner() {
		return nameOwner.getFullName();
	}

	// Return the type of the room
	public String getTypeRoom() {
		switch (this.typeRoom) {
		case (TYPEsingle):
			return TYPEsingle;
		case (TYPEdouble):
			return TYPEdouble;

		case (TYPEfamily):
			return TYPEfamily;

		case (TYPEpresidential):
			return TYPEpresidential;
		default:
			return "error";
		}
	}

	// Sets the room's type
	public void setTypeRoom(String typeRoom) {
		this.typeRoom = typeRoom;
	}

	// Get the number of each bed for a room
	public int[] getNbrOfEachBed() { // {simple,double,extra large}
		nbrOfEachBed = new int[3];
		switch (this.typeRoom) {
		case (TYPEsingle):
			nbrOfEachBed[0] = 1; // 1 simple
			nbrOfEachBed[1] = 0; // 0 double
			nbrOfEachBed[2] = 0; // 0 XL
			break;
		case (TYPEdouble):
			nbrOfEachBed[0] = 0;
			nbrOfEachBed[1] = 1;
			nbrOfEachBed[2] = 0;
			break;
		case (TYPEfamily):
			nbrOfEachBed[0] = 2;
			nbrOfEachBed[1] = 1;
			nbrOfEachBed[2] = 1;
			break;
		case (TYPEpresidential):
			nbrOfEachBed[0] = 1;
			nbrOfEachBed[1] = 2;
			nbrOfEachBed[2] = 2;
			break;
		default:
			nbrOfEachBed[0] = 0;
			nbrOfEachBed[1] = 0;
			nbrOfEachBed[2] = 0;
			break;
		}

		return nbrOfEachBed;
	}

	// Set the number of each bed
	public void setNbrOfEachBed(int[] nbrOfEachBed) {
		this.nbrOfEachBed = nbrOfEachBed;
	}

	// Get the number of single bed
	public int getNbrSingleBed() {
		return getNbrOfEachBed()[0];
	}

	// Get the number of double bed
	public int getNbrDoubleBed() {
		return getNbrOfEachBed()[1];
	}

	// Get the number of XL bed
	public int getNbrExtraLargeBed() {
		return getNbrOfEachBed()[2];
	}

	// Get the total number of beds
	public int getTotalNumberBeds() {
		int total;
		total = getNbrOfEachBed()[0] + getNbrOfEachBed()[1] + getNbrOfEachBed()[2];
		return total;
	}

	// Get the information if a room contains a jacuzzi
	// Here 2 rooms have a one
	public boolean getInfoJacuzzi() {
		if (this.typeRoom.equals(TYPEpresidential) && this.getRoomNumber().equals("14")) {
			return true;
		} else if (this.typeRoom.equals(TYPEfamily) && this.getRoomNumber().equals("13")) {
			return true;
		} else {
			return false;
		}

	}

	// Get the cost of a room in function of it's type,view & if there is a
	// jacuzzi or not
	public double getCost() {
		if (this.typeRoom.equals(TYPEsingle)) {
			if (this.getRoomView().equals("street view")) {
				return 50.0;
			} else if (this.getRoomView().equals("garden view")) {
				return 62.5;
			} else {
				return 75.5;
			}
		} else if (this.typeRoom.equals(TYPEdouble)) {
			if (this.getRoomView().equals("street view")) {
				return 69.99;
			} else if (this.getRoomView().equals("garden view")) {
				return 80.0;
			} else {
				return 100.0;
			}
		} else if (this.typeRoom.equals(TYPEfamily)) {
			if (this.getRoomView().equals("street view")) {
				return 85.99;
			} else if (this.getRoomView().equals("garden view")) {
				if (getInfoJacuzzi() == true) {
					return 150.0;
				} else {
					return 102.99;
				}
			} else {
				if (getInfoJacuzzi() == true) {
					return 270.0;
				} else {
					return 210.00;
				}
			}
		} else if (this.typeRoom.equals(TYPEpresidential)) {
			if (this.getRoomView().equals("street view")) {
				return 200.0;
			} else if (this.getRoomView().equals("garden view")) {
				if (getInfoJacuzzi() == true) {
					return 519.99;
				} else {
					return 370.0;
				}
			} else {
				if (getInfoJacuzzi() == true) {
					return 1000.0;
				} else {
					return 699.99;
				}
			}
		} else {
			return 0.0;
		}
	}

	// Get the room's view in function of the number of the room
	public String getRoomView() {
		// From room 1 to 4 (include)
		if (this.getRoomNumber().equals("1") || this.getRoomNumber().equals("2") || this.getRoomNumber().equals("3")
				|| this.getRoomNumber().equals("4")) {
			return "street view";
		} 
		// From room 5 to 8 (include)
		else if (this.getRoomNumber().equals("5") || this.getRoomNumber().equals("6")
				|| this.getRoomNumber().equals("7") || this.getRoomNumber().equals("8")) {
			return "garden view";
		} else {
			return "sea view";
		}
	}

	// Return a string containing all the details of a room
	public String getFullDetails() {
		String fullDetails;
		if (this.typeRoom.equals(TYPEfamily) || this.typeRoom.equals(TYPEpresidential)) {
			if (getInfoJacuzzi() == true) {
				fullDetails = "The room number " + getRoomNumber() + " is a " + getTypeRoom() + " with a "
						+ getRoomView() + " and " + getTotalNumberBeds() + " bed(s) :" + getNbrSingleBed() + " single, "
						+ getNbrDoubleBed() + " double & " + getNbrExtraLargeBed() + " XL " + "which has a jacuzzi "
						+ "at the price of " + getCost() + "£ attributed to " + getNameOwner() + ".";
			} else {
				fullDetails = "The room number " + getRoomNumber() + " is a " + getTypeRoom() + " with a "
						+ getRoomView() + " and " + getTotalNumberBeds() + " bed(s) :" + getNbrSingleBed() + " single, "
						+ getNbrDoubleBed() + " double & " + getNbrExtraLargeBed() + " XL " + "wich has no jacuzzi "
						+ "at the price of " + getCost() + "£ attributed to " + getNameOwner() + ".";
			}

		} else {
			fullDetails = "The room number " + getRoomNumber() + " is a " + getTypeRoom() + " with a " + getRoomView()
					+ " and " + getTotalNumberBeds() + " bed(s) :" + getNbrSingleBed() + " single, " + getNbrDoubleBed()
					+ " double & " + getNbrExtraLargeBed() + " XL " + "at the price of " + getCost()
					+ "£ attributed to " + getNameOwner() + ".";
		}

		return fullDetails;
	}

	// Return a string containing all the short details of a room
	String getShortDetails() {
		String shortDetails = getRoomNumber() + "; {" + getNbrSingleBed() + "," + getNbrDoubleBed() + ","
				+ getNbrExtraLargeBed() + "}; " + getCost() + "£; " + nameOwner.getInitials() + ".";
		return shortDetails;
	}

}