package payment;

import javax.swing.*;

import idcontroller.PaymentIDController;

public class CashPayment {

    private double amount;
    private String paymentID;
    private boolean isCancelled; // Track if payment is canceled
    private String employeeHandle;

    public CashPayment(double amount, String paymentID, String employeeHandle) 
    {
        this.amount = amount;
        this.paymentID = paymentID;
        this.isCancelled = false; // Initially, payment is not canceled
        this.employeeHandle = employeeHandle;
    }

    public void addPaymentInfoToCSV(double amount, String paymentID) 
    {
        try 
        {
            PaymentIDController.writeToCSV(new String[]{paymentID, String.format(employeeHandle), "cash", "true", String.valueOf(amount), null});
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
            JOptionPane.showMessageDialog(null, "Payment with ID " + paymentID + " has been canceled.", 
                    "Payment Canceled", JOptionPane.INFORMATION_MESSAGE);
            
            // log cancellation in CSV
            try 
            {
            	PaymentIDController.writeToCSV(new String[]{paymentID, String.format(employeeHandle), "cash", "false"});
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
        
        // Process payment if it hasn't been canceled
        if (!isCancelled) 
        {
            JOptionPane.showMessageDialog(null, 
                "Your payment of $" + amount + " is completed.", 
                "Payment Completed", 
                JOptionPane.INFORMATION_MESSAGE);
            
            addPaymentInfoToCSV(amount, paymentID);
        }
        
        return false;
    }


   
}
