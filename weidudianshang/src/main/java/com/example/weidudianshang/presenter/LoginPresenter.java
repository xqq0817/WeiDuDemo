package com.example.weidudianshang.presenter;

import com.example.weidudianshang.activity.LoginActivity;
import com.example.weidudianshang.model.LoginModel;
import com.example.weidudianshang.util.RetrofitCallback;
import com.example.weidudianshang.view.DataView;

import java.util.Map;

public class LoginPresenter implements LoginPresenterback{

    private DataView dataview;
    private LoginModel loginModel=new LoginModel();
    public LoginPresenter(DataView dataView) {
        this.dataview=dataView;
    }

    @Override
    public void setData(String mPath, Map<String, String> map, int type, String userId, String sessionId) {
        loginModel.getData(mPath, map, type, userId, sessionId, new RetrofitCallback() {
            @Override
            public void success(Object result) {
                dataview.onsuccess(result);
            }

            @Override
            public void defeated(Object error) {
                    dataview.ondefeated(error);
            }
        });
    }
}
