package com.wfwlf.mark.pumb.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.wfwlf.mark.pumb.util.CommonUtils;
import com.wfwlf.mark.pumb.bean.LoginBean;
import com.wfwlf.mark.pumb.NetValues;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.util.PermissionUtil;
import com.wfwlf.mark.pumb.util.SPUtils;
import com.wfwlf.mark.pumb.volley.BaseVO;
import com.wfwlf.mark.pumb.volley.MyErrorListener;
import com.wfwlf.mark.pumb.volley.MyReponseListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements PermissionUtil.OnRequestPermissionsResultCallbacks  {


    private static final int REQUEST_CODE_CAMERA = 101;
    NetValues netValues;


    Context mContext;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.line_user_name)
    TextView lineUserName;
    @BindView(R.id.et_pass_word)
    EditText etPassWord;
    @BindView(R.id.image_hide_password)
    ImageView imageHidePassword;
    @BindView(R.id.line_pass_word)
    TextView linePassWord;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private boolean isChecked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        netValues = NetValues.getInstance(this);
        mContext = this;
        getpermission();
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.image_hide_password, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_hide_password:
                isChecked = !isChecked;
                if (isChecked) {
                    etPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageHidePassword.setImageResource(R.mipmap.image_visible);
                } else {
                    etPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageHidePassword.setImageResource(R.mipmap.image_hide_password);
                }
                etPassWord.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = etPassWord.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
                break;
            case R.id.btn_login:
                if (CommonUtils.checkNull(etUserName.getText().toString())) {
                    if (CommonUtils.checkNull(etPassWord.getText().toString())) {
                        netValues.login(etUserName.getText().toString(), etPassWord.getText().toString(), new MyReponseListener() {
                            @Override
                            public void onResponse(BaseVO arg0) {
                                LoginBean loginBean = (LoginBean) arg0;
                                if (loginBean.isSuccess()) {
                                    String key = loginBean.getData().getSessionId();
                                    SPUtils.put(LoginActivity.this, "key", key);
                                    SPUtils.put(LoginActivity.this, "account_name", loginBean.getData().getUserName());
                                    SPUtils.put(LoginActivity.this, "dept", loginBean.getData().getDeptName());
                                    CommonUtils.startActivity(LoginActivity.this,MainActivity.class);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "" + loginBean.getData().getReason(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new MyErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                super.onErrorResponse(error);
                            }
                        });
                    }
                }
                break;
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied) {

    }
}
