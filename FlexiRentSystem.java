import java.util.Scanner;

public class FlexiRentSystem {

	RentalProperties[] rps = new RentalProperties[50];// contains both Apartment and PremiumSuite

	Scanner sc = new Scanner(System.in);

	public void addProperty() {
		Apartment ap = new Apartment();
		PremiumSuite p = new PremiumSuite();

		String id = "";
		int noOfBeds = 0;

		System.out.println("Enter property Id:");
		id = sc.nextLine();
		Character a = id.charAt(0);
		String FirstIndexofId = a.toString();
		if (!(FirstIndexofId.equals("A") || FirstIndexofId.equals("S"))) {
			System.out.println("Please enter an ID begins with 'A' or 'S'!");
			System.out.println("\t");
			showMenu();
		}
		System.out.println();
		System.out.println("Enter Street Number:");

		String sNo = sc.nextLine();

		// to see whether the input String is all int
		for (int s = 0; s < sNo.length(); s++) {
			char sbs = sNo.charAt(s);
			if (Character.isDigit(sbs) == false) {
				System.out.println("Please enter numbers!\n");
				showMenu();
			}
		}

		System.out.println("Enter Street Name:");
		String sName = sc.nextLine();
		System.out.println("Enter Suberb:");
		String suberb = sc.nextLine();

		if (FirstIndexofId.equals("A")) {
			System.out.println("Enter number of bedrooms:");
			noOfBeds = sc.nextInt();
			sc.nextLine();
		}
		// catch blank line
		// if not int then show error msg

		System.out.println("Enter property type:");
		String type = sc.nextLine();
		if (FirstIndexofId.equals("A") && type.equals("Apartment")) {
			ap = new Apartment(id, sNo, sName, suberb, noOfBeds, type);
		} else if (FirstIndexofId.equals("S") && type.equals("PremiumSuite")) {
			p = new PremiumSuite(id, sNo, sName, suberb, noOfBeds, type);
		} else {
			System.out.println("Please enter a valid property type!");
			showMenu();
		}

		System.out.println("Enter property status:");
		String status = sc.nextLine();
		if (!(status.equals("Available"))) {
			System.out.println("Only ‘Available’ is accepted !");
			showMenu();
		}

		for (int i = rps.length - 1; i > 0; i--) {
			rps[i] = rps[i - 1];
		}
		if (type.equals("Apartment")) {
			rps[0] = ap;
		} else if (type.equals("PremiumSuite")) {
			rps[0] = p;
		}
		rps[0].setPropertyStatus(status);
		displayAllProperties();

	}

	public void showMenu() {
		System.out.println("**** FLEXIRENT SYSTEM MENU ****");
		System.out.println("Add Property: " + "          " + "1");
		System.out.println("Rent Property: " + "         " + "2");
		System.out.println("Return Property:" + "        " + "3");
		System.out.println("Property Maintenance:" + "   " + "4");
		System.out.println("Complete Maintenance:" + "   " + "5");
		System.out.println("Display All Properties:" + " " + "6");
		System.out.println("Exit Program:" + "           " + "7");
		System.out.println("Enter your choice:");
		int sc1 = sc.nextInt();
		sc.nextLine();
		while (sc1 > 7 || sc1 == 0) {
			System.out.println("Outside the Range!");
			System.out.println("**** FLEXIRENT SYSTEM MENU ****");
			System.out.println("Add Property: " + "          " + "1");
			System.out.println("Rent Property: " + "         " + "2");
			System.out.println("Return Property:" + "        " + "3");
			System.out.println("Property Maintenance:" + "   " + "4");
			System.out.println("Complete Maintenance:" + "   " + "5");
			System.out.println("Display All Properties:" + " " + "6");
			System.out.println("Exit Program:" + "           " + "7");
			System.out.println("Enter your choice:");
			sc1 = sc.nextInt();
			sc.nextLine();
		}
		if (sc1 == 1) {
			addProperty();
		} else if (sc1 == 2) {
			rentProperty();
		} else if (sc1 == 3) {
			returnProperty();
		} else if (sc1 == 4) {
			propertyMaintenance();
		} else if (sc1 == 5) {
			completeMaintenance();
		} else if (sc1 == 6) {
			displayAllProperties();
		} else if (sc1 == 7) {
			exitProgram();
		}
	}

	public void exitProgram() {
		System.out.println("Thank you!");
		System.exit(7);
	}

	// see if user input a propertyID that has already stored in rps[]
	public boolean validateEnterPropertyId(String pid) {
		boolean index = false;
		if (rps == null) {//if rps[50] is empty
			index = false;
		} else { 
			for (int i = 0; i < rps.length; i++) {
				if (!(rps[i] == null)) {// if not null
					if (rps[i].getPropertyId().equals(pid)) {// find the No in the rps[] and get the No
						setFoundedPropertyId(i);
						index = true;
					} else {
						continue;
					}
				} else {
					break; 
				}
			}
		}
		return index;
	}

	int _i;

	public void setFoundedPropertyId(int i) {
		_i = i;
	}

	// return found index
	public int getFoundedPropertyId(String pid) {
		return _i;
	}

	public void rentProperty() {
		System.out.println("Enter property id:");
		String pid = sc.nextLine();
		boolean test = validateEnterPropertyId(pid);
		if (test == false) { // no such property id
			System.out.println("The id does not exist!");
			displayAllProperties();
		} else {
			if (rps[getFoundedPropertyId(pid)].getPropertyStatus().equals("Available")) {
				System.out.println("Customer id:");
				String cid = sc.nextLine();

				String rd = "";
				System.out.println("Rent Date (dd/mm/yyyy):");
				rd = sc.nextLine();
				DateTime rentDate = DateTime.stringToDateTime(rd);// rentDate，input String -> DateTime
				DateTime cdt = DateTime.stringToDateTime(DateTime.getCurrentTime());// current time
				if (DateTime.diffDays(rentDate, cdt) < 0) {// rent before current time
					System.out.println("Please enter a valid date!");
					displayAllProperties();
				}

				System.out.println("How many days?");
				int numOfRentDay = sc.nextInt();
				sc.nextLine();

				if (rps[getFoundedPropertyId(pid)].rent(cid, rentDate, numOfRentDay) == true) { // rent method from
																								// Apartment or
																								// PremiumSuite
					rps[getFoundedPropertyId(pid)].setCustomerId(cid);
					rps[getFoundedPropertyId(pid)].setPropertyStatus("rented");
					String rid = rps[getFoundedPropertyId(pid)].constructRecordId(rentDate);
					DateTime dt = new DateTime(rentDate, numOfRentDay);
					RentalRecords rr = new RentalRecords(rid, rentDate, dt);
					rps[getFoundedPropertyId(pid)].addRentalRecords(rr);

					System.out.println(rps[getFoundedPropertyId(pid)].getPropertyType() + " "
							+ rps[getFoundedPropertyId(pid)].getPropertyId() + " " + "is now rented by customer "
							+ cid);
					displayAllProperties();
				} else {
					System.out.println(rps[getFoundedPropertyId(pid)].getPropertyType() + " " + "could not be rented");
					displayAllProperties();
				}
			} else {
				System.out.println(rps[getFoundedPropertyId(pid)].getPropertyType() + " " + "could not be rented");
				displayAllProperties();
			}
		}
	}

	public void returnProperty() {
		System.out.println("Enter property id:");
		String pid = sc.nextLine();

		if (validateEnterPropertyId(pid) == false) {
			System.out.println("The id does not exist!");
			displayAllProperties();
		} else {
			if (rps[getFoundedPropertyId(pid)].getPropertyStatus().equals("rented")) {
				System.out.println("Return Date (dd/mm/yyyy):");
				String returndate = sc.nextLine();
				DateTime redate = DateTime.stringToDateTime(returndate); // return date string->DateTime
				if (rps[getFoundedPropertyId(pid)].returnTime(redate) == true) {
					rps[getFoundedPropertyId(pid)].properties[0].setActualReturnDate(redate);
					fee(pid);
					rps[getFoundedPropertyId(pid)].setPropertyStatus("Available");
					System.out.println(rps[getFoundedPropertyId(pid)].getPropertyType() + " is returned by customer "
							+ rps[getFoundedPropertyId(pid)].getCustomerId());

					System.out.println(rps[getFoundedPropertyId(pid)].getDetails());
					System.out.println("RENTAL RECORD");
					for (int i = 0; i < RentalProperties.getCountNumofAddRecords(); i++) {
						if (!(rps[getFoundedPropertyId(pid)].properties[i] == null)) {
							String a = rps[getFoundedPropertyId(pid)].properties[i].getDetails();
							System.out.println(a);
							System.out.println("----------------------------------------------");
						} else {
							continue;
						}
					}
					displayCurrentProperties(pid);
				} else {
					System.out.println("Please enter a valid return date");
					showMenu();
				}
			} else {
				System.out.println("You did not rent this property!");
				showMenu();
			}
		}
	}

	public void propertyMaintenance() {
		System.out.println("Enter property id:");
		String pid = sc.next();

		if (validateEnterPropertyId(pid) == false) {
			System.out.println("The id does not exist!");
			showMenu();
		} else {
			if (rps[getFoundedPropertyId(pid)].properties[0] != null) {
				if (rps[getFoundedPropertyId(pid)].performMaintenance() == false) {
					System.out.println(rps[getFoundedPropertyId(pid)].getPropertyType() + " " + pid + " "
							+ "could not be maintented");
					showMenu();
				} else {
					rps[getFoundedPropertyId(pid)].setPropertyStatus("under maintenance");
					displayCurrentProperties(pid);
				}
			}
		}
	}

	public void completeMaintenance() {
		System.out.println("Enter property id:");
		String pid = sc.nextLine();

		if (validateEnterPropertyId(pid) == false) {
			System.out.println("The id does not exist!");
			showMenu();
		} else {
			if (rps[getFoundedPropertyId(pid)].getPropertyStatus().equals("under maintenance")) {
				System.out.println("Maintenance completion date (dd/mm/yyyy):");
				String mcd = sc.nextLine();
				DateTime finishMantenDate = DateTime.stringToDateTime(mcd);
				if (rps[getFoundedPropertyId(pid)].completeMaintenance(finishMantenDate) == true) {
					rps[getFoundedPropertyId(pid)].setPropertyStatus("Available");
					rps[getFoundedPropertyId(pid)].getProperties()[0].setCompletionDate(finishMantenDate);
					displayAllProperties();
				} else {
					System.out.println(
							rps[getFoundedPropertyId(pid)].getPropertyType() + " " + "is not under maintenance!");
					displayCurrentProperties(pid);
				}
			} else {
				System.out
						.println(rps[getFoundedPropertyId(pid)].getPropertyType() + " " + "is not under maintenance!");
				displayCurrentProperties(pid);
			}
		}
	}

	public void fee(String pid) {
		double rfee = 0;
		double lfee = 0;
		if (rps[getFoundedPropertyId(pid)] instanceof Apartment) {
			RentalProperties a = new Apartment();
			a = rps[getFoundedPropertyId(pid)];
			String ard1 = rps[getFoundedPropertyId(pid)].properties[0].getActualReturnDate();
			DateTime ard = DateTime.stringToDateTime(ard1);
			rfee = ((Apartment) a).calculateRentalFee(ard);
			lfee = ((Apartment) a).calculateLateFee(ard);

		} else {
			PremiumSuite p = (PremiumSuite) rps[getFoundedPropertyId(pid)];
			String ard1 = p.properties[0].getActualReturnDate();
			DateTime ard = DateTime.stringToDateTime(ard1);
			rfee = p.calculateRentalFee(ard);
			lfee = p.calculateLateFee(ard);
		}
		rps[getFoundedPropertyId(pid)].properties[0].setRentalFee(rfee);
		rps[getFoundedPropertyId(pid)].properties[0].setLateFee(lfee);
	}

	public void displayCurrentProperties(String pid) {
		System.out.println(rps[getFoundedPropertyId(pid)].getDetails());
		System.out.println("RENTAL RECORD");
		for (int j = 0; j < RentalProperties.getCountNumofAddRecords(); j++) {
			if (!(rps[getFoundedPropertyId(pid)].properties[j] == null)) {
				String a = rps[getFoundedPropertyId(pid)].properties[j].getDetails();
				System.out.println(a);
				System.out.println("----------------------------------------------");
			} else {
				continue;
			}
		}
	}

	public void displayAllProperties() {
		for (int i = 0; i < rps.length; i++) {
			if (rps[i] != null) {
				String allProperties = rps[i].getDetails();
				System.out.println(allProperties);
				System.out.print("RENTAL RECORD");
				if (rps[i].properties[0] == null) {
					System.out.print("           " + "empty\n");
					System.out.println("----------------------------------------------");
				}
				for (int j = 0; j < RentalProperties.getCountNumofAddRecords(); j++) {
					if (!(rps[i].properties[j] == null)) {
						String a = rps[i].properties[j].getDetails();
						System.out.println(a);
						System.out.println("----------------------------------------------");
					} else {
						continue;
					}
				}
			} else {
				break;
			}
		}
		showMenu();
	}
}
