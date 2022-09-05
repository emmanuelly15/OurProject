package com.example.paycoprototype.data;

import android.text.format.Time;

public class Document

{
  int id;
  String employee,device, department,time,format,image,type,amount,comment,location;
    //Gets
    public int getID() {return id;}
    public String getEmp(){return employee;}
    public String getDepartment(){return department;}
    public String getDevice(){return device;}
    public String getTime(){return time;}
    public String getFormat(){return format;}
    public String getImage(){return image;}
    public String getType(){return type;}
    public String getAmount(){return amount;}
    public String getComment(){return comment;}
    public String getLocation(){return location;}

   //Gets

    public int setId(int id){return this.id= id;}
    public String setEmp(String employee){return this.employee=employee;}
    public String setDepartment(String department){return this.department=department;}
    public String setDevice(String device){return  this.device=device;}
    public String setTime(String time){return this.time= time;}
    public String setFormat(String format){return this.format=format;}
    public String setImage(String image){return this.image= image ;}
    public String setType(String type){return this.type=type;}
    public String setAmount(String amount){return this.amount=amount;}
    public String setComment(String comment){return this.comment=comment;}
    public String setLocation(String location){return this.location=location;}




   // public String getUrl() {return ;}
   // public String getStatus() {return status;}

   // public void setUrl(String url){this.url=url;}
   // public void setStatus(String status){this.status=status;}



}
