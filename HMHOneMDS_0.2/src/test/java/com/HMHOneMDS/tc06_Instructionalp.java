package com.HMHOneMDS;

import java.io.IOException;

import com.utilities.Constants;
import com.utilities.ReadConfigInpData;
import com.utilities.ReportGenerate;
import com.utilities.Reports;
import com.utilities.genericMethods;

public class tc06_Instructionalp  extends genericMethods{

	genericMethods method = new genericMethods();
	ReportGenerate report = new ReportGenerate();
	ReadConfigInpData read = new ReadConfigInpData();
	Constants c = new Constants();
	String op_inst;

	public void instructionalPurpose(String guid, String inp_compo, String inp_disp_title, String inp_inst) throws IOException {

		System.out.println("Input Inst: " +inp_inst);

		try
		{
			//	op_inst = genericMethods.getTextbyxpath(".//*[@class = 'container']/div[2]/div/ul/li/label/span");
			//op_inst = genericMethods.getTextbyxpath(".//section[@class='open--3Z0JU']/ul/li/label");//*[@class = 'container']/div[2]/div/ul/li/checkbox
			op_inst = GetText("openText");
			System.out.println(op_inst);
			if(op_inst.equals(inp_inst.trim()))
			{
				Reports.Pass("Instructional type Matched", op_inst);
				ReportGenerate.writeResult("TC06_Instructional", Constants.program_title, guid, inp_compo, inp_disp_title,"Instructional type Matched", op_inst, "PASS");
			}
			else
			{
				Reports.Fail("Instructional type not Matched", op_inst);
				ReportGenerate.writeResult("TC06_Instructional", Constants.program_title, guid, inp_compo, inp_disp_title,"Instructional type not Matched", op_inst, "Fail");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		finally
		{
			Reports.after();
		}	


	}

}
