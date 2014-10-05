

package JdbcConn;

import java.sql.*;


public class JdbcEx
{
    public static void main(String[] args) throws SQLException
    {
        String driverClassName="oracle.jdbc.driver.OracleDriver";
        String url="jdbc:oracle:thin:@karthik1:1521:orcl";
        String user="scott";
        String password="tiger";
		try
		{
			Class.forName(driverClassName);
            System.out.println("Oracle JDBC driver loaded ok.");
		}
        catch (Exception e)
        {
                System.out.println("Unable to load driver class");
        }
        Connection conn=DriverManager.getConnection(url,user,password);
        System.out.println("Connection established");
        conn.close();
    }

}








