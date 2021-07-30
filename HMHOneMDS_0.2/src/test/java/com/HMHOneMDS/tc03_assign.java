package com.HMHOneMDS;

import java.io.IOException;

import org.testng.annotations.Test;

import com.utilities.Constants;
import com.utilities.ReportGenerate;
import com.utilities.Reports;
import com.utilities.genericMethods;

public class tc03_assign {
	genericMethods method = new genericMethods();
	  @Test
	  public void assign(String guid, String inp_compo, String inp_disp_title, String inp_assign) throws IOException {
		  
		  try {
				//System.out.println("Am going to verify assign and my value " + inp_assign);
				//method.getAttrbyXpath("//span[.='Assign']");
				
			  System.out.println(inp_assign);
			  if(inp_assign.equalsIgnoreCase("N"))
				{
				  System.out.println("No Assign button");
					   Reports.Pass("Assign functionality not available for Guid " +guid , "");
					   ReportGenerate.writeResult("TC03_Assign", Constants.program_title, guid, inp_compo, inp_disp_title, "Assign functionality not available for Guid", "", "PASS");
				}
			  else  if(inp_assign.equalsIgnoreCase("Y"))
			  {
			  
				genericMethods.getTextbyxpath("//button[@data-track-event='DiscoverResourceCard.Assign']//span");
				
				System.out.println("Assign Attribute: " + genericMethods.textbyxpath);
				
				if(inp_assign.equalsIgnoreCase("Y") && genericMethods.textbyxpath.equalsIgnoreCase("Assign"))
				{
					   Reports.Pass("Assign functionality available for Guid " +guid , "");
					   ReportGenerate.writeResult("TC03_Assign", Constants.program_title, guid, inp_compo, inp_disp_title, "Assign Functionality available", "", "PASS");
				}
			/*	else if(inp_assign=="N" && genericMethods.attribute.equalsIgnoreCase("Assign") )
				{
					Reports.Pass("Assign functionality not available for Guid " +guid, "");
					ReportGenerate.writeResult("TC03_Assign", Constants.program_title, guid, inp_compo, inp_disp_title, "Assign Functionality Not Available", "", "PASS");
				}*/
				else 
				{
					Reports.Fail("Error in Input" +guid, "");
					ReportGenerate.writeResult("TC03_Assign", Constants.program_title, guid, inp_compo, inp_disp_title, "Error in Input", "", "FAIL");
				}
			  }
			} catch (NullPointerException e) {
				
			/*	if(inp_assign=="N")
				{
					   Reports.Pass("Assign functionality not available for Guid " +guid , "");
					   ReportGenerate.writeResult("TC03_Assign", Constants.program_title, guid, inp_compo, inp_disp_title, "Assign functionality not available for Guid", "", "PASS");
				}
				else
				{
					Reports.Fail("Assign Functionality not available in online" +guid, "");
					ReportGenerate.writeResult("TC03_Assign", Constants.program_title, guid, inp_compo, inp_disp_title, "Assign Functionality not available in online", "", "FAIL");
				}*/
			}
			  finally
			  {
				  	Reports.after();
			  }			 
		  } 
}
