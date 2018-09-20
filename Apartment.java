import java.util.Calendar;

public class Apartment extends RentalProperties implements FeeCalculator {
	private int pricePerDay;
	private static double lateFeeRate = 1.15;

	// constructor
	public Apartment() {
		super();
	}

	public Apartment(String propertyId, String streetNumber, String streetName, String suburb, int numberOfBedrooms,
			String propertyType) {
		super(propertyId, streetNumber, streetName, suburb, numberOfBedrooms, "Apartment");
	}

	public Apartment(String propertyId, String streetNumber, String streetName, String suburb, int numberOfBedrooms,
			String propertyType, String propertyStatus) {
		super(propertyId, streetNumber, streetName, suburb, numberOfBedrooms, "Apartment", propertyStatus);
	}

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPrice() {
		if (super.getNumberOfBedrooms() == 1) {
			this.pricePerDay = 143;
		} else if (super.getNumberOfBedrooms() == 2) {
			this.pricePerDay = 210;
		} else if (super.getNumberOfBedrooms() == 3) {
			this.pricePerDay = 319;
		}
	}

	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		DateTime dt = new DateTime(rentDate, numOfRentDay); // date+days,estimate return date

		String dt1 = rentDate.toString(); // rentDate DateTime to String
		Calendar dt2 = DateTime.stringToDate(dt1);// renteDate String to Calendar

		int week = dt2.get(Calendar.DAY_OF_WEEK) - 1;

		if (DateTime.diffDays(dt, rentDate) < 0 || DateTime.diffDays(dt, rentDate) >= 28) {
			return false;
		} // returnDate early than rentDate or rent over 28 days

		if (week >= 1 && week <= 5 && DateTime.diffDays(dt, rentDate) >= 2) { //a minimum of 2 days if between Sun - Thur
			return true;
		} else if (week == 6 && week == 7 && DateTime.diffDays(dt, rentDate) >= 3) { // a minimum of 3 days between Fri or Sat
			return true;
		} else {
			return false;
		}
	}

	public boolean returnTime(DateTime returnDate) {
		DateTime rt = DateTime.stringToDateTime(this.getProperties()[0].getRentDate());
		if (DateTime.diffDays(returnDate, rt) < 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean performMaintenance() {
		
		if (this.getPropertyStatus().equals("rented")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean completeMaintenance(DateTime completionDate) {
		//can not complete maintenance before return date
		if (DateTime.diffDays(completionDate, DateTime.stringToDateTime(this.getProperties()[0].getActualReturnDate())) < 0) { 
			return false;
		} else {
			return true;
		}
	}

	public double calculateRentalFee(DateTime actualReturnDate) {
		this.properties[0].setActualReturnDate(actualReturnDate);
		DateTime rd = DateTime.stringToDateTime(this.properties[0].getRentDate());
		double rentfee = 0;
		DateTime erd = DateTime.stringToDateTime(this.properties[0].getEstimatedReturnDate());

		if (this.getNumberOfBedrooms() == 1) {
			rentfee = 143 * (DateTime.diffDays(erd, rd));
		} else if (this.getNumberOfBedrooms() == 2) {
			rentfee = 210 * (DateTime.diffDays(erd, rd));
		} else {
			rentfee = 319 * (DateTime.diffDays(erd, rd));

		}
		return rentfee;
	}// later than ,additional fee is applied

	public double calculateLateFee(DateTime actualReturnDate) {
		this.properties[0].setActualReturnDate(actualReturnDate);
		double latefee = 0;
		DateTime erd = DateTime.stringToDateTime(this.properties[0].getEstimatedReturnDate());

		if (this.getNumberOfBedrooms() == 1) {
			latefee = lateFeeRate * 143 * (DateTime.diffDays(actualReturnDate, erd));
		} else if (this.getNumberOfBedrooms() == 2) {
			latefee = lateFeeRate * 210 * (DateTime.diffDays(actualReturnDate, erd));
		} else {
			latefee = lateFeeRate * 319 * (DateTime.diffDays(actualReturnDate, erd));
		}
		return latefee;
	}

}
