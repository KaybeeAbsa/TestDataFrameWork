package Combi_Cards_Filter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CombiCardFilterMain {

    public static void main(String[] args) throws IOException {

        String userData;
        String username;
        String password;
        String cardType;
        String personalized;
        String chequeAccountNo;
        String creditCardAccountNo;
        String savingsAccountNo;
        String brand;
        String branch;
        String cardNo;
        String autoLink;
        String result;
        String ChechPoint;
        String comment;
        String runStatus;

        boolean userLoggedIn = false;
        CombiCardFilterJagacy combiCardFilterJagacy = null;
        ExcelFunctions excel;

        int _username = 0;
        int _password = 0;
        int _RunStatus = 0;
        int _userData = 0;
        int _Results = 0;
        int _CardType = 0;
        int _Personalized = 0;
        int _AccountNo = 0;
        int _CreditAccountNo = 0;
        int _SavingsAccountNo = 0;
        int _ChequeAccountNo = 0;
        int _Brand = 0;
        int _Branch = 0;
        int _CardN0 = 0;
        int _AutoLink = 0;
        int _Comment = 0;

        ExtentReports extent =  null;
        ExtentTest test = null;
        Screen sikuliScreen = new Screen(0);
        String capture= "";

       // String filePath = "C:\\Automation\\Combi Card Filter\\DataSheets\\CombiCardFilter.xlsx";
        String filePath = System.getProperty("user.dir")+"\\Data_File\\Combi_Card_Filter_Excel\\CombiCardFilter.xlsx";
        ReportFolder.ReportDirectory();

        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();

            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _RunStatus = excel.columnsNames.indexOf("Run_Status");
            _Results = excel.columnsNames.indexOf("Results");
            _userData = excel.columnsNames.indexOf("User_Data");
            _ChequeAccountNo = excel.columnsNames.indexOf("Cheque_Account_No");
            _CreditAccountNo =  excel.columnsNames.indexOf("CreditCard_Account_No");
            _SavingsAccountNo =  excel.columnsNames.indexOf("Savings_Account_No");
            _AutoLink = excel.columnsNames.indexOf("Auto_Link");
            _Branch = excel.columnsNames.indexOf("Branch");
            _Brand = excel.columnsNames.indexOf("Brand");
            _CardN0 = excel.columnsNames.indexOf("Card_No");
            _CardType = excel.columnsNames.indexOf("Card_Type");
            _Personalized = excel.columnsNames.indexOf("Personalized");
            _Comment = excel.columnsNames.indexOf("Comment");

            int reply = JOptionPane.showConfirmDialog(null, "Do you want to Filter Combi Cards...", "Combi Cards Filter...", JOptionPane.YES_NO_OPTION);

            if(reply == JOptionPane.YES_OPTION) {

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
                    cardNo = excel.ReadCell(y,_CardN0);
                    personalized = excel.ReadCell(y,_Personalized);

                    if(runStatus.equalsIgnoreCase("RUN"))
                    {

                        //Passing Jagacy Properties and Opening
                        System.setProperty("sessionA.window", "true");
                        combiCardFilterJagacy  = new CombiCardFilterJagacy();
                        combiCardFilterJagacy.open();

                        ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                        //Login
                        userLoggedIn = combiCardFilterJagacy.userLogin(username, password);

                        //
                        if(userLoggedIn){

                            ChechPoint = combiCardFilterJagacy.filterCombiCard(userData,cardType,personalized,chequeAccountNo, savingsAccountNo, creditCardAccountNo,"s",branch,autoLink, cardNo);

                            test = extent.startTest("Combi Card Filter ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: Data Management Team");
                            test.assignCategory("Combi Card Filter:");

                            if(ChechPoint.equals(""))
                            {
                                System.out.println("Failed 2");
                                test.log(LogStatus.INFO, ChechPoint);
                                test.log(LogStatus.FAIL, "Data Management Team");
                                excel.WriteToCell("FAIL","RUN",ChechPoint,y,_RunStatus, _Results,_Comment);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                combiCardFilterJagacy.close();

                            }else{

                                if(ChechPoint.equalsIgnoreCase("CMMI  NEW  Card Number invalid, please enter valid Number") || ChechPoint.equalsIgnoreCase("CMMI  NEW  Combi Card Number already exists"))
                                {
                                    System.out.println("Failed  1:" + y);
                                    test.log(LogStatus.INFO, ChechPoint);
                                    test.log(LogStatus.FAIL, "Data Management Team");
                                    excel.WriteToCell("Failed","NO RUN",ChechPoint,y,_RunStatus, _Results, _Comment);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                    combiCardFilterJagacy.close();

                                }else{
                                    System.out.println("Passed" + y);
                                    test.log(LogStatus.INFO, ChechPoint);
                                    test.log(LogStatus.PASS, "Data Management Team");
                                    excel.WriteToCell("PASS","NO RUN","Combi Card Number Can be used",y,_RunStatus, _Results, _Comment);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                    combiCardFilterJagacy.close();
                                }
                            }
                            combiCardFilterJagacy.close();
                        }else{
                            test = extent.startTest("Combi Card Filter ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: Data Management Team");
                            test.assignCategory("Combi Card Filter");
                            test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                            test.log(LogStatus.FAIL, "Invalid User Credintials...");
                            excel.WriteToCell("Failed","NO RUN","Invalid User Credintials...",y,_RunStatus, _Results, _Comment);
                            combiCardFilterJagacy.close();
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Combi Cards Filter Execution has been Cancelled...");
                System.exit(0);
            }

            extent.endTest(test);
            extent.flush();
            JOptionPane.showMessageDialog(null, "Combi Card Filter Complete...");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
