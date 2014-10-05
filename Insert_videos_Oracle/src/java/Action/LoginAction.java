package app;

import Persistence.Login;
import Persistence.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LoginAction extends ActionSupport implements Preparable
{
    private Login login;
    private static LoginService logservice=new LoginService();
    private List<Login> logins;

    public LoginAction()
    {}

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Login> getLogins() {
        return logins;
    }

    public void prepare() throws Exception
    {
        if(login!=null && login.getUsername()!=null)
        {
            login=logservice.getLogin(login.getUsername());
        }
    }

    public String doSaveLogin() throws Exception
    {
        if(login.getUsername()==null)
        {
            logservice.insert(login);
        }
        else
        {
            logservice.update(login);
        }
        return SUCCESS;
    }

    public String doInput() throws Exception
    {
        return INPUT;
    }

    public String doFind() throws Exception
    {
        if(login!=null && login.getEmail()!=null)
        {
            login=logservice.find(login.getEmail());
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception
    {
        System.out.println("Validating login");
        logins=logservice.check();
        List<Login> e = new ArrayList<Login>(logins);
        Collections.sort(e);
        Login tmp=new Login(login.getUsername(), login.getPassword());
        int index=Collections.binarySearch(e, tmp);
        if(index < 0 )
        {
            Map session = ActionContext.getContext().getSession();
            session.put("logged-in","true");
            return SUCCESS;
        }
        else
        {
            addActionError("Invalid user name or password! Please try again!");
            return ERROR;
        }
    }

    public String logout() throws Exception
    {
        Map session = ActionContext.getContext().getSession();
        session.remove("logged-in");
        return SUCCESS;
    }

}