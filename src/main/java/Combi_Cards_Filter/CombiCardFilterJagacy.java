package Combi_Cards_Filter;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.awt.*;

public class CombiCardFilterJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;

    public CombiCardFilterJagacy() throws JagacyException
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

    public String filterCombiCard(String userData, String cardType, String Personalized, String ChequeAccountNo,String SavingsAccountNo, String creditCardAccountNo, String brand, String branch, String autoLink, String cardNo) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 41;
        this.writePosition(userIdRow, userIdColumn, cardType);

        userIdRow = 3;
        userIdColumn = 41;
        this.writePosition(userIdRow, userIdColumn, Personalized);

        if(ChequeAccountNo == null || ChequeAccountNo.equalsIgnoreCase(""))
        {
            userIdRow = 6;
            userIdColumn = 41;
            this.writePosition(userIdRow, userIdColumn, SavingsAccountNo);
        }else{
            userIdRow = 6;
            userIdColumn = 41;
            this.writePosition(userIdRow, userIdColumn, ChequeAccountNo);
        }

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        String[] screenData = this.readScreen();
        int vasa = 0;
        for(int x = 0; x < screenData.length; x++)
        {
            if(screenData[x].contains("LGDC")){
                vasa = x;
            }
        }

        userIdRow = vasa;
        userIdColumn = 2;
        this.writePosition(userIdRow, userIdColumn, brand);


        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        String newCardNumber = cardNo.replace("-","");
        userIdRow = 7;
        userIdColumn = 22;
        this.writePosition(userIdRow, userIdColumn, newCardNumber);

        userIdRow = 9;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, branch);

        if(ChequeAccountNo != null && SavingsAccountNo != null)
        {

            userIdRow = 12;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, ChequeAccountNo);

            userIdRow = 13;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, SavingsAccountNo);

        }else if(ChequeAccountNo != null)
        {
            userIdRow = 12;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, ChequeAccountNo);

        }else
        {
            userIdRow = 13;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, SavingsAccountNo);

        }

        userIdRow = 19;
        userIdColumn = 20;
        this.writePosition(userIdRow, userIdColumn, autoLink);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 1;
        message = this.readPosition(userIdRow, userIdColumn, 60).trim();
        System.out.println("Heading Message: " + message);
        return message;
    }

    public String ChangeLinmit(String userLimit, String cardNo, String FirstAmount, String secondAmount, String thirdAmount) throws JagacyException, AWTException {

        this.waitForChange(1000);
        String newCardNumber = cardNo.replace("-","");
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userLimit);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, newCardNumber.trim());
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 6;
        userIdColumn = 27;
        this.writePosition(userIdRow, userIdColumn, FirstAmount);

        userIdRow = 7;
        userIdColumn = 27;
        this.writePosition(userIdRow, userIdColumn, secondAmount);

        userIdRow = 8;
        userIdColumn = 27;
        this.writePosition(userIdRow, userIdColumn,thirdAmount);

        this.writeKey(Key.PF14);
        this.waitForChange(1000);

        userIdRow = 1;
        userIdColumn = 1;
        message = this.readPosition(userIdRow, userIdColumn, 50).trim();
        System.out.println("Message: " + message);

        return message;
    }
}
