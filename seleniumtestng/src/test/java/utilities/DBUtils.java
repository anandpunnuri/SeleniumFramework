package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	String DBURL="jdbc:mysql://localhost:3036/emp";
	Connection conn;
	Statement stmt;
	ResultSet rs;
	public void connectToDB()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DBURL, "root", "password");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("sql query");
		}		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(SQLException sqle)
		{
			System.out.println("SQL Exception while connecting to DB: "+sqle.getMessage());
		}
	}
	
	public ResultSet executequery(String query)
	{
		try
		{
		connectToDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("sql query");
		}
		catch(SQLException sqle)
		{
			System.out.println("SQL Exception while running the query: "+sqle.getMessage());
		}
		return rs;
	}
	
	public boolean verifyinDB(String query, String StrToValidate, String columnName)
	{
		String value = null;
		boolean StrFoundNotFound = false;
		try
		{
		ResultSet rsv = executequery(query);		
			while(rsv.next())
			{
				value = rsv.getString(columnName);
			}
			if(value!=null)
			{
				if(value.equalsIgnoreCase(StrToValidate))
				{
					StrFoundNotFound = true;
				}
				else
				{
					StrFoundNotFound = false;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception while verifying in DB: "+e.getMessage());
		}
		return StrFoundNotFound;
	}
	
	public void closeconn()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
