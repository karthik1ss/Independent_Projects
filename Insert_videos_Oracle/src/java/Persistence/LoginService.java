package Persistence;

import Exception.DAOException;
import java.util.List;

public class LoginService
{
    private LoginDAO loginDAO;

    public LoginService()
    {
        loginDAO=new LoginDAOImpl();
    }

    public void update(Login login) throws DAOException {
        loginDAO.update(login);
    }

    public void insert(Login login) throws DAOException {
        loginDAO.insert(login);
    }

    public Login getLogin(String username) throws DAOException {
        return loginDAO.getLogin(username);
    }

    public Login find(String email) throws DAOException
    {
        return loginDAO.find(email);
    }

    public List<Login> check() throws DAOException
    {
        return loginDAO.check();
    }
    

}
