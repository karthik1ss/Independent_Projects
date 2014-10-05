
package app;

import Persistence.Users;
import Persistence.UsersService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.List;

public class UsersAction extends ActionSupport implements Preparable
{
    private Users user;
    private static UsersService usersservice=new UsersService();
    private List<Users> users;

    public UsersAction() 
    {}

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void prepare() throws Exception
    {
        if(user!=null && user.getEmail()!=null)
        {
            user=usersservice.getUsers(user.getEmail());
        }
    }

    public String doSaveReg() throws Exception
    {
        usersservice.insert(user);
        return SUCCESS;
    }

    public String doInput() throws Exception
    {
        return INPUT;
    }

}