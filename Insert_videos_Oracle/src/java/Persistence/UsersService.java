
package Persistence;

import Exception.DAOException;

public class UsersService
{
    private UsersDAO usersDAO;

    public UsersService()
    {
        usersDAO=new UsersDAOImpl();
    }

    public void insert(Users users) throws DAOException {
        usersDAO.insert(users);
    }

    public Users getUsers(String email) throws DAOException {
        return usersDAO.getUsers(email);
    }

      
}
