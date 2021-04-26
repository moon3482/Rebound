package com.example.rebound.Login;

import java.io.Serializable;

public class User implements Serializable {
    public String ID;
    public String Password;
    public String PasswordCheck;
    public String Name;
    public String Birthday;
    //    public String Gender;
    public String Phone1;
    public String Phone2;
    public String Phone3;
    public String Email1;
    public String Email2;
    public String Height;
    public String Weight;
    public String Team;

//    public User(String ID, String password, String passwordCheck, String name, String birthday, String phone1, String phone2, String phone3, String email1, String email2, String height, String weight, String team) {
//        this.ID = ID;
//        this.Password = password;
//        this.PasswordCheck = passwordCheck;
//        this.Name = name;
//        this.Birthday = birthday;
//        this.Phone1 = phone1;
//        this.Phone2 = phone2;
//        this.Phone3 = phone3;
//        this.Email1 = email1;
//        this.Email2 = email2;
//        this.Height = height;
//        this.Weight = weight;
//        this.Team = team;
//    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPasswordCheck() {
        return PasswordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        PasswordCheck = passwordCheck;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getPhone1() {
        return Phone1;
    }

    public void setPhone1(String phone1) {
        Phone1 = phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public void setPhone2(String phone2) {
        Phone2 = phone2;
    }

    public String getPhone3() {
        return Phone3;
    }

    public void setPhone3(String phone3) {
        Phone3 = phone3;
    }

    public String getEmail1() {
        return Email1;
    }

    public void setEmail1(String email1) {
        Email1 = email1;
    }

    public String getEmail2() {
        return Email2;
    }

    public void setEmail2(String email2) {
        Email2 = email2;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }
}
