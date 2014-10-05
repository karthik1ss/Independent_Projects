package Persistence;

import Exception.DAOException;
import java.util.List;

public interface LoginDAO
{
    public Login getLogin(String username) throws DAOException;
    public void insert(Login login) throws DAOException;
    public void update(Login login) throws DAOException;
    public Login find(String email) throws DAOException;
    public List<Login> check() throws DAOException;

}
