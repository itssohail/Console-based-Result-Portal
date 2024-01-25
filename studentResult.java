package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class studentResult {
    void resultCheck()
    {
        try {
            // Establishing database connection
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");

            // Taking input from user using Scanner
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter roll number:");
            String rollNumber = scanner.nextLine();
            
            PreparedStatement pstmt = con.prepareStatement("SELECT stname, result FROM STUDENTRESULTS WHERE rollnumber = ?");
            pstmt.setString(1, rollNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("NAME : " + rs.getString("stname"));
                System.out.println("Result: " + rs.getString("result"));
                
                System.out.println("\n");
                System.out.println("Would you like to check other Student's Result? If yess Press Yes if no Press No");
                String input = scanner.next();
                if(input.equalsIgnoreCase("yes"))
                {
                	resultCheck();
                }else {
                	System.out.println("Thank you for choosing our Portal");
                }
            } else {
                System.out.println("INVALID ROLL NUMBER OR NO DATA FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) 
    {
    	new studentResult().resultCheck();
		
	}
}
