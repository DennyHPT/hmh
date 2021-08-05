package com.HMHOneMDS;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import com.utilities.Constants;
import com.utilities.ReportGenerate;
import com.utilities.Reports;
import com.utilities.genericMethods;



public class tc02_location extends genericMethods {
	genericMethods method = new genericMethods();
	ReportGenerate report = new ReportGenerate();
	Constants c = new Constants();
	String loc;
	@Test
	public void verifyLocation(String guid, String inp_compo, String inp_disp_title, String inp_location) {

		try {
			// System.out.println("Am going to verify Location and my value " + inp_location);

			if(inp_location.isEmpty())
			{
				ReportGenerate.writeResult("TC02_Location", Constants.program_title, guid, inp_compo, inp_disp_title, "Location not available", "", "PASS");
				Reports.Pass(guid + "Location not Available", "");
			}
			else
			{
				// genericMethods.waitForElement("//div[@class='content']/p[@class='location ng-scope']");
				if(genericMethods.driver.findElement(By.xpath(".//div[@class='details--3xY92']/ul")).isDisplayed()==true)
				{
					//genericMethods.getTextbyxpath("//div[@class='details--3xY92']/ul");
					String getTex = GetText("gettext");
					loc = getTex.replace("\n", " ");
					System.out.println(loc);
					if(loc.equalsIgnoreCase(inp_location.trim()))
					{
						ReportGenerate.writeResult("TC02_Location", Constants.program_title, guid, inp_compo, inp_disp_title, "Location Matched", loc, "PASS");
						Reports.Pass("Location Matched", genericMethods.textbyxpath);
					}
					else
					{
						ReportGenerate.writeResult("TC02_Location", Constants.program_title, guid, inp_compo, inp_disp_title, "Location not Matched", loc, "Fail");
						Reports.Fail("Location not Matched", genericMethods.textbyxpath);
					}

				}
				else
				{
					ReportGenerate.writeResult("TC02", Constants.program_title, guid, inp_compo, inp_disp_title, "Location not available", genericMethods.textbyxpath, "PASS");
					Reports.Pass("Location not Available", "");
				} }
		} catch (NoSuchElementException e) {
			if(inp_location.isEmpty())
			{
				ReportGenerate.writeResult("TC02_Location", Constants.program_title, guid, inp_compo, inp_disp_title, "Location not available ", "", "Pass");
				Reports.Pass(guid + "Location not Available ", "");
			}
			else
			{
				ReportGenerate.writeResult("TC02_Location", Constants.program_title, guid, inp_compo, inp_disp_title, "Location not available in online", "", "Fail");
				Reports.Pass(guid + "Location not Available in online", "");
			}
		}

		finally
		{
			Reports.after();
		}	
	}

}
