package idcontroller;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PaymentIDController {
	
    private static final String CSV_FILE_PATH = "payment.csv";
    private static final String DATE_FORMAT = "yyyyMMdd";

    
    // Generate a new paymentID
    public static String generatePaymentID() throws IOException 
    {
        String today = getCurrentDate(); // YYYYMMDD
        List<String[]> data = readCSV("payment.csv");
        int currentMaxID = 0;

        // Check if no paymentID has been created for today
        for (String[] row : data) 
        {
            String paymentID = row[0]; // row[0] reference to paymentID on CSV file
            if (paymentID.startsWith(today)) 
            {
                // Get the "XXX" part
                int paymentNumber = Integer.parseInt(paymentID.substring(8));
                if (paymentNumber > currentMaxID) 
                {
                    currentMaxID = paymentNumber; // Get max "ID" in the day
                }
            }
        }

        // If not, start with 001, else +1
        String newPaymentID = today + String.format("%03d", currentMaxID + 1);

        return newPaymentID;
    }

    
    // Get the current day according to format YYYYMMDD
    private static String getCurrentDate() 
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(new Date());
    }
    

    // Read CSV file and return row list
    private static List<String[]> readCSV(String filePath) throws IOException
    {
        List<String[]> data = new ArrayList<>();
        File csvFile = new File(filePath);

        if (csvFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
            {
                String line;
                while ((line = br.readLine()) != null) 
                {
                    data.add(line.split(",")); // CSV separated by comma
                }
            }
        }

        return data;
    }

    
    // Write a new line to CSV
    public static void writeToCSV(String[] newRow) throws Exception 
    {
        try (FileWriter fw = new FileWriter(CSV_FILE_PATH, true); // true to add to file
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) 
        {
            out.println(String.join(",", newRow));
        }
    }
    
    
}
