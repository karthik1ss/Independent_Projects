
package Persistence;

import Exception.DAOException;
import java.util.List;

public interface UsersDAO
{
    
    public Users getUsers(String email) throws DAOException;
    public void insert(Users users) throws DAOException;
    
    
}
