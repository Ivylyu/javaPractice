
interface FeeCalculator {
	double calculateRentalFee(DateTime actualReturnDate);
	double calculateLateFee(DateTime actualReturnDate);
}
