package com.example.mcbsystem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mcbsystem.R;

public class SelectInfoActivity extends AppCompatActivity {
    TextView tvName, tvAge, tvGender, tvFace, tvDate, tvPhone, tvEmail, tvHobby, tvWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_info);
        init();
        showInfo();
    }

    public void init() {
        tvName = this.findViewById(R.id.SelectName);
        tvAge = this.findViewById(R.id.SelectAge);
        tvGender = this.findViewById(R.id.SelectGender);
        tvFace = this.findViewById(R.id.SelectFace);
        tvDate = this.findViewById(R.id.SelectDate);
        tvPhone = this.findViewById(R.id.SelectPhone);
        tvEmail = this.findViewById(R.id.SelectEmail);
        tvHobby = this.findViewById(R.id.SelectHobby);
        tvWork = this.findViewById(R.id.SelectWork);
    }

    public void showInfo() {
        Intent intent = this.getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvAge.setText(intent.getIntExtra("age",-1)+"");
        tvGender.setText(intent.getStringExtra("gender"));
        tvFace.setText(intent.getStringExtra("face"));
        tvPhone.setText(intent.getIntExtra("phone",-1)+"");
        tvEmail.setText(intent.getStringExtra("email"));
        tvWork.setText(intent.getStringExtra("work"));
        tvHobby.setText(intent.getStringExtra("hobby"));
        tvDate.setText(intent.getStringExtra("date"));
    }
    public void back(View view){
        Intent intent =new Intent(SelectInfoActivity.this,SystemActivity.class);
        startActivity(intent);
    }
}