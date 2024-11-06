package payment;

import javax.swing.JOptionPane;

import idcontroller.PaymentIDController;

public class CardPayment {

    private double amount;
    private String customerCardNumber;
    private String paymentID;
    private boolean isCancelled; // Track if payment is canceled
    private String employeeHandle;

    public CardPayment(double amount, String paymentID, String employeeHandle) 
    {
        this.amount = amount;
        this.customerCardNumber = null;
        this.paymentID = paymentID;
        this.isCancelled = false; // Initially, payment is not canceled
        this.employeeHandle = employeeHandle;
    }

    public void addPaymentInfoToCSV(double amount, String paymentID, String customerCardNumber) 
    {
        try 
        {
            PaymentIDController.writeToCSV(new String[]{paymentID, String.format(employeeHandle), "card", "true", String.valueOf(amount), customerCardNumber});
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
            JOptionPane.showMessageDialog(null, "Payment with ID " + paymentID + " has been canceled.", 
                    "Payment Canceled", JOptionPane.INFORMATION_MESSAGE);
            
            // log cancellation in CSV
            try 
            {
            	PaymentIDController.writeToCSV(new String[]{paymentID, String.format(employeeHandle), "card", "false"});
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            
        } 
    }

    public boolean processPayment() 
    {
    	
    	// Display confirmation dialog to continue with the payment
        int userChoice = JOptionPane.showConfirmDialog(null, 
            "Do you wish to continue with your payment?", 
            "Payment Confirmation", 
            JOptionPane.YES_NO_OPTION);
        
        if (userChoice == JOptionPane.NO_OPTION) 
        {
            cancelPayment();
            return true;
        }
    	
    	if (!isCancelled) 
    	{
    	    boolean isValidCardNumber = false;

    	    // Prompt user for credit card number
    	    while (!isValidCardNumber) 
    	    {
    	        customerCardNumber = JOptionPane.showInputDialog(null, 
    	            "Please provide your credit card number:");

    	        if (customerCardNumber == null) // Handle cancel action in the input dialog
    	        {
    	            cancelPayment();
    	            return false; // Exit if the user cancels the input
    	        }

    	        // Validate the card number
    	        if (isCardNumberValid(customerCardNumber)) 
    	        {
    	            isValidCardNumber = true; // Valid card number, exit loop
    	        } 
    	        else 
    	        {
    	            JOptionPane.showMessageDialog(null, 
    	                "Invalid card number. Please try again.", 
    	                "Invalid Input", 
    	                JOptionPane.ERROR_MESSAGE);
    	        }
    	    }
    	    
    	    JOptionPane.showMessageDialog(null, 
    	        "Your payment of $" + amount + " is completed.", 
    	        "Payment Completed", 
    	        JOptionPane.INFORMATION_MESSAGE);
    	    
    	    addPaymentInfoToCSV(amount, paymentID, customerCardNumber);
    	}
        
        return false;
    }
}
