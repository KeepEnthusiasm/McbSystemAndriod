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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcbsystem.R;
import com.example.mcbsystem.domain.User;
import com.example.mcbsystem.service.IServiceUserDao;
import com.example.mcbsystem.service.serviceImpl.ServiceUserDaoImpl;

public class SelectActivity extends AppCompatActivity {
    private EditText ageText,birthdayText,addressText,emaiText,phoneText;
    private TextView nameView;
    private RadioGroup radgroup;
    private int y,m,d;
    private String gender;
    private Intent intent;
    private IServiceUserDao serviceUserDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
       serviceUserDao= new ServiceUserDaoImpl(this);
        //初始化
        nameView=this.findViewById(R.id.name);
        phoneText=this.findViewById(R.id.phone);
        ageText=(EditText) this.findViewById(R.id.age);
        birthdayText=this.findViewById(R.id.birthday);
        addressText=this.findViewById(R.id.address);
        emaiText=this.findViewById(R.id.email);
        radgroup = (RadioGroup) this.findViewById(R.id.radioGroupID);
        //得到Intent，获取name值
        intent=this.getIntent();
        nameView.setText(intent.getStringExtra("name"));
        //调用根据名字查看所有信息方法
        User user = serviceUserDao.findByName(intent.getStringExtra("name"));
        //将获取的信息设置到文本框
        ageText.setText(user.getAge()+"");
        birthdayText.setText(user.getBirthday());
        phoneText.setText(user.getPhone()+"");
        addressText.setText(user.getAddress());
        emaiText.setText(user.getEmail());
        getGender();
        //判断是男是女
        if(user.getGender().equals("男")){
            radgroup.check(R.id.maleGroupID);
        }else{
            radgroup.check(R.id.femaleGroupID);
        }


    }
    //性别监听
    public void getGender(){

        radgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                gender=(String) radbtn.getText();

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
    //修改按钮监听
    public void updateUser(View view){
        User user = serviceUserDao.findByName(nameView.getText().toString());
        user.setAge(Integer.parseInt(ageText.getText()+""));
        user.setGender(gender+"");
        user.setBirthday(birthdayText.getText()+"");
        user.setAddress(addressText.getText()+"");
        user.setEmail(emaiText.getText()+"");
        user.setPhone(Long.parseLong(phoneText.getText()+""));
        serviceUserDao.update(user);
        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
        Intent intent =new Intent(SelectActivity.this,SystemActivity.class);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();


    }
    public void deleteUser(View view){
        serviceUserDao.deleteByName(nameView.getText().toString());
        Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_LONG).show();
        Intent intent =new Intent(SelectActivity.this,SystemActivity.class);
        startActivity(intent);
    }
    public void backSystem(View view){
        Intent intent =new Intent(SelectActivity.this,SystemActivity.class);
        startActivity(intent);
    }
}
