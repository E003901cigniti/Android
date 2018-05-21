package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.android.workflows.*;

public class TC54_RTDomesticBookingNCheckinWithNaSmiles extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData_UAT_Reg"),"FL_WEB_27");

	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_54_RTDomesticBookingNCheckinWithNaSmiles(String tripType, String origin, String dest, 
			String deptDate, String origin2,String departure2, String retdate,String Adult,String Child,String infant, String promo, String bookingClass, String bundle, 
			String flightType,String totalpass,String nationality,String docType,String docNumber,
			String naSmiles,String Mobile,String selectSeat,String paymentType,String bookingType, 
			String charity,String currency, String accType,String description
			) throws Throwable {
		try {
			//System.out.println(paymentType);
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			String deptdate = pickDate(deptDate);
			
			String[] Credentials = pickCredentials("BronzeCreds");
			String username =Credentials[0];
			String password =Credentials[1];
			
			Homepage homepage = new Homepage();
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username,password);
			homepage.select_Bookflights("registered");
			
			inputBookingDetails(tripType, origin, dest, deptdate, origin2, departure2, retdate,Adult, Child, infant,promo,currency);
			selectPayWithSmilePoint();
			clickFindFlightsBtn();
			selectClass(bookingClass, bundle);
			continueOnPsngrDtls();
			continueOnExtras();
			continueOnSeatSelection();
			nasmilespayment(username,password);
			String PNRnumber=getReferenceNumber();
			validate_ticketStatus();
			
			//navigating to home page to manage booking
			navigatetoHmPg();
			handleRatingRequest();
			homepage.select_OnlineCheckIn("Registered");
			registeredUsrCheckin(PNRnumber);
			//Checking in
			performCheckin();
			cntinueOnTravelDocument();
			cntinueRandomSeatSelection();
			confirmRandomSeatSelection();
			nasmilespayment(username,password); 
			validateCheckin();
			         
			Reporter.SuccessReport("TC54_RTDomesticBookingNCheckinWithNaSmiles", "Pass");
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC54_RTDomesticBookingNCheckinWithNaSmiles", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{
	    			xls.getCellValue("Trip Type", "Value2"),
		    		xls.getCellValue("Origin", "Value"),
		    		xls.getCellValue("Destination", "Value"),
		    		xls.getCellValue("Departure Date", "Value"),
		    		"",
		    		"",
		    		xls.getCellValue("Return Date", "Value"),
		    		xls.getCellValue("Adults Count", "Value"),
		    		xls.getCellValue("Child Count", "Value"),
		    		xls.getCellValue("Infant Count", "Value"),
		    		xls.getCellValue("Promo", "Value"),
		    		xls.getCellValue("Booking Class", "Value"),
		    		xls.getCellValue("Bundle", "Value"),
		    		xls.getCellValue("Flight Type", "Value"),
		    		xls.getCellValue("Total Passenger", "Value"),
		    		xls.getCellValue("Nationality", "Value"),
		    		xls.getCellValue("Document Type", "Value"),
		    		xls.getCellValue("Doc Number", "Value"),
		    		xls.getCellValue("naSmile", "Value"),
		    		xls.getCellValue("Mobile", "Value"),
		    		xls.getCellValue("Select Seat", "Value"),
		    		xls.getCellValue("Payment Type", "Value"),
		    		"",
	    			xls.getCellValue("Charity Donation", "Value"),
	    			xls.getCellValue("currency", "Value"),
	    			xls.getCellValue("AccountType", "Value"),
		    		"Validate Domestic booking and Checkin with naSmiles"}};
	}

}