package com.example.paycoprototype.data.model;

public class User
{
    int id;
    String email,name,phoneNo,empid,password;

    public int getID() {return id;}
    public String getEmail() {return email;}
    public String getName() {return name;}
    public String getPhoneNo() {return phoneNo;}
    public String getEmpid() {return empid;}
    public String getPassword() {return password;}
    public void setId(int id){this.id= id;}
    public void setEmail(String email){this.email=email;}
    public void setName(String name){this.name=name;}
    public void setPhoneNo(String phoneNo ){this.phoneNo=phoneNo;}
    public void setEmpid(String empid){this.empid=empid;}
    public void setPassword(String password){this.password=password;}
}
