package payment;
import java.util.Scanner;

import idcontroller.PaymentIDController;

public class CardPayment {

    private double amount;
    private String customerCardNumber;
    private String paymentID;
    private boolean isCancelled; // Track if payment is canceled

    public CardPayment(double amount, String paymentID) {
        this.amount = amount;
        this.paymentID = paymentID;
        this.isCancelled = false; // Initially, payment is not canceled
    }

    public void addPaymentInfoToCSV(double amount, String paymentID, String customerCardNumber) {
        try {
            PaymentIDController.writeToCSV(new String[]{paymentID, String.valueOf(amount), customerCardNumber});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isCardNumberValid(String cardNumber) {
        return cardNumber.matches("\\d{16}"); // Simple card number validation
    }

    public void processPayment() {

    	
    	Scanner userInput = new Scanner(System.in);
    	
    	System.out.println("Do you wish to continue with your payment? (Yes/No)");
    	String userAnswer = userInput.nextLine().trim().toLowerCase();
    	
    	
    	if(userAnswer.equals("yes"))
    	{
    		cancelPayment();
    		// What should we do now?
    	}
    	
        if (!isCancelled) {
        	try {
                System.out.println("Please provide your credit card number:");
                customerCardNumber = userInput.nextLine().trim();

                if (!isCardNumberValid(customerCardNumber)) {
                    System.out.println("Invalid card number. Please try again.");
                    return;
                }

                System.out.println("Your payment of $" + amount + " is completed.");
                addPaymentInfoToCSV(amount, paymentID, customerCardNumber);
            } 
            finally {
                userInput.close(); 
            }
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
