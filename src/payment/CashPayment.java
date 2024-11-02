package payment;
import java.util.*;

import idcontroller.PaymentIDController;

public class CashPayment {

    private double amount;
    private String paymentID;
    private boolean isCancelled; // Track if payment is canceled

    public CashPayment(double amount, String paymentID) {
        this.amount = amount;
        this.paymentID = paymentID;
        this.isCancelled = false; // Initially, payment is not canceled
    }

    public void addPaymentInfoToCSV(double amount, String paymentID) {
        try {
            PaymentIDController.writeToCSV(new String[]{paymentID, String.valueOf(amount)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processPayment() {
    	
    	Scanner userInput = new Scanner(System.in);
    	
    	System.out.println("Do you wish to continue with your payment? (Yes/No)");
    	
    	String userAnswer = userInput.nextLine().trim().toLowerCase();
    	
    	userInput.close();
    	
    	if(userAnswer.equals("no"))
    	{
    		cancelPayment();
    		// What should we do now?
    	}
    	
        if (!isCancelled) {
            System.out.println("Your payment of $" + amount + " is completed.");
            addPaymentInfoToCSV(amount, paymentID);
            // What should we do now? exit or something?
        }
    }

    public void cancelPayment() {
    	
        if (!isCancelled) {
            isCancelled = true; // Mark the payment as canceled
            System.out.println("Payment with ID " + paymentID + " has been canceled.");
            
            // log cancellation in CSV
            try {
            	PaymentIDController.writeToCSV(new String[]{paymentID, "Canceled"});
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } 
    }
}
