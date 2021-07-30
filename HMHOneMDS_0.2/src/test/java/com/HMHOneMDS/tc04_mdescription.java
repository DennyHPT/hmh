package com.HMHOneMDS;

import org.testng.annotations.Test;

import com.utilities.Constants;
import com.utilities.ReportGenerate;
import com.utilities.Reports;
import com.utilities.genericMethods;

public class tc04_mdescription {
	genericMethods method = new genericMethods();
	
  @Test
  public void meaningfuldescription(String guid, String inp_compo, String inp_disp_title, String inp_desc) {
	  
	  try {
			//System.out.println("Am going to verify Meaningful description and my value " + inp_desc);
		  
		  
		  
		//genericMethods.clickElement(".//div[@class= 'details--3xY92']/section/a");
		//genericMethods.getTextbyxpath(".//li[@class='resourceListItem--3decV']/div/section/div/p");
		//div[@class='resourceDetails']/p[@class='ng-binding']
		  //genericMethods.clickElement(".//div[@class='details--3yvRh']/section/a");
		  
		  genericMethods.clickElement(".//button[@class='showDetailsButton--3XFJ4']");
		  genericMethods.getTextbyxpath(".//li[@class='resourceListItem--3PV_a']/div/section/div/p");
		 if(genericMethods.textbyxpath.equalsIgnoreCase(inp_desc.trim()))
				 {
					Reports.Pass("Meaningful description Matched for Guid " +guid , genericMethods.textbyxpath);
					 ReportGenerate.writeResult("TC04_Meaningful Description", Constants.program_title, guid, inp_compo, inp_desc, "Description Matched", genericMethods.textbyxpath, "PASS");
				 }
				 else
				 {
					 Reports.Fail("Meaningful description Mismatched for Guid " +guid, genericMethods.textbyxpath);
					 ReportGenerate.writeResult("TC04_Meaningful Description", Constants.program_title, guid, inp_compo, inp_desc, "Description not Matched", genericMethods.textbyxpath, "Fail");
				 }
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		  finally{
			  	Reports.after();
			     } 
	  
  }
}
