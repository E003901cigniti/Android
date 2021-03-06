package flynas.androidchrome.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.androidchrome.testObjects.BookingPageLocators;
import flynas.androidchrome.workflows.BookingPageFlow;

public class TC23_oneWayDomesticOneAdultCheckInAR extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData"),"FL_WEB_23");

	@Test(dataProvider = "testData",groups={"Chrome"})
	public  void TC_23_oneWayDomesticOneAdultCheckInAR(String tripType, String origin, String dest, 
			String deptDate,String origin2,String departure2,
			String retdate, String strTolPass, String Adult,String Child,String infant, String promo, String strBookingClass,
			String FlightType,String totalpass,String nationality,String Doctypr,String docNumber,
			String naSmiles,String Mobile,String email ,String SelectSeat,String paymenttype, String bookingtype,String Charity, 
			String Currency,String Description
			)  throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, Description);
			deptDate = pickDate(deptDate);
			
			click(BookingPageLocators.Arabic_pdctn_AR("العربية"), "Arabic Language");
			inputBookingDetails_Arabic(tripType,origin, dest, deptDate , origin2, departure2, retdate,Adult, Child, infant,"","");
			selectClass(strBookingClass,tripType);
			String lastname[]=inputPassengerDetails_Arabic(FlightType, totalpass, nationality, Doctypr,docNumber,"", Mobile, email, "", "", "");
			Baggage_Extra(tripType);
			click(BookingPageLocators.continueBtn, "Continue");
			selectSeat(SelectSeat, bookingtype);
			payment(paymenttype, "");
			waitforElement(BookingPageLocators.summaryRefNumber_AR_uat);
			String strpnr = getText(BookingPageLocators.summaryRefNumber_AR_uat,"PNR");
			String strPNR = strpnr.trim();
			System.out.println(strPNR);
			validate_ticketStatus_AR(strPNR);
			searchFlightCheckin(strPNR, "", "", lastname[1]);
			performCheckin(SelectSeat,paymenttype,strTolPass);
			validateCheckin();
			
			Reporter.SuccessReport("TC23_oneWayDomesticOneAdultCheckInAR", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.SuccessReport("TC23_oneWayDomesticOneAdultCheckInAR", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    	{xls.getCellValue("Trip Type", "Value"),
	    		xls.getCellValue("Origin", "Value"),
	    		xls.getCellValue("Destination", "Value"),
	    		xls.getCellValue("Departure Date", "Value"),
	    		"",
	    		"",
	    		xls.getCellValue("Return Date", "Value"),
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("Adults Count", "Value"),
	    		xls.getCellValue("Child Count", "Value"),
	    		xls.getCellValue("Infant Count", "Value"),
	    		xls.getCellValue("Promo", "Value"),
	    		xls.getCellValue("Booking Class", "Value"),
	    		xls.getCellValue("Flight Type", "Value"),
	    		xls.getCellValue("Total Passenger", "Value"),
	    		xls.getCellValue("Nationality", "Value"),
	    		xls.getCellValue("Document Type", "Value"),
	    		xls.getCellValue("Doc Number", "Value"),
	    		"1234567890",
	    		xls.getCellValue("Mobile", "Value"),
	    		xls.getCellValue("Email Address", "Value"),
	    		xls.getCellValue("Select Seat", "Value"),
	    		"Credit Card",
	    		"",
    			xls.getCellValue("Charity Donation", "Value"),"",
	    		"Validate One way Domestic with one Adult CheckIn In AR"}};
	}

}
