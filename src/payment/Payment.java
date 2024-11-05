package payment;

import java.util.*;

import Order;
import idcontroller.PaymentIDController;


public class Payment {
	
	private double totalAmount;
	private String paymentID;
	private boolean status;

	
	public Payment(double totalAmount) 
	{
		this.totalAmount = totalAmount;
		this.paymentID = generateNewPaymentID();
		this.status = false;
	}

	public String getID() 
	{
		return paymentID;
	}
	
	public boolean getStatus()
	{
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	private String generateNewPaymentID() 
	{
	    String newPaymentID = null; 
	    try {
	        newPaymentID = PaymentIDController.generatePaymentID();
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    return newPaymentID; 
	}
	

	public boolean processPayment() 
	{
		boolean currentPaymentStatus = false;
		
		System.out.println("Your payment is ready to go.");
		System.out.println("=== Payment Method Selection ===");
		System.out.println("Please select your desired payment method (Type 1 or 2):");
		System.out.println("------------------------------");
		System.out.println("1. Cash");
		System.out.println("2. Card");
		System.out.println("------------------------------");
		System.out.println("Enter the number corresponding to your choice:");

		Scanner userInput = new Scanner(System.in);
		String paymentMethod = userInput.nextLine().trim();
		userInput.close(); 

		if (paymentMethod.equals("1")) 
		{
			
			CashPayment cash = new CashPayment(totalAmount, paymentID);
			currentPaymentStatus = cash.processPayment(); // Process payment first
			
		
		} 
		else if (paymentMethod.equals("2")) 
		{
			
			CardPayment card = new CardPayment(totalAmount, paymentID);
			currentPaymentStatus = card.processPayment(); // Process payment first
	        
		} 
		
		return status = currentPaymentStatus;
	}

}

