package Checking_Customer_Profile;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.util.ArrayList;

public class CustomerProfileJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;
    private String[] readData;
    private String value = null;
    private ArrayList<String> accountInformation = null;

    public CustomerProfileJagacy() throws JagacyException
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

        if(message.equalsIgnoreCase("INCORRECT OR NO PASSWORD ENTERED.") || message.equalsIgnoreCase("USERID HAS BEEN REVOKED.") || message.equalsIgnoreCase("USERID IS NOT DEFINED TO RACF.") || message.equalsIgnoreCase("PASSWORD HAS EXPIRED.") || message.equalsIgnoreCase("Your USERID is already logged on.")) {
            return false;
        }else {

            userIdRow = 2;
            userIdColumn = 2;
            this.writePosition(userIdRow, userIdColumn, "/test mfs");
            this.writeKey(Key.ENTER);
            return true;
        }
    }

    public String CheckCustomerProfile(String userData,String clientType, String id_Number, String accountNo) throws JagacyException {
        this.waitForChange(3000);
        userIdRow = 2;
        userIdColumn = 2;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(3000);

        if(accountNo.equals("")){
            userIdRow = 15;
            userIdColumn = 12;
            this.writePosition(userIdRow, userIdColumn, clientType);

            userIdRow = 15;
            userIdColumn = 39;
            this.writePosition(userIdRow, userIdColumn, id_Number.trim());
            this.writeKey(Key.ENTER);
            this.waitForChange(3000);
            System.out.println("Id no: " + id_Number);

            userIdRow =  22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow,userIdColumn,70).trim();
            System.out.println("Id no: " + message);

            if(message.equalsIgnoreCase("NO CLIENT RECORD FOUND ENTER TO PROCEED TO NAME SEARCH")|| message.equalsIgnoreCase("ID NUMBER INVALID") || message.equalsIgnoreCase("INVALID PASSPORT NUMBER"))
            {
                return message;
            }else {

                userIdRow = 6;
                userIdColumn = 14;
                this.writePosition(userIdRow, userIdColumn, "e");

                userIdRow = 6;

                userIdColumn = 52;
                this.writePosition(userIdRow, userIdColumn, "001");
                this.writeKey(Key.ENTER);
                this.waitForChange(3000);
            }
        }else{
            userIdRow = 7;
            userIdColumn = 27;
            this.writePosition(userIdRow, userIdColumn, accountNo.trim());
            this.writeKey(Key.ENTER);
            this.waitForChange(3000);

            userIdRow =  10;
            userIdColumn = 22;
           String  id_No = this.readPosition(userIdRow,userIdColumn,14).trim();
           System.out.println("id_Number : " + id_No);

        }

        userIdRow =  18;
        userIdColumn = 22;
        message = this.readPosition(userIdRow,userIdColumn,12).trim();
        System.out.println("Client Code: " + message);

        userIdRow =  21;
        userIdColumn = 44;
        message = this.readPosition(userIdRow,userIdColumn,12).trim();

        this.writeKey(Key.CLEAR);
//        this.writeKey(Key.PA1);
        return message;
    }

    public String CheckAUL(String useraul, String clientCode) throws JagacyException {
        this.waitForChange(3000);
        userIdRow = 2;
        userIdColumn = 2;
        this.writePosition(userIdRow, userIdColumn, useraul);
        this.writeKey(Key.ENTER);
        this.waitForChange(3000);

        userIdRow = 9;
        userIdColumn = 24;
        this.writePosition(userIdRow, userIdColumn, clientCode.trim());
        this.writeKey(Key.ENTER);
        this.waitForChange(3000);

        userIdRow = 6;
        userIdColumn = 15;
        message = this.readPosition(userIdRow, userIdColumn, 4).trim();
        System.out.println("Segment: " + message);

        userIdRow = 10;
        userIdColumn = 39;
        this.writePosition(userIdRow, userIdColumn, "2");
        this.writeKey(Key.ENTER);
        this.waitForChange(3000);

        userIdRow =  8;
        userIdColumn = 37;
        message = this.readPosition(userIdRow,userIdColumn,5).trim();
        this.writeKey(Key.CLEAR);
//        this.writeKey(Key.PA1);
        return message;
    }

    public ArrayList<String> checkAccountProfiles(String useraul, String clientCode) throws JagacyException {

        accountInformation = new ArrayList<String>();
        this.waitForChange(3000);
        userIdRow = 0;
        userIdColumn = 0;
        this.writePosition(userIdRow, userIdColumn, useraul);
        this.writeKey(Key.ENTER);
        this.waitForChange(3000);

        userIdRow = 9;
        userIdColumn = 24;
        this.writePosition(userIdRow, userIdColumn, clientCode.trim());
        this.writeKey(Key.ENTER);
        this.waitForChange(3000);

        userIdRow = 10;
        userIdColumn = 39;
        this.writePosition(userIdRow, userIdColumn, "3");
        this.writeKey(Key.ENTER);
        this.waitForChange(3000);

        readData = this.readScreen();

        this.writeKey(Key.CLEAR);
      //  this.writeKey(Key.PA1);

        for(int x = 0; x < readData.length; x++)
        {
          if(readData[x].contains("CURRENT") || readData[x].contains("DORMANT") || readData[x].contains("OPEN") || readData[x].contains("ALLOCATE") || readData[x].contains("LEGAL")|| readData[x].contains("DECLINED")|| readData[x].contains("PENDING")|| readData[x].contains("ACCEPT"))
           {
               value = readData[x].substring(1,80);
               accountInformation.add(value);
           }else if(readData[x].contains("ACTIVE")){
               value = readData[x].substring(1,80);
               accountInformation.add(value);
           }

           /*if(readData[x].contains("DELINQ")){
                value = readData[x].substring(1,20);
                accountInformation.add(value);
            }*/
        }
        return accountInformation;
    }
 }

