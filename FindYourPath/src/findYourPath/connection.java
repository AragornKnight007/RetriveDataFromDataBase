package findYourPath;

import java.sql.*;

public class connection {
	private static Connection conn;
	static String url = "jdbc:mysql://localhost:3306/tree";
	static String uname = "root";
	static String pass = "997700";

	public static Connection connectDB()

	{

		try {
			if (conn == null) {
				// Importing and registering drivers
				Class.forName("com.mysql.cj.jdbc.Driver");

				conn = DriverManager.getConnection(url, uname, pass);
			}

		} catch (Exception e) {

			System.out.println(e);
		}
		return conn;
	}
}