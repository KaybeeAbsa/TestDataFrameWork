package Assign_Combi_Card_Numbers;


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

public class AssignCombiCardsMain {

    private String userData;
    private String runStatus;
    private String username;
    private String password;
    private String cardType;
    private String personalized;
    private String chequeAccountNo;
    private String creditCardAccountNo;
    private String savingsAccountNo;
    private String brand;
    private String branch;
    private String cardNo;
    private String autoLink;
    private String withdrwalLimit;
    private String transferLimit;
    private String posLimit;
    private String ChechPoint;
    private boolean userLoggedIn = false;
    private String checkLimits;
    private String user_limit;

    private AssignCombiCardsJagacy assignCardsJC = null;
    ExcelFunctions excel;

    int _username = 0;
    int _password = 0;
    int _RunStatus = 0;
    int _userData = 0;
    int _Results = 0;
    int _CardType = 0;
    int _Personalized = 0;
    int _CreditAccountNo = 0;
    int _SavingsAccountNo = 0;
    int _ChequeAccountNo = 0;
    int _Brand = 0;
    int _Branch = 0;
    int _CardN0 = 0;
    int _AutoLink = 0;
    int _WithdrawlLimit = 0;
    int _TransafareLimit = 0;
    int _PosLimit = 0;
    int _User_limit = 0;
    int _SetLimit = 0;
    int _Comment = 0;

    static ExtentReports extent =  null;
    static ExtentTest test = null;
    private Screen sikuliScreen = new Screen(0);
    private String capture= "";

    @Test
    public void mainDebitOrder() throws JagacyException, InterruptedException, IOException
    {
        String filePath = System.getProperty("user.dir")+"\\Data_File\\Assign_Combi_Card_Numbers_Excel\\Combis_Assign.xlsx";
        ReportFolder.ReportDirectory();

        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel = new ExcelFunctions();

            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _RunStatus = excel.columnsNames.indexOf("Run_Status");
            _Results = excel.columnsNames.indexOf("Results");
            _userData = excel.columnsNames.indexOf("User_Data");
            _ChequeAccountNo = excel.columnsNames.indexOf("Cheque_Account_No");
            _CreditAccountNo =  excel.columnsNames.indexOf("CreditCard_Account_No");
            _SavingsAccountNo =  excel.columnsNames.indexOf("Savings_Account_No");
            _AutoLink = excel.columnsNames.indexOf("Auto_Link");
            _Branch = excel.columnsNames.indexOf("Branch_Code");
            _Brand = excel.columnsNames.indexOf("Brand");
            _CardN0 = excel.columnsNames.indexOf("Card_No");
            _CardType = excel.columnsNames.indexOf("Card_Type");
            _Personalized = excel.columnsNames.indexOf("Personalized");
            _WithdrawlLimit = excel.columnsNames.indexOf("WithdrawalLimit");
            _TransafareLimit = excel.columnsNames.indexOf("TransaferLimit");
            _PosLimit = excel.columnsNames.indexOf("PosLimit");
            _User_limit = excel.columnsNames.indexOf("User_Limit");
            _SetLimit = excel.columnsNames.indexOf("SetLimit");
            _Comment = excel.columnsNames.indexOf("Comment");

            //Looping through the Excel Sheet
            for(int y = 1; y < ExcelFunctions.ScenarioCount; y++)
            {

                password = excel.ReadCell(y,_password);
                username = excel.ReadCell(y,_username);
                runStatus = excel.ReadCell(y,_RunStatus);
                userData = excel.ReadCell(y,_userData);
                chequeAccountNo = excel.ReadCell(y,_ChequeAccountNo);
                creditCardAccountNo = excel.ReadCell(y,_CreditAccountNo);
                savingsAccountNo = excel.ReadCell(y,_SavingsAccountNo);
                autoLink = excel.ReadCell(y, _AutoLink);
                branch = excel.ReadCell(y,_Branch);
                brand = excel.ReadCell(y, _Brand);
                cardType = excel.ReadCell(y, _CardType);
                cardNo = excel.ReadCell(y,_CardN0).trim();
                personalized = excel.ReadCell(y,_Personalized);
                withdrwalLimit = excel.ReadCell(y,_WithdrawlLimit);
                transferLimit = excel.ReadCell(y,_TransafareLimit);
                posLimit = excel.ReadCell(y,_PosLimit);
                user_limit = excel.ReadCell(y,_User_limit);

                if(runStatus.equalsIgnoreCase("RUN"))
                {

                    //Passing Jagacy Properties and Opening
                    System.setProperty("sessionA.window", "true");
                    assignCardsJC  = new AssignCombiCardsJagacy();
                    assignCardsJC.open();

                    ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                    //Login
                    userLoggedIn = assignCardsJC.userLogin(username, password);

                    if(userLoggedIn){

                        ChechPoint = assignCardsJC.assignCombiCardsNumbers(userData,cardType,personalized,chequeAccountNo, savingsAccountNo, creditCardAccountNo,"s",branch,autoLink, cardNo);

                        System.out.println(ChechPoint);
                                test = extent.startTest("Assign Combi Number ", "Test Case Scenarios");
                        test.assignAuthor("AUTHOR: Data Management Team");
                        test.assignCategory("Assign Card Number:");

                        if(ChechPoint.equalsIgnoreCase("CMMI  NEW  Client has Active Savings Acc, please Nominate")){

                            ChechPoint = assignCardsJC.checkSavingsAccount(chequeAccountNo);
                            savingsAccountNo = ChechPoint.replace("-","");
                            ChechPoint = assignCardsJC.assignCombiCardsNumbers(userData,cardType,personalized,chequeAccountNo, savingsAccountNo, creditCardAccountNo,"s",branch,autoLink, cardNo);
                        }

                        if(ChechPoint != null)
                        {

                            System.out.println("Check point things: " + ChechPoint);

                            if(ChechPoint.equalsIgnoreCase("CMMI  NEW  Additional Combi Card, Card Fee will be charged") || ChechPoint.equalsIgnoreCase("CMMI  NEW  ACCS - Stock Code is different in System") || checkLimits.equalsIgnoreCase("CMMI  NEW  Combi Card Number already exists"))
                            {
                                test.log(LogStatus.INFO, ChechPoint);
                                test.log(LogStatus.PASS, "Data Management Team");
                                excel.WriteToCell("FAIL","RUN",cardNo,"NO",ChechPoint,y,_RunStatus, _Results, _CardN0,_SetLimit,_Comment);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                assignCardsJC.close();
                            }else{

                                checkLimits = assignCardsJC.ChangeLinmit(user_limit,ChechPoint,withdrwalLimit,transferLimit,posLimit);

                                if(checkLimits.equalsIgnoreCase("Acknowledgement of daily limits"))
                                {
                                    test.log(LogStatus.INFO, ChechPoint);
                                    test.log(LogStatus.PASS, "Data Management Team");
                                    excel.WriteToCell("PASS","NO RUN",ChechPoint,"Yes","Passed",y,_RunStatus, _Results, _CardN0,_SetLimit,_Comment);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                    assignCardsJC.close();
                                }else
                                {
                                    test.log(LogStatus.INFO, ChechPoint);
                                    test.log(LogStatus.PASS, "Data Management Team");
                                    excel.WriteToCell("PASS","NO RUN",ChechPoint,"NO","Passed",y,_RunStatus, _Results, _CardN0,_SetLimit,_Comment);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                    assignCardsJC.close();
                                }

                            }

                        }else{

                            test.log(LogStatus.INFO, ChechPoint);
                            test.log(LogStatus.FAIL, "Data Management Team");
                            excel.WriteToCell("FAIL","RUN",cardNo,"NO",ChechPoint,y,_RunStatus, _Results, _CardN0,_SetLimit,_Comment);
                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                            String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            assignCardsJC.close();
                        }
                        assignCardsJC.close();
                    }else{
                        test = extent.startTest("Assign Combi Number", "Test Case Scenarios");
                        test.assignAuthor("AUTHOR: Data Management Team");
                        test.assignCategory("Assign Combi Number");
                        test.log(LogStatus.INFO, "User Login");
                        test.log(LogStatus.FAIL, "Invalid User Credintials...");
                        excel.WriteToCell("FAIL","RUN",cardNo,"NO","Check your Login Credentials: ",y,_RunStatus, _Results, _CardN0,_SetLimit,_Comment);

                        assignCardsJC.close();
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
