package flynas.android.scripts.uat.reg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import flynas.android.testObjects.BookingPageLocators;
import flynas.android.workflows.BookingPageFlow;
import flynas.android.workflows.Homepage;
import flynas.android.workflows.SettingsPage;

public class TC14_oneWayDomesticSimpleCCEuro extends BookingPageFlow{
	ExcelReader xls = new ExcelReader(configProps.getProperty("TestData_UAT_Reg"),"FL_WEB_13");

	@Test(dataProvider = "testData",groups={"Android"})
	public  void TC_14_oneWayDomesticSimpleCCEuro(String tripType, String origin, String dest,String deptDate, String origin2,
			String departure2, String retdate,String Adult,String Child,String infant, String promo,String bookingClass, String bundle, 
			String flightType,String totalpass,String nationality,String docType,String docNumber,String naSmiles,String Mobile,
			String email ,String selectSeat,String paymentType,String bookingType,String charity,String currency, String description
			) throws Throwable {
		try {
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			// Handlepopup();
			String[] Credentials = pickCredentials("UserCredentials");
			
			String username =Credentials[0];
			String password =Credentials[1];
		
			String depDate = pickDate(deptDate);
			String rtrndate = pickDate(retdate);
			
			Homepage homepage = new Homepage();
			SettingsPage settings = new SettingsPage();
			homepage.click_Settings();
			settings.set("currency", currency);
			settings.click_BackIcn();			
			homepage.select_TittleMenu();
			homepage.Click_login();
			homepage.Login(username,password);
			homepage.select_Bookflights("registered");
					
 			inputBookingDetails(tripType, origin, dest, depDate, origin2, departure2, rtrndate,Adult, Child, infant,promo,currency);
 			clickFindFlightsBtn();
 			selectClass(bookingClass, bundle);
 			continueOnPsngrDtls();
			continueOnExtras();
			continueOnSeatSelection();
			payment(paymentType,"");
			validate_ticketStatus();
			Reporter.SuccessReport("TC14_oneWayDomesticSimpleCCEuro", "Pass");
			
			}
		
	catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("TC14_oneWayDomesticSimpleCCEuro", "Failed");
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
					"",
					xls.getCellValue("Mobile", "Value"),
					xls.getCellValue("Email Address", "Value"),
					xls.getCellValue("Select Seat", "Value"),
					xls.getCellValue("Payment Type", "Value"),
					"",
					xls.getCellValue("Charity Donation", "Value"),
					xls.getCellValue("currency", "Value2"),
					"Validate one Way Domestic Simple class booking with Euros"}};
	}
}
