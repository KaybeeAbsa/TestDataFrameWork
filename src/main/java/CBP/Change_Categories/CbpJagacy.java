package CBP.Change_Categories;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

public class CbpJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;

    public CbpJagacy() throws JagacyException
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

    public String CheckSite(String userData,String accountNo) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 1;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, accountNo);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 28;
        message = this.readPosition(userIdRow, userIdColumn, 5).trim();
        System.out.println("Message: " + message);
        return message;
    }


    public void checkMpp(String siteCode) throws JagacyException {
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
        this.writePosition(userIdRow, userIdColumn, siteCode);

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


    public String changeCRG(String accountNo, String chargeDay,String chargeFrom, String chargeStatement, String priceScheme) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 0;
        userIdColumn = 0;
        this.writePosition(userIdRow, userIdColumn, "chqa");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 7;
        this.writePosition(userIdRow, userIdColumn, "crg");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, accountNo);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        //month
        userIdRow = 5;
        userIdColumn = 52;
        this.writePosition(userIdRow, userIdColumn, chargeFrom);


        //day
        userIdRow = 6;
        userIdColumn = 52;
        this.writePosition(userIdRow, userIdColumn, chargeDay);

        //statement charges
        userIdRow = 16;
        userIdColumn = 22;
        this.writePosition(userIdRow, userIdColumn, chargeStatement);

      /*  //pricing schemer
        userIdRow = 22;
        userIdColumn = 24;
        this.writePosition(userIdRow, userIdColumn, "");*/

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 22;
        userIdColumn = 22;
        this.writePosition(userIdRow, userIdColumn, "113");

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 12;
        message = this.readPosition(userIdRow, userIdColumn, 60).trim();
        System.out.println("Message: " + message);

        if(message.equalsIgnoreCase("CHARGE CAP DAY IS NOT VALID FOR THE FREQUENCY"))
        {
            return message;
        }else
        {
            userIdRow = 22;
            userIdColumn = 63;
            this.writePosition(userIdRow, userIdColumn, "u");

            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            userIdRow = 22;
            userIdColumn = 31;
            message = this.readPosition(userIdRow, userIdColumn, 14).trim();
            System.out.println("Message: " + message);

            return message;
        }

      //  return message;
    }

}
