package com.HMHOneMDS;

import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.utilities.Constants;
import com.utilities.ReadConfigInpData;
import com.utilities.ReportGenerate;
import com.utilities.Reports;
import com.utilities.genericMethods;

public class tc07_standards {

  genericMethods method = new genericMethods();
	ReportGenerate report = new ReportGenerate();
	ReadConfigInpData read = new ReadConfigInpData();
	Constants c = new Constants();
	
	public static int Standard_size;

	int codelen, start, end;
	public static String Std_Xpath, std_count, std_code, Standard_Desc;
	public static String[] std_count_data ;
	public static int online_standard_count;
		
@BeforeTest
public void beforetest() throws IOException, InterruptedException {
		try {

			read.readConfigData();
			start = Integer.parseInt(read.startVal);
			end = Integer.parseInt(read.endVal);
			
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
	}

@Test
public void std_validation(String guid, String inp_compo, String inp_disp_title, String inp_std) throws InterruptedException
	{
		ArrayList<String> code = new ArrayList<String>();
	
		ArrayList<String> inp_code = new ArrayList<String>();
		online_standard_count = 0;
	
		//Split the Code from input and store in array
		
		if(!inp_std.equalsIgnoreCase("x"))
		{
			String[] inptStandard = inp_std.split(", ");
			for (String s : inptStandard)
			{
				inp_code.add(s);
			 } 
			System.out.println("Input: " + inp_code.toString());
			System.out.println("Input Code size " + inp_code.size());
		}
	try
		{  
			genericMethods.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//if(genericMethods.driver.findElements(By.xpath(".//*[@class='details--3yvRh']/section/p/span")).size() != 0)
			if(genericMethods.driver.findElements(By.xpath(".//*[@class='details--3xY92']/section/p/span")).size() != 0)
			{
				//Get Standard Count from the Card
				genericMethods.getTextbyxpath(".//*[@class='details--3xY92']/section/p/span");
				std_count_data = genericMethods.textbyxpath.split("\\s+"); 
				std_count = std_count_data[0].trim();
				online_standard_count = Integer.parseInt(std_count);
				System.out.println("Number of standards in Online - Displayed in Card : " + online_standard_count); 
			}
	
			if( (inp_std.isEmpty() || inp_std.equalsIgnoreCase("X")) && (online_standard_count == 0) )
			{
				System.out.println("No Standards both input & Online");
				ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "No Standards", " ", "PASS");
			}
			else 
			{
				//Click Show Details
				//genericMethods.clickElement("//button[@class='showDetailsButton--1sCJD']");
				  genericMethods.clickElement("//button[@class='showDetailsButton--3XFJ4']");	
				//Check if standard count is > 5 click Show All Standards
				if (online_standard_count>5)
				{
					Thread.sleep(3000);
					//genericMethods.clickElement(".//*[@class='asLink--ZZYJa']");
					genericMethods.clickElement(".//*[@class='asLink--33Y50']");
					//genericMethods.elementsbyxpath(".//*[@class='modalContent--3fzCf standardsSection']/section/ul/li");
					genericMethods.elementsbyxpath(".//*[@class='modalContent--qFr7M standardsSection']/section/ul/li");
					for (int i = 0; i < genericMethods.itemsbyxpath.size(); i++ )
		 			{
						//Get the Standard Code
			 			//genericMethods.elementsbyxpath(".//*[@class='modalContent--3fzCf standardsSection']/section/ul/li/h3");
						genericMethods.elementsbyxpath(".//*[@class='modalContent--qFr7M standardsSection']/section/ul/li/h3");
						std_code = genericMethods.itemsbyxpath.get(i).getText();
			 			code.add(std_code);	
			 			
			 			//genericMethods.elementsbyxpath(".//*[@class='modalContent--3fzCf standardsSection']/section/ul/li/p");
			 			genericMethods.elementsbyxpath(".//*[@class='modalContent--qFr7M standardsSection']/section/ul/li/p");
			 			Standard_Desc = genericMethods.itemsbyxpath.get(i).getText();
			 				
				 		if(!Standard_Desc.isEmpty())
				 		{
				 			//System.out.println("Description available");
				 			ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "Description available", std_code , "Pass");
				 		}	
				 		else
				 		{
				 			System.out.println("Description missing");
				 			ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "Description missing", std_code + " " + Standard_Desc, "Fail");
				 		}
			 		}
		 			
					if(inp_std.isEmpty() || inp_std.equalsIgnoreCase("X") && (online_standard_count != 0))
					{
						System.out.println("Additional Standards available " + code.toString());
						ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "Standards are additional", code.toString(), "Fail");
						Reports.Fail("Standards are additional", " ");
					}	
					else
		 			{
						System.out.println("Online " + code.toString());
						
						for (String temp : code)
			 			{
			 				inp_code.remove(temp);
			 			}
						System.out.println("Input size after remove: " + inp_code.size());
						if(inp_code.size()==0)
	 					{
							System.out.println("All codes matched with input and description available: " + code.toString());
	 						ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "All codes are available", code.toString(), "Pass");
	 					} 
						else 
	 					{	
	 						System.out.println("Missing codes in online " + inp_code.toString());
	 						ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "Missing", inp_code.toString(),"FAIL");
	 					}
					}	
							//genericMethods.clickElement(".//*[@class='modalContent--3fzCf standardsSection']/footer/button");
					          genericMethods.clickElement(".//*[@class='modalContent--qFr7M standardsSection']/footer/button");
			 				genericMethods.ScrollUp();
			 				genericMethods.clickByXpath(".//div[@class='arvo-search-box']//button[@aria-label='Clear text']");
			 			
		 		}
				
				//Standard Count < 5 get code from the card
				else
				{
					genericMethods.elementsbyxpath(".//*[@class='resourceStandardsInCard']/div/ul/li");
						
					for (int k = 0; k < genericMethods.itemsbyxpath.size(); k++ )
				   	{	
						//Get the Standard Code
						genericMethods.elementsbyxpath(".//*[@class='resourceStandardsInCard']/div/ul/li/h3");
						std_code = genericMethods.itemsbyxpath.get(k).getText();
			 			code.add(std_code);
						
						genericMethods.elementsbyxpath(".//*[@class='resourceStandardsInCard']/div/ul/li/p");
						Standard_Desc = genericMethods.itemsbyxpath.get(k).getText();
			 			
						if(!Standard_Desc.isEmpty())
				 		{
							System.out.println("Description available");
				 			ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "Description available", std_code , "Pass");
				 		}	
				 		else
				 		{
				 			System.out.println("Description missing");
				 			ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "Description missing", std_code + " " + Standard_Desc, "Fail");
				 		}
				   	}
			 		if(inp_std.isEmpty() || inp_std.equalsIgnoreCase("X") && (online_standard_count != 0))
					{
						System.out.println("Additional Standards available: " + code.toString());
						ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "Standards are additional", code.toString(), "Fail");
					}
		 			else
		 			{	
							System.out.println("Online "  + code.toString());
							for (String temp1 : code)
							{
								inp_code.remove(temp1);
							}
							System.out.println("Input size after remove: " + inp_code.size());
							if(inp_code.size()==0)
							{
								System.out.println("All codes matched with input");	
								ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "All codes are available", code.toString(),"Pass");
							}
							else
							{	
								System.out.println("Missing codes in online " + inp_code.toString());
								ReportGenerate.writeResult("TC07_Standards", Constants.program_title , guid, inp_compo	, inp_disp_title, "Missing", inp_code.toString(),"FAIL");
							}
			 			}
			 		}
			}
				
			
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
					
					
					
					
					

