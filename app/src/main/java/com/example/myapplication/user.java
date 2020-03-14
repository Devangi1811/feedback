package com.example.myapplication;

public class user {
    String sub1;
    String sub2;
    String sub3;
    String sub4;
    String sub5;

    public user(){

    }
    public  user(String sub1,String sub2,String sub3,String sub4,String sub5){
        this.sub1=sub1;
        this.sub2=sub2;
        this.sub3=sub3;
        this.sub4=sub4;
        this.sub5=sub5;

    }
    public String getUsersub1(){
        return sub1;
    }
    public String getUsersub2(){
        return sub2;
    }
    public String getUsersub3(){
        return sub3;
    }
    public String getUsersub4(){
        return sub4;
    }
    public String getUsersub5(){
        return sub5;
    }

}
