package Checking_Customer_Profile;

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

public class CustomerProfileMain {

    private String userData;
    private String runStatus;
    private String username;
    private String password;
    private String id_Number;
    private String accountNo;
    private String user_aul;
    private String clientProfile;
    private String message;
    private String clientCode;

    private boolean userLoggedIn = false;
    private CustomerProfileJagacy debitOrderJagacy = null;
    ExcelFunctions excel;
    int username_ = 0;
    int password_ = 0;
    int RunStatus_ = 0;
    int userData_ = 0;
    int aul_registered_ = 0;
    int id_Number_ = 0;
    int user_aul_ = 0;
    int Client_code_ = 0;
    int linked_Accounts_ = 0;
    int results_ = 0;
    int accountNo_ = 0;

    static ExtentReports extent =  null;
    static ExtentTest test = null;
//    private Screen sikuliScreen = new Screen(0);
    private String capture= "";

    @Test
    public void mainDebitOrder() throws JagacyException, InterruptedException, IOException
    {
        String filePath = System.getProperty("user.dir")+"\\Data_File\\Checking_Customer_Profile_Excel\\CustomerProfileSheet.xlsx";
        ReportFolder.ReportDirectory();

        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();

            username_ = excel.columnsNames.indexOf("Username");
            password_ = excel.columnsNames.indexOf("Password");
            RunStatus_ = excel.columnsNames.indexOf("Run_Status");
            aul_registered_ = excel.columnsNames.indexOf("Aul_Registered");
            userData_ = excel.columnsNames.indexOf("User_Data");
            id_Number_ = excel.columnsNames.indexOf("id_Number");
            user_aul_ = excel.columnsNames.indexOf("User_aul");
            Client_code_ = excel.columnsNames.indexOf("Client_code");
            linked_Accounts_ = excel.columnsNames.indexOf("Linked_Accounts");
            results_ = excel.columnsNames.indexOf("Results");
            accountNo_ = excel.columnsNames.indexOf("account_No");

            //Looping through the Excel Sheet
            for(int y = 1; y < ExcelFunctions.ScenarioCount; y++)
            {
                password = excel.ReadCell(y,password_).trim();
                username = excel.ReadCell(y,username_).trim();
                runStatus = excel.ReadCell(y,RunStatus_).trim();
                userData = excel.ReadCell(y,userData_);
                id_Number =  excel.ReadCell(y,id_Number_).trim();
                user_aul = excel.ReadCell(y,user_aul_);
                accountNo = excel.ReadCell(y,accountNo_).trim();
                clientCode = excel.ReadCell(y,Client_code_);

                if(runStatus.equalsIgnoreCase("RUN"))
                {

                    //Passing Jagacy Properties and Opening
//                    System.setProperty("sessionA.window", "true");
                    debitOrderJagacy  = new CustomerProfileJagacy();
                    debitOrderJagacy.open();

                    ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                    try {
                        //Login
                        userLoggedIn = debitOrderJagacy.userLogin(username, password);
                        //
                        if (userLoggedIn) {

                            test = extent.startTest("Client Profile ", "Test Case Scenarios");
                            test.assignCategory("Checking Client Profile:");
                            test.assignAuthor("AUTHOR: Data Management Team");

                            if (clientCode.equals("")) {

                                clientProfile = debitOrderJagacy.CheckCustomerProfile(userData, "01", id_Number, accountNo);

                                if (clientProfile.equalsIgnoreCase("") || clientProfile.equalsIgnoreCase("PASSWORD HAS EXPIRED.") ||clientProfile.equalsIgnoreCase("INVALID PASSPORT NUMBER") || clientProfile.equalsIgnoreCase("NO CLIENT RECORD FOUND ENTER TO PROCEED TO NAME SEARCH") || clientProfile.equalsIgnoreCase("ID NUMBER INVALID")) {
                                    test.log(LogStatus.INFO, message);
                                    test.log(LogStatus.FAIL, "Client Profile  ");
                                    excel.WriteToCell("RUN", "Client Code Not Found", "Not Found", "Linked Account Not Found", "FAIL", y, RunStatus_, Client_code_, aul_registered_, linked_Accounts_, results_);
                                  //  capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                                    String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                    debitOrderJagacy.close();
                                } else {

                                    message = debitOrderJagacy.CheckAUL(user_aul, clientProfile);

                                    ArrayList<String> linkedAccounts = debitOrderJagacy.checkAccountProfiles(user_aul, clientProfile);
                                    System.out.println("Linked Acounts Numbres: " + linkedAccounts.toString());
                                    System.out.println("Y:" + y + " : " + id_Number + " : " + clientProfile + " : " + message);

                                    test.log(LogStatus.INFO, "Client Profile ");
                                    test.log(LogStatus.PASS, "Client Profile ");
                                    excel.WriteToCell("NO RUN", clientProfile, message, linkedAccounts.toString(), "PASS", y, RunStatus_, Client_code_, aul_registered_, linked_Accounts_, results_);
                                    //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                                    String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                    debitOrderJagacy.close();
                                }
                            } else {
                                message = debitOrderJagacy.CheckAUL(user_aul, clientCode);

                                ArrayList<String> linkedAccounts = debitOrderJagacy.checkAccountProfiles(user_aul, clientCode);
                                System.out.println("Linked Acounts Numbres: " + linkedAccounts.toString());
                                System.out.println("Y:" + y + " : " + id_Number + " : " + clientProfile + " : " + message);

                                test.log(LogStatus.INFO, "Client Profile ");
                                test.log(LogStatus.PASS, "Client Profile ");
                                excel.WriteToCell("NO RUN", clientCode, message, linkedAccounts.toString(), "PASS", y, RunStatus_, Client_code_, aul_registered_, linked_Accounts_, results_);
                             //   capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                                String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                debitOrderJagacy.close();
                            }
                            debitOrderJagacy.close();
                        } else {
                            test = extent.startTest("Client Profile  ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: Data Management Team");
                            test.assignCategory("Client Profile ");
                            test.log(LogStatus.INFO, "User Login");
                            test.log(LogStatus.FAIL, "Invalid User Credintials...");
                            excel.WriteToCell("NO RUN", clientProfile, message, "Check Your User Credintials...", "FAIL", y, RunStatus_, Client_code_, aul_registered_, linked_Accounts_, results_);
                            debitOrderJagacy.close();
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
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
