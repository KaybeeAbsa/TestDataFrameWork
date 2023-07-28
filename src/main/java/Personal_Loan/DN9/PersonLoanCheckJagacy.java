package Personal_Loan.DN9;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.util.ArrayList;

public class PersonLoanCheckJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;
    private String[] readData;
    private String value = null;
    private String indicator = null;
    private String amount = null;
    private String date = null;

    private String repaymentDate = null;
    private String Arreramount = null;

    private String outstandingAmount = null;
    private String accountType = null;

    private ArrayList<String> accountInformation = null;

    public PersonLoanCheckJagacy() throws JagacyException
    {
       // super("sessionA","host3270.absa.co.za",993,"IBM-3279-5-E",true);

        super("sessionA", "host3270.absa.co.za",993,"IBM-3279-5-E",true);

    }

    public boolean userLogin(String username,String password) throws JagacyException
    {
        waitForChange(1000);
        userIdRow = 22;
        userIdColumn = 26;
        this.writePosition(userIdRow, userIdColumn, "iv01");
        this.writeKey(Key.ENTER);

        this.waitForChange(1000);
        userIdRow = 14;
        userIdColumn = 10;
        this.writePosition(userIdRow,userIdColumn,username);

        userIdRow = 16;
        userIdColumn = 11 ;
        this.writePosition(userIdRow, userIdColumn, password);
        this.writeKey(Key.ENTER);
        this.waitForChange(3000);

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

   public String CheckDM9Indicator(String userData, String accountNo) throws JagacyException {

       this.waitForChange(1000);
       userIdRow = 2;
       userIdColumn = 1;
       this.writePosition(userIdRow, userIdColumn, userData);
       this.writeKey(Key.ENTER);
       this.waitForChange(3000);

       userIdRow = 23;
       userIdColumn = 25;
       this.writePosition(userIdRow, userIdColumn, accountNo);
       this.writeKey(Key.ENTER);
       this.waitForChange(3000);

       userIdRow = 14;
       userIdColumn = 28;
       repaymentDate = this.readPosition(userIdRow, userIdColumn, 3).trim();

       this.writeKey(Key.ENTER);
       this.waitForChange(3000);

       userIdRow = 6;
       userIdColumn = 28;
       accountType = this.readPosition(userIdRow, userIdColumn, 3).trim();


       userIdRow = 15;
       userIdColumn = 69;
       date = this.readPosition(userIdRow, userIdColumn, 10).trim();
       //System.out.println("Date of Last installment: " + date + "     Account No: " + accountNo);

       this.writeKey(Key.ENTER);
       this.waitForChange(3000);

       userIdRow = 7;
       userIdColumn = 27;
       indicator = this.readPosition(userIdRow, userIdColumn, 4).trim();


       userIdRow = 12;
       userIdColumn = 69;
       outstandingAmount = this.readPosition(userIdRow, userIdColumn, 10).trim();

       this.writeKey(Key.ENTER);
       this.waitForChange(3000);

       this.writeKey(Key.ENTER);
       this.waitForChange(3000);

       userIdRow = 6;
       userIdColumn = 69;
       amount = this.readPosition(userIdRow, userIdColumn, 10).trim();

       userIdRow = 7;
       userIdColumn = 69;
       Arreramount = this.readPosition(userIdRow, userIdColumn, 10).trim();
       //System.out.println("Commitment Outstanding: " + amount);
       //System.out.println("DM9 Indicator: " + indicator + "     Account No: " + accountNo + "   Amount: " + amount) ;
       //message = indicator + " : R "  + amount + " : Date Last Installment: " + date;
       message = "account no: " + accountNo + " Account type: " + accountType + " : repayment Date: " + repaymentDate + " : outstanding amount: " + outstandingAmount + " : arrear Amount: " + Arreramount;
      System.out.println(message);
       return message;
   }

   public String checkInstallmentMonths(String userdata, String accountNo)
   {



       return "";
   }
 }

