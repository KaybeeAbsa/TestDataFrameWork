package Client_Creation.Business_Clients;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.util.ArrayList;

public class BusinessAccountJagacy extends Session3270
{

    private int userIdRow;
    private int userIdColumn;
    private String message;
    private String value;
    private  String data;
    private ArrayList<String> numbers;
    private int valid = 0;
    public BusinessAccountJagacy() throws JagacyException
    {
        super("sessionA","host3270.absa.co.za",993,"IBM-3279-5-E",true);
    }

    public boolean userLogin(String username,String password) throws JagacyException
    {
        waitForChange(10000);
        userIdRow = 22;
        userIdColumn = 26;
        this.writePosition(userIdRow, userIdColumn, "IMSS");
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
    public String CustomerOnBoarding(String userData, String companyName, String clientTypeGroup, String accountOption, String idType, String idNumber,String Language, String clientType, String subClass,
                                     String sicCode, String detailedClass, String percentage, String contactPerson, String refN0, String address1, String town,  String areaCode,  String country1, String email, String countNo,
                                    String SourceOfIncome, String empNo , String ProductType, String methodDelivary , String TaxNo, String TaxDataNo, String FOREIGNTAX , String sourceFund, String accountName) throws JagacyException
    {

        this.waitForChange(10000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn,userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(10000);

        this.writeKey(Key.ENTER);
        this.waitForChange(10000);

        userIdRow = 8;
        userIdColumn = 35;
        this.writePosition(userIdRow, userIdColumn, companyName);

        userIdRow = 19;
        userIdColumn = 36;
        this.writePosition(userIdRow, userIdColumn, clientTypeGroup);

        this.writeKey(Key.ENTER);
        this.waitForChange(10000);

        userIdRow = 2;
        userIdColumn = 10;
        this.writePosition(userIdRow, userIdColumn, accountOption);

        this.writeKey(Key.ENTER);
        this.waitForChange(10000);

        userIdRow = 2;
        userIdColumn = 55;
        this.writePosition(userIdRow, userIdColumn, refN0);

        System.out.println("Ref No: " + refN0);
        userIdRow = 4;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, idType);

        userIdRow = 5;
        userIdColumn = 22;
        this.writePosition(userIdRow, userIdColumn, idNumber);

        userIdRow = 6;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, Language);

        userIdRow = 7;
        userIdColumn = 17;
        this.writePosition(userIdRow, userIdColumn, clientType);

        if(clientType.equalsIgnoreCase("03001")){
            userIdRow = 8;
            userIdColumn = 22;
            this.writePosition(userIdRow, userIdColumn, subClass);
        }

        userIdRow = 10;
        userIdColumn = 17;
        this.writePosition(userIdRow, userIdColumn, sicCode);

        userIdRow = 12;
        userIdColumn = 27;
        this.writePosition(userIdRow, userIdColumn, detailedClass);

        userIdRow = 12;
        userIdColumn = 71;
        this.writePosition(userIdRow, userIdColumn, percentage);

        this.writeKey(Key.ENTER);
        this.waitForChange(10000);

            userIdRow = 3;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, address1);

            userIdRow = 3;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, address1);

            userIdRow = 5;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, town);

            userIdRow = 5;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, town);

            userIdRow = 6;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, town);

            userIdRow = 6;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, town);

            userIdRow = 7;
            userIdColumn = 19;
            this.writePosition(userIdRow, userIdColumn, areaCode);

            userIdRow = 7;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, areaCode);

            userIdRow = 8;
            userIdColumn = 22;
            this.writePosition(userIdRow, userIdColumn, country1);

            userIdRow = 8;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, email);

        userIdRow = 11;
        userIdColumn = 18;
        this.writePosition(userIdRow, userIdColumn, contactPerson);

        userIdRow = 12;
        userIdColumn = 19;
        this.writePosition(userIdRow, userIdColumn, "01");

        userIdRow = 12;
        userIdColumn = 61;
        this.writePosition(userIdRow, userIdColumn, countNo);

        userIdRow = 15;
        userIdColumn = 33;
        this.writePosition(userIdRow, userIdColumn, "Y");



        userIdRow = 16;
        userIdColumn = 48;
        this.writePosition(userIdRow, userIdColumn, "y");

        userIdRow = 16;
        userIdColumn = 68;
        this.writePosition(userIdRow, userIdColumn, "y");


        userIdRow = 17;
        userIdColumn = 48;
        this.writePosition(userIdRow, userIdColumn, "y");

        userIdRow = 17;
        userIdColumn = 68;
        this.writePosition(userIdRow, userIdColumn, "y");

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

        userIdRow = 5;
        userIdColumn = 18;
        this.writePosition(userIdRow, userIdColumn, address1);

        userIdRow = 5;
        userIdColumn = 50;
        this.writePosition(userIdRow, userIdColumn, address1);

        userIdRow = 7;
        userIdColumn = 18;
        this.writePosition(userIdRow, userIdColumn, town);

        userIdRow = 7;
        userIdColumn = 50;
        this.writePosition(userIdRow, userIdColumn, town);

        userIdRow = 8;
        userIdColumn = 18;
        this.writePosition(userIdRow, userIdColumn, town);

        userIdRow = 8;
        userIdColumn = 50;
        this.writePosition(userIdRow, userIdColumn, town);

        userIdRow = 9;
        userIdColumn = 19;
        this.writePosition(userIdRow, userIdColumn, areaCode);

        userIdRow = 9;
        userIdColumn = 50;
        this.writePosition(userIdRow, userIdColumn, areaCode);

        userIdRow = 10;
        userIdColumn = 30;
        this.writePosition(userIdRow, userIdColumn, country1);

        userIdRow = 10;
        userIdColumn = 73;
        this.writePosition(userIdRow, userIdColumn, country1);

        userIdRow = 12;
        userIdColumn = 30;
        this.writePosition(userIdRow, userIdColumn, country1);

        this.writeKey(Key.ENTER);
        this.waitForChange(10000);

        userIdRow = 4;
        userIdColumn = 29;
        this.writePosition(userIdRow, userIdColumn, SourceOfIncome);

            userIdRow = 8;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, "t");

            userIdRow = 9;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, empNo);

            userIdRow = 10;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, "t");
            userIdRow = 11;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, empNo);

            userIdRow = 17;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, ProductType);

            userIdRow = 19;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, empNo);

            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 50).trim();

            System.out.println("Message Product Busket: " + message);


        userIdRow = 8;
            userIdColumn = 54;
            this.writePosition(userIdRow, userIdColumn, methodDelivary);

            userIdRow = 11;
            userIdColumn = 45;
            this.writePosition(userIdRow, userIdColumn, TaxNo);

            /*userIdRow = 12;
             userIdColumn = 29;
            this.writePosition(userIdRow, userIdColumn, TaxDataNo);*/

            userIdRow = 15;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, FOREIGNTAX);

            /*userIdRow = 17;
            userIdColumn = 3;
            this.writePosition(userIdRow, userIdColumn, country1);

            userIdRow = 17;
            userIdColumn = 9;
            this.writePosition(userIdRow, userIdColumn, TaxDataNo);*/


            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 12;
            userIdColumn = 33;
            this.writePosition(userIdRow, userIdColumn, sourceFund);

            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 11;
            userIdColumn = 21;
            String account = this.readPosition(userIdRow, userIdColumn, 25).trim();

            userIdRow = 14;
            userIdColumn = 1;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();
            this.writeKey(Key.ENTER);
            this.waitForChange(10000);


            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            message = message + account;
            System.out.println("Account No: " + account);
            return message;
    }
}
