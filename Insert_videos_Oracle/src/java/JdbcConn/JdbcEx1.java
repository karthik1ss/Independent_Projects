

package JdbcConn;

import java.sql.*;

class Jdbc
{
    public static void main(String[] args) throws Exception
    {
        String url="jdbc:mysql://localhost:3306/test";
        String username="root";
        String password="dss";
		Class.forName("com.mysql.jdbc.Driver");
        System.out.println("MySql JDBC driver loaded ok.");
		Connection conn=DriverManager.getConnection(url,username,password);
        System.out.println("Connection established");
        conn.close();
    }

}








