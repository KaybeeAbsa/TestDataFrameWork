package Interface;

import org.testng.annotations.Test;

import javax.swing.*;

public class Design {
    public static void main(String[] args){

        String[] optionsToChoose = {"CHQA", "CHQO", "FINANCIALS", "CHQQ"};
        String[] financials = {"CW", "CD", "MD","CWC","CDC"};
        String[] chqq = {"BAL","BAS","CRG","CLSE","CRGH","CRGC","HIST","HOLD","INT"
                ,"INTB","INTC","INTH","MINI","UNCL","PRES","CHIS","INFO","ODC","ODH"};
        String[] chqo = {"SOD","SODD","EXTN","DEXT","CDCR","ROD","RODD"};

        String getFunction = (String) JOptionPane.showInputDialog(
                null,
                "Which function do you want to execute?...",
                "Choose Function to be executed",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsToChoose,
                optionsToChoose[3]);



        if(getFunction.equalsIgnoreCase("CHQA")){
            System.out.println("Your chosen Function: " + getFunction);


        }else  if(getFunction.equalsIgnoreCase("CHQO")){

            String getTransaction = (String) JOptionPane.showInputDialog(
                    null,
                    "Select CHQO Type to be Executed...",
                    "Choose CHQO Type to be executed.",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    chqo,
                    chqo[3]);

            if(getTransaction.equals("SOD")){

                System.out.println("Inside add Odd");
            }else if(getFunction.equals("SODD")){
                System.out.println("Inside SOOD");
            }else{

            }


        }else  if(getFunction.equalsIgnoreCase("FINANCIALS")){

            String getTransaction = (String) JOptionPane.showInputDialog(
                    null,
                    "Select Transaction Type to be Executed...",
                    "Choose Transaction Type to be executed.",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    financials,
                    financials[3]);

            if(getTransaction.equals("CD")){
                System.out.println("Cash Deposit");
            }else if(getTransaction.equalsIgnoreCase("CW")){
                System.out.println("Cash Withdrawal");
            }else{
                System.out.println("simple");
            }
        }else  if(getFunction.equalsIgnoreCase("CHQQ")){
            String getTransaction = (String) JOptionPane.showInputDialog(
                    null,
                    "Select CHQQ Type to be Executed...",
                    "Choose CHQQ Type to be executed.",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    chqq,
                    chqq[3]);

            if(getTransaction.equals("BAL")){
                System.out.println("Inside Balance");
            }else if(getTransaction.equals("BAS")){
                System.out.println("Inside Basic");
            }else{

            }
        }

        JOptionPane.showMessageDialog(null, "Test Execution completed...");
    }

}
