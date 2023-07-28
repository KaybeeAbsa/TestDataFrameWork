package Add_OD_Amount;

import com.gargoylesoftware.htmlunit.javascript.host.media.MediaElementAudioSourceNode;
import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

public class AddODJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;

    public AddODJagacy() throws JagacyException
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

    public String AddOD(String userData,String accountNo, String requiredAction, String overdraftType, String aggrementSize) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 0;
        userIdColumn = 0;
        this.writePosition(userIdRow, userIdColumn, "CHQO");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 7;
        this.writePosition(userIdRow, userIdColumn, "SOD");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);
        System.out.print(accountNo);
        userIdRow = 2;
        userIdColumn = 30;
        this.writePosition(userIdRow, userIdColumn, accountNo.trim());

        userIdRow = 4;
        userIdColumn = 30;
        this.writePosition(userIdRow, userIdColumn, requiredAction);

        userIdRow = 11;
        userIdColumn = 30;
        this.writePosition(userIdRow, userIdColumn, overdraftType);

        userIdRow = 12;
        userIdColumn = 30;
        this.writePosition(userIdRow, userIdColumn, aggrementSize);

        userIdRow = 13;
        userIdColumn = 30;
        this.writePosition(userIdRow, userIdColumn, "n");

        userIdRow = 14;
        userIdColumn = 30;
        this.writePosition(userIdRow, userIdColumn, "n");

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 12;
        message = this.readPosition(userIdRow, userIdColumn, 60).trim();
        System.out.println("Message: " + message);

        if(message.equalsIgnoreCase("INCREASE NOT ALLOWED IF NO OVERDRAFT EXISTS") || message.equalsIgnoreCase("TRANSACTION INVALID FOR ACCOUNT TYPE") || message.equalsIgnoreCase("TRAN PROHIBITED - RESTRICTIVE DIGITAL HOLD") || message.equalsIgnoreCase("OVERDRAFT INVALID FOR ACCOUNT TYPE") || message.equalsIgnoreCase("NOT AUTHORIZED TO PROCESS OVERDRAFT")  || message.equalsIgnoreCase("ACCOUNT NUMBER DOES NOT EXIST") || message.equalsIgnoreCase("NO RAC-SITE FOR ACCOUNT DOMICILE") || message.equalsIgnoreCase("TRANSACTION NOT ALLOWED - UNDER DEBT COUNSELLING") || message.equalsIgnoreCase("ENTER APPLICATION NUMBER")){
            return message;
        }else{
            userIdRow = 8;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "01012022");

            userIdRow = 9;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "01012022");

            userIdRow = 15;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "grnt");

            userIdRow = 18;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "113");

            userIdRow = 19;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "1196035");

            userIdRow = 20;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "n");

            /*userIdRow = 21;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "n");*/
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(2000);

            this.writeKey(Key.ENTER);
            this.waitForChange(3000);

            userIdRow = 0;
            userIdColumn = 12;
            message = this.readPosition(userIdRow, userIdColumn, 60).trim();

            if(message.equalsIgnoreCase("?? Missing data entry                           ERR-"))
            {
                userIdRow = 23;
                userIdColumn = 24;
                this.writePosition(userIdRow, userIdColumn, "y");

                this.writeKey(Key.ENTER);
                this.waitForChange(30000);

            }

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 0;
            userIdColumn = 12;
            message = this.readPosition(userIdRow, userIdColumn, 60).trim();
            System.out.println("Message: " + message);
            return message;
        }
    }


    public void checkMpp() throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, "mpp");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

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

}
