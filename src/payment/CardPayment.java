package payment;
import java.util.Scanner;

import idcontroller.PaymentIDController;

public class CardPayment {

    private double amount;
    private String customerCardNumber;
    private String paymentID;
    private boolean isCancelled; // Track if payment is canceled

    public CardPayment(double amount, String paymentID) 
    {
        this.amount = amount;
        this.customerCardNumber = null;
        this.paymentID = paymentID;
        this.isCancelled = false; // Initially, payment is not canceled
    }

    public void addPaymentInfoToCSV(double amount, String paymentID, String customerCardNumber) 
    {
        try 
        {
            PaymentIDController.writeToCSV(new String[]{paymentID, "card", "true", String.valueOf(amount), customerCardNumber});
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public boolean isCardNumberValid(String cardNumber) 
    {
        return cardNumber.matches("\\d{16}"); // Simple card number validation
    }
    

    
    public void cancelPayment() 
    {
    	
        if (isCancelled == false) 
        {
            isCancelled = true; // Mark the payment as canceled
            System.out.println("Payment with ID " + paymentID + " has been canceled.");
            
            // log cancellation in CSV
            try 
            {
            	PaymentIDController.writeToCSV(new String[]{paymentID, "card", "false"});
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            
        } 
    }

    public boolean processPayment() 
    {

    	
    	Scanner userInput = new Scanner(System.in);
    	
    	System.out.println("Do you wish to continue with your payment? (Yes/No)");
    	String userAnswer = userInput.nextLine().trim().toLowerCase();
    	
    	
    	if(userAnswer.equals("yes"))
    	{
    		cancelPayment();
    		userInput.close();
    		return true;
    	}
    	
        if (isCancelled == false) 
        {

            System.out.println("Please provide your credit card number:");
            while(isCardNumberValid(customerCardNumber) == false)
            {
            	customerCardNumber = userInput.nextLine().trim();
            	System.out.println("Invalid card number. Please try again.");
                
            }
            System.out.println("Your payment of $" + amount + " is completed.");
            addPaymentInfoToCSV(amount, paymentID, customerCardNumber);
        }

        userInput.close(); 
        
        return false;
    }
}
