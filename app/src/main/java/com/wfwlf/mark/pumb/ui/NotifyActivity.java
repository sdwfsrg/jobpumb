package com.wfwlf.mark.pumb.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.adapter.NotifyAdapter;
import com.wfwlf.mark.pumb.bean.WarnInfo;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marksong on 2018/11/30.
 */

public class NotifyActivity extends AppCompatActivity {
    @BindView(R.id.return_iv)
    AutoRelativeLayout returnIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.lv_notify)
    ListView lvNotify;
    NotifyAdapter notifyAdapter;
    List<WarnInfo.DataBean> mdata=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);
        try {
            mdata= (List<WarnInfo.DataBean>) getIntent().getSerializableExtra("list");
            notifyAdapter=new NotifyAdapter(this,mdata);
            lvNotify.setAdapter(notifyAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.return_iv)
    public void onViewClicked() {
        finish();
    }
}
