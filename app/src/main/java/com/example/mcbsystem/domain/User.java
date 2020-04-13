package com.example.mcbsystem.domain;

import android.icu.text.SymbolTable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;


public class User implements Serializable {
    private int id;//id
    private String name;//姓名
    private int age;//年龄
    private String gender;//性别
    private String birthday;//生日
    private String address;//地址
    private long phone;//电话
    private String email;//邮箱

    public User(){}
    public User(String name,int age,String gender,String birthday,String address,long phone,String email){
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.birthday=birthday;
        this.address=address;
        this.phone=phone;
        this.email=email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
