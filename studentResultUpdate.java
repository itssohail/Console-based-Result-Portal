package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class studentResultUpdate 
{
	void update()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			Scanner sc = new Scanner(System.in);
			System.out.println("ENTER THE BELOW DETAILS TO UPDATE INTO DATABASE\n");
			System.out.println("student name: ");
			String studentName = sc.nextLine();
			System.out.println("Roll Number: ");
			String rollNumber = sc.nextLine();
			System.out.println("Result: ");
			String result = sc.next();
			
			String query  = " insert into studentresults values(?,?,?) ";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, studentName);
			pstmt.setString(2, rollNumber);
			pstmt.setString(3, result);
			
			int rowsAffected = pstmt.executeUpdate();

			if(rowsAffected>0)
			{
				System.out.println("Student Data inserted successfully");
				System.out.println();
				System.out.println("Would you like to insert another student's data? if yes press Y if no press N");
				String input = sc.next();
				if(input.equalsIgnoreCase("yes"))
				{
					update();
				}else
				{
					System.out.println("PORTAL TERMINATED");
				}
			}
			else
			{
				System.out.println("insertion failed");
			}
		}
		catch(Exception e)
		{
			
		}
	}
	public static void main(String[] args) 
	{
		 studentResultUpdate aobj = new studentResultUpdate();
		 aobj.update();
		
	}
}
