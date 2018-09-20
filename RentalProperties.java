abstract class RentalProperties {
	private String PropertyId;
	private String StreetNumber;
	private String StreetName;
	private String Suburb;
	private int NumberOfBedrooms;
	private String PropertyType;
	private String PropertyStatus;
	private String customerId;
	protected RentalRecords[] properties =new RentalRecords[10];// 10 records in a property

	public String getAddress() {
		return getStreetNumber() + "  " + getStreetName() + "  " + getSuburb();
	}

	private static int countNumofAddRecords = 0;

	public static int getCountNumofAddRecords() {
		return countNumofAddRecords;
	}

	public static void setCountNumofAddRecords(int countNumofAddRecords) {
		RentalProperties.countNumofAddRecords = countNumofAddRecords;
	}

	

	// "recordId:rentDate:estimatedReturnDate:actualReturnDate:returnFee:lateFee"
	public String constructRecordId(DateTime rt) {
		String recordId = this.getPropertyId() + "_" + getCustomerId() + "_" + rt.getEightDigitDate();
		return recordId;
	}

	// constructor
	public RentalProperties() {
	}

	public RentalProperties(String propertyId, String streetNumber, String streetName, String suburb,
			int numberOfBedrooms, String propertyType) {
		PropertyId = propertyId;
		StreetNumber = streetNumber;
		StreetName = streetName;
		Suburb = suburb;
		NumberOfBedrooms = numberOfBedrooms;
		PropertyType = propertyType;

	}

	public RentalProperties(String propertyId, String streetNumber, String streetName, String suburb,
			int numberOfBedrooms, String propertyType, String propertyStatus) {
		PropertyId = propertyId;
		StreetNumber = streetNumber;
		StreetName = streetName;
		Suburb = suburb;
		NumberOfBedrooms = numberOfBedrooms;
		PropertyType = propertyType;
		PropertyStatus = propertyStatus;
	}

	public RentalRecords[] getProperties() {
		return this.properties;
	}

	public void setProperties(RentalRecords[] properties) {
		this.properties = properties;
	}

	public void addRentalRecords(RentalRecords rr) {
		for (int i = properties.length - 1; i > 0; i--) {
			properties[i] = properties[i - 1];
		}
		properties[0] = rr;
		countNumofAddRecords++;
	}

	abstract boolean rent(String customerId, DateTime rentDate, int numOfRentDay);

	abstract boolean returnTime(DateTime returnDate);

	abstract boolean performMaintenance();

	abstract boolean completeMaintenance(DateTime completionDate);

	@Override
	public String toString() {
		return getPropertyId() + ":" + getStreetNumber() + ":" + getStreetName() + ":" + getSuburb() + ":"
				+ getPropertyType() + ":" + getNumberOfBedrooms() + ":" + getPropertyStatus();
	}

	public String getDetails() {
		return "Property ID:" + "		" + getPropertyId() + "\n" + "Address:" + "			" + getAddress() + "\n"
				+ "Type:" + "			" + getPropertyType() + "\n" + "Bedroom:" + "			"
				+ getNumberOfBedrooms() + "\n" + "Status:" + "		  	" + getPropertyStatus() + "\n";

	}

	// setter&getter
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPropertyId() {
		return PropertyId;
	}

	public void setPropertyId(String propertyId) {
		PropertyId = propertyId;
	}

	public String getStreetNumber() {
		return StreetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		StreetNumber = streetNumber;
	}

	public String getStreetName() {
		return StreetName;
	}

	public void setStreetName(String streetName) {
		StreetName = streetName;
	}

	public String getSuburb() {
		return Suburb;
	}

	public void setSuburb(String suburb) {
		Suburb = suburb;
	}

	public int getNumberOfBedrooms() {
		return NumberOfBedrooms;
	}

	public void setNumberOfBedrooms(int numberOfBedrooms) {
		NumberOfBedrooms = numberOfBedrooms;
	}

	public String getPropertyType() {
		return PropertyType;
	}

	public void setPropertyType(String propertyType) {
		PropertyType = propertyType;
	}

	public String getPropertyStatus() {
		return PropertyStatus;
	}

	public void setPropertyStatus(String propertyStatus) {
		PropertyStatus = propertyStatus;
	}

}
