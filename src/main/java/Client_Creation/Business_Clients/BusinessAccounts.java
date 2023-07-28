package Client_Creation.Business_Clients;

import com.jagacy.util.JagacyException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BusinessAccounts {

    private String userData;
    private String username;
    private String password;
    private String companyName;
    private String clientTypeGroup;
    private String accountOption;
    private String idType;
    private String idNumber;
    private String Language;
    private String clientType;
    private String subClass;
    private String sicCode;
    private String detailedSub;
    private String perc;
    private String refNo;

    private String message;
    private String runStatus;
    private String accountNo;

    private String address1;
    private String address2;
    private String city;
    private String town;
    private String areaCode;
    private String country1;
    private String email;
    private String countNo;
    private String contactPerson;

    private String occupation;
    private String companyAddress;
    private String companyTown;
    private String companyAreaCode;
    private String companyCity;
    private String MonthlyIncome;
    private String SourceOfIncome;
    private String NoDepandats;
    private String ResidentStatus;
    private String homeLangauge;
    private String Qualification;
    private String PostQualification;
    private String socialGrant;
    private String DateIdentified;
    private String empNo;
    private String DateVerified	;
    private String ProductType;
    private String methodDelivary;
    private String TaxNo;
    private String taxDataNo;
    private String FOREIGNTAX;
    private String sourceFund;
    private String accountName;

    public static boolean validTest = false;
    private BusinessAccountJagacy jc = null;
    ExcelFunctions excel;


    int _userData = 0;
    int _username = 0;
    int _password = 0;
    int _RunStatus = 0;
    int _companyName;
    int _clientTypeGroup = 0;
    int _accountOption;
    int _idType;
    int _idNumber =0;
    int _Language;
    int _clientType;
    int _subClass;
    int _sicCode;
    int _detailedSub;
    int _perc;
    int _refNo;


    int _Results = 0;


    int _address1;
    int _address2;
    int _city;
    int _town;
    int _areaCode;
    int _country1;
    int _email;
    int _countNo;
    int _contactPerson;



    int _MonthlyIncome;
    int _SourceOfIncome;

    int _DateIdentified;
    int _empNo;
    int _DateVerified;
    int _ProductType;
    int _methodDelivary;
    int _TaxNo;
    int _TaxDataNo;
    int _FOREIGNTAX;
    int _sourceFund;
    int _accountNo;
    int _Comment;
    int _TransactionCode;
    int _TransactionStatus;
    int _TransactionType;
    int _TransactionAmount;
    int _TransactionComment;
    int _AccountName;

    static ExtentReports extent =  null;
    static ExtentTest test = null;
    private Screen sikuliScreen = new Screen(0);
    private String capture= "";
    private  String filePath = System.getProperty("user.dir")+"\\Data_File\\Client_Sheet\\Business_clients\\BusinessClient.xlsx";

    @BeforeTest
    public void setUp() throws IOException {

        ReportFolder.ReportDirectory();

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();

            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _RunStatus = excel.columnsNames.indexOf("RunStatus");
            _Results = excel.columnsNames.indexOf("Results");
            _userData = excel.columnsNames.indexOf("userData");
            _companyName = excel.columnsNames.indexOf("CompanyName");
            _clientTypeGroup = excel.columnsNames.indexOf("ClientTypeGroup");
            _accountOption  = excel.columnsNames.indexOf("OptionAccount");
            _idType  = excel.columnsNames.indexOf("IdType");
            _idNumber = excel.columnsNames.indexOf("IdNumber");
            _Language  = excel.columnsNames.indexOf("Language");
            _clientType = excel.columnsNames.indexOf("ClientType");
             _subClass = excel.columnsNames.indexOf("SubClass");
             _sicCode= excel.columnsNames.indexOf("SICCode");
             _detailedSub= excel.columnsNames.indexOf("detailedSub");
             _perc= excel.columnsNames.indexOf("percentage");
             _refNo  = excel.columnsNames.indexOf("RefNo");

            _address1 = excel.columnsNames.indexOf("address1");
            _address2 = excel.columnsNames.indexOf("address2");
            _city = excel.columnsNames.indexOf("city");
            _town = excel.columnsNames.indexOf("town");
            _areaCode = excel.columnsNames.indexOf("areaCode");
            _country1 = excel.columnsNames.indexOf("country");
            _email = excel.columnsNames.indexOf("email");
            _countNo = excel.columnsNames.indexOf("contactNo");
            _contactPerson = excel.columnsNames.indexOf("contactPerson");

            _MonthlyIncome = excel.columnsNames.indexOf("MonthlyIncome");
            _SourceOfIncome = excel.columnsNames.indexOf("SourceOfIncome");
            _DateIdentified = excel.columnsNames.indexOf("DateIdentified") ;
            _empNo = excel.columnsNames.indexOf("empNo");
            _DateVerified = excel.columnsNames.indexOf("DateVerified") ;
            _ProductType = excel.columnsNames.indexOf("ProductType");
            _methodDelivary = excel.columnsNames.indexOf("methodDelivary");
            _TaxNo = excel.columnsNames.indexOf("TaxNo");
            _TaxDataNo  = excel.columnsNames.indexOf("TaxDataNumber");
            _FOREIGNTAX = excel.columnsNames.indexOf("FOREIGNTAX");
            _sourceFund = excel.columnsNames.indexOf("sourceFund");
            _accountNo = excel.columnsNames.indexOf("AccountNo");
            _Comment = excel.columnsNames.indexOf("Comment");
            _TransactionCode = excel.columnsNames.indexOf("TransactionCode");
            _TransactionStatus = excel.columnsNames.indexOf("Transaction_Status");
            _TransactionType = excel.columnsNames.indexOf("TransactionType");
            _TransactionAmount = excel.columnsNames.indexOf("TransactionAmount");
            _TransactionComment = excel.columnsNames.indexOf("TransactionComment");
            _AccountName = excel.columnsNames.indexOf("AccountName");
    }

    @Test(groups = "Create Customer")
    public void TestCustomerBoarding()throws JagacyException, IOException {

        for (int y = 1; y < ExcelFunctions.ScenarioCount; y++) {

            test = extent.startTest("Client Creation ", "Test Case Scenarios");
            test.assignAuthor("AUTHOR: Data Management Team");
            test.assignCategory("Client Creation: " + y );

            password = excel.ReadCell(y, _password);
            username = excel.ReadCell(y, _username);
            runStatus = excel.ReadCell(y, _RunStatus);
            userData = excel.ReadCell(y, _userData);
            companyName = excel.ReadCell(y, _companyName);
            clientTypeGroup = excel.ReadCell(y, _clientTypeGroup);
            accountOption = excel.ReadCell(y, _accountOption);
            idType = excel.ReadCell(y, _idType);
            idNumber = excel.ReadCell(y, _idNumber);
            Language = excel.ReadCell(y, _Language);
            clientType = excel.ReadCell(y, _clientType);
           subClass = excel.ReadCell(y, _subClass);
           sicCode = excel.ReadCell(y, _sicCode);
           detailedSub = excel.ReadCell(y, _detailedSub);
           perc = excel.ReadCell(y, _perc);
           refNo = excel.ReadCell(y, _refNo);

           address1 = excel.ReadCell(y, _address1);
           address2 = excel.ReadCell(y, _address2);
           city = excel.ReadCell(y, _city);
           town = excel.ReadCell(y, _town);
           areaCode = excel.ReadCell(y, _areaCode);
           country1 = excel.ReadCell(y, _country1);
           email = excel.ReadCell(y, _email);
           countNo = excel.ReadCell(y, _countNo);
           contactPerson = excel.ReadCell(y,_contactPerson);

            MonthlyIncome = excel.ReadCell(y, _MonthlyIncome);
            SourceOfIncome = excel.ReadCell(y, _SourceOfIncome);
            empNo = excel.ReadCell(y, _empNo);
            ProductType = excel.ReadCell(y, _ProductType);
            methodDelivary = excel.ReadCell(y, _methodDelivary);
            TaxNo = excel.ReadCell(y, _TaxNo);
            taxDataNo      = excel.ReadCell(y, _TaxDataNo);
            FOREIGNTAX = excel.ReadCell(y, _FOREIGNTAX);
            sourceFund = excel.ReadCell(y, _sourceFund);
            accountName  =excel.ReadCell(y, _AccountName);

            if (runStatus.equalsIgnoreCase("RUN")) {
                System.setProperty("sessionA.window", "true");
                jc = new BusinessAccountJagacy();
                jc.open();

                ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                validTest = jc.userLogin(username, password);

                if (validTest) {

                    message = jc.CustomerOnBoarding(userData,companyName,clientTypeGroup, accountOption, idType, idNumber,Language, clientType, subClass, sicCode, detailedSub, perc, contactPerson, refNo, address1, town, areaCode, country1, email, countNo,
                            MonthlyIncome, empNo,ProductType, methodDelivary, TaxNo, taxDataNo, FOREIGNTAX, sourceFund, accountName);

                    if (message.equalsIgnoreCase("") || message.equalsIgnoreCase("DUPLICATE RECORDS EXIST, PLEASE RESOLVE") || message.equalsIgnoreCase("INVALID SITE FOR CAPTURE") || message.equalsIgnoreCase("ABSA ISLAMIC PRIVATE ACCOUNT MAY ONLY BE OPENED BY") || message.equalsIgnoreCase("ACC TYPE ONLY VALID FOR ABSA WEALTH SITES") || message.equalsIgnoreCase("ACCOUNT TYPE AND BANKING SECTOR INCOMPATIBLE") || message.equalsIgnoreCase("STRUCTURED LOAN MAY ONLY BE OPENED BY A PRIVATE BA") || message.equalsIgnoreCase("INVALID CATEGORY") || message.equalsIgnoreCase("client already exists") || message.equalsIgnoreCase("BIRTH DATE NOT THE SAME AS IN ID NUMBER") || message.equalsIgnoreCase("INVALID CHARACTERS IN NAME") || message.equalsIgnoreCase("DATE ISSUED CANNOT BE LESS THAN DATE OF BIRTH") || message.equalsIgnoreCase("ID NUMBER INVALID") || message.equalsIgnoreCase("CLIENT TYPE AND ACCOUNT TYPE INCOMPATIBLE") || message.equalsIgnoreCase("INCOMPATIBLE CATEGORY AND DATE OF BIRTH")) {
                        System.out.println("Empty:   " + y);
                        test.log(LogStatus.INFO, message);
                        test.log(LogStatus.FAIL, "Client Creation");
                        excel.WriteToCell("Fail", "RUN", "0", message, "no run", y, _RunStatus, _Results, _accountNo, _Comment,_TransactionStatus);
                        capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                        String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                        test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                        jc.close();

                    } else {

                        String data = message.substring(0, 25).trim();
                        int messageLeangth = message.length();
                        String account = message.substring(25, messageLeangth);

                        if (data.equalsIgnoreCase("CLIENT INFORMATION UPDATE")) {
                            test.log(LogStatus.INFO, message);
                            test.log(LogStatus.PASS, "Client Creation");
                            excel.WriteToCell("PASS", "run", account, "Succesfully Created", "RUN", y, _RunStatus, _Results, _accountNo, _Comment,_TransactionStatus);
                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                            String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                            jc.close();
                        } else {
                            test.log(LogStatus.INFO, message);
                            test.log(LogStatus.FAIL, "Client Creation");
                            excel.WriteToCell("Fail", "RUN", "0", message,"No Run", y, _RunStatus, _Results, _accountNo, _Comment,_TransactionStatus);
                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                            String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                            jc.close();
                        }
                    }
                } else {
                    test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                    test.log(LogStatus.FAIL, "Invalid User Credintials...");
                    excel.WriteToCell("FAIL", "RUN", "0", message, "No run",y, _Results, _RunStatus, _accountNo, _Comment,_TransactionStatus);
                    capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                    String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                    test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                    jc.close();
                }
            }
        }
    }

    @AfterTest
    public void AfterCustomerBoarding()  {
        extent.endTest(test);
        extent.flush();
    }
}
