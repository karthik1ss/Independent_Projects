package Persistence;

import Exception.DAOException;
import JdbcConn.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UsersDAOImpl implements UsersDAO
{
    
    private static final String GET_USERS="SELECT name,sex,bdate,address FROM USERS WHERE " +
                                                        "email=?";
    private static final String INSERT="INSERT INTO USERS VALUES(?,?,?,?,?)";
        
    public Users getUsers(String email) throws DAOException 
    {
        Users users=null;
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            conn=ConnectionUtil.getConnection();
            ps=conn.prepareCall(GET_USERS);
            fillStatement(ps,new Object[]{email});
            rs=ps.executeQuery();
            while (rs.next())
            {
                String name=rs.getString(1);
                String sex=rs.getString(3);
                Integer age=new Integer(rs.getInt(4));
                String address=rs.getString(5);
                users=new Users(name, email, sex, age, address);
            }
            ConnectionUtil.close(rs);
            ConnectionUtil.close(ps);
            ConnectionUtil.close(conn);
        }
        catch(SQLException e)
        {
            throw new DAOException(e.getMessage());
        }
        return users;

    }

    public void insert(Users users) throws DAOException
    {
            Connection conn;
            PreparedStatement ps;
            try
            {
                conn=ConnectionUtil.getConnection();
                ps=conn.prepareCall(INSERT);
                fillStatement(ps,new Object[]{users.getName(),users.getEmail(),
                users.getSex(),users.getAge(),users.getAddress()});
                ps.executeUpdate();
                ConnectionUtil.close(ps);
                ConnectionUtil.close(conn);

            }
            catch (SQLException e)
            {
                throw new DAOException(e.getMessage());
            }

    }

    private static void fillStatement(PreparedStatement ps,Object[] objArray) throws
            SQLException
    {
        for (int i = 0; i < objArray.length; i++)
        {
            if (objArray[i]!=null)
            {
                ps.setObject(i+1,objArray[i] );
            } else
            {
                ps.setNull(i+1,Types.NULL );
            }

        }
    }
}
