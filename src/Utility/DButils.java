package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButils {

	public Connection getConncetion()
	{
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        try {
        		 conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/mini_project", "root", "Admin@123");

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

		return conn;
		
	}
}
