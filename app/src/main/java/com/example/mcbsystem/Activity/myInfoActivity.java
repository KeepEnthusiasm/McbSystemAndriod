package com.example.mcbsystem.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mcbsystem.R;

public class myInfoActivity extends AppCompatActivity {
    EditText edtName,edtAge,edtDate,edtPhone,edtEmail,edtHobby,edtWork;
    int y,m,d;//生命年月日；
    private RadioGroup radgroup;//性别单选按钮组
    private Spinner sp;//政治面貌复选框
    private String name;//姓名
    private int age;//年龄
    private String gender;//性别
    private String face;//政治面貌
    private int phone;//联系方式
    private String email;//邮箱
    private String hobby;//爱好
    private String work;//工作经验
    private String date;//出生日期
    //性别
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        init();
        getGender();
        getFace();
    }
    //初始化各类组件
    public void init(){
        edtName=this.findViewById(R.id.infoName);
        edtAge=this.findViewById(R.id.infoAge);
        edtPhone=this.findViewById(R.id.infoPhone);
        edtEmail=this.findViewById(R.id.infoEmail);
        edtHobby=this.findViewById(R.id.infoHobby);
        edtWork=this.findViewById(R.id.infoWork);
        edtDate=this.findViewById(R.id.infoDate);
        radgroup = (RadioGroup) this.findViewById(R.id.radioGroupID);
        sp=this.findViewById(R.id.spinner);
    }
    //获取值
    public void getInfo(){
        name=edtName.getText().toString();
        age=Integer.parseInt(edtAge.getText().toString());
        phone=Integer.parseInt(edtPhone.getText().toString());
        email=edtEmail.getText().toString();
        hobby=edtHobby.getText().toString();
        work=edtWork.getText().toString();
        date=edtDate.getText().toString();

    }
    //提交按钮监听
    public void infoCommit(View view){
        getInfo();
        Intent intent = new Intent(myInfoActivity.this,SelectInfoActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("age",age);
        intent.putExtra("gender",gender);
        intent.putExtra("face",face);
        intent.putExtra("phone",phone);
        intent.putExtra("email",email);
        intent.putExtra("hobby",hobby);
        intent.putExtra("work",work);
        intent.putExtra("date",date);
        startActivity(intent);
    }
    public void backSystem(View view){
        Intent intent =new Intent(myInfoActivity.this,SystemActivity.class);
        startActivity(intent);
    }

    //获取性别监听方法
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

    //获取政治面貌监听
    public void getFace(){
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                face=sp.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

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
                edtDate.setText(y+"-"+m+"-"+d);
            }
        }, y, m, d);
        dpd1.show();
    }
}
