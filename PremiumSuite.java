
public class PremiumSuite extends RentalProperties implements FeeCalculator {
	private DateTime lastCompletionDate;

	public DateTime getLastCompletionDate() {
		return lastCompletionDate;
	}

	public void setLastCompletionDate(DateTime lastCompletionDate) {
		this.lastCompletionDate = lastCompletionDate;
	}

	public PremiumSuite() {
		super();
	}

	public PremiumSuite(String propertyId, String streetNumber, String streetName, String suburb, int numberOfBedrooms,
			String propertyType) {
		super(propertyId, streetNumber, streetName, suburb, 3, "PremiumSuite");
	}

	public PremiumSuite(String propertyId, String streetNumber, String streetName, String suburb, int numberOfBedrooms,
			String propertyType, String propertyStatus, DateTime lastCompletionDate) {
		super(propertyId, streetNumber, streetName, suburb, 3, "PremiumSuite", propertyStatus);
		this.lastCompletionDate = lastCompletionDate;
	}

	public String toString() {
		return getPropertyId() + ":" + getStreetNumber() + ":" + getStreetName() + ":" + getSuburb() + ":"
				+ getPropertyType() + ":" + getNumberOfBedrooms() + ":" + getPropertyStatus() + ":"
				+ getLastCompletionDate();
	}

	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		DateTime dt = new DateTime(rentDate, numOfRentDay); // estimated return date
		if (this.lastCompletionDate != null) {
			DateTime availableForRentDate = new DateTime(this.lastCompletionDate, 10);
			if (DateTime.diffDays(rentDate, availableForRentDate) < 0
					|| DateTime.diffDays(dt, availableForRentDate) < 0) {
				return false;
			}
		}

		if (DateTime.diffDays(dt, rentDate) >= 1) { // must rent more than 1 day
			return true;
		} else {
			return false;
		}
	}

	public boolean returnTime(DateTime returnDate) {
		DateTime dt2 = DateTime.stringToDateTime(this.properties[0].getRentDate());

		if (DateTime.diffDays(returnDate, dt2) < 0) {
			return false;
		} // returnDate early than rentDate
		if (DateTime.diffDays(returnDate, dt2) >= 1) { // must rent more than 1 day
			return true;
		} else {
			return false;
		}

	}

	public boolean performMaintenance() {
		if (this.properties[0].getActualReturnDate() == "none") { //if not being returned
			return false;
		} else {
			return true;
		}
	}

	public boolean completeMaintenance(DateTime completionDate) {
		if (this.properties[0].getActualReturnDate() == "none") { //if not being returned
			return false;
		} else

		{
			this.setPropertyStatus("Available");
			this.setLastCompletionDate(completionDate);
			System.out.println(
					"Premium Suite " + this.getPropertyId() + " has all maintenance completed and ready for rent");
			return true;
		}
	}

	public double calculateRentalFee(DateTime actualReturnDate) {
		this.properties[0].setActualReturnDate(actualReturnDate);
		DateTime rd = DateTime.stringToDateTime(this.properties[0].getRentDate());
		double rentfee = 0;
		DateTime erd = DateTime.stringToDateTime(this.properties[0].getEstimatedReturnDate());
		rentfee = 554 * (DateTime.diffDays(erd, rd));

		return rentfee;
	}

	public double calculateLateFee(DateTime actualReturnDate) {
		this.properties[0].setActualReturnDate(actualReturnDate);
		double latefee = 0;
		DateTime erd = DateTime.stringToDateTime(this.properties[0].getEstimatedReturnDate());
		latefee = 662 * (DateTime.diffDays(actualReturnDate, erd));

		return latefee;
	}
}
