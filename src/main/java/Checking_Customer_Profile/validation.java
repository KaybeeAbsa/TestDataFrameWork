package Checking_Customer_Profile;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
public class validation {


    public static void main(String[] args) {
        String cellphoneNumber;
        cellphoneNumber= JOptionPane.showInputDialog("Please enter your cellphone number = ) ");
        if (validateSAPhoneNumber(cellphoneNumber)) {
            JFrame frame = new JFrame("Input Dialog");
            JOptionPane.showMessageDialog(frame,
                    "Cellphone number is valid =)");
            System.exit(0);
        }else
        {
            JFrame frame = new JFrame("Output Dialog");
            JOptionPane.showMessageDialog(frame,
                    "Cellphone number is invalid =(");
            System.exit(0);
        }
    }
    public static boolean validateSAPhoneNumber(String cellphoneNumber) {
        if(cellphoneNumber.matches("^((?:\\+27|27)|0)(\\d{2})-?(\\d{3})-?(\\d{4})$")){
            return true;
        }else{
            return false;
        }
    }


    @Test
    public void testPhoneValidation(){

        String[] validTest = {"072" , "0727642310","ABSJSJS","OWIWUEUEJD","07276422"};
        String cellPhoneNumber;

        for(int x = 0; x < validTest.length;x++){
            cellPhoneNumber = validTest[x];

            boolean validNumbers = validateSAPhoneNumber(cellPhoneNumber);

            if(!validNumbers){
                System.out.println("Failed");
            }else{
                System.out.println("Passed");
            }
        }
    }
}
