package Interface;

import javax.swing.*;

public class ProductsInterface {
    public static void main(String[] args){
        String[] products = {"Cheques","Savings", "Personal Loan","Credit  Card"};

        String getProducts = (String) JOptionPane.showInputDialog(
                null,
                "Which Product Do you want to Test?...",
                "Choose Product to be Tested",
                JOptionPane.QUESTION_MESSAGE,
                null,
                products,
                products[3]);

        if(getProducts.equalsIgnoreCase("Cheques")){
            String[] optionsToChoose = {"CHQA", "CHQO", "FINANCIALS", "CHQQ"};
            String[] financials = {"CW", "CD", "MD","CWC","CDC"};
            String[] chqq = {"BAL","BAS","CRG","CLSE","CRGH","CRGC","HIST","HOLD","INT"
                    ,"INTB","INTC","INTH","MINI","UNCL","PRES","CHIS","INFO","ODC","ODH"};
            String[] chqo = {"SOD","SODD","EXTN","DEXT","CDCR","ROD","RODD"};

            System.out.println("Inside Cheqes");

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

        }
        else if(getProducts.equalsIgnoreCase("Personal Loan")){
            //Logic for PL

        }else if(getProducts.equalsIgnoreCase("Savings")){
            System.out.println("Inside Savings");
            String[] optionsToChoose = {"Savings And enquiry", "Term Deposit Enquiry", "FINANCIALS", "Saving Maintenance"};
            String[] financials = {"CW", "CD", "MD","CWC","CDC"};
            String[] savingsMaintenance = {"AFM",
                    "AUTA",
                    "AUTD",
                    "CAT",
                    "CDOM",
                    "CHAC",
                    "CHG",
                    "CHPV",
                    "FCAP",
                    "GEND",
                    "RQA",
                    "RQD",
                    "UNCL",
                    "UPDI",
                    "YCAP"
                    ,"NRES","SETL"
            };

            String[] TermDepositEnquiry = {"AFM",
                    "BAL",
                    "BAS",
                    "CLSE",
                    "EXCP",
                    "HIST",
                    "HOLD",
                    "PRES",
                    "RATE",
                    "REN",
                    "UNCL",
                    "WNOT",
                    "QUOT",
                    "MAND",
                    "BINT",
                    "RCHG"

            };

            String[] SavingsEnquiry = {"SAVQ ACON",
                    "SAVQ APPL",
                   " SAVQ BAL",
                    "SAVQ BAS",
                    "SAVQ CHRG",
                    "SAVQ CLSE",
                   " SAVQ EXCP",
                    "SAVQ HIST",
                    "SAVQ HOLD",
                    "SAVQ INT",
                    "SAVQ MINI",
                    "SAVQ MGR",
                    "SAVQ PCON",
                   " SAVQ PDTE",
                    "SAVQ PRES",
                    "SAVQ RATE",
                    "SAVQ YLD",
                    "SAVQ YLDM",
                    "SAVQ MMF"
            };

            String getFunction = (String) JOptionPane.showInputDialog(
                    null,
                    "Which function do you want to execute?...",
                    "Choose Function to be executed",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsToChoose,
                    optionsToChoose[3]);

            if(getFunction.equalsIgnoreCase("Term Deposit Enquiry")){
                System.out.println("Your chosen Function: " + getFunction);
                String getTransaction = (String) JOptionPane.showInputDialog(
                        null,
                        "Select Term Deposit Enquiry Type to be Executed...",
                        "Choose Term Deposit Enquiry Type to be executed.",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        TermDepositEnquiry,
                        TermDepositEnquiry[3]);

                if(getTransaction.equals("BAL")){

                    System.out.println("Inside Bal");
                }else if(getFunction.equals("BAS")){
                    System.out.println("Inside Bas");
                }else{

                }


            }else  if(getFunction.equalsIgnoreCase("Savings And enquiry")){

                String getTransaction = (String) JOptionPane.showInputDialog(
                        null,
                        "Select Savings Enquiry Type to be Executed...",
                        "Choose Savings Enquiry Type to be executed.",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        SavingsEnquiry,
                        SavingsEnquiry[3]);

                if(getTransaction.equals("SAVQ ACON")){

                    System.out.println("Inside SAVQ ACON");
                }else if(getFunction.equals("SAVQ APPL")){
                    System.out.println("Inside SAVQ APPL");
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
            }else  if(getFunction.equalsIgnoreCase("Saving Maintenance")){
                String getTransaction = (String) JOptionPane.showInputDialog(
                        null,
                        "Select Saving Maintenance Type to be Executed...",
                        "Choose Saving Maintenance Type to be executed.",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        savingsMaintenance,
                        savingsMaintenance[3]);

                if(getTransaction.equals("AFM")){
                    System.out.println("Inside AFM");
                }else if(getTransaction.equals("AUTA")){
                    System.out.println("Inside AUTA");
                }else{

                }
            }

        }

        JOptionPane.showMessageDialog(null, "Test Execution completed...");
    }

}
