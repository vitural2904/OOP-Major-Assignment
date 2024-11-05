package payment;
import java.util.*;

import idcontroller.PaymentIDController;

public class CashPayment {

    private double amount;
    private String paymentID;
    private boolean isCancelled; // Track if payment is canceled

    public CashPayment(double amount, String paymentID) 
    {
        this.amount = amount;
        this.paymentID = paymentID;
        this.isCancelled = false; // Initially, payment is not canceled
    }

    public void addPaymentInfoToCSV(double amount, String paymentID) 
    {
        try 
        {
            PaymentIDController.writeToCSV(new String[]{paymentID, "cash", "true", String.valueOf(amount), null});
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
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
            	PaymentIDController.writeToCSV(new String[]{paymentID, "cash", "false"});
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
    	
    	userInput.close();
    	
    	if(userAnswer.equals("No"))
    	{
    		cancelPayment();
    		return true;
    	}
    	
        if (isCancelled == false) 
        {
            System.out.println("Your payment of $" + amount + " is completed.");
            addPaymentInfoToCSV(amount, paymentID);
        }
        
        return false;
    }

   
}
