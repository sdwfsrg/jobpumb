package com.wfwlf.mark.pumb.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.wfwlf.mark.pumb.NetValues;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.util.CommonUtils;
import com.wfwlf.mark.pumb.util.PermissionUtil;
import com.wfwlf.mark.pumb.util.SPUtils;
import com.wfwlf.mark.pumb.volley.BaseVO;
import com.wfwlf.mark.pumb.volley.MyErrorListener;
import com.wfwlf.mark.pumb.volley.MyReponseListener;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements PermissionUtil.OnRequestPermissionsResultCallbacks {
    private Context mContext;
    private static final int REQUEST_CODE_CAMERA = 101;
    NetValues netValues;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        netValues=new NetValues(this);
        mContext = this;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        },2000);


    }
    void getpermission(){
        PermissionUtil.requestPerssions(this, REQUEST_CODE_CAMERA, Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        PermissionUtil.getCameraPermissions(this, REQUEST_CODE_CAMERA);
        PermissionUtil. getLocationPermissions(this,REQUEST_CODE_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    private void initData() {
        //检测账号是否登陆
        if(CommonUtils.checkNull((String)SPUtils.get(this,"key",""))){
            goToMainActivity();
        }else {
            startActivity(new Intent(mContext, LoginActivity.class));
            finish();
        }

    }

    private void goToMainActivity() {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    private void goToRegisterAndLoginActivity() {
        startActivity(new Intent(mContext, LoginActivity.class));
        finish();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied) {

    }
}
