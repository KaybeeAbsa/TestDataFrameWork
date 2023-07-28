package Personal_Loan.DN9;

import com.jagacy.util.JagacyException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PersonLoanCheckMain {

    private String userData;
    private String runStatus;
    private String username;
    private String password;
    private String accountNo;
    private String message;
    private boolean userLoggedIn = false;
    private PersonLoanCheckJagacy debitOrderJagacy = null;
    ExcelFunctions excel;

    int username_ = 0;
    int password_ = 0;
    int RunStatus_ = 0;
    int userData_ = 0;
    int results_ = 0;
    int accountNo_ = 0;
    int _Comment = 0;

    static ExtentReports extent =  null;
    static ExtentTest test = null;
    private Screen sikuliScreen = new Screen(0);
    private String capture= "";



    @Test
    public void mainDebitOrder() throws JagacyException, InterruptedException, IOException
    {
        String filePath = System.getProperty("user.dir")+"\\Data_File\\Personal_Loan\\DM9\\PersonalLoanDM9.xlsx";
        ReportFolder.ReportDirectory();

        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();

            username_ = excel.columnsNames.indexOf("Username");
            password_ = excel.columnsNames.indexOf("Password");
            RunStatus_ = excel.columnsNames.indexOf("Run_Status");
            userData_ = excel.columnsNames.indexOf("User_Data");
            results_ = excel.columnsNames.indexOf("Results");
            accountNo_ = excel.columnsNames.indexOf("account_No");
            _Comment = excel.columnsNames.indexOf("Comment");

            //Looping through the Excel Sheet
            for(int y = 1; y < ExcelFunctions.ScenarioCount; y++)
            {
                password = excel.ReadCell(y,password_).trim();
                username = excel.ReadCell(y,username_).trim();
                runStatus = excel.ReadCell(y,RunStatus_).trim();
                userData = excel.ReadCell(y,userData_);
                accountNo = excel.ReadCell(y,accountNo_).trim();

                if(runStatus.equalsIgnoreCase("RUN"))
                {

                    //Passing Jagacy Properties and Opening
                    //System.setProperty("sessionA.window", "true");
                    debitOrderJagacy  = new PersonLoanCheckJagacy();
                    debitOrderJagacy.open();

                    ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                    //Login
                    userLoggedIn = debitOrderJagacy.userLogin(username, password);

                    //
                    if(userLoggedIn){

                        test = extent.startTest("Client Profile ", "Test Case Scenarios");
                        test.assignAuthor("AUTHOR: Data Management Team");
                        test.assignCategory("Checking Client Profile:");

                        message = debitOrderJagacy.CheckDM9Indicator(userData, accountNo);

                        if(message.equalsIgnoreCase(""))
                        {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Client Profile  ");
                                excel.WriteToCell("NO RUN",message,"FAIL",y,RunStatus_,_Comment,results_); capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                debitOrderJagacy.close();
                        } else{

                                test.log(LogStatus.INFO, "Client Profile ");
                                test.log(LogStatus.PASS, "Client Profile ");
                                excel.WriteToCell("NO RUN",message,"PASS",y,RunStatus_,_Comment,results_);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                debitOrderJagacy.close();
                        }
                        debitOrderJagacy.close();
                    }else{
                        test = extent.startTest("Client Profile  ", "Test Case Scenarios");
                        test.assignAuthor("AUTHOR: Data Management Team");
                        test.assignCategory("Client Profile ");
                        test.log(LogStatus.INFO, "User Login");
                        test.log(LogStatus.FAIL, "Invalid User Credintials...");
                        excel.WriteToCell("NO RUN",message,"FAIL",y,RunStatus_,_Comment,results_);
                        debitOrderJagacy.close();
                    }
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @AfterTest
    public void afterTestDebitOrder()
    {
        extent.endTest(test);
        extent.flush();
    }
}
