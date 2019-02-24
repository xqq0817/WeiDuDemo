package com.example.weidudianshang.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidudianshang.R;
import com.example.weidudianshang.entity.RegBean;
import com.example.weidudianshang.network.API;
import com.example.weidudianshang.presenter.LoginPresenter;
import com.example.weidudianshang.view.DataView;

import java.util.HashMap;

public class RegActivity<T> extends AppCompatActivity implements View.OnClickListener,DataView<T> {


    private EditText regUser;
    private EditText regYz;
    private TextView regHq;
    private EditText regPassword;
    private TextView yiyou;
    private LoginPresenter loginPresenter;
    private Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        //资源ID
        initView();
        //实例化p层
        loginPresenter = new LoginPresenter(this);

    }

    private void initView() {
        regUser = (EditText) findViewById(R.id.reg_user);
        regYz = (EditText) findViewById(R.id.reg_yz);
        regHq = (TextView) findViewById(R.id.reg_hq);
        regPassword = (EditText) findViewById(R.id.reg_password);
        yiyou = (TextView) findViewById(R.id.yiyou);
        reg = findViewById(R.id.reg);

        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg:
             String zhuphone = regUser.getText().toString().trim();
             String zhupasswd = regPassword.getText().toString().trim();
             HashMap<String, String> map = new HashMap<>();
             map.put("phone",zhuphone);
             map.put("pwd",zhupasswd);
             loginPresenter.setData(API.REGISTER_URL,map,2,null,null);
        }
    }

    @Override
    public void onsuccess(T data) {
        if (data instanceof RegBean){
            RegBean regBean=(RegBean) data;
            if (regBean.getStatus().equals("0000")){
                Toast.makeText(RegActivity.this,"注册成功", Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(RegActivity.this,regBean.getMessage()+"",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void ondefeated(T error) {

    }
}
