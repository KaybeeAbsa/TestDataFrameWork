package Create_Existing_Client;

import com.jagacy.util.JagacyException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExistingClientMain {
    private String accountCode;
    private String username;
    private String password;
    private String code;
    private String accountNo;
    private String codeNo;
    private String productCode;
    private String accountAmount;
    private String employeeNo;
    private String message;
    private String runStatus;
    private String clientCode;
    private String idNumber;
    private String userdata;
    private String ulRegistration;

    public static boolean validTest = false;
    ExcelFunctions excel;

    int _AccountCode = 0;
    int _Code = 0;
    int _AccountNo = 0;
    int _CodeNo = 0;
    int _ProductCode = 0;
    int _EmployeeNo = 0;
    int _AccAmount = 0;
    int _username = 0;
    int _password  = 0;
    int _Results = 0;
    int _RunStatus = 0;
    int _ClientCode = 0;
    int _IdNumber = 0;
    int _UserData = 0;
    int _Registered_OUL = 0;

    public static CreateExistingClientJagacy mainFrame = null;

    static ExtentReports extent =  null;
    static ExtentTest test = null;
    private Screen sikuliScreen = new Screen(0);
    private String capture= "";

    @Test
    public void CreateCustomer() throws JagacyException, IOException, AWTException {

        try {
            String filePath = System.getProperty("user.dir")+"\\Data_File\\Create_Existing_Customer_Excel\\ExistingCustomer.xlsx";
            ReportFolder.ReportDirectory();

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();

            _AccountCode = excel.columnsNames.indexOf("AccountCode");
            _Code =excel.columnsNames.indexOf("Code");
            _AccountNo = excel.columnsNames.indexOf("AccountNo");
            _CodeNo = excel.columnsNames.indexOf("CodeNo");
            _ProductCode = excel.columnsNames.indexOf("ProductCode");
            _EmployeeNo = excel.columnsNames.indexOf("EmployeeNo");
            _AccAmount = excel.columnsNames.indexOf("AccountAmount");
            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _Results = excel.columnsNames.indexOf("newAccountNo");
            _RunStatus = excel.columnsNames.indexOf("Run_Status");
            _ClientCode = excel.columnsNames.indexOf("ClientCode");
            _IdNumber = excel.columnsNames.indexOf("IdNumber");
            _UserData = excel.columnsNames.indexOf("userData");
            _Registered_OUL = excel.columnsNames.indexOf("Registered_OUL");

            for(int x = 1; x < ExcelFunctions.ScenarioCount; x++)
            {
                System.setProperty("sessionA.window", "true");
                mainFrame = new CreateExistingClientJagacy();
                mainFrame.open();

                accountCode = excel.ReadCell(x, _AccountCode);
                code = excel.ReadCell(x, _Code);
                accountNo = excel.ReadCell(x, _AccountNo).trim();
                codeNo = excel.ReadCell(x, _CodeNo);
                productCode = excel.ReadCell(x, _ProductCode);
                employeeNo = excel.ReadCell(x, _EmployeeNo);
                accountAmount = excel.ReadCell(x, _AccAmount);
                username = excel.ReadCell(x,_username);
                password = excel.ReadCell(x,_password);
                runStatus  = excel.ReadCell(x,_RunStatus);
                idNumber = excel.ReadCell(x,_IdNumber);
                userdata = excel.ReadCell(x,_UserData);
                clientCode = excel.ReadCell(x,_ClientCode).trim();

                ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                if(runStatus.equalsIgnoreCase("RUN")) {

                    validTest = mainFrame.userLogin(username, password);

                    test = extent.startTest("Create an Existing Account: ", "Test Case Scenarios");
                    test.assignAuthor("AUTHOR: Data Management Team");
                    test.assignCategory("Create Existing Account");

                    if(validTest)
                    {

                        if(clientCode.equals("") && accountNo.equals("")) {

                            message = mainFrame.getClientCode(accountCode,"01",idNumber);

                            System.out.println("Client code: " + message);
                            if(message.equalsIgnoreCase(""))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Create Existing Account");
                                excel.WriteToCell("FAIL","RUN","",x,_Results, _RunStatus,_Registered_OUL);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                mainFrame.close();
                            }else
                            {

                                String newAccount = mainFrame.createNewAccount(accountCode,"01",idNumber, codeNo, productCode, employeeNo, accountAmount);

                                System.out.println("New Account: " + newAccount);
                                test.log(LogStatus.INFO, "Account Created");
                                test.log(LogStatus.PASS, "Create Existing Account");
                                excel.WriteToCell(message,"NO RUN",newAccount,x,_ClientCode,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                mainFrame.close();
                            }

                            mainFrame.close();

                        }else{

                            if(accountNo.equals("")){

                                message = mainFrame.customerCreation(accountCode, code,clientCode ,accountNo, codeNo, productCode, employeeNo, accountAmount).trim();

                                if (message.equalsIgnoreCase("NO CLIENT RECORD FOUND E")) {
                                    test.log(LogStatus.INFO, message);
                                    test.log(LogStatus.FAIL, "Create Existing Account");
                                    excel.WriteToCell("FAIL", "RUN", "", x, _Results, _RunStatus, _Registered_OUL);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                                    String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                    mainFrame.close();
                                } else {
                                    test.log(LogStatus.INFO, "Account Created");
                                    test.log(LogStatus.PASS, "Create Existing Account");
                                    excel.WriteToCell(message, "NO RUN", "", x, _Results, _RunStatus, _Registered_OUL);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                                    String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                    mainFrame.close();
                                }

                                mainFrame.close();
                            }else{

                                message = mainFrame.customerCreation(accountCode, code,clientCode ,accountNo, codeNo, productCode, employeeNo, accountAmount).trim();

                                if (message.equalsIgnoreCase("NO CLIENT RECORD FOUND E")) {
                                    test.log(LogStatus.INFO, message);
                                    test.log(LogStatus.FAIL, "Create Existing Account");
                                    excel.WriteToCell("FAIL", "RUN", "", x, _Results, _RunStatus, _Registered_OUL);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                                    String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                    mainFrame.close();
                                } else {
                                    test.log(LogStatus.INFO, "Account Created");
                                    test.log(LogStatus.PASS, "Create Existing Account");
                                    excel.WriteToCell(message, "NO RUN", "", x, _Results, _RunStatus, _Registered_OUL);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                                    String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                    mainFrame.close();
                                }
                                mainFrame.close();
                            }
                            mainFrame.close();
                        }

                    }else{
                        test = extent.startTest("Create an Existing Account: ", "Test Case Scenarios");
                        test.assignAuthor("AUTHOR: Data Management Team");
                        test.assignCategory("Create Existing Account");
                        test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                        test.log(LogStatus.FAIL, "Invalid User Credintials...");
                        excel.WriteToCell("FAIL","RUN","",x,_Results,_RunStatus,_Registered_OUL);
                        capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                        String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                        test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                        mainFrame.close();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void getResult(){
        extent.endTest(test);
        extent.flush();
    }
}