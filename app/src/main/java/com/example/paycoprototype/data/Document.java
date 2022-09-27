package com.example.paycoprototype.data;

import android.text.format.Time;

public class Document

{
  int id;
  String email,title,time,image,format,comment,location,status,amount;
    //Gets
    public int getID() {return id;}
    public String getEmail(){return email;}
    public String getTitle(){return title;}
    public String getTime(){return time;}
    public String getFormat(){return format;}
    public String getImage(){return image;}
    public String getStatus(){return status;}
    public String getAmount(){return amount;}
    public String getComment(){return comment;}
    public String getLocation(){return location;}


   //Gets

    public int setId(int id){return this.id= id;}
    public String setEmail(String email){return this.email=email;}
    public String setTime(String time){return this.time= time;}
    public String setFormat(String format){return this.format=format;}
    public String setImage(String image){return this.image= image ;}
    public String setTitle(String title){return this.title=title;}
    public String setAmount(String amount){return this.amount=amount;}
    public String setComment(String comment){return this.comment=comment;}
    public String setLocation(String location){return this.location=location;}





   // public String getUrl() {return ;}
   // public String getStatus() {return status;}

   // public void setUrl(String url){this.url=url;}
   // public void setStatus(String status){this.status=status;}



}
