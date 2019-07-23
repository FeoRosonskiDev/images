package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private Connection con;
	private final String url = "jdbc:mysql://localhost:3306/imagenes";
	private final String user = "root";
	private final String pass = "";
	
	public DatabaseConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url, user, pass);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getCon()
	{
		return con;
	}
}
