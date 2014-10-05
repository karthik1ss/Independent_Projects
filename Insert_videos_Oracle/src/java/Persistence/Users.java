package Persistence;

import java.io.Serializable;

public class Users implements Serializable
{
    private String name;
    private String email;
    private String sex;
    private Integer age;
    private String address;

    public Users()
    {}

    public Users(String name, String email, String sex, Integer age, String address)
    {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.age = age;
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    

}
