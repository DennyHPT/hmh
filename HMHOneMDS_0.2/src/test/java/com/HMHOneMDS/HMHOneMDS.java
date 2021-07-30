package com.HMHOneMDS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.Keys;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.utilities.Constants;
import com.utilities.ReadConfigInpData;
import com.utilities.ReportGenerate;
import com.utilities.Reports;
import com.utilities.TestData;
import com.utilities.genericMethods;

public class HMHOneMDS  extends genericMethods{
	
	static genericMethods method = new genericMethods();
	Constants c = new Constants();
	ReadConfigInpData read = new ReadConfigInpData();
	ReportGenerate report = new ReportGenerate();
	
	static tc01_URI_Validation tc01 = new tc01_URI_Validation();
	static tc02_location tc02 = new tc02_location();
	static tc03_assign tc03 = new tc03_assign();
	static tc04_mdescription tc04 = new tc04_mdescription();
	static tc06_Instructionalp tc06 = new tc06_Instructionalp();
	static tc07_standards tc07 = new tc07_standards();
	
	static int card_result_size, start, end;
	
	public static String guid, Prog_title, inp_location, inp_disp_title, inp_desc, inp_compo, inp_uri, inp_inst, inp_std, inp_assign;
		
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	


	ArrayList<ArrayList<String>> data = TestData.getSheet();

	@BeforeTest
	public void beforetest() throws IOException, InterruptedException {
		try {
			dataPropertiesFile();
			read.readConfigData();
			start = Integer.parseInt(read.startVal);
			end = Integer.parseInt(read.endVal);
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "\\testdata\\" + Constants.mds_filename));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(Constants.sheetname);
			ReportGenerate.createFolder(Constants.program_title);
			ReportGenerate.createHeader("Test Result"); // Excel Report
			Reports.logStart(); // ExtentReports
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
	}

	@Test

	public void login() throws InterruptedException, IOException {
		try {
			method.invokeApplication(Constants.browser, Constants.URL);
			Thread.sleep(3000);
			genericMethods.enterValuebyXpath("//*[@id='react-select-state-selector-input']", Constants.State);
			Thread.sleep(2000);
			genericMethods.enterValuebyXpath("//*[@id='react-select-district-selector-input']", Constants.District);
			Thread.sleep(2000);
	        genericMethods.clickByXpath("//*[@class='nedButton-primary-submit ']");
			Thread.sleep(3000);
            genericMethods.elementbyxpath("//*[@id='username']");
            genericMethods.element.sendKeys(Constants.username);
	        genericMethods.elementbyxpath("//*[@id='password']");
	        genericMethods.element.sendKeys(Constants.password);
		    genericMethods.clickByXpath("//*[@class='nedButton-primary-A ']");
			
			Reports.Pass("Login Successful",  Constants.username + " " + Constants.password);
			Thread.sleep(10000);
			genericMethods.clickByXpath("//li[@class='discover']");
			Reports.Info("Discover - Clicked", " ");
			Thread.sleep(5000);
			//Select the Program
			if(Constants.dropdown.equalsIgnoreCase("Y"))
			{
				genericMethods.clickElement("//program-dropdown[@data-data=' vm.programsList.data ']");
				genericMethods.elementsbyxpath("//ul[@role='menu']");
				genericMethods.elementsbyxpath("//*[@id='programDropdown']/div/ul/li");
				System.out.println(Constants.program_title);
				for(int i=0; i<genericMethods.itemsbyxpath.size(); i++)
				{
					  Prog_title = genericMethods.itemsbyxpath.get(i).getText();
					  if(Prog_title.equalsIgnoreCase(Constants.program_title))
					  {
						 genericMethods.itemsbyxpath.get(i).click();
						}
				}
			}
			else if(Constants.dropdown.equalsIgnoreCase("N"))
			{
				genericMethods.elementbyxpath("//*[@id='programDropdown']/h1");
			}
			Thread.sleep(5000);
			main_method();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

public static void main_method() throws InterruptedException, IOException
{
	try {
		//click search icon	
		//genericMethods.clickByXpath("//*[@id='discover']/main/arvo-sub-header/div[6]/arvo-button[1]/button");
		genericMethods.clickByXpath("//arvo-button[@label='Show search']");
		//genericMethods.clickByXpath("//*[@id='discover']/main/arvo-sub-header/div[6]/arvo-button[1]/button");
		Reports.Info("Search Clicked", " ");
		for(int y=start; y<end+1; y++)
		{
				System.out.println("Row #: " + y);
			
				if(Constants.mds_version.equalsIgnoreCase("onecms"))
				{
					guid = sheet.getRow(y).getCell(29).getStringCellValue();
					System.out.println(guid);
					inp_location = sheet.getRow(y).getCell(37).getStringCellValue();
					inp_disp_title = sheet.getRow(y).getCell(1).getStringCellValue();
					inp_desc = sheet.getRow(y).getCell(2).getStringCellValue();
					inp_compo = sheet.getRow(y).getCell(30).getStringCellValue();
					inp_assign = sheet.getRow(y).getCell(7).getStringCellValue();
					inp_uri = sheet.getRow(y).getCell(3).getStringCellValue();
					inp_std = sheet.getRow(y).getCell(54).getStringCellValue();
					inp_inst = sheet.getRow(y).getCell(5).getStringCellValue();
				}	
				else if(Constants.mds_version.equalsIgnoreCase("mdsv3.3"))

				{
					guid = sheet.getRow(y).getCell(38).getStringCellValue();
					System.out.println(guid);
					//inp_location = sheet.getRow(y).getCell(119).getStringCellValue();
					inp_disp_title = sheet.getRow(y).getCell(37).getStringCellValue();
					inp_desc = sheet.getRow(y).getCell(14).getStringCellValue();
					inp_compo = sheet.getRow(y).getCell(52).getStringCellValue();
					inp_assign = sheet.getRow(y).getCell(42).getStringCellValue();
					//inp_uri = sheet.getRow(y).getCell(47).getStringCellValue();
					inp_std = sheet.getRow(y).getCell(136).getStringCellValue();
					inp_inst = sheet.getRow(y).getCell(2).getStringCellValue();
				}
				
				//enter guid in search text box
				genericMethods.elementbyxpath("//label[@type='search']");	
				genericMethods.element.sendKeys(guid);
				genericMethods.element.sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				
				
				genericMethods.elementbyxpath("//ul[@id='resourceList']");
				
				if(genericMethods.element.isDisplayed())
				{
					//*[@id="resourceList"]/li/div[3]/div/button[1]
					//genericMethods.elementsbyxpath("//*[@id='resourceList']/li[1]/div[3]/div/button[1]/span");
				      //genericMethods.elementsbyxpath("//li[@id='resourceList']")
					   genericMethods.elementsbyxpath("//li[@class='resourceListItem--3PV_a']");
					{
					  card_result_size = genericMethods.itemsbyxpath.size();
					  if(card_result_size>1) {
							System.out.println("Duplicate Links");
							ReportGenerate.writeResult("", Constants.program_title, guid, inp_compo, inp_disp_title,
									"Duplicate links/Verifaction of 1st CARD", "", "WARNING");
							Reports.Fail("Duplicate links are displayed", " ");
						}

						if (Constants.tc1.equalsIgnoreCase("y")) {
							tc01.urivalidation(guid, inp_compo, inp_disp_title, inp_uri);
						}
						
						if (Constants.tc2.equalsIgnoreCase("y")) { 
							tc02.verifyLocation(guid, inp_compo, inp_disp_title, inp_location); 
						}
						
						if (Constants.tc3.equalsIgnoreCase("y")) {
							tc03.assign(guid, inp_compo, inp_disp_title, inp_assign);
						}
						if (Constants.tc4.equalsIgnoreCase("y")) {
							tc04.meaningfuldescription(guid, inp_compo, inp_disp_title, inp_desc);
						}
						
						if (Constants.tc6.equalsIgnoreCase("y")) {
							tc06.instructionalPurpose(guid, inp_compo, inp_disp_title, inp_inst);
						}
					
						if (Constants.tc7.equalsIgnoreCase("y")) {
							tc07.std_validation(guid, inp_compo, inp_disp_title, inp_std);
						}
						Thread.sleep(2000);
					}
					
				}
				else 
				  { 
					 ReportGenerate.writeResult("", Constants.program_title, guid," ","", "No Result Found"," ", "fail");
					 System.out.println("No Result found"); 
				  }
				genericMethods.clickElement(".//div[@class='arvo-search-box']//button[@aria-label='Clear text']");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
