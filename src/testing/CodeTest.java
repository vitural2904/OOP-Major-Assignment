package testing;

import payment.Payment;

public class CodeTest {
	
	public static void main(String args[])
	{
		try 
		{
            Payment testPayment1 = new Payment(100000.0, "export", "Son");
            testPayment1.processPayment();
            Thread.sleep(1000 * 15); 

            Payment testPayment2 = new Payment(25000.0, "import", "Nguyet Anh");
            testPayment2.processPayment();
            Thread.sleep(1000 * 15);

            Payment testPayment3 = new Payment(45222.0, "export", "Khanh");
            testPayment3.processPayment();
        } 
		catch (InterruptedException e) 
		{
            e.printStackTrace();
        }
	}
}
