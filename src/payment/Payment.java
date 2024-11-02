package payment;

import java.text.SimpleDateFormat;
import java.util.*;

import Order;
import idcontroller.PaymentIDController;
import order.OrderInfo;


public class Payment {
	
	private Map<String, Integer> rawCustomerOrderInfo = new HashMap<String, Integer>(); // productID and Quantity
	private double amount;
	private String paymentID;

	
	public Payment(double amount, Map<String, Integer> rawCustomerOrderInfo) 
	{
		this.amount = amount;
		this.paymentID = generateNewPaymentID();
		this.rawCustomerOrderInfo = rawCustomerOrderInfo;
	}

	public String getID() 
	{
		return paymentID;
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
	
	public static String getCurrentDate() {
        String DATE_FORMAT = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(new Date());
    }
	
	public void exportOrderReceipt()
	{
		System.out.println("==================================");
		System.out.println("           ORDER RECEIPT");
		System.out.println("==================================");
		System.out.println(String.format("Order/Payment ID : %s", paymentID));
		System.out.println(String.format("Date : %s", getCurrentDate()));
		System.out.println("----------------------------------");
		System.out.println();
		System.out.println("Product Details :");
		System.out.println("----------------------------------");
		
		// Print the order receipt
		 System.out.println("| Product Name        | Quantity | Unit Price | Total Price |");
	     System.out.println("|---------------------|----------|------------|-------------|");

        for (Map.Entry<String, Integer> productInfo : rawCustomerOrderInfo.entrySet()) 
        {
            String productID = productInfo.getKey();
            int quantity = productInfo.getValue();

            OrderInfo productReceipt = new OrderInfo(productID, quantity);

            System.out.printf("| %-19s | %8d | %10.0f | %11.0f    |\n",
            productReceipt.getProductName(),
            productReceipt.getQuantity(),
            productReceipt.getUnitPrice(),
            productReceipt.getTotalPrice()
            );
     	}
		//
		
        System.out.println("----------------------------------");
        
        System.out.println(String.format("Total Amount Due :             %d VND", amount));
        System.out.println("Payment Status :               Paid ");
        System.out.println();
		
		System.out.println();
		System.out.println("==================================");
		System.out.println("Thank you for your purchase!");
		System.out.println("==================================");
		
	}

	public void processPayment() 
	{
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

		if (paymentMethod.equals("1")) {
			
			CashPayment cash = new CashPayment(amount, paymentID);
			cash.processPayment(); // Process payment first

		
		} else if (paymentMethod.equals("2")) {
			
			CardPayment card = new CardPayment(amount, paymentID);
			card.processPayment(); // Process payment first
	        
		} else {
			System.out.println("Invalid payment method selected.");
		}
		
		exportOrderReceipt();
		
		//After finish the payment, what should we do next?
		
		userInput.close(); 
	
	}

}

