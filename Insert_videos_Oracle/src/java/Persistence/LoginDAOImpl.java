
package Persistence;

import Exception.DAOException;
import JdbcConn.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO
{
    private static final String GET_LOGIN="SELECT email FROM LOGIN WHERE " +
                                                        "username=? ";
    private static final String INSERT="INSERT INTO LOGIN VALUES(?,?,?)";
    private static final String UPDATE="UPDATE LOGIN SET password=? " +
                                                        "WHERE username=?";
    private static final String FIND="SELECT password FROM LOGIN WHERE email=?";
    private static final String CHECK="SELECT username,password FROM LOGIN";

    public Login getLogin(String username) throws DAOException
    {
        Login login=null;
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;

        try
        {
            conn=ConnectionUtil.getConnection();
            ps=conn.prepareCall(GET_LOGIN);
            fillStatement(ps,new Object[]{username});
            rs=ps.executeQuery();
            while (rs.next())
            {
                 String password=rs.getString(2);
                 String email=rs.getString(3);
                 login=new Login(username,password, email);
            }
            ConnectionUtil.close(rs);
            ConnectionUtil.close(ps);
            ConnectionUtil.close(conn);
        }
        catch(SQLException e)
        {
            throw new DAOException(e.getMessage());
        }
        return login;

    }

    public void insert(Login login) throws DAOException
    {
            Connection conn;
            PreparedStatement ps;
            try
            {
                conn=ConnectionUtil.getConnection();
                ps=conn.prepareCall(INSERT);
                fillStatement(ps,new Object[]{login.getUsername(),login.getPassword(),login.getEmail()});
                ps.executeUpdate();
                ConnectionUtil.close(ps);
                ConnectionUtil.close(conn);
            }
            catch (SQLException e)
            {
                throw new DAOException(e.getMessage());
            }

    }

    public void update(Login login) throws DAOException
    {
            Connection conn;
            PreparedStatement ps;
            try
            {
                conn=ConnectionUtil.getConnection();
                ps=conn.prepareCall(UPDATE);
                fillStatement(ps,new Object[]{login.getPassword(),login.getUsername()});
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

    public Login find(String email) throws DAOException
    {
        Login login=null;
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;

        try
        {
            conn=ConnectionUtil.getConnection();
            ps=conn.prepareCall(FIND);
            fillStatement(ps,new Object[]{email});
            rs=ps.executeQuery();
            while (rs.next())
            {
                 String username=rs.getString(1);
                 String password=rs.getString(2);
                 login=new Login(username,password, email);
            }
            ConnectionUtil.close(rs);
            ConnectionUtil.close(ps);
            ConnectionUtil.close(conn);
        }
        catch(SQLException e)
        {
            throw new DAOException(e.getMessage());
        }
        return login;

    }

    public List<Login> check() throws DAOException
    {
        List<Login> loginList=new ArrayList<Login>();
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            conn=ConnectionUtil.getConnection();
            ps=conn.prepareCall(CHECK);
            rs=ps.executeQuery();
            while (rs.next())
            {
                String username=rs.getString(1);
                String password=rs.getString(2);
                loginList.add(new Login(username, password));

            }
            ConnectionUtil.close(rs);
            ConnectionUtil.close(ps);
            ConnectionUtil.close(conn);

        }
        catch (Exception e)
        {
            throw new DAOException(e.getMessage());
        }
        return loginList;
    }




}
