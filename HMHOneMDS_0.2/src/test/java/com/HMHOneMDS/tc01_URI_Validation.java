package com.HMHOneMDS;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.utilities.Constants;

import com.utilities.ReportGenerate;
import com.utilities.Reports;
import com.utilities.genericMethods;

public class tc01_URI_Validation extends genericMethods
{
	
	genericMethods method = new genericMethods();
	String URL;
	
	@Test
  public void urivalidation(String guid, String inp_compo, String inp_disp_title, String inp_uri) throws IOException, InterruptedException 
	{
	 try 
		{
			//Component title
		     //genericMethods.getTextbyxpath(".//*[@class='details--3xY92']/h3");
		     String component = GetText("gettext");
			 if(component.equalsIgnoreCase(inp_compo.trim()))
			 {
				 Reports.Pass(guid + " Component Matched for Guid" , genericMethods.textbyxpath);
				 ReportGenerate.writeResult("TC01_Component", Constants.program_title, guid, inp_compo, "", "Component Matched", genericMethods.textbyxpath, "PASS");
				 
			 }
			 else
			 {
				 Reports.Fail(guid + " Component not Matched for Guid" , genericMethods.textbyxpath);
				 ReportGenerate.writeResult("TC01_Component", Constants.program_title, guid, inp_compo, "", "Component Not Matched", genericMethods.textbyxpath, "FAIL");
			 }
		 
	    //Display title
			genericMethods.getTextbyxpath(".//*[@class='details--3xY92']/h2");
			String displaytitle = genericMethods.textbyxpath;
			if(displaytitle.equalsIgnoreCase(inp_disp_title.trim()))
			{
				 Reports.Pass("Display title Matched for Guid " +guid , genericMethods.textbyxpath);
				 ReportGenerate.writeResult("TC01_DisplayTitle", Constants.program_title, guid, "", inp_disp_title, "Display title Matched", genericMethods.textbyxpath, "PASS");
			}
			else
			{
				 Reports.Fail("Display title Mismatched for Guid " +guid, genericMethods.textbyxpath);
				 ReportGenerate.writeResult("TC01_DisplayTitle", Constants.program_title, guid, "", inp_disp_title, "Display title Not Matched", genericMethods.textbyxpath, "FAIL");
			}
		 
			//click Open and validate if link is broken
			//genericMethods.clickElement("//span[.='Open']");
			ClickElement("openLink");
	 		Thread.sleep(5000);
			
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		   	int tabCount = tabs.size();
		   	System.out.println(tabCount);
			
			try
			{
				if(tabCount==2)	
				{
					Thread.sleep(3000);
					genericMethods.switchWindow();
					//System.out.println("Link Launching in New Tab");
		 			Thread.sleep(1000);
		 			genericMethods.getCurrentURL();	
					URL = genericMethods.currURL;
					System.out.println("Output URL " + URL);
				
					if(genericMethods.driver.getPageSource().contains("The specified key does not exist.") || genericMethods.driver.getPageSource().contains("AccessDenied") || genericMethods.driver.getPageSource().contains("This XML file does not appear to have any style information associated with it. The document tree is shown below.") ||genericMethods.driver.getPageSource().contains("Parameter cannot be blank"))
					{	
						System.out.println("Broken Link");
						Reports.Fail("Broken Link - XML Error ", URL);
						ReportGenerate.writeResult("TC01_Link", Constants.program_title, guid, component,displaytitle,"Broken Link - XML Error", genericMethods.driver.getCurrentUrl(), "FAIL");
						Thread.sleep(1000);
						genericMethods.driver.close();
						genericMethods.driver.switchTo().window(genericMethods.parentWindow);
					}
					else
					{
						genericMethods.uricheck(URL);
					
						if(genericMethods.statusCode==404) 
						{
							ReportGenerate.writeResult("TC01_Link", Constants.program_title , guid, component,displaytitle,genericMethods.statusCode + " Broken Link", URL,  "FAIL");
						}
					
						if((genericMethods.statusCode==200) && (URL.equalsIgnoreCase(inp_uri)))
						{
							Reports.Pass("Link launched successfully", URL);
							ReportGenerate.writeResult("TC01_Link", Constants.program_title, guid, component,displaytitle,genericMethods.statusCode + " Link Launched Successfully", URL, "PASS");
						}
					
						else if((genericMethods.statusCode==400) && (URL.equalsIgnoreCase(inp_uri)))
						{
							Reports.Pass("Link launched successfully", URL);
							ReportGenerate.writeResult("TC01_Link", Constants.program_title, guid, component,displaytitle,genericMethods.statusCode + " Link Launched Successfully", URL, "PASS");
						}
				
						else
						{
							Reports.Pass("Link launched successfully but not matched with input", URL);
							ReportGenerate.writeResult("TC01_Link", Constants.program_title, guid, component,displaytitle,genericMethods.statusCode + " Link Launched Successfully but not matched with input", URL, "PASS");
						}
					
						Thread.sleep(1000);
						genericMethods.driver.close();
						genericMethods.driver.switchTo().window(genericMethods.parentWindow);
	    			
					}
				}
				else
				{
					if(inp_uri.contains(".jnlp") || inp_uri.contains(".rtf")|| inp_uri.contains(".pptx")|| inp_uri.contains(".doc") || inp_uri.contains(".tns") || inp_uri.contains(".8xp") || inp_uri.contains(".flipchart") || inp_uri.contains(".notebook") || inp_uri.contains(".bnk") || inp_uri.contains(".docx"))
					{
						genericMethods.driver.get("chrome://downloads/");
						System.out.println("Downloads Page has been Launched");
						System.out.println("Validating Downloaded URL");
						WebElement root1 = genericMethods.driver.findElement(By.tagName("downloads-manager"));//Get shadow root element
						WebElement shadowRoot1 = genericMethods.expandRootElement(root1);
						WebElement root3 = shadowRoot1.findElement(By.cssSelector("downloads-item"));
						WebElement shadowRoot3 = genericMethods.expandRootElement(root3);
						System.out.println("Shadow Root - 3rd Tree has been expand");
						String actualHeading = shadowRoot3.findElement(By.cssSelector("a[id=url")).getText();
						Thread.sleep(1000);
						System.out.println("Output URL " + actualHeading);
						ReportGenerate.writeResult("TC01_Link", Constants.program_title, guid, component,displaytitle,"Link Downloaded Successfully", actualHeading, "PASS");
						genericMethods.driver.navigate().back();
						System.out.println("MDS Page has been Launched");
					}
				}
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
	 	{ 
			Reports.after();
		}	
	}
}	
		   
			
            