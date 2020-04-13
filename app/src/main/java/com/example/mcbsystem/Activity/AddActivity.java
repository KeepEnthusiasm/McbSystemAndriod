package com.example.mcbsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mcbsystem.R;
import com.example.mcbsystem.domain.User;
import com.example.mcbsystem.service.IServiceUserDao;
import com.example.mcbsystem.service.serviceImpl.ServiceUserDaoImpl;

public class AddActivity extends AppCompatActivity {
    EditText nameText,ageText,birthdayText,addressText,emailText,phoneText;
    RadioGroup radgroup;
    int y,m,d;
    String gender;
    IServiceUserDao serviceUserDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        serviceUserDao=new ServiceUserDaoImpl(this);
        //初始化
        nameText=this.findViewById(R.id.addName);
        phoneText=this.findViewById(R.id.addPhone);
        ageText=(EditText) this.findViewById(R.id.addAge);
        birthdayText=this.findViewById(R.id.addBirthday);
        addressText=this.findViewById(R.id.addAddress);
        emailText=this.findViewById(R.id.addEmail);
        radgroup = (RadioGroup) this.findViewById(R.id.addradioGroupID);
        getGender();
    }

    //性别监听
    public void getGender(){

        radgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                gender=(String) radbtn.getText();
                Toast.makeText(getApplicationContext(), "你选了" + radbtn.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }
    //获取日期监听
    public void date(View view){

        DatePickerDialog dpd1=new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                y=year;m=monthOfYear;d=dayOfMonth;
                birthdayText.setText(y+"-"+m+"-"+d);
            }
        }, y, m, d);
        dpd1.show();
    }
    //添加监听
    public void addUser(View v){

        String name=nameText.getText()+"";
        int age=Integer.parseInt(ageText.getText()+"");
        String birthday=birthdayText.getText()+"";
        String address=addressText.getText()+"";
        String email=emailText.getText()+"";
        Long phone=Long.parseLong(phoneText.getText()+"");
        User user = new User(name,age,gender,birthday,address,phone,email);
        System.out.println(user);
        serviceUserDao.add(user);
        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_LONG).show();
        Intent intent =new Intent(AddActivity.this,SystemActivity.class);
        startActivity(intent);

    }
    public void backSystem(View v){
        Intent intent =new Intent(AddActivity.this,SystemActivity.class);
        startActivity(intent);
    }
    public void reset(View view){
        nameText.setText("");
        phoneText.setText("");
        emailText.setText("");
        birthdayText.setText("");
        ageText.setText("");
        addressText.setText("");
    }
}
