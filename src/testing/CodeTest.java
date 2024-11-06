package testing;

import payment.Payment;

public class CodeTest {
	
	public static void main(String args[])
	{
		try 
		{
            Payment testPayment1 = new Payment(100000.0, "export", "Son");
            testPayment1.processPayment();
            Thread.sleep(1000 * 10); 

            Payment testPayment2 = new Payment(25000.0, "import", "Nguyet Anh");
            testPayment2.processPayment();
            Thread.sleep(1000 * 10);

            Payment testPayment3 = new Payment(45222.0, "export", "Khanh");
            testPayment3.processPayment();
            Thread.sleep(1000 * 10);

            Payment testPayment4 = new Payment(200000.0, "export", "Hoang");
            testPayment4.processPayment();
            Thread.sleep(1000 * 10);

            Payment testPayment5 = new Payment(65123.0, "import", "Quan");
            testPayment5.processPayment();
        } 
		catch (InterruptedException e) 
		{
            e.printStackTrace();
        }
	}
}
