package Create_Existing_Client;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CreateExistingClientJagacy extends Session3270
{
    private int userIdRow;
    private int userIdColumn;
    private String message;

    public CreateExistingClientJagacy() throws JagacyException
    {
        super("sessionA","host3270.absa.co.za",993,"IBM-3279-5-E",true);
    }

    public boolean userLogin(String username,String password) throws JagacyException
    {
        waitForChange(1000);
        userIdRow = 22;
        userIdColumn = 26;
        this.writePosition(userIdRow, userIdColumn,  "IV02");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 14;
        userIdColumn = 10;
        this.writePosition(userIdRow,userIdColumn,username);

        userIdRow = 16;
        userIdColumn = 11 ;
        this.writePosition(userIdRow, userIdColumn, password);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

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


    public String customerCreation(String accountCode, String code, String clientCode,String accountNo, String codeNo, String productCode, String employeeNo, String accountAmount) throws JagacyException, AWTException {

                Robot rob = new Robot();
                this.waitForChange(1000);
                userIdRow = 2;
                userIdColumn = 2;
                this.writePosition(userIdRow, userIdColumn, accountCode);
                this.writeKey(Key.ENTER);

                this.waitForChange(1000);
                userIdRow = 4;
                userIdColumn = 72;
                this.writePosition(userIdRow, userIdColumn, code);

                if(accountNo.equals("")){
                    userIdRow = 11;
                    userIdColumn = 15;
                    this.writePosition(userIdRow, userIdColumn, clientCode);
                    this.writeKey(Key.ENTER);

                    this.waitForChange(1000);
                }else{
                    userIdRow = 7;
                    userIdColumn = 27;
                    this.writePosition(userIdRow, userIdColumn, accountNo);
                    this.writeKey(Key.ENTER);

                    this.waitForChange(1000);
                }

                userIdRow = 22;
                userIdColumn = 7;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                if(message.equalsIgnoreCase("NO CLIENT RECORD FOUND E"))
                {
                    return message;
                }else
                {
                    this.waitForChange(1000);
                    userIdRow = 2;
                    userIdColumn = 57;
                    this.writePosition(userIdRow, userIdColumn, codeNo);
                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    userIdRow = 21;
                    userIdColumn = 48;
                    this.writePosition(userIdRow, userIdColumn, "N");
                    rob.keyPress(KeyEvent.VK_TAB);

                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    userIdRow = 18;
                    userIdColumn = 17;
                    this.writePosition(userIdRow, userIdColumn, productCode);

                    userIdRow = 20;
                    userIdColumn = 22;
                    this.writePosition(userIdRow, userIdColumn, employeeNo);
                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    userIdRow = 12;
                    userIdColumn = 33;
                    this.writePosition(userIdRow, userIdColumn, accountAmount);
                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    this.writeKey(Key.ENTER);
                    this.waitForChange(3000);

                    userIdRow = 11;
                    userIdColumn = 21;
                    message = this.readPosition(userIdRow, userIdColumn, 25);
                    System.out.println("account no: " +message);

                    this.writeKey(Key.ENTER);
                    this.waitForChange(3000);

                    this.writeKey(Key.CLEAR);

                    return  message;
                }
    }


    public String getClientCode(String userData, String idType, String idNumber ) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 2;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 15;
        userIdColumn = 12;
        this.writePosition(userIdRow, userIdColumn, idType);

        userIdRow = 15;
        userIdColumn = 39;
        this.writePosition(userIdRow, userIdColumn, idNumber.trim());
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 6;
        userIdColumn = 14;
        this.writePosition(userIdRow, userIdColumn, "e");

        userIdRow = 6;
        userIdColumn = 52;
        this.writePosition(userIdRow, userIdColumn, "001");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow =  21;
        userIdColumn = 44;
        message = this.readPosition(userIdRow,userIdColumn,12).trim();
        this.writeKey(Key.CLEAR);
        System.out.println(message);
        return message;
    }


    public String createNewAccount(String userData, String idType, String idNumber, String recCode, String producdtType, String employeeNo,String accountAmount) throws JagacyException, AWTException {
        Robot rob = new Robot();
        this.waitForChange(1000);
        userIdRow = 0;
        userIdColumn = 0;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 15;
        userIdColumn = 12;
        this.writePosition(userIdRow, userIdColumn, idType);

        userIdRow = 15;
        userIdColumn = 39;
        this.writePosition(userIdRow, userIdColumn, idNumber.trim());
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow =  22;
        userIdColumn = 8;
        message = this.readPosition(userIdRow,userIdColumn,50).trim();
        //System.out.println("Dates issues: " + message);
        System.out.println(idType + " : " + idNumber + " : " + userData + " : " + message);
        userIdRow = 6;
        userIdColumn = 14;
        this.writePosition(userIdRow, userIdColumn, "a");

        userIdRow = 6;
        userIdColumn = 52;
        this.writePosition(userIdRow, userIdColumn, "001");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 2;
        userIdColumn = 57;
        this.writePosition(userIdRow, userIdColumn, recCode);
        userIdRow = 11;
        userIdColumn = 58;
        this.writePosition(userIdRow, userIdColumn, "t");

        userIdRow = 12;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "SOU01");

        userIdRow = 13;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "SO003");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow =  22;
        userIdColumn = 8;
        message = this.readPosition(userIdRow,userIdColumn,25).trim();
        System.out.println("Dates issues: " + message);

       /* if(message.equalsIgnoreCase("DATE ISSUED MANDATORY")) {
            userIdRow = 11;
            userIdColumn = 58;
            this.writePosition(userIdRow, userIdColumn, "02022001");
            System.out.println("Dates issues: " + message);
        }
            userIdRow = 12;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, "SOU01");

            userIdRow = 13;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, "SO003");
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow =  22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow,userIdColumn,70).trim();
            System.out.println("Message is: " + message);*/

            if(message.equals("COUNTRY PASSPORT ISSUED NOT REQUIRED FOR ID TYPE 01") || message.equalsIgnoreCase("COUNTRY PASSPORT ISSUED N"))
            {
                //press tab 5 times
                userIdRow = 14;
                userIdColumn = 28;

                this.writeKey(Key.ENTER);
                this.waitForChange(1000);
                userIdRow =  22;
                userIdColumn = 8;
                message = this.readPosition(userIdRow,userIdColumn,70).trim();
                System.out.println("Message inside: " + message);
                System.out.println("Sorted");

                return message;
            }

            userIdRow = 9;
            userIdColumn = 26;
            this.writePosition(userIdRow, userIdColumn, "SO003");
            rob.keyPress(KeyEvent.VK_TAB);

            userIdRow = 16;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "Y");
            rob.keyPress(KeyEvent.VK_TAB);

            userIdRow = 16;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "Y");
            rob.keyPress(KeyEvent.VK_TAB);

            userIdRow = 18;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "n");
            rob.keyPress(KeyEvent.VK_TAB);

            userIdRow = 18;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "n");
            rob.keyPress(KeyEvent.VK_TAB);

            userIdRow = 21;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");
            rob.keyPress(KeyEvent.VK_TAB);

            userIdRow = 21;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");
            rob.keyPress(KeyEvent.VK_TAB);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow =  22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow,userIdColumn,25).trim();

            System.out.println("Message after Address: " + message);

            if(message.equals("")){

                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                userIdRow =  22;
                userIdColumn = 8;
                message = this.readPosition(userIdRow,userIdColumn,25).trim();

                System.out.println("Before products: " + message);
                if(message.equals("")){
                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    userIdRow = 18;
                    userIdColumn = 17;
                    this.writePosition(userIdRow, userIdColumn, producdtType);

                    userIdRow = 20;
                    userIdColumn = 22;
                    this.writePosition(userIdRow, userIdColumn, employeeNo);
                    this.writeKey(Key.ENTER);
                    this.waitForChange(1000);

                    userIdRow =  22;
                    userIdColumn = 8;
                    message = this.readPosition(userIdRow,userIdColumn,25).trim();

                    System.out.println("after products: " + message);
                    if(message.equalsIgnoreCase("SOURCE OF INCOME MANDATOR")){

                        userIdRow = 5;
                        userIdColumn = 23;
                        this.writePosition(userIdRow, userIdColumn, accountAmount);
                        this.writeKey(Key.ENTER);
                        this.waitForChange(1000);

                        userIdRow = 11;
                        userIdColumn = 48;
                        this.writePosition(userIdRow, userIdColumn, "y");

                        userIdRow = 12;
                        userIdColumn = 40;
                        this.writePosition(userIdRow, userIdColumn, "04");

                        userIdRow = 14;
                        userIdColumn = 28;
                        this.writePosition(userIdRow, userIdColumn, "t");

                        userIdRow = 15;
                        userIdColumn = 28;
                        this.writePosition(userIdRow, userIdColumn, employeeNo);

                        userIdRow =  22;
                        userIdColumn = 8;
                        message = this.readPosition(userIdRow,userIdColumn,25).trim();
                        System.out.println("after products: " + message);
                    }

                   /* if(message.equalsIgnoreCase("PLEASE ENTER Y OR N - POS")){
                        userIdRow = 11;
                        userIdColumn = 48;
                        this.writePosition(userIdRow, userIdColumn, "y");

                        userIdRow = 12;
                        userIdColumn = 40;
                        this.writePosition(userIdRow, userIdColumn, "04");

                        userIdRow = 14;
                        userIdColumn = 28;
                        this.writePosition(userIdRow, userIdColumn, "t");

                        userIdRow = 15;
                        userIdColumn = 28;
                        this.writePosition(userIdRow, userIdColumn, employeeNo);

                        this.writeKey(Key.ENTER);
                        this.waitForChange(1000);

                        userIdRow =  22;
                        userIdColumn = 8;
                        message = this.readPosition(userIdRow,userIdColumn,25).trim();
                        System.out.println("after products: " + message);
                    }*/

                    if(message.equals(""))
                    {
                        userIdRow = 11;
                        userIdColumn = 45;
                        this.writePosition(userIdRow, userIdColumn, "n");

                        userIdRow = 15;
                        userIdColumn = 37;
                        this.writePosition(userIdRow, userIdColumn, "n");

                        this.writeKey(Key.ENTER);
                        this.waitForChange(1000);

                        userIdRow =  22;
                        userIdColumn = 8;
                        message = this.readPosition(userIdRow,userIdColumn,25).trim();

                       /* if(message.equalsIgnoreCase("PLEASE ENTER Y OR N"))
                        {
                            userIdRow = 11;
                            userIdColumn = 45;
                            this.writePosition(userIdRow, userIdColumn, "n");

                            userIdRow = 15;
                            userIdColumn = 37;
                            this.writePosition(userIdRow, userIdColumn, "n");

                            this.writeKey(Key.ENTER);
                            this.waitForChange(1000);

                            userIdRow =  22;
                            userIdColumn = 8;
                            message = this.readPosition(userIdRow,userIdColumn,25).trim();
                            System.out.println(" Taxs: " + message);
                        }*/
                        System.out.println("Tax products: " + message);
                        if(message.equals("")){
                            this.writeKey(Key.ENTER);
                            this.waitForChange(1000);

                            userIdRow = 12;
                            userIdColumn = 33;
                            this.writePosition(userIdRow, userIdColumn, accountAmount);
                            this.writeKey(Key.ENTER);
                            this.waitForChange(1000);

                            this.writeKey(Key.ENTER);
                            this.waitForChange(1000);

                            userIdRow =  11;
                            userIdColumn = 21;
                            message = this.readPosition(userIdRow,userIdColumn,25).trim();
                            System.out.println("Account no: " + message);

                            if(message.equals(""))
                            {

                            }else{

                                this.writeKey(Key.ENTER);
                                this.waitForChange(1000);

                                this.writeKey(Key.CLEAR);
                                System.out.println("Account no: " + message);
                                return message;
                            }

                            userIdRow =  22;
                            userIdColumn = 8;
                            message = this.readPosition(userIdRow,userIdColumn,25).trim();

                            System.out.println("Message after amount 20: " +  message);

                            if(message.equalsIgnoreCase("PRESS ENTER TO ADD ACCOUN"))
                            {
                                userIdRow = 10;
                                userIdColumn = 57;
                                this.writePosition(userIdRow, userIdColumn, "y");
                                this.writeKey(Key.ENTER);
                                this.waitForChange(1000);

                                userIdRow = 16;
                                userIdColumn = 57;
                                this.writePosition(userIdRow, userIdColumn, "y");
                                this.writeKey(Key.ENTER);
                                this.waitForChange(1000);

                                userIdRow = 20;
                                userIdColumn = 57;
                                this.writePosition(userIdRow, userIdColumn, "y");
                                this.writeKey(Key.ENTER);
                                this.waitForChange(1000);
                            }
                            this.writeKey(Key.ENTER);
                            this.waitForChange(1000);
                            //this.waitForChange(500000);
                            userIdRow =  22;
                            userIdColumn = 8;
                            message = this.readPosition(userIdRow,userIdColumn,25).trim();

                            System.out.println("Message after amount: " +  message);
                            userIdRow =  11;
                            userIdColumn = 21;
                            message = this.readPosition(userIdRow,userIdColumn,25).trim();
                            System.out.println("Account no: " + message);

                            this.writeKey(Key.ENTER);
                            this.waitForChange(1000);

                            this.writeKey(Key.CLEAR);
                            System.out.println("Account no: " + message);
                            return message;
                        }else{
                            this.writeKey(Key.CLEAR);
                            System.out.println(message);
                            return message;
                        }

                    }else{
                        this.writeKey(Key.CLEAR);
                        System.out.println(message);
                        return message;
                    }

                }else{
                    this.writeKey(Key.CLEAR);
                    System.out.println(message);
                    return message;
                }

            }else{
                this.writeKey(Key.CLEAR);
                System.out.println(message);
                return message;
            }

       /* }else
        {
            this.writeKey(Key.CLEAR);
            System.out.println(message);
            return message;
        }*/
    }
}
