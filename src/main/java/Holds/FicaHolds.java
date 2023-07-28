package Holds;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FicaHolds extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;

    public FicaHolds() throws JagacyException
    {
        super("sessionA","host3270.absa.co.za",993,"IBM-3279-5-E",true);
    }

    public boolean userLogin(String username,String password) throws JagacyException
    {
        waitForChange(10000);
        userIdRow = 22;
        userIdColumn = 26;
        this.writePosition(userIdRow, userIdColumn, "Iv01");
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

    public String addFicHold(String userData, String clientID) throws JagacyException, AWTException, InterruptedException {
        Robot robot = new Robot();

        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 11;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn, clientID);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 13;
        message = this.readPosition(userIdRow, userIdColumn, 30).trim();

        if(message.equalsIgnoreCase("< Data entry too short"))
        {
            return message;
        }

        userIdRow = 22;
        userIdColumn = 10;
        message = this.readPosition(userIdRow, userIdColumn, 30).trim();

        System.out.println("Messsage: "  +message);
        if(message.equalsIgnoreCase("RISK LOCK ALREADY PLACED"))
        {
            return message;
        }else
        {
            userIdRow = 14;
            userIdColumn = 45;
            this.writePosition(userIdRow, userIdColumn, "y");
            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            userIdRow = 14;
            userIdColumn = 19;
            message = this.readPosition(userIdRow, userIdColumn, 30).trim();

            System.out.println("message: " + message);
            return message;
        }
    }

    public String removeFicHold(String userData, String clientID) throws JagacyException, AWTException, InterruptedException {
        Robot robot = new Robot();

        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 11;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn, clientID);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 13;
        message = this.readPosition(userIdRow, userIdColumn, 30).trim();

        if(message.equalsIgnoreCase("< Data entry too short"))
        {
            return message;
        }

        userIdRow = 22;
        userIdColumn = 10;
        message = this.readPosition(userIdRow, userIdColumn, 30).trim();

        System.out.println("Messsage: "  +message);
        if(message.equalsIgnoreCase("CLIENT IS NOT IN FIC LOCK"))
        {
            return message;
        }else
        {
            userIdRow = 19;
            userIdColumn = 40;
            this.writePosition(userIdRow, userIdColumn, "y");
            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            userIdRow = 21;
            userIdColumn = 10;
            message = this.readPosition(userIdRow, userIdColumn, 30).trim();
            System.out.println("message: " + message);

            if(message.equalsIgnoreCase("FICA FIELDS NOT COMPLETED ON C")){
                return message;
            }else{
                userIdRow = 21;
                userIdColumn = 10;
                message = this.readPosition(userIdRow, userIdColumn, 30).trim();
                System.out.println("message: " + message);
            }
            return message;
        }
    }

    public String addCIFHold(String userData, String clientID,String IDENTIFICATIONREQUIRED, String INSOLVENTESTATE, String DECEASEDESTATE,String SPOUSEDECEASED,String CURATORSHIP, String CLIENTAGREEMENTISSUED, String NEWPOSTALADDRESS, String NEWRESIDENTIALADDRESS, String NEWEMPLOYERSNAMEADDRESS ) throws JagacyException, AWTException, InterruptedException {
        Robot robot = new Robot();

        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 7;
        this.writePosition(userIdRow, userIdColumn, "hold");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 3;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, clientID);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 13;
        message = this.readPosition(userIdRow, userIdColumn, 30).trim();

        if(IDENTIFICATIONREQUIRED.equalsIgnoreCase("yes")){
            userIdRow = 4;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "Y");

        }else{
            userIdRow = 4;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "N");
        }

        if(INSOLVENTESTATE.equalsIgnoreCase("YES"))
        {
            userIdRow = 5;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "Y");
        }else{
            userIdRow = 5;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "N");
        }

        if(DECEASEDESTATE.equalsIgnoreCase("YES")){
            userIdRow = 6;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "Y");
        }else{
            userIdRow = 6;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "N");
        }

        if(SPOUSEDECEASED.equalsIgnoreCase("YES")){
            userIdRow = 7;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "Y");
        }else{
            userIdRow = 7;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "N");
        }

        if(CURATORSHIP.equalsIgnoreCase("YES")){
            userIdRow = 8;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "Y");
        }else{
            userIdRow = 8;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "N");
        }

        if(CLIENTAGREEMENTISSUED.equalsIgnoreCase("YES")){
            userIdRow = 11;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "Y");
        }else{
            userIdRow = 11;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "N");
        }

        if(NEWEMPLOYERSNAMEADDRESS.equalsIgnoreCase("YES")){
            userIdRow = 13;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "Y");
        }else{
            userIdRow = 13;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "N");
        }

        if(NEWPOSTALADDRESS.equalsIgnoreCase("YES")){
            userIdRow = 14;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "Y");
        }else{
            userIdRow = 14;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "N");
        }

        if(NEWRESIDENTIALADDRESS.equalsIgnoreCase("YES")){
            userIdRow = 15;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "Y");
        }else{
            userIdRow = 15;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, "N");
        }

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 22;
        userIdColumn = 10;
        message = this.readPosition(userIdRow, userIdColumn, 30).trim();

        System.out.println("Messsage: "  +message);
        return message;

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
        this.writePosition(userIdRow, userIdColumn, idNumber);
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

    boolean zPressed = false;

    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_F4)
        {
            if (!zPressed) {
              //  channel.noteOn (((WhiteKey) WhiteKeys[0]).getNote (), 127);
                System.out.print("Pressed....");
                zPressed = true;
            }
        }
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_F4) {
            zPressed = false;
        }
    }
}
