package Client_Creation;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ClientCreationJagacy extends Session3270
{

    private int userIdRow;
    private int userIdColumn;
    private String message;
    private String value;
    private  String data;
    private ArrayList<String> numbers;
    private int valid = 0;

    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement element;
    public ClientCreationJagacy() throws JagacyException
    {
        super("sessionA","host3270.absa.co.za",993,"IBM-3279-5-E",true);
    }

    public boolean userLogin(String username,String password) throws JagacyException
    {
        waitForChange(10000);
        userIdRow = 22;
        userIdColumn = 26;
        this.writePosition(userIdRow, userIdColumn, "iv01");
        this.writeKey(Key.ENTER);

        this.waitForChange(10000);
        userIdRow = 14;
        userIdColumn = 10;
        this.writePosition(userIdRow,userIdColumn,username);

        userIdRow = 16;
        userIdColumn = 11 ;
        this.writePosition(userIdRow, userIdColumn, password);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow =  23;
        userIdColumn = 22;
        message = this.readPosition(userIdRow,userIdColumn,40).trim();

        if(message.equalsIgnoreCase("INCORRECT OR NO PASSWORD ENTERED.") || message.equalsIgnoreCase("USERID IS NOT DEFINED TO RACF.") || message.equalsIgnoreCase("Your USERID is already logged on.")) {
            return false;
        }else {

            userIdRow = 2;
            userIdColumn = 2;
            this.writePosition(userIdRow, userIdColumn, "/test mfs");
            this.writeKey(Key.ENTER);
            return true;
        }
    }

    //Create Customer Onboarding
    public String CustomerOnBoarding(String userData, String idNumber, String surname, String intials, String Title, String Gender,
                                     String clientType, String accountOption, String firstName, String idType, String dateIssued, String Nationality, String country,
                                     String marriedStatus, String Language, String occuptationStatus, String clientType1, String refN0, String address1, String address2,
                                     String city, String town,  String areaCode,  String country1, String email, String countNo,String occupation, String companyName,
                                     String companyAddress, String companyCity, String companyTown, String companyAreaCode, String MonthlyIncome, String SourceOfIncome, String NoDepandats,
                                     String ResidentStatus, String homeLangauge, String Qualification, String PostQualification, String socialGrant, String DateIdentified, String empNo,
                                     String DateVerified, String ProductType, String methodDelivary , String TaxNo, String taxAccNo, String FOREIGNTAX , String sourceFund) throws JagacyException
    {


        this.waitForChange(1000);
        if(ProductType.equalsIgnoreCase("05016")){
            userIdRow = 0;
            userIdColumn = 0;
            this.writePosition(userIdRow, userIdColumn,userData);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);
        }else{
            userIdRow = 2;
            userIdColumn = 1;
            this.writePosition(userIdRow, userIdColumn,userData);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);
        }

        String date = idNumber.substring(0,6);
        String year = date.substring(0,2);
        String month = date.substring(2,4);
        String day = date.substring(4,6);
        String newYear = "";
        String valid = year.substring(0,1);

        if(valid.equalsIgnoreCase("0"))
        {
            newYear = "20" + year;
        }else
        {
            newYear = "19" + year;
        }

        String dateOfBirth = day+month+newYear;

        if(clientType.equalsIgnoreCase("N"))
        {
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 14;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, clientType);

            userIdRow = 8;
            userIdColumn = 35;
            this.writePosition(userIdRow, userIdColumn,"");
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();

            if(message.equals("")){

                userIdRow = 2;
                userIdColumn = 10;
                this.writePosition(userIdRow, userIdColumn,accountOption);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);


                return "";
            }else {
                return message;
            }

        }else {


            this.writeKey(Key.ENTER);

            this.waitForChange(1000);

            userIdRow = 8;
            userIdColumn = 35;
            this.writePosition(userIdRow, userIdColumn, surname);

            userIdRow = 13;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, intials);

            userIdRow = 14;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, Title);

      /*  int dateLength = dateOfBirth.length();
        if(dateLength != 8)
        {
            String newDate = "0" + dateOfBirth;
            userIdRow = 15;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, newDate);
        }else
        {
            userIdRow = 15;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, dateOfBirth);
        }*/

            userIdRow = 15;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, dateOfBirth);

            userIdRow = 16;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, Gender);

            userIdRow = 19;
            userIdColumn = 36;
            this.writePosition(userIdRow, userIdColumn, clientType);
            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 50).trim();

            System.out.println("Passed : " + message);

            if (message.equalsIgnoreCase("TYPE E AND F5/F6 OR TYPE M/A/N AND ENTER")) {
                message = "client already exists";
                return message;
            }

            if (message.equalsIgnoreCase("INVALID BIRTH DATE - ENTER IN DDMMCCYY FORMAT")) {
                message = "INVALID BIRTH DATE - ENTER IN DDMMCCYY FORMAT";
                return message;
            }

       /*if(message != null)
        {
            System.out.println("Failed : " + message);
            return message;
        }else{*/
            System.out.println("Passed : " + message);
            userIdRow = 2;
            userIdColumn = 9;
            this.writePosition(userIdRow, userIdColumn, accountOption);

            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 50).trim();
//            System.out.println("Messages: " + message);

            userIdRow = 2;
            userIdColumn = 57;
            this.writePosition(userIdRow, userIdColumn, refN0);

            userIdRow = 5;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, firstName);

            userIdRow = 10;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, idType);

            userIdRow = 11;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, idNumber);

            userIdRow = 11;
            userIdColumn = 58;
            this.writePosition(userIdRow, userIdColumn, dateIssued);

            userIdRow = 12;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, Nationality);

            userIdRow = 13;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, country);

            userIdRow = 15;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, marriedStatus);

            userIdRow = 17;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, Language);

            userIdRow = 18;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, occuptationStatus);

            userIdRow = 19;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, clientType1);

            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 50).trim();

            System.out.println("Message before address: " + message);

            if (message.equalsIgnoreCase("INVALID CHARACTERS IN NAME") || message.equalsIgnoreCase("DATE ISSUED CANNOT BE LESS THAN DATE OF BIRTH"))
            {
                return message;
            }
            if(message.equalsIgnoreCase("GENDER CODE INVALID TO ID NUMBER")){
                if(Gender.equalsIgnoreCase("M"))
                {
                    Gender = "f";
                    userIdRow = 8;
                    userIdColumn = 23;
                    this.writePosition(userIdRow, userIdColumn, Gender);

                    Title = "03";
                    userIdRow = 7;
                    userIdColumn = 23;
                    this.writePosition(userIdRow, userIdColumn, Title);
                }else
                {
                    Gender = "M";
                    userIdRow = 8;
                    userIdColumn = 23;
                    this.writePosition(userIdRow, userIdColumn, Gender);

                    Title = "01";
                    userIdRow = 7;
                    userIdColumn = 23;
                    this.writePosition(userIdRow, userIdColumn, Title);
                }

                this.writeKey(Key.ENTER);
                this.waitForChange(10000);

                userIdRow = 22;
                userIdColumn = 8;
                message = this.readPosition(userIdRow, userIdColumn, 50).trim();

                System.out.println("Message before address: " + message);

                if(message.equalsIgnoreCase("DUPLICATE RECORDS EXIST, PLEASE RESOLVE")){
                    return message;
                }
            }


            if(message.equalsIgnoreCase("BIRTH DATE NOT THE SAME AS IN ID NUMBER") ||message.equalsIgnoreCase("DUPLICATE RECORDS EXIST, PLEASE RESOLVE") || message.equalsIgnoreCase("ID NUMBER INVALID"))
            {
                return message;
            }

            userIdRow = 4;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, address1);

            userIdRow = 4;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, address1);

            userIdRow = 5;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, address2);

            userIdRow = 5;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, address2);

            userIdRow = 6;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, town);

            userIdRow = 6;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, town);

            userIdRow = 7;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, city);

            userIdRow = 7;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, city);

            userIdRow = 8;
            userIdColumn = 19;
            this.writePosition(userIdRow, userIdColumn, areaCode);

            userIdRow = 8;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, areaCode);

            userIdRow = 9;
            userIdColumn = 26;
            this.writePosition(userIdRow, userIdColumn, country1);

            userIdRow = 10;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, email);

            userIdRow = 14;
            userIdColumn = 77;
            this.writePosition(userIdRow, userIdColumn, "Y");

            String newContacts = "0" + countNo;
            userIdRow = 15;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, countNo);

            userIdRow = 16;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 16;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");


            userIdRow = 17;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 17;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 18;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 18;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 19;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 19;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 20;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 20;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 21;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 21;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");
            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();

//            System.out.println("Messages: " + message);


            userIdRow = 4;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, occupation);

        /*userIdRow = 5;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "13");

        userIdRow = 6;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "07");

        userIdRow = 7;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "33331");

        userIdRow = 8;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "1038378");*/

            userIdRow = 13;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyName);

            userIdRow = 14;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyAddress);

            userIdRow = 15;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyTown);

            userIdRow = 16;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyCity);

            userIdRow = 17;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyAreaCode);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();
//
//            System.out.println("Messages: " + message);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 4;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, MonthlyIncome);

            userIdRow = 5;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, SourceOfIncome);

            userIdRow = 6;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, NoDepandats);

            userIdRow = 7;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, ResidentStatus);

            userIdRow = 8;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, homeLangauge);

            userIdRow = 11;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, Qualification);

            userIdRow = 12;
            userIdColumn = 40;
            this.writePosition(userIdRow, userIdColumn, PostQualification);

            userIdRow = 13;
            userIdColumn = 39;
            this.writePosition(userIdRow, userIdColumn, socialGrant);

            userIdRow = 14;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, DateIdentified);

            userIdRow = 15;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, empNo);

            userIdRow = 16;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, DateVerified);
            userIdRow = 17;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, empNo);

            userIdRow = 18;
            userIdColumn = 17;
            this.writePosition(userIdRow, userIdColumn, ProductType);

            userIdRow = 20;
            userIdColumn = 22;
            this.writePosition(userIdRow, userIdColumn, empNo);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 50).trim();

            System.out.println("Message Empo Stuff: " + message);

            if(message.equalsIgnoreCase("CLIENT TYPE AND ACCOUNT TYPE INCOMPATIBLE") ||message.equalsIgnoreCase("INVALID SITE FOR CAPTURE")|| message.equalsIgnoreCase("ABSA ISLAMIC PRIVATE ACCOUNT MAY ONLY BE OPENED BY") || message.equalsIgnoreCase("INCOMPATIBLE CATEGORY AND DATE OF BIRTH") || message.equalsIgnoreCase("INVALID CATEGORY") || message.equalsIgnoreCase("ACC TYPE ONLY VALID FOR ABSA WEALTH SITES") || message.equalsIgnoreCase("ACCOUNT TYPE AND BANKING SECTOR INCOMPATIBLE")||message.equalsIgnoreCase("STRUCTURED LOAN MAY ONLY BE OPENED BY A PRIVATE BA"))
            {
                return message;
            }


            userIdRow = 8;
            userIdColumn = 54;
            this.writePosition(userIdRow, userIdColumn, methodDelivary);

            if(TaxNo.equalsIgnoreCase("N")){
                userIdRow = 11;
                userIdColumn = 45;
                this.writePosition(userIdRow, userIdColumn, TaxNo);

                userIdRow = 15;
                userIdColumn = 37;
                this.writePosition(userIdRow, userIdColumn, FOREIGNTAX);
            }else{
                userIdRow = 11;
                userIdColumn = 45;
                this.writePosition(userIdRow, userIdColumn, TaxNo);

                userIdRow = 12;
                userIdColumn = 29;
                this.writePosition(userIdRow, userIdColumn, taxAccNo);

                userIdRow = 15;
                userIdColumn = 37;
                this.writePosition(userIdRow, userIdColumn, FOREIGNTAX);

                userIdRow = 17;
                userIdColumn = 3;
                this.writePosition(userIdRow, userIdColumn, country1);

                userIdRow = 17;
                userIdColumn = 9;
                this.writePosition(userIdRow, userIdColumn, taxAccNo);

            }

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();

//            System.out.println("Messages: " + message);

            userIdRow = 12;
            userIdColumn = 33;
            this.writePosition(userIdRow, userIdColumn, sourceFund);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();

//            System.out.println("Messages: " + message);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 11;
            userIdColumn = 21;
            String account = this.readPosition(userIdRow, userIdColumn, 25).trim();

            userIdRow = 14;
            userIdColumn = 1;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();
            this.writeKey(Key.ENTER);
            this.waitForChange(3000);

            userIdRow = 0;
            userIdColumn = 1;
            String check = this.readPosition(userIdRow, userIdColumn, 3).trim();

            System.out.println(check);

            if(check.equalsIgnoreCase("STL")){
                System.out.println("Inside STL");
                this.writeKey(Key.ENTER);
                this.waitForChange(30000);

                userIdRow = 14;
                userIdColumn = 61;
                this.writePosition(userIdRow, userIdColumn, "N");

                userIdRow = 15;
                userIdColumn = 45;
                this.writePosition(userIdRow, userIdColumn, "900000");

                userIdRow = 16;
                userIdColumn = 45;
                this.writePosition(userIdRow, userIdColumn, "900000");

                userIdRow = 17;
                userIdColumn = 45;
                this.writePosition(userIdRow, userIdColumn, "900000");

                this.writeKey(Key.ENTER);
                this.waitForChange(30000);

                userIdRow = 5;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "T");

                userIdRow = 6;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "20210620");

                userIdRow = 7;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "01");

                userIdRow = 8;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "60");

                userIdRow = 9;
                userIdColumn = 27;
                this.writePosition(userIdRow, userIdColumn, "7");

                userIdRow = 14;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "Y");

                userIdRow = 15;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "Y");

                userIdRow = 16;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "N");

                userIdRow = 17;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "N");


                this.writeKey(Key.ENTER);
                this.waitForChange(30000);

                userIdRow = 0;
                userIdColumn = 12;
                String msg = this.readPosition(userIdRow, userIdColumn, 25).trim();
                System.out.println(msg);
                if(msg.equalsIgnoreCase("RATE DEFAULTED TO MINIMUM"))
                {
                    this.writeKey(Key.ENTER);
                    this.waitForChange(30000);

                }

                this.writeKey(Key.ENTER);
                this.waitForChange(30000);

                userIdRow = 0;
                userIdColumn = 55;
                String ss = this.readPosition(userIdRow, userIdColumn, 5).trim();
                System.out.println("QMS: "+ ss);
                return ss + account;
            }
           /* if(check.equalsIgnoreCase("CHQ")){

                userIdRow = 4;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "Test Data");

                userIdRow = 8;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "01");

                userIdRow = 9;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "01");

                userIdRow = 23;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "113");
            }*/
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            message = message + account;
            System.out.println("Account No: " + account);
            return message;

            //}
        }
    }

    //Cheque Deposit(QD)
    public String chequeDeposit(String userData, String transactionType, String accountNo, String amount, String transactionNo, String controllerID) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 2;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, transactionType);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 1;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, accountNo);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, amount);

        userIdRow = 5;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, transactionNo);

        if (userData.equalsIgnoreCase("FINS")) {
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);
            userIdRow = 21;
            userIdColumn = 1;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();

            this.writeKey(Key.CLEAR);
            return message;
        } else {

            userIdRow = 3;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, amount);

            userIdRow = 5;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, transactionNo);

            userIdRow = 10;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, transactionNo);

            userIdRow = 17;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, controllerID);
            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            userIdRow = 21;
            userIdColumn = 1;
            message = this.readPosition(userIdRow, userIdColumn, 30).trim();

            if (message.equalsIgnoreCase("ACCOUNT NUMBER DOES NOT EXIST")) {
                this.waitForChange(1000);
                return message;

            } else {

                userIdRow = 17;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, controllerID);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                return message;
            }
        }
    }
    /*//Cheque Deposit(QD)
    public String chequeDeposit(String userData, String transactionType, String accountNo, String amount, String transactionNo, String controllerID) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 2;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, transactionType);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 1;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, accountNo);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, amount);

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 21;
        userIdColumn = 1;
        message = this.readPosition(userIdRow, userIdColumn, 25).trim();
        System.out.println(message);
        return message;
    }*/

    public void checkSTL() throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, "mpp");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        this.waitForChange(1000);
        userIdRow = 0;
        userIdColumn = 7;
        this.writePosition(userIdRow, userIdColumn, "pf13");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "Y");

        userIdRow = 7;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "99");

        userIdRow = 8;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "8198");

        userIdRow = 9;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "1212");

        userIdRow = 10;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "122222");

        userIdRow = 11;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "121212");

        userIdRow = 12;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "034");

        userIdRow = 13;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "034");

        userIdRow = 14;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "ABS");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);
    }

    public String checkMpp(String accountNo) throws JagacyException, InterruptedException{
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, "STL");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 16;
        message = this.readPosition(userIdRow, userIdColumn, 20).trim();
        System.out.println("STL:" + message);

        Thread.sleep(100);
        userIdRow = 0;
        userIdColumn = 7;
        this.writePosition(userIdRow, userIdColumn, "ABF");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 15;
        message = this.readPosition(userIdRow, userIdColumn, 20).trim();
        System.out.println("Qms:" + message);
        if(message.equalsIgnoreCase("RINT AND REPORT THIS")){
            return message;
        }
        //Thread.sleep(1000);

        userIdRow = 1;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, accountNo);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 14;
        userIdColumn = 61;
        this.writePosition(userIdRow, userIdColumn, "N");

        userIdRow = 15;
        userIdColumn = 45;
        this.writePosition(userIdRow, userIdColumn, "900000");

        userIdRow = 16;
        userIdColumn = 45;
        this.writePosition(userIdRow, userIdColumn, "900000");

        userIdRow = 17;
        userIdColumn = 45;
        this.writePosition(userIdRow, userIdColumn, "900000");


        userIdRow = 5;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "T");

        userIdRow = 6;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "20211220");

        userIdRow = 7;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "01");

        userIdRow = 8;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "60");

        userIdRow = 9;
        userIdColumn = 27;
        this.writePosition(userIdRow, userIdColumn, "7");


        userIdRow = 14;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "Y");

        userIdRow = 15;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "Y");

        userIdRow = 16;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "N");

        userIdRow = 17;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "N");


        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 55;
        message = this.readPosition(userIdRow, userIdColumn, 5).trim();
        return message;
    }

    public void AcceptQuote(String qoute) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\lib\\chromedriver.exe");  //for chrome
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://wasuat.absa.co.za/qmswf/");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.switchTo().frame(driver.findElement( By.xpath("/html/frameset/frame[2]")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("LoginName")).sendKeys("abks580");
        driver.findElement(By.name("password")).sendKeys("quantIty72");

        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[5]/td/input[4]")).click();

        driver.findElement(By.name("quoteNo")).sendKeys(qoute);

        driver.findElement(By.name("qmsOption")).click();

        driver.findElement(By.name("button_continueBtn")).click();

        driver.close();

    }

}
