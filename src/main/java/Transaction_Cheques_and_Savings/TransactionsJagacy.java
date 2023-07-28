package Transaction_Cheques_and_Savings;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransactionsJagacy extends Session3270
{

    private int userIdRow;
    private int userIdColumn;
    private String message;
    private String value;
    private String data;
    private ArrayList<String> numbers;
    private int valid = 0;

    public TransactionsJagacy() throws JagacyException {
        super("sessionA", "host3270.absa.co.za", 993, "IBM-3279-5-E", true);
    }

    public boolean userLogin(String username, String password) throws JagacyException {
       try{
           waitForChange(10000);
           userIdRow = 22;
           userIdColumn = 26;
           this.writePosition(userIdRow, userIdColumn, "iv01");
           this.writeKey(Key.ENTER);

           this.waitForChange(10000);
           userIdRow = 14;
           userIdColumn = 10;
           this.writePosition(userIdRow, userIdColumn, username);

           userIdRow = 16;
           userIdColumn = 11;
           this.writePosition(userIdRow, userIdColumn, password);
           this.writeKey(Key.ENTER);
           this.waitForChange(30000);

           userIdRow =  23;
           userIdColumn = 22;
           message = this.readPosition(userIdRow,userIdColumn,40).trim();

           System.out.println(message);
           if(message.equalsIgnoreCase("INCORRECT OR NO PASSWORD ENTERED.") || message.equalsIgnoreCase("USERID IS NOT DEFINED TO RACF.") || message.equalsIgnoreCase("Your USERID is already logged on.")) {
               return false;
           }else {

               userIdRow = 2;
               userIdColumn = 2;
               this.writePosition(userIdRow, userIdColumn, "/test mfs");
               this.writeKey(Key.ENTER);
               return true;
           }
       }catch (JagacyException j){
           return false;
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

    //Cash Withdrawal (CW)
    public String cashWithdraw(String userData, String transactionType, String accountNo, String amount, String transactionNo, String controllerID) throws JagacyException, AWTException {
        try {
            Robot rob = new Robot();
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

            if (userData.equalsIgnoreCase("FINS")) {

                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 17;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, controllerID);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
            } else {

                userIdRow = 8;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionNo);
                //this.writeKey(Key.ENTER);
                // this.waitForChange(30000);

                userIdRow = 9;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "N");
                rob.keyPress(KeyEvent.VK_TAB);

                userIdRow = 10;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "N");
                rob.keyPress(KeyEvent.VK_TAB);

                userIdRow = 11;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "N");
                rob.keyPress(KeyEvent.VK_TAB);

                userIdRow = 17;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, controllerID);
                this.writeKey(Key.ENTER);
                this.waitForChange(30000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                System.out.print("Error: " + message);
               /* if(message.equalsIgnoreCase("")){

                }*/

                userIdRow = 17;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, controllerID);
                this.writeKey(Key.ENTER);
                this.waitForChange(30000);


                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                System.out.print("Error: " + message);
               /* if(message.equalsIgnoreCase("")){

                }*/

                this.writeKey(Key.ENTER);
                this.waitForChange(30000);
                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
            }

            //System.out.println("CW: " + message);
            return message;
        } catch (JagacyException e) {
            return "[INVALID POSITION ERROR] Protected field";
        }

        //return message;
    }

    //Cash Deposit(CD)
    public String cashDeposit(String userData, String transactionType, String accountNo, String amount, String transactionNo) {

        try{
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

            if (userData.equalsIgnoreCase("FINS")) {
                this.waitForChange(1000);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25);
                System.out.println("Cd: " + message);
                // this.writeKey(Key.CLEAR);
                return message;
            } else {
                userIdRow = 10;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionNo);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25);

                if(message.equalsIgnoreCase("CONTROLLER OVERIDE REQUIR")){
                    userIdRow = 17;
                    userIdColumn = 25;
                    this.writePosition(userIdRow, userIdColumn, "123456");
                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    userIdRow = 21;
                    userIdColumn = 1;
                    message = this.readPosition(userIdRow, userIdColumn, 25);
                    System.out.println("Cd: " + message);
                    return message;
                }else if(message.equalsIgnoreCase("TELLER OVERIDE REQUIRED")){
                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    userIdRow = 21;
                    userIdColumn = 1;
                    message = this.readPosition(userIdRow, userIdColumn, 25);
                    System.out.println("Cd: " + message);

                    return message;
                }else{
                    return message;
                }
                // this.writeKey(Key.CLEAR);
            }
        }catch(JagacyException j){
            return "[KEYBOARD LOCKED ERROR] Keyboard is locked";
        }

    }

    //Mixed Deposit(MD)
    public String MixedDeposit(String userData, String transactionType, String accountNo, String amount, String transactionAmount, String transactionNo, String controllerID) throws JagacyException, InterruptedException {

        waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 2;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, transactionType);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 1;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, accountNo);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, amount);

        userIdRow = 3;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, amount);

        userIdRow = 5;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, transactionNo);

        if (userData.equalsIgnoreCase("FINS")) {
            this.waitForChange(1000);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 21;
            userIdColumn = 1;
            message = this.readPosition(userIdRow, userIdColumn, 25);
            //this.writeKey(Key.CLEAR);
            return message;

        } else {
            userIdRow = 10;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, transactionNo);

            userIdRow = 17;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, controllerID);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 21;
            userIdColumn = 1;
            message = this.readPosition(userIdRow, userIdColumn, 30).trim();

            if (message.equalsIgnoreCase("ACCOUNT NUMBER DOES NOT EXIST") || message.equalsIgnoreCase("TRANSACTION PROHIBITED")) {
                this.writeKey(Key.CLEAR);
                return message;
            } else {

                this.waitForChange(1000);
                userIdRow = 17;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, controllerID);
                this.writeKey(Key.ENTER);
                this.waitForChange(30000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25);
                return message;
            }
        }
    }

    //Cheque Payment(QP)
    public String ChequePayment(String userData, String transactionType, String accountNo, String amount, String transactionNo, String controllerID) throws JagacyException {

        Robot rob = null;
        try {

            rob = new Robot();
            this.waitForChange(1000);
            userIdRow = 2;
            userIdColumn = 2;
            this.writePosition(userIdRow, userIdColumn, userData);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 2;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, transactionType);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 1;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, accountNo);

            userIdRow = 2;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, amount);

            userIdRow = 6;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, transactionNo);

            if (userData.equalsIgnoreCase("FINS")) {
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 17;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, controllerID);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25);
                return message;
            } else {
                userIdRow = 8;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionNo);

                userIdRow = 9;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "N");
                rob.keyPress(KeyEvent.VK_TAB);

                userIdRow = 10;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, "N");
                rob.keyPress(KeyEvent.VK_TAB);

                userIdRow = 17;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, controllerID);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 30).trim();

                if (message.equalsIgnoreCase("ACCOUNT NUMBER DOES NOT EXIST") || message.equalsIgnoreCase("TRANSACTION PROHIBITED")) {
                    return message;

                } else {

                    userIdRow = 17;
                    userIdColumn = 25;
                    this.writePosition(userIdRow, userIdColumn, controllerID);
                    this.writeKey(Key.ENTER);
                    this.waitForChange(2000);

                    userIdRow = 21;
                    userIdColumn = 1;
                    message = this.readPosition(userIdRow, userIdColumn, 25);

                    return message;
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return message;
    }

    //CWC
    public String CashWithdrawalClose(String userData, String dataUse, String transactionType, String accountNo, String amount, String transactionNo, String controllerID, String bankCode) throws JagacyException {

        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 2;
        this.writePosition(userIdRow, userIdColumn, dataUse);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        if(dataUse.equalsIgnoreCase("SAVQ CLSE"))
        {
            userIdRow = 2;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, accountNo);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 19;
            userIdColumn = 39;
            message = this.readPosition(userIdRow, userIdColumn, 15).trim();

            System.out.println("Amount: R " + message);

            Pattern p = Pattern.compile("-?\\d+(,\\d+)*?\\.?\\d+?");
            numbers = new ArrayList<String>();

            Matcher m = p.matcher(message);
            while (m.find()) {
                numbers.add(m.group());
            }

            if (numbers.size() != 0) {

                value = numbers.toString();
                int valueLeaght = numbers.toString().length();
                int totalLength = valueLeaght - 1;
                data = value.substring(1, totalLength);
                valid = 1;
            }

            if(valid > 0)
            {
                this.writeKey(Key.CLEAR);
                this.waitForChange(1000);

                userIdRow = 0;
                userIdColumn = 0;
                this.writePosition(userIdRow, userIdColumn, userData);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 2;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionType);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 1;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, accountNo);

                this.waitForChange(1000);
                userIdRow = 8;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionNo);


                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                this.writeKey(Key.CLEAR);
                return message;

            }
        }else {
            userIdRow = 1;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, accountNo);

            userIdRow = 2;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "t");
            this.writeKey(Key.ENTER);
            this.waitForChange(30000);
            userIdRow = 10;
            userIdColumn = 23;

            message = this.readPosition(userIdRow, userIdColumn, 15).trim();
            Pattern p = Pattern.compile("-?\\d+(,\\d+)*?\\.?\\d+?");
            numbers = new ArrayList<String>();

            Matcher m = p.matcher(message);
            while (m.find()) {
                numbers.add(m.group());
            }
            if (numbers.size() != 0) {

                value = numbers.toString();
                int valueLeaght = numbers.toString().length();
                int totalLength = valueLeaght - 1;
                data = value.substring(1, totalLength);
                valid = 1;
            }

            if (valid > 0) {
                this.writeKey(Key.CLEAR);
                this.waitForChange(1000);

                userIdRow = 0;
                userIdColumn = 0;
                this.writePosition(userIdRow, userIdColumn, userData);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 2;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionType);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 1;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, accountNo);

                userIdRow = 7;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionNo);

                userIdRow = 8;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionNo);

                userIdRow = 11;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, bankCode);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25);

                if (message.contains("TELLER OVERIDE REQUIRED")) {

                    this.writeKey(Key.ENTER);
                    this.waitForChange(30000);

                    userIdRow = 21;
                    userIdColumn = 1;
                    message = this.readPosition(userIdRow, userIdColumn, 25);

                    return message;
                } else if (message.contains("TRANSACTION PROHIBITED")) {

                    return message;
                } else {

                    userIdRow = 17;
                    userIdColumn = 25;
                    this.writePosition(userIdRow, userIdColumn, controllerID);
                    this.writeKey(Key.ENTER);
                    this.waitForChange(30000);

                    userIdRow = 21;
                    userIdColumn = 1;
                    message = this.readPosition(userIdRow, userIdColumn, 25);

                    return message;
                }
            }
        }
        userIdRow = 0;
        userIdColumn = 12;
        message = this.readPosition(userIdRow, userIdColumn, 40).trim();
        return message;

    }


    //CDC
    public String CashDepositClose(String userData, String dataUse, String transactionType, String accountNo, String amount, String transactionNo, String controllerID, String bankCode) throws JagacyException {

        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 2;
        this.writePosition(userIdRow, userIdColumn, dataUse);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        if(dataUse.equalsIgnoreCase("SAVQ CLSE"))
        {
            userIdRow = 2;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, accountNo);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 19;
            userIdColumn = 39;
            message = this.readPosition(userIdRow, userIdColumn, 15).trim();

            System.out.println("Amount: R " + message);

            Pattern p = Pattern.compile("-?\\d+(,\\d+)*?\\.?\\d+?");
            numbers = new ArrayList<String>();

            Matcher m = p.matcher(message);
            while (m.find()) {
                numbers.add(m.group());
            }

            if (numbers.size() != 0) {

                value = numbers.toString();
                int valueLeaght = numbers.toString().length();
                int totalLength = valueLeaght - 1;
                data = value.substring(1, totalLength);
                valid = 1;
            }

            if(valid > 0)
            {

                this.writeKey(Key.CLEAR);
                this.waitForChange(1000);

                userIdRow = 0;
                userIdColumn = 0;
                this.writePosition(userIdRow, userIdColumn, userData);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 2;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionType);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 1;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, accountNo);

                this.waitForChange(1000);
                userIdRow = 2;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, data);

                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                this.writeKey(Key.CLEAR);
                return message;
            }

            return message;
        }else {
            userIdRow = 1;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, accountNo);

            userIdRow = 2;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "t");

            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            userIdRow = 10;
            userIdColumn = 23;
            message = this.readPosition(userIdRow, userIdColumn, 15).trim();

            System.out.println("Amount: R " + message);

            Pattern p = Pattern.compile("-?\\d+(,\\d+)*?\\.?\\d+?");
            numbers = new ArrayList<String>();

            Matcher m = p.matcher(message);
            while (m.find()) {
                numbers.add(m.group());
            }
            if (numbers.size() != 0) {

                value = numbers.toString();
                int valueLeaght = numbers.toString().length();
                int totalLength = valueLeaght - 1;
                data = value.substring(1, totalLength);
                valid = 1;
            }

            if (valid > 0) {

                this.writeKey(Key.CLEAR);
                this.waitForChange(1000);

                userIdRow = 0;
                userIdColumn = 0;
                this.writePosition(userIdRow, userIdColumn, userData);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow = 2;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionType);
                this.writeKey(Key.ENTER);

                this.waitForChange(1000);
                userIdRow = 1;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, accountNo);

                this.waitForChange(1000);
                userIdRow = 2;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, data);

                userIdRow = 7;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, transactionNo);

                userIdRow = 11;
                userIdColumn = 25;
                this.writePosition(userIdRow, userIdColumn, bankCode);

                this.waitForChange(1000);
                this.writeKey(Key.ENTER);

                this.waitForChange(1000);
                userIdRow = 21;
                userIdColumn = 1;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                this.writeKey(Key.CLEAR);
                return message;
            }
        }
        userIdRow = 21;
        userIdColumn = 1;
        message = this.readPosition(userIdRow, userIdColumn, 25).trim();
        return message;
    }
}

