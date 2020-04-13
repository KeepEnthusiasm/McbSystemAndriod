package com.example.mcbsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.mcbsystem.R;
import com.example.mcbsystem.domain.User;
import com.example.mcbsystem.service.IServiceUserDao;
import com.example.mcbsystem.service.serviceImpl.ServiceUserDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemActivity extends AppCompatActivity {
    IServiceUserDao serviceUserDao;
    ListView listView;
    EditText selectText;//搜索框
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        //初始化组件
        selectText = this.findViewById(R.id.selectText);
        listView = (ListView) this.findViewById(R.id.listView);
        serviceUserDao = new ServiceUserDaoImpl(this);
        //listView显示内容
        listView();
        //为查询框设置文字改变监听
        selectText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count==0){
                    //如果输入的内容数量为0，则显示所有信息listView
                    listView.setAdapter(null);
                    listView();
                }else {
                    //如果输入的内容数量不为0.则实行模糊查询后的listView
                    afterSelectListView();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //listView方法，在listView中显示获取的姓名
    public void listView() {
        List<User> userList = serviceUserDao.finAll();
        List<Map<String, Object>> listItem = getListItem(userList);
        //创建listView适配器 SimpleAdapter（上下文环境，显示内容集合，list布局文件，list里的map键值，list布局里的TextViewID）
        SimpleAdapter adapter=new SimpleAdapter(this,listItem,R.layout.list,new String[]{"name","phone"},new int[]{R.id.ListName,R.id.ListPhone});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Intent intent= new Intent(SystemActivity.this,SelectActivity.class);
                intent.putExtra("name",map.get("name").toString());
                startActivity(intent);
            }
        });
    }
    /**
     *  模糊查询后的listView
     */
    public void afterSelectListView(){
        //先将listView的适配器设置为空
        listView.setAdapter(null);
        //获取查询框的内容
        String str = selectText.getText()+"";
        //调用模糊查询方法
        List<User> list = serviceUserDao.findByNameMoHu(str);
        //调用获取listItem方法
        List<Map<String, Object>> listItem = getListItem(list);
        //创建listView适配器 SimpleAdapter（上下文环境，显示内容集合，list布局文件，list里的map键值，list布局里的TextViewID）
        SimpleAdapter adapter=new SimpleAdapter(this,listItem,R.layout.list,new String[]{"name","phone"},new int[]{R.id.ListName,R.id.ListPhone});
        //为listView设置适配器
        listView.setAdapter(adapter);
        //为lisitView添加点击项目监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取点击的那一行的map
                Map<String,Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Intent intent= new Intent(SystemActivity.this,SelectActivity.class);
                //将获取的name转发到显示信息界面
                intent.putExtra("name",map.get("name").toString());
                startActivity(intent);
            }
        });
    }
    /**
     * 添加信息按钮监听
     */
    public void add(View view){
        Intent intent= new Intent(SystemActivity.this,AddActivity.class);
        startActivityForResult(intent,2);
    }
    /**
     * 我的信息
     */
    public void myInfo(View view){
        Intent intent = new Intent(SystemActivity.this,myInfoActivity.class);
        startActivity(intent);
    }

    /**
     * 定义getListItem方法
     * @param list 从数据获取的User集合
     * @return listItem listView的内容
     */
    public List<Map<String,Object>>  getListItem( List<User> list){
        //声明姓名集合
        ArrayList<String> name_list = new ArrayList<String>();
        //声明手机号集合
        ArrayList<Long> phone_list = new ArrayList<Long>();
        //listView集合，存的是map，map里存姓名和手机号
        List<Map<String,Object>> listItem;
        //遍历从数据库获取的集合并且将姓名和手机号存到
        for (User user : list) {
            //将得到的姓名存到姓名集合中
            name_list.add(user.getName());
            //将得到的手机号存到手机号中
            phone_list.add(user.getPhone());
        }
        //将两个数组存到map中
        listItem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < name_list.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name_list.get(i));
            map.put("phone", phone_list.get(i));
            listItem.add(map);
        }
        return listItem;
    }
}
