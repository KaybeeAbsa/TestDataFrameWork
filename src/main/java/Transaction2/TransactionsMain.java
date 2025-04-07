package Transaction2;

import Transaction_Cheques_and_Savings.ExcelFunctions;
import Transaction_Cheques_and_Savings.ReportFolder;
import Transaction_Cheques_and_Savings.TransactionsJagacy;
import com.jagacy.util.JagacyException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TransactionsMain
{
  /*  private String userData;
    private String dataUsage;
    private String username;
    private String password;
    private String controllerID;
    private String accountNo;
    private String transactionNo;
    private String TransactionAmount1;
    private String transactionType;
    private String amount;
    private String message;
    private String runStatus;
    private String testName;

    public static boolean validTest = false;
    private MainJagacy jc = null;
    ExcelFunctions excel;

    int _accountNO = 0;
    int _transactionType = 0;
    int _transactionAmount = 0;
    int _transactionAmount1 = 0;
    int _controlerID = 0;
    int _userData = 0;
    int _transactionNo = 0;
    int _username = 0;
    int _password = 0;
    int _RunStatus = 0;
    int _dateUsage = 0;
    int _Results = 0;
    int _TestName = 0;

    static ExtentReports extent =  null;
    static ExtentTest test = null;
    private Screen sikuliScreen = new Screen(0);
    private String capture = "";*/

    public static void main(String[] args) throws IOException, JagacyException, InterruptedException, AWTException {

        //String filePath = "C:\\Automation\\datasheet\\BasicSheets.xlsx";
        String filePath = System.getProperty("user.dir")+"\\Data_File\\Transaction2_Excel\\TransactionsSheets2.xlsx";
        Transaction_Cheques_and_Savings.ReportFolder.ReportDirectory();

         String userData;
         String dataUsage;
         String username;
         String password;
         String controllerID;
         String accountNo;
         String transactionNo;
         String TransactionAmount1;
         String transactionType;
         String amount;
         String message;
         String runStatus;
         String testName;
         String bankCode;

         boolean validTest = false;

         TransactionsJagacy jc = null;
         Transaction_Cheques_and_Savings.ExcelFunctions excel;

        int _accountNO =0;
        int _transactionType =0;
        int _transactionAmount = 0;
        int _transactionAmount1 = 0;
        int _controlerID = 0;
        int _userData = 0;
        int _transactionNo = 0;
        int _username = 0;
        int _password = 0;
        int _RunStatus = 0;
        int _dateUsage = 0;
        int _Results = 0;
        int _TestName = 0;
        int number = 1;
        int _BankCode = 0;
        int _Comment = 0;

         ExtentReports extent =  null;
         ExtentTest test = null;

         String capture= "";

        try {

            extent = new ExtentReports(Transaction_Cheques_and_Savings.ReportFolder.fullReportPath, true);
            Transaction_Cheques_and_Savings.ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new Transaction_Cheques_and_Savings.ExcelFunctions();

            _accountNO = excel.columnsNames.indexOf("AccountNo");
            _transactionType =excel.columnsNames.indexOf("TransactionType");
            _transactionAmount = excel.columnsNames.indexOf("TransactionAmount");
            _transactionAmount1 = excel.columnsNames.indexOf("TransactionAmount1");
            _controlerID = excel.columnsNames.indexOf("ControllerID");
            _userData = excel.columnsNames.indexOf("UserData");
            _transactionNo = excel.columnsNames.indexOf("TransactionNo");
            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _RunStatus = excel.columnsNames.indexOf("Run_Status");
            _Results = excel.columnsNames.indexOf("Results");
            _dateUsage = excel.columnsNames.indexOf("DataUser");
            _TestName = excel.columnsNames.indexOf("TestName");
            _BankCode = excel.columnsNames.indexOf("BankCode");
            _Comment = excel.columnsNames.indexOf("Comment");


            int reply = JOptionPane.showConfirmDialog(null, "Do you want to run Transactions...", "CBP Transactions...", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
            for(int y = 1; y < Transaction_Cheques_and_Savings.ExcelFunctions.ScenarioCount; y++) {

                transactionType = excel.ReadCell(y,_transactionType);
                TransactionAmount1 = excel.ReadCell(y,_transactionAmount1);
                userData = excel.ReadCell(y,_userData);
                accountNo = excel.ReadCell(y,_accountNO);
                amount = excel.ReadCell(y,_transactionAmount);
                transactionNo = excel.ReadCell(y,_transactionNo);
                controllerID = excel.ReadCell(y,_controlerID);
                password = excel.ReadCell(y,_password);
                username = excel.ReadCell(y,_username);
                dataUsage = excel.ReadCell(y,_dateUsage);
                runStatus = excel.ReadCell(y,_RunStatus);
                runStatus = excel.ReadCell(y,_RunStatus);
                testName = excel.ReadCell(y,_TestName);
                bankCode = excel.ReadCell(y,_BankCode);

                if(runStatus.equalsIgnoreCase("RUN"))
                {
               //    System.setProperty("sessionA.window", "true");
                    jc = new TransactionsJagacy();
                    jc.open();

                    ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                    validTest = jc.userLogin(username, password);

                    if(validTest)
                    {
                        test = extent.startTest("MainFrame Transactions: ", "Test Case Scenarios");
                        test.assignAuthor("AUTHOR: Data Management Team");
                        test.assignCategory("Transactions:");
                        if(transactionType.equalsIgnoreCase("bas")){
                            message = jc.basicEnqury(userData, transactionType, accountNo.trim(), amount, transactionNo).trim();

                            System.out.println(y + " Basic Message: " + message + " : Account No: " + accountNo);
                            if(message.equalsIgnoreCase("NUMBER INCORRECT / NOT ON FILE"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Basic ");
                                excel.WriteToCell("Fail","NO RUN",message,y,_RunStatus, _Results,_Comment);
                                //     capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Basic.ReportFolder.screenshortReportPath+File.separator+screenshotName));

                            }else{

                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Basic ");
                                excel.WriteToCell("Pass","NO RUN",message,y,_RunStatus, _Results, _Comment);
                                //   capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Basic.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }if(transactionType.equalsIgnoreCase("CD")) {

                            message = jc.cashDeposit(userData, transactionType, accountNo.trim(), amount, transactionNo).trim();

                            System.out.println("Transaction: " + message + " Account No: " + accountNo +" : "+ y);
                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cash Deposit");
                                excel.WriteToCell("PASS","NO RUN",message,y,_RunStatus, _Results,_Comment);
                           //     capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));

                            }else{

                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cash Deposit");
                                excel.WriteToCell("FAIL","NO RUN",message,y,_RunStatus, _Results, _Comment);
                             //   capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("CW")) {


                            message = jc.cashWithdraw(userData, transactionType, accountNo, amount, transactionNo, controllerID).trim();

                            System.out.println("Transaction: " + message + " Account No: " + accountNo +" : "+ y);
                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cash WIthdrawal");
                                excel.WriteToCell("PASS","NO RUN",message,y,_RunStatus, _Results,_Comment);
                               // capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{

                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cash Withdrawal");
                                excel.WriteToCell("FAIL","NO RUN",message,y,_RunStatus, _Results,_Comment);
                                //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }
                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("MD")) {

                            message = jc.MixedDeposit(userData, transactionType, accountNo, amount, TransactionAmount1, transactionNo, controllerID).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Mixed Deposit");
                                excel.WriteToCell("PASS","NO RUN",message,y,_RunStatus, _Results,_Comment);
                                //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{

                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Mixed Deposit");
                                excel.WriteToCell("FAIL","NO RUN",message,y,_RunStatus, _Results,_Comment);
                            }

                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("QD")) {

                            message =  jc.chequeDeposit(userData, transactionType, accountNo, amount, transactionNo, controllerID).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cheque Deposit");
                                //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                excel.WriteToCell("PASS","NO RUN",message,y,_RunStatus,_Results,_Comment);
                            }else{
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cheque Deposit");
                                excel.WriteToCell("FAIL","NO RUN",message,y,_RunStatus, _Results,_Comment);
                                //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("QP")) {


                            message =  jc.ChequePayment(userData, transactionType, accountNo, amount, transactionNo, controllerID).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cheque Payment");
                                excel.WriteToCell("PASS","NO RUN",message,y,_RunStatus, _Results,_Comment);
                                //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cheque Payment");
                                excel.WriteToCell("FAIL","NO RUN",message,y,_RunStatus, _Results,_Comment);
                                //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }
                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("CWC")) {


                            message =  jc.CashWithdrawalClose(userData, dataUsage, transactionType, accountNo, amount, transactionNo, controllerID, bankCode).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cash Withdrawal Closet");
                                excel.WriteToCell("PASS","NO RUN",message,y,_RunStatus, _Results,_Comment);
                                //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cash Withdrawal Close");
                                excel.WriteToCell("FAIL","NO RUN",message,y,_RunStatus, _Results,_Comment);
                                //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("CDC")) {

                            message =  jc.CashDepositClose(userData, dataUsage, transactionType, accountNo, amount, transactionNo, controllerID, bankCode).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cash Deposit Closet");
                                excel.WriteToCell("PASS","NO RUN",message,y,_RunStatus, _Results,_Comment);
                              //  capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cash Deposit Close");
                                excel.WriteToCell("FAIL","NO RUN",message,y,_RunStatus, _Results,_Comment);
                                //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }

                    }else {
                        test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                        test.log(LogStatus.FAIL, "Invalid User Credintials...");
                        excel.WriteToCell("FAIL","NO RUN","Invalid User Credintials...",y,_RunStatus, _Results,_Comment);
                        //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                        String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                        test.log(LogStatus.INFO, test.addScreenCapture(Transaction_Cheques_and_Savings.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                        jc.close();
                    }
                }else
                {
                    number = 2;
                    test = extent.startTest("Transaction: ", "Test Case Scenarios");
                    test.assignAuthor("AUTHOR: AutoBotos");
                    test.assignCategory("Transaction");

                    test.log(LogStatus.INFO, "skipped(No run Status)");
                    test.log(LogStatus.SKIP, "Unpaid Debit");
                  //  excel.WriteToCell("PASS","RUN",y,_RunStatus, _Results);
                    //capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                    String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                }
            } }

            else {
                JOptionPane.showMessageDialog(null, "Transactions Execution has been Cancelled...");
                System.exit(0);
            }

            if(number == 2)
            {
                JOptionPane.showMessageDialog(null, "NO Transaction with Run Status on the sheet!! Fix your Transaction Sheet...");
            }
            extent.endTest(test);
            extent.flush();
            JOptionPane.showMessageDialog(null, "Your Transactions have been processed successfully...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* @Test
    public void TestMain() throws JagacyException, InterruptedException, IOException {

        String filePath = System.getProperty("user.dir")+"\\src\\main\\java\\datasheet\\BasicSheets.xlsx";
        ReportFolder.ReportDirectory();

        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();

            _accountNO = excel.columnsNames.indexOf("AccountNo");
            _transactionType =excel.columnsNames.indexOf("TransactionType");
            _transactionAmount = excel.columnsNames.indexOf("TransactionAmount");
            _transactionAmount1 = excel.columnsNames.indexOf("TransactionAmount1");
            _controlerID = excel.columnsNames.indexOf("ControllerID");
            _userData = excel.columnsNames.indexOf("UserData");
            _transactionNo = excel.columnsNames.indexOf("TransactionNo");
            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _RunStatus = excel.columnsNames.indexOf("Run_Status");
            _Results = excel.columnsNames.indexOf("Results");
            _dateUsage = excel.columnsNames.indexOf("DataUser");
            _TestName = excel.columnsNames.indexOf("TestName");

            for(int y = 1; y < ExcelFunctions.ScenarioCount; y++) {

                transactionType = excel.ReadCell(y,_transactionType);
                TransactionAmount1 = excel.ReadCell(y,_transactionAmount1);
                userData = excel.ReadCell(y,_userData);
                accountNo = excel.ReadCell(y,_accountNO);
                amount = excel.ReadCell(y,_transactionAmount);
                transactionNo = excel.ReadCell(y,_transactionNo);
                controllerID = excel.ReadCell(y,_controlerID);
                password = excel.ReadCell(y,_password);
                username = excel.ReadCell(y,_username);
                dataUsage = excel.ReadCell(y,_dateUsage);
                runStatus = excel.ReadCell(y,_RunStatus);
                testName = excel.ReadCell(y,_TestName);

                if(runStatus.equalsIgnoreCase("NO RUN"))
                {
                    System.setProperty("sessionA.window", "true");
                    jc = new MainJagacy();
                    jc.open();

                    ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                    validTest = jc.userLogin(username, password);

                    if(validTest)
                    {
                        if(transactionType.equalsIgnoreCase("CD")) {

                            test = extent.startTest("Cash Deposit: ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: AutoBotos");
                            test.assignCategory("Cash Deposit");

                            message = jc.cashDeposit(userData, transactionType, accountNo, amount, transactionNo).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cash Deposit");
                                excel.WriteToCell("PASS","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));

                            }else{

                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cash Deposit");
                                excel.WriteToCell("FAIL","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("CW")) {

                            test = extent.startTest("Cash Withdrawal: ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: AutoBotos");
                            test.assignCategory("Cash Withdrawal");

                            message = jc.cashWithdraw(userData, transactionType, accountNo, amount, transactionNo, controllerID).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cash WIthdrawal");
                                excel.WriteToCell("PASS","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{

                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cash Withdrawal");
                                excel.WriteToCell("FAIL","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }
                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("MD")) {

                            test = extent.startTest("Mixed Deposit: ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: AutoBotos");
                            test.assignCategory("Mixed Deposit");
                            message = jc.MixedDeposit(userData, transactionType, accountNo, amount, TransactionAmount1, transactionNo, controllerID).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Mixed Deposit");
                                excel.WriteToCell("PASS","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{

                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Mixed Deposit");
                                excel.WriteToCell("FAIL","RUN",y,_RunStatus, _Results);
                            }

                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("QD")) {

                            test = extent.startTest("Cheque Deposit: ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: AutoBotos");
                            test.assignCategory("Cheque Deposit");

                            message =  jc.chequeDeposit(userData, transactionType, accountNo, amount, transactionNo, controllerID).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cheque Deposit");
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                excel.WriteToCell("PASS","RUN",y,_RunStatus, _Results);
                            }else{
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cheque Deposit");
                                excel.WriteToCell("FAIL","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("QP")) {

                            test = extent.startTest("Cheque Payment: ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: AutoBotos");
                            test.assignCategory("Cheque Payment");

                            message =  jc.ChequePayment(userData, transactionType, accountNo, amount, transactionNo, controllerID).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cheque Payment");
                                excel.WriteToCell("PASS","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cheque Payment");
                                excel.WriteToCell("FAIL","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("CWC")) {

                            test = extent.startTest("Cash Withdrawal Close: ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: AutoBotos");
                            test.assignCategory("Cash Withdrawal Closet");

                            message =  jc.CashWithdrawalClose(userData, dataUsage, transactionType, accountNo, amount, transactionNo, controllerID).trim();

                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cash Withdrawal Closet");
                                excel.WriteToCell("PASS","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cash Withdrawal Close");
                                excel.WriteToCell("FAIL","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }else if(transactionType.equalsIgnoreCase("CDC")) {

                            test = extent.startTest("Cash Deposit Close: ", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: AutoBotos");
                            test.assignCategory("Cash Deposit Closet");

                            message =  jc.CashDepositClose(userData, dataUsage, transactionType, accountNo, amount, transactionNo, controllerID).trim();

                            System.out.println("Run Status: " + message);
                            if(message.equalsIgnoreCase("TRANSACTION PROCESSED"))
                            {
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.PASS, "Cash Deposit Closet");
                                excel.WriteToCell("PASS","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }else{
                                test.log(LogStatus.INFO, message);
                                test.log(LogStatus.FAIL, "Cash Deposit Close");
                                excel.WriteToCell("FAIL","RUN",y,_RunStatus, _Results);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            }

                            jc.close();
                        }
                    } else {
                        test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                        test.log(LogStatus.FAIL, "Invalid User Credintials...");
                        excel.WriteToCell("FAIL","RUN",y,_RunStatus, _Results);
                        capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                        String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                        test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                        jc.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Transactions Processed...!");
    }

    @AfterTest
    public void getResult(){
        //JOptionPane.showMessageDialog(null, "Transactions Processed...");
        extent.endTest(test);
        extent.flush();
    }*/

    public static int yesnocancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,"alert", JOptionPane.YES_NO_CANCEL_OPTION);
        return result;
    }
}