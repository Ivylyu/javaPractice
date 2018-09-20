import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class DateTime
{
	
	private long advance;
	private long time;
	
	public DateTime()
	{
		time = System.currentTimeMillis();
	}//time gap in ms from 1.1.1970 till now
	
	
	public DateTime(int setClockForwardInDays)
	{
		advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
		time = System.currentTimeMillis() + advance;
		
		
	}

	public DateTime(DateTime startDate, int setClockForwardInDays)
	{
		advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;//ms
		
		time = startDate.getTime() + advance;
	}//：time=date+days，ms
	
	public DateTime(int day, int month, int year)
	{
		setDate(day, month, year);
	}
	
	public long getTime()
	{
		return time;
	}
	
	public String toString()
	{
//		long currentTime = getTime();
//		Date gct = new Date(currentTime);
//		return gct.toString();
		return getFormattedDate();
	}
	
	// String -> DateTime
	public static DateTime stringToDateTime(String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		java.util.Date estDate1 = null;
		try {
			estDate1 = sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calDate = Calendar.getInstance();
		calDate.clear();
		calDate.setTime(estDate1);// Date -> Calendar

		DateTime estmadDate1 = new DateTime(calDate.get(Calendar.DAY_OF_MONTH), (calDate.get(Calendar.MONTH)) +1,
				(calDate.get(Calendar.YEAR)) );
		return estmadDate1;
	}
	

	// String -> Calendar
	public static Calendar stringToDate(String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		java.util.Date estDate1 = null;
		try {
			estDate1 = sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calDate = Calendar.getInstance();
		calDate.clear();
		calDate.setTime(estDate1);// Date->Calendar
		return calDate;
	}
	
	public static String getCurrentTime()
	{
		Date date = new Date(System.currentTimeMillis());// returns current Date/Time
		Calendar calDate = Calendar.getInstance();
		calDate.clear();
		calDate.setTime(date);
		
		DateTime ct = new DateTime(calDate.get(Calendar.DAY_OF_MONTH), (calDate.get(Calendar.MONTH)) +1,
				(calDate.get(Calendar.YEAR)) );
		
		return ct.toString();
	} // if rent date is before current date ,wrong

	
	
	public String getFormattedDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		long currentTime = getTime();
		Date gct = new Date(currentTime);

		
		return sdf.format(gct);
	}//time in ms -> string "dd/MM/yyyy"
	

	public String getEightDigitDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		long currentTime = getTime();
		Date gct = new Date(currentTime);
		return sdf.format(gct);
	}
	
	// returns difference in days
	public static int diffDays(DateTime endDate, DateTime startDate)
	{
		final long HOURS_IN_DAY = 24L;
		final int MINUTES_IN_HOUR = 60;
		final int SECONDS_IN_MINUTES = 60;
		final int MILLISECONDS_IN_SECOND = 1000;
		long convertToDays = HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTES * MILLISECONDS_IN_SECOND;
		long hirePeriod = endDate.getTime() - startDate.getTime();
		double difference = (double)hirePeriod / (double)convertToDays;
		int round = (int)Math.round(difference);
		return round;
	}
	
	private void setDate(int day, int month, int year)
	{
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, 0, 0);   

		java.util.Date date = calendar.getTime();

		time = date.getTime();
	}
	
	// Advances date/time by specified days, hours and mins for testing purposes
		public void setAdvance(int days, int hours, int mins)
		{
			advance = ((days * 24L + hours) * 60L) * 60000L;
		}
}
