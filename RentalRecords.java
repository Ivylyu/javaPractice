import java.text.DecimalFormat;

public class RentalRecords {

	private String recordId;
	private DateTime rentDate;
	private DateTime estimatedReturnDate;
	private DateTime actualReturnDate;
	private double rentalFee;
	private double lateFee;

    private DateTime completionDate;


	public DateTime getCompletionDate() {
   	 return completionDate;
   	 }
   	
   	 public void setCompletionDate(DateTime completionDate) {
   	 this.completionDate = completionDate;
   	 }

	DecimalFormat df = new DecimalFormat("0.00");
	private String rf;
	private String lf;

	public String toString() {
		if (getRentalFee() == 0) {
			rf = "none";
		} else {
			rf = df.format(getRentalFee());
		} // initial is none

		if (getLateFee() == 0) {
			lf = "none";
		} else {
			lf = df.format(getLateFee());
		} // initial is none

		return getRecordId() + ":" + getRentDate() + ":" + getEstimatedReturnDate() + ":" + getActualReturnDate() + ":"
				+ rf + ":" + lf;
	}

	public String getDetails() {
		return "Record ID:" + "		" + getRecordId() + "\n" + "Rent Date:" + "		" + getRentDate() + "\n"
				+ "Estimated Return Date:" + "	" + getEstimatedReturnDate() + "\n" + "Actual Return Date:" + "     "
				+ getActualReturnDate() + "\n" + "Rental Fee:" + "		" + df.format(getRentalFee()) + "\n" + "Late Fee:" + " 		" + df.format(getLateFee());
	}
	

	// constructor
	public RentalRecords() {
	};
	
	public RentalRecords(DateTime rentDate) {
		this.rentDate = rentDate;

	};
	

	public RentalRecords(String recordId, DateTime rentDate, DateTime estimatedReturnDate, DateTime actualReturnDate,
			double rentalFee, double lateFee) {

		this.recordId = recordId;
		this.rentDate = rentDate;
		this.estimatedReturnDate = estimatedReturnDate;
		this.actualReturnDate = actualReturnDate;
		this.rentalFee = rentalFee;
		this.lateFee = lateFee;
	}

	public RentalRecords(String recordId, DateTime rentDate, DateTime estimatedReturnDate) {
		this(recordId, rentDate, estimatedReturnDate, null, 0, 0);
	
	}

	// setter&getter
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRentDate() {
		if (!(this.rentDate == null)) {
			return this.rentDate.getFormattedDate();
		} else {
			return "none";
		}
	}
	
	//专门为recordId建立的getter方法
	public String getRentDateInEightNum() {
		return this.rentDate.getEightDigitDate();
	}
	
	public void setRentDate(DateTime rentDate) {
		this.rentDate = rentDate;
	}

	public String getEstimatedReturnDate() {
		if (!(this.estimatedReturnDate == null)) {
			return this.estimatedReturnDate.getFormattedDate();
		} else {
			return "none";
		}

	}

	public void setEstimatedReturnDate(DateTime estimatedReturnDate) {
		this.estimatedReturnDate = estimatedReturnDate;
	}

	public String getActualReturnDate() {

		if (!(this.actualReturnDate == null)) {
			return this.actualReturnDate.getFormattedDate();
		} else {
			return "none";
		}
	}

	public void setActualReturnDate(DateTime actualReturnDate) {// put in a actualReturnDate and calculate a fee
		this.actualReturnDate = actualReturnDate;
	}

	public double getRentalFee() {
		return this.rentalFee;
	}

	public void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}

	public double getLateFee() {
		return this.lateFee;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}
	
	

}
