package Holds;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddFicLockMain {

    public static void main(String[] args) throws IOException {

        String userData;
        String username;
        String password;
        String result;
        String comment;
        String runStatus;
        String clientID;
        String message;
        String idNumber;
        String idType;

        boolean userLoggedIn = false;
        FicaHolds ficaHolds = null;
        ExcelFunctions excel;

        int _username = 0;
        int _password = 0;
        int _RunStatus = 0;
        int _userData = 0;
        int _Results = 0;
        int _clientID = 0;
        int _Comment = 0;
        int _idNumber = 0;
        int _IdType = 0;

        ExtentReports extent =  null;
        ExtentTest test = null;
        Screen sikuliScreen = new Screen(0);
        String capture= "";

        String filePath = System.getProperty("user.dir")+"\\Data_File\\Fica_Holds_Excel\\addFicaHold.xlsx";
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
            _clientID = excel.columnsNames.indexOf("Client_ID");
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
                    clientID = excel.ReadCell(y,_clientID);


                    if(runStatus.equalsIgnoreCase("RUN"))
                    {

                        System.setProperty("sessionA.window", "true");
                        ficaHolds  = new FicaHolds();
                        ficaHolds.open();

                        ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                        //Login
                        userLoggedIn = ficaHolds.userLogin(username, password);

                        //
                        if(userLoggedIn){

                            test = extent.startTest("Pre-Approved PL OD:", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: Data Management Team");
                            test.assignCategory("Pre-Approved PL OD:");

                            message = ficaHolds.addFicHold(userData,clientID);

                                if(message.equals("RISK LOCK ALREADY PLACED") || message.equalsIgnoreCase("< Data entry too short"))
                                {
                                    System.out.println("Failed");
                                    test.log(LogStatus.INFO, message);
                                    test.log(LogStatus.FAIL, "Data Management Team");
                                    excel.WriteToCell("FAIL","RUN",message,y,_RunStatus, _Results,_Comment);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                    ficaHolds.close();

                                }else{

                                    System.out.println("Passed: " + y +  clientID);
                                    test.log(LogStatus.INFO, message);
                                    test.log(LogStatus.PASS, "Data Management Team");
                                    excel.WriteToCell("PASS","NO RUN","Combi Card Number Can be used",y,_RunStatus, _Results, _Comment);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                    ficaHolds.close();

                                }

                        }else{
                            test = extent.startTest("Pre-Approved PL OD:", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: Data Management Team");
                            test.assignCategory("Assign Pre-Approved PL OD:");
                            test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                            test.log(LogStatus.FAIL, "Invalid User Credintials...");
                            excel.WriteToCell("Failed","NO RUN","Invalid User Credintials...",y,_RunStatus, _Results, _Comment);
                            ficaHolds.close();
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
