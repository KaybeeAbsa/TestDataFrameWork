package CBP.Change_Categories;

import com.jagacy.util.JagacyException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CbpProject {


        String userData;
        String username;
        String password;
        String result;
        String comment;
        String runStatus;
        String accountNo;
        String overDraftAmount;
        String message;
        String crg_data;
        String siteCode;
        String charge_day;
        String charge_statement;
        String charge_From;
        String price_schem;


        boolean userLoggedIn = false;
        CbpJagacy preApproved = null;
        ExcelFunctions excel;

        int _username = 0;
        int _password = 0;
        int _RunStatus = 0;
        int _userData = 0;
        int _Results = 0;
        int _accountNo = 0;
        int _Comment = 0;
        int _siteCode = 0;
        int _IdType = 0;
        int _Id_UserData = 0;
        int _OverDraftAmount = 0;
        int _crg_data = 0;
        int _charge_day;
        int  _charge_statement;
        int _charge_From;
        int  _price_schem;

        ExtentReports extent =  null;
        ExtentTest test = null;
        Screen sikuliScreen = new Screen(0);
        String capture= "";

        String filePath = System.getProperty("user.dir")+"\\Data_File\\CBP_Sheets\\Categories\\ChangeCategory.xlsx";

    @BeforeTest
    public void setUp() throws IOException {
        ReportFolder.ReportDirectory();
        extent = new ExtentReports(ReportFolder.fullReportPath, true);
        ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
        excel =  new ExcelFunctions();

        _username = excel.columnsNames.indexOf("Username");
        _password = excel.columnsNames.indexOf("Password");
        _RunStatus = excel.columnsNames.indexOf("Run_Status");
        _Results = excel.columnsNames.indexOf("Results");
        _userData = excel.columnsNames.indexOf("User_Data");
        _Comment = excel.columnsNames.indexOf("Comment");
        _accountNo = excel.columnsNames.indexOf("Account_No");
        _siteCode = excel.columnsNames.indexOf("Site_Code");
        _charge_day = excel.columnsNames.indexOf("Charge_Day");
        _charge_statement = excel.columnsNames.indexOf("Charge_Statement");
        _charge_From = excel.columnsNames.indexOf("Charge_Cap");
        _price_schem = excel.columnsNames.indexOf("Price_Scheme");

        }

    @Test(groups = "Check Site")
    public void CheckSite() throws IOException, JagacyException {

        for(int y = 1; y < ExcelFunctions.ScenarioCount; y++)
        {

            password = excel.ReadCell(y,_password);
            username = excel.ReadCell(y,_username);
            runStatus = excel.ReadCell(y,_RunStatus);
            userData = excel.ReadCell(y,_userData);
            accountNo = excel.ReadCell(y,_accountNo);
            //overDraftAmount = excel.ReadCell(y,_OverDraftAmount);

            if(runStatus.equalsIgnoreCase("RUN"))
            {

                System.setProperty("sessionA.window", "true");
                preApproved  = new CbpJagacy();
                preApproved.open();

                ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                //Login
                userLoggedIn = preApproved.userLogin(username, password);

                //
                if(userLoggedIn){

                    test = extent.startTest("Add OD:", "Test Case Scenarios");
                    test.assignAuthor("AUTHOR: Data Management Team");
                    test.assignCategory("Add OD:");

                    //  preApproved.checkMpp();

                    message = preApproved.CheckSite(userData,accountNo);

                    if(message.equalsIgnoreCase("INCREASE NOT ALLOWED IF NO OVERDRAFT EXISTS") || message.equalsIgnoreCase("NOT AUTHORIZED TO PROCESS OVERDRAFT")  || message.equalsIgnoreCase("ACCOUNT NUMBER DOES NOT EXIST") || message.equalsIgnoreCase("NO RAC-SITE FOR ACCOUNT DOMICILE") || message.equalsIgnoreCase("TRANSACTION NOT ALLOWED - UNDER DEBT COUNSELLING")){

                        System.out.println("Failed");
                        test.log(LogStatus.INFO, message);
                        test.log(LogStatus.FAIL, "Data Management Team");
                        excel.WriteToCell("FAIL","NO RUN",message,y,_RunStatus, _Results,_Comment);
                        capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                        String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                        test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                        preApproved.close();

                    }else{

                        System.out.println("Passed: " + message + " : " + y);
                        test.log(LogStatus.INFO, message);
                        test.log(LogStatus.PASS, "Data Management Team");
                        excel.WriteToCell("pass","RUN",message,y,_RunStatus, _Results, _siteCode);
                        capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                        String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                        test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                        preApproved.close();
                    }

                    preApproved.close();
                }else{
                    test = extent.startTest("Add OD:", "Test Case Scenarios");
                    test.assignAuthor("AUTHOR: Data Management Team");
                    test.assignCategory("Add OD:");
                    test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                    test.log(LogStatus.FAIL, "Invalid User Credintials...");
                    excel.WriteToCell("Failed","NO RUN","Invalid User Credintials...",y,_RunStatus, _Results, _Comment);
                    preApproved.close();
                }
            }
        }

    }

    @AfterGroups("Check Site")
    public void ChangeCRG() throws JagacyException, IOException {
        for(int y = 1; y < ExcelFunctions.ScenarioCount; y++)
        {

            password = excel.ReadCell(y,_password);
            username = excel.ReadCell(y,_username);
            runStatus = excel.ReadCell(y,_RunStatus);
            userData = excel.ReadCell(y,_userData);
            accountNo = excel.ReadCell(y,_accountNo);
            siteCode = excel.ReadCell(y,_siteCode);
            charge_day = excel.ReadCell(y,_charge_day);
            charge_statement = excel.ReadCell(y,_charge_statement);
            charge_From = excel.ReadCell(y,_charge_From);
            price_schem = excel.ReadCell(y,_price_schem);

           /* if(runStatus.equalsIgnoreCase("RUN"))
            {*/

                System.setProperty("sessionA.window", "true");
                preApproved  = new CbpJagacy();
                preApproved.open();

                ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                //Login
                userLoggedIn = preApproved.userLogin(username, password);

                //
                if(userLoggedIn){

                    test = extent.startTest("Add OD:", "Test Case Scenarios");
                    test.assignAuthor("AUTHOR: Data Management Team");
                    test.assignCategory("Add OD:");

                      preApproved.checkMpp(siteCode);

                    message = preApproved.changeCRG(accountNo, charge_day, charge_From, charge_statement, price_schem);

                    if(message.equalsIgnoreCase("TRAN PROCESSED")){

                        System.out.println("Passed: " + message + " : " + y);
                        test.log(LogStatus.INFO, message);
                        test.log(LogStatus.PASS, "Data Management Team");
                        excel.WriteToCell("pass","RUN",message,y,_RunStatus, _Results, _Comment);
                        capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                        String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                        test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                        preApproved.close();

                    }else{
                        System.out.println("Failed");
                        test.log(LogStatus.INFO, message);
                        test.log(LogStatus.FAIL, "Data Management Team");
                        excel.WriteToCell("FAIL","NO RUN",message,y,_RunStatus, _Results,_Comment);
                        capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                        String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                        test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                        preApproved.close();

                    }

                    preApproved.close();
                }else{
                    test = extent.startTest("Add OD:", "Test Case Scenarios");
                    test.assignAuthor("AUTHOR: Data Management Team");
                    test.assignCategory("Add OD:");
                    test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                    test.log(LogStatus.FAIL, "Invalid User Credintials...");
                    excel.WriteToCell("Failed","NO RUN","Invalid User Credintials...",y,_RunStatus, _Results, _Comment);
                    preApproved.close();
                }
            }
      //  }

    }

    @AfterTest
    public void afterTest()
    {
        extent.endTest(test);
        extent.flush();
    }

}
