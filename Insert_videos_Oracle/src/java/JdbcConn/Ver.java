import java.sql.*;
import oracle.jdbc.driver.*;

class JDBCVersion
{
   public static void main (String args[])
          throws SQLException
   {
      // Load the Oracle JDBC driver
      DriverManager.registerDriver
              (new oracle.jdbc.driver.OracleDriver());
      Connection conn = DriverManager.getConnection
              ("jdbc:oracle:thin:@karthik1:1521:oracle","scott","tiger");

      // Create Oracle DatabaseMetaData object
      DatabaseMetaData meta = conn.getMetaData();

      // gets driver info:
      System.out.println("JDBC driver version is " + meta.getDriverVersion());
   }
}