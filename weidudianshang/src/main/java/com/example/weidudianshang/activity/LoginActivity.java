package com.example.weidudianshang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weidudianshang.MainActivity;
import com.example.weidudianshang.R;
import com.example.weidudianshang.entity.LoginBean;
import com.example.weidudianshang.network.API;
import com.example.weidudianshang.presenter.LoginPresenter;
import com.example.weidudianshang.util.RegexUtil;
import com.example.weidudianshang.util.RetrofitUtil;
import com.example.weidudianshang.util.SPFUtil;
import com.example.weidudianshang.view.DataView;

import java.util.HashMap;

public class LoginActivity<T> extends AppCompatActivity implements View.OnClickListener,DataView<T> {

    private EditText loginUser;
    private EditText loginPassword;
    private CheckBox checkbox;
    private TextView ksReg;
    private LoginPresenter loginPresenter;
    private SPFUtil spfUtil;
    private Button login;
    private String iphone;
    private String ipasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //资源ID
        initView();
        //实例化p层
        loginPresenter = new LoginPresenter(this);
        //是否记住密码
        isremeber();
    }
    //是否记住密码
    private void isremeber() {
        String iphone = spfUtil.getString(this, "iphone", null);
        String ipasswd = spfUtil.getString(this, "ipasswd", null);
        boolean check = spfUtil.getBoolean(this, "check", false);
        if (check){
            loginUser.setText(iphone);
            loginPassword.setText(ipasswd);
            checkbox.setChecked(true);
        }
    }

    //资源ID
    private void initView() {
        spfUtil = new SPFUtil();
        loginUser = (EditText) findViewById(R.id.login_user);
        loginPassword = (EditText) findViewById(R.id.login_password);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        ksReg = (TextView) findViewById(R.id.ks_reg);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        ksReg.setOnClickListener(this);
        checkbox.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                iphone = loginUser.getText().toString().trim();
                ipasswd = loginPassword.getText().toString().trim();
                HashMap<String, String> map = new HashMap<>();
                map.put("phone",iphone);
                map.put("pwd",ipasswd);
                boolean b = RegexUtil.checkMobile(iphone);
                if (b==true){
                    loginPresenter.setData(API.LOGIN_URL,map,1,null,null);
                }else{
                    Toast.makeText(LoginActivity.this,"手机号或密码错误",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ks_reg:
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onsuccess(T data) {
        if (data instanceof LoginBean){
            LoginBean loginBean=(LoginBean) data;
            if (loginBean.getStatus().equals("0000")){
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                jizhumima();
                Intent intent = new Intent(LoginActivity.this, BottomBarActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(LoginActivity.this,loginBean.getMessage()+"",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void jizhumima(){
        if (checkbox.isChecked()){
            spfUtil.putString(this,"iphone",iphone);
            spfUtil.putString(this,"ipasswd",ipasswd);
            spfUtil.putBoolean(this,"check",true);
        }else{
            spfUtil.clear(LoginActivity.this);
        }
    }

    @Override
    public void ondefeated(T error) {

    }
}
