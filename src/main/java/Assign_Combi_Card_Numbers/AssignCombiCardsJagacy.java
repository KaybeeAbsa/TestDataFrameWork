package Assign_Combi_Card_Numbers;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.awt.*;

public class AssignCombiCardsJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;

    public AssignCombiCardsJagacy() throws JagacyException
    {
        super("sessionA","host3270.absa.co.za",993,"IBM-3279-5-E",true);
    }

    public boolean userLogin(String username,String password) throws JagacyException
    {
        waitForChange(10000);
        userIdRow = 22;
        userIdColumn = 26;
        this.writePosition(userIdRow, userIdColumn, "IMSV");
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

    public String assignCombiCardsNumbers(String userData, String cardType, String Personalized, String ChequeAccountNo,String SavingsAccountNo, String creditCardAccountNo, String brand, String branch, String autoLink, String cardNo) throws JagacyException {
        this.waitForChange(10000);
        this.writeKey(Key.ENTER);

        this.waitForChange(10000);
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

        ChequeAccountNo = ChequeAccountNo.replace("-","");

        if (ChequeAccountNo.equals("")) {
            System.out.println("inside Savings: " + SavingsAccountNo);
            userIdRow = 6;
            userIdColumn = 41;
            this.writePosition(userIdRow, userIdColumn, SavingsAccountNo);
        } else {
            System.out.println("inside Cheques: " + ChequeAccountNo);
            userIdRow = 6;
            userIdColumn = 41;
            this.writePosition(userIdRow, userIdColumn, ChequeAccountNo);
        }

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 1;
        message = this.readPosition(userIdRow, userIdColumn, 55).trim();

        if (message.equalsIgnoreCase("CMMI  NEWP Client has no Active Accounts")) {
            System.out.println(message);
            return message;
        } else {

            if(Personalized.equalsIgnoreCase("n")){

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

                String newCardNumber = cardNo.replace("-","");
                userIdRow = 7;
                userIdColumn = 22;
                this.writePosition(userIdRow, userIdColumn, newCardNumber);

            }else{

                userIdRow = 4;
                userIdColumn = 2;
                this.writePosition(userIdRow, userIdColumn, brand);

                this.writeKey(Key.ENTER);
                this.waitForChange(30000);

                this.writeKey(Key.ENTER);
                this.waitForChange(30000);
            }

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
                System.out.println("Inside Cheque");
                userIdRow = 12;
                userIdColumn = 37;
                this.writePosition(userIdRow, userIdColumn, ChequeAccountNo);
            }else
            {
                System.out.println("Inside Savings");
                userIdRow = 13;
                userIdColumn = 37;
                this.writePosition(userIdRow, userIdColumn, SavingsAccountNo);
            }

            userIdRow = 19;
            userIdColumn = 20;
            this.writePosition(userIdRow, userIdColumn, autoLink);
            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            userIdRow = 0;
            userIdColumn = 1;
            message = this.readPosition(userIdRow, userIdColumn, 60).trim();

            System.out.println(message);
            if(message.equalsIgnoreCase("CMMI  NEW  Client has Active Savings Acc, please Nominate") || message.equalsIgnoreCase("CMMI  NEW  Combi Card Number already exists") || message.equalsIgnoreCase("CMMI  NEW  ACCS - Stock Code is different in System")){
                return message;
            }else if(message.equalsIgnoreCase("CMMI  NEW  Additional Combi Card, Card Fee will be charged")){
                return message;
            }else if(message.equalsIgnoreCase("CMMI  NEW  Manager Override Required")){
                this.writeKey(Key.ENTER);
                this.waitForChange(30000);
            }

            if(message.length() == 0){

                userIdRow = 23;
                userIdColumn = 70;
                message = this.readPosition(userIdRow, userIdColumn, 10).trim();
                System.out.println("Heading Message2: " + message);

                userIdRow = 1;
                userIdColumn = 23;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                System.out.println("Heading Message3: " + message);
                this.writeKey(Key.CLEAR);
                return message;

            }else{

                this.writeKey(Key.ENTER);
                this.waitForChange(30000);

                userIdRow = 23;
                userIdColumn = 70;
                message = this.readPosition(userIdRow, userIdColumn, 10).trim();
                System.out.println("Heading Message 2: " + message);

                userIdRow = 1;
                userIdColumn = 23;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                System.out.println("Heading Message 3: " + message);
                this.writeKey(Key.CLEAR);
                return message;
            }
        }
    }

    public String checkSavingsAccount(String accountNo) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 0;
        userIdColumn = 0;
        this.writePosition(userIdRow, userIdColumn, "CIFQ");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 5;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, accountNo);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 10;
        userIdColumn = 39;
        this.writePosition(userIdRow, userIdColumn, "3");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        String[] screenData = this.readScreen();
        String account = null;
        for(int x = 0; x < screenData.length; x++)
        {
            if(screenData[x].contains("OPEN") || screenData[x].contains("ALLOCATE")){

                account = screenData[x].substring(1,20);
            }
        }
        System.out.println("Saving account: " + account);
        this.writeKey(Key.CLEAR);
        return account;
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
