package PreApproved_Accounts;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PreApprovedPLODJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;

    public PreApprovedPLODJagacy() throws JagacyException
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

    public String preApprovedPLOD(String userData, String clientID, String new_Scoring_Grade, String new_TMR, String new_AMR, String new_AMLR, String new_SOL, String new_AOL, String new_Capped_AOL, String new_CF_Grade, String new_CDTO, String new_UDTO, String new_Gross_Income, String new_CB_Commit, String new_Max_Living_EXP, String new_Accl) throws JagacyException, AWTException, InterruptedException {
        Robot robot = new Robot();

        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 8;
        userIdColumn = 22;
        this.writePosition(userIdRow, userIdColumn, clientID);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 12;
        message = this.readPosition(userIdRow, userIdColumn, 25).trim();
        System.out.println("Message: " + message);

        if(message.equalsIgnoreCase("Data entry too short") || message.equalsIgnoreCase("No client info to display"))
        {
            return message;
        }else
        {

            userIdRow = 23;
            userIdColumn = 38;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();
            System.out.println("Message: " + message);

            if(message.equalsIgnoreCase("UPDATE LIMITS/RISK GRADES")) {


                userIdRow = 5;
                userIdColumn = 66;
                this.writePosition(userIdRow, userIdColumn, new_Scoring_Grade);

                userIdRow = 7;
                userIdColumn = 67;
                this.writePosition(userIdRow, userIdColumn, new_TMR);

                userIdRow = 8;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_AMR);

                userIdRow = 9;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_AMLR);

                userIdRow = 10;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_Accl);

                userIdRow = 11;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_SOL);

                userIdRow = 12;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_AOL);

                userIdRow = 13;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_Capped_AOL);


                userIdRow = 14;
                userIdColumn = 66;
                this.writePosition(userIdRow, userIdColumn, new_CF_Grade);

                userIdRow = 15;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_CDTO);

                userIdRow = 9;
                userIdColumn = 69;
                this.writePosition(userIdRow, userIdColumn, new_Accl);

                userIdRow = 16;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_UDTO);

                userIdRow = 17;
                userIdColumn = 67;
                this.writePosition(userIdRow, userIdColumn, new_Gross_Income);

                userIdRow = 18;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_CB_Commit);

                userIdRow = 19;
                userIdColumn = 68;
                this.writePosition(userIdRow, userIdColumn, new_Max_Living_EXP);


                this.writeKey(Key.PF4);
                this.waitForChange(1000);

                userIdRow = 0;
                userIdColumn = 12;
                message = this.readPosition(userIdRow, userIdColumn, 25).trim();
                System.out.println("Message: " + message);

                return message;
            }else{
                return message;
            }
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
