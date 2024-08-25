package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHandler {
	
	private   String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/project";
      private String dbUsername = "root";
       private String dbPassword = "Country@7755";
       private Connection connection;

	public DBHandler() {
		try {
			connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet get_hosteldata(String address)
	{
		String query = "SELECT * FROM hostel WHERE address = ? ORDER BY hostel_id LIMIT 1";

		PreparedStatement statement;
		
		ResultSet resultSet = null;
		try {
			
			statement = connection.prepareStatement(query);
			statement.setString(1,address);
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
		
	}
	
	public ResultSet authenticateUser2(String username,String password )
	{
		 String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		 
		 PreparedStatement statement ;
		 ResultSet resultSet = null;
		 try {
			 statement=	connection.prepareStatement(query);
				 statement.setString(1, username);
         statement.setString(2, password);
         resultSet = statement.executeQuery();
		 } catch (SQLException e) {
				e.printStackTrace();
			}
			
		 return resultSet;
	}
	public ResultSet bookingdetail(String username ) 
	{
		String query = "SELECT * FROM room WHERE customer_id = ?";
		 PreparedStatement statement ;
		 ResultSet resultSet = null;
		 try {
			 statement=	connection.prepareStatement(query);
			statement.setString(1, username);
         resultSet = statement.executeQuery();
		 } catch (SQLException e) {
				e.printStackTrace();
			}
		 
		return resultSet;
	}

}
