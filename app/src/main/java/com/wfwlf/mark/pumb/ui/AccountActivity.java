package com.wfwlf.mark.pumb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.util.SPUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marksong on 2018/12/1.
 */

public class AccountActivity extends AppCompatActivity {
    @BindView(R.id.return_iv)
    AutoRelativeLayout returnIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_site_name)
    TextView tvSiteName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        tvAccountName.setText((String) SPUtils.get(AccountActivity.this, "account_name", ""));
        tvSiteName.setText((String) SPUtils.get(AccountActivity.this, "dept", ""));
    }


    @OnClick({R.id.return_iv, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_iv:
                finish();
                break;
            case R.id.btn_logout:
                SPUtils.clear(this);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
                break;
        }
    }
}
