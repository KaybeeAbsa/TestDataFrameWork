package PreApproved_Accounts;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PreApprovedPLODMain {

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
        String id_UserData;
        String new_Scoring_Grade;
        String new_TMR;
        String new_AMR;
        String new_AMLR;
        String new_SOL;
        String new_AOL;
        String new_Capped_AOL;
        String new_CF_Grade;
        String new_CDTO;
        String new_UDTO;
        String new_Gross_Income;
        String new_CB_Commit;
        String new_Max_Living_EXP;
        String new_Accl;

        boolean userLoggedIn = false;
        PreApprovedPLODJagacy preApproved = null;
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
        int _Id_UserData = 0;
        int _new_Scoring_Grade = 0;
        int _new_TMR = 0;
        int _new_AMR =0;
        int _new_AMLR = 0;
        int _new_SOL = 0;
        int _new_AOL =0;
        int _new_Capped_AOL = 0;
        int _new_CF_Grade = 0;
        int _new_CDTO = 0;
        int _new_UDTO =0;
        int _new_Gross_Income =0;
        int _new_CB_Commit = 0;
        int _new_Max_Living_EXP =0;
        int _new_Accl = 0;

        ExtentReports extent =  null;
        ExtentTest test = null;
        Screen sikuliScreen = new Screen(0);
        String capture= "";

       // String filePath = "C:\\Automation\\Combi Card Filter\\sheets\\PreApprovedPLOD.xlsx";
        String filePath = System.getProperty("user.dir")+"\\Data_File\\PreApproved_Accounts_Excel\\PreApprovedPLOD.xlsx";
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
            _idNumber = excel.columnsNames.indexOf("id_number");
            _IdType = excel.columnsNames.indexOf("id_Type");
            _Id_UserData = excel.columnsNames.indexOf("Id_User_Data");
            _new_Scoring_Grade = excel.columnsNames.indexOf("New_Scoring_Grade");
            _new_TMR = excel.columnsNames.indexOf("New_TMR");
            _new_AMR = excel.columnsNames.indexOf("NEW_AMR");
            _new_AMLR = excel.columnsNames.indexOf("New_AMLR");
            _new_SOL = excel.columnsNames.indexOf("New_SOL");
            _new_AOL = excel.columnsNames.indexOf("New_AOL");
            _new_Capped_AOL = excel.columnsNames.indexOf("New_Capped_AOL");
            _new_CF_Grade = excel.columnsNames.indexOf("New_CF_Grade");
            _new_CDTO = excel.columnsNames.indexOf("New_CDTO");
            _new_UDTO = excel.columnsNames.indexOf("New_UDTO");
            _new_Gross_Income = excel.columnsNames.indexOf("New_Gross_Income");
            _new_CB_Commit = excel.columnsNames.indexOf("New_CB_Commit");
            _new_Max_Living_EXP = excel.columnsNames.indexOf("New_Max_Living_EXP");
            _new_Accl = excel.columnsNames.indexOf("New_Accl");

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
                    idNumber = excel.ReadCell(y,_idNumber);
                    id_UserData = excel.ReadCell(y,_Id_UserData);
                    idType = excel.ReadCell(y,_IdType);
                    new_Scoring_Grade = excel.ReadCell(y,_new_Scoring_Grade);
                    new_TMR = excel.ReadCell(y,_new_TMR);
                    new_AMR = excel.ReadCell(y,_new_AMR);
                    new_AMLR = excel.ReadCell(y,_new_AMLR);
                    new_SOL = excel.ReadCell(y,_new_SOL);
                    new_AOL = excel.ReadCell(y,_new_AOL);
                    new_Capped_AOL = excel.ReadCell(y,_new_Capped_AOL);
                    new_CF_Grade  = excel.ReadCell(y,_new_CF_Grade);
                    new_CDTO  = excel.ReadCell(y,_new_CDTO);
                    new_UDTO  = excel.ReadCell(y,_new_UDTO);
                    new_Gross_Income = excel.ReadCell(y,_new_Gross_Income);
                    new_CB_Commit = excel.ReadCell(y,_new_CB_Commit);
                    new_Max_Living_EXP = excel.ReadCell(y,_new_Max_Living_EXP);
                    new_Accl = excel.ReadCell(y,_new_Accl);

                    if(runStatus.equalsIgnoreCase("RUN"))
                    {

                       System.setProperty("sessionA.window", "true");
                        preApproved  = new PreApprovedPLODJagacy();
                        preApproved.open();

                        ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                        //Login
                        userLoggedIn = preApproved.userLogin(username, password);

                        //
                        if(userLoggedIn){

                            test = extent.startTest("Pre-Approved PL OD:", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: Data Management Team");
                            test.assignCategory("Pre-Approved PL OD:");

                            if(clientID.equals(""))
                            {
                                message = preApproved.getClientCode(id_UserData,idType,idNumber);

                                if(message.equals(""))
                                {
                                    System.out.println("Failed 2");
                                    test.log(LogStatus.INFO, message);
                                    test.log(LogStatus.FAIL, "Data Management Team");
                                    excel.WriteToCell("FAIL","RUN",message,y,_RunStatus, _Results,_Comment);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                    preApproved.close();

                                }else{

                                    System.out.print("Client id: " + message);

                                    if(clientID.equals("")){
                                        message = preApproved.preApprovedPLOD(userData, message, new_Scoring_Grade, new_TMR, new_AMR, new_AMLR, new_SOL, new_AOL, new_Capped_AOL, new_CF_Grade, new_CDTO, new_UDTO, new_Gross_Income, new_CB_Commit, new_Max_Living_EXP, new_Accl);
                                    }else{
                                        message = preApproved.preApprovedPLOD(userData,clientID, new_Scoring_Grade, new_TMR, new_AMR, new_AMLR, new_SOL, new_AOL, new_Capped_AOL, new_CF_Grade, new_CDTO, new_UDTO, new_Gross_Income, new_CB_Commit, new_Max_Living_EXP, new_Accl);
                                    }

                                        if(message.equals("") || message.equalsIgnoreCase("No Update"))
                                        {
                                            System.out.println("Failed ");
                                            test.log(LogStatus.INFO, message);
                                            test.log(LogStatus.FAIL, "Data Management Team");
                                            excel.WriteToCell("FAIL","RUN",message,y,_RunStatus, _Results,_Comment);
                                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                            String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                            preApproved.close();

                                        }else{

                                            System.out.println("Passed: " + y + message);
                                            test.log(LogStatus.INFO, message);
                                            test.log(LogStatus.PASS, "Data Management Team");
                                            excel.WriteToCell("PASS","NO RUN","Combi Card Number Can be used",y,_RunStatus, _Results, _Comment);
                                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                            String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                            preApproved.close();

                                        }
                                }
                            }else{

                                message = preApproved.preApprovedPLOD(userData,clientID, new_Scoring_Grade, new_TMR, new_AMR, new_AMLR, new_SOL, new_AOL, new_Capped_AOL, new_CF_Grade, new_CDTO, new_UDTO, new_Gross_Income, new_CB_Commit, new_Max_Living_EXP, new_Accl);

                                if(message.equals(""))
                                {
                                    System.out.println("Failed");
                                    test.log(LogStatus.INFO, message);
                                    test.log(LogStatus.FAIL, "Data Management Team");
                                    excel.WriteToCell("FAIL","RUN",message,y,_RunStatus, _Results,_Comment);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                    preApproved.close();

                                }else{

                                    System.out.println("Passed: " + y +  clientID);
                                    test.log(LogStatus.INFO, message);
                                    test.log(LogStatus.PASS, "Data Management Team");
                                    excel.WriteToCell("PASS","NO RUN","Combi Card Number Can be used",y,_RunStatus, _Results, _Comment);
                                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                    preApproved.close();

                                }
                            }

                            preApproved.close();
                        }else{
                            test = extent.startTest("Pre-Approved PL OD:", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: Data Management Team");
                            test.assignCategory("Assign Pre-Approved PL OD:");
                            test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                            test.log(LogStatus.FAIL, "Invalid User Credintials...");
                            excel.WriteToCell("Failed","NO RUN","Invalid User Credintials...",y,_RunStatus, _Results, _Comment);
                            preApproved.close();
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
