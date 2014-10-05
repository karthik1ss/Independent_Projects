
package JdbcConn;

import java.sql.*;

public class ConnectionUtil
{
    private static final String driverClassName="oracle.jdbc.OracleDriver";
    private static final String url="jdbc:oracle:thin:@karthik1:1521:Oracle";
    private static final String user="scott";
    private static final String password="tiger";

    static
    {
        try
        {
            Class.forName(driverClassName);
        }
        catch (Exception e)
        {
            System.out.println("Unable to load driver class");
        }
    }

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection conn) throws SQLException
    {
        if(conn!=null)
            conn.close();
    }

    public static void close(Statement stm) throws SQLException
    {
        if(stm!=null)
            stm.close();
    }

    public static void close(ResultSet rs) throws SQLException
    {
        if(rs!=null)
            rs.close();
    }
 
}






    

