package com.wfwlf.mark.pumb.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.wfwlf.mark.pumb.NetValues;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.adapter.ChartDatAdapter;
import com.wfwlf.mark.pumb.adapter.CurrDatAdapter;
import com.wfwlf.mark.pumb.bean.NewSWBean;
import com.wfwlf.mark.pumb.bean.WstationBean;
import com.wfwlf.mark.pumb.util.CommonUtils;
import com.wfwlf.mark.pumb.volley.BaseVO;
import com.wfwlf.mark.pumb.volley.MyErrorListener;
import com.wfwlf.mark.pumb.volley.MyReponseListener;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marksong on 2019/1/5.
 */

public class StationDetailActivity extends BaseActivity {

    @BindView(R.id.return_iv)
    AutoRelativeLayout returnIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.rv_current_data)
    RecyclerView rvCurrentData;
    @BindView(R.id.btn_week)
    TextView btnWeek;
    @BindView(R.id.btn_mouth)
    TextView btnMouth;
    @BindView(R.id.btn_season)
    TextView btnSeason;
    @BindView(R.id.rv_chart_data)
    RecyclerView rvChartData;
    CurrDatAdapter currDatAdapter;
    ChartDatAdapter chartDatAdapter;
    NetValues netValues;
    String stationid;
    int timetype=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_site);
        ButterKnife.bind(this);
        stationid = getIntent().getStringExtra("str");
        netValues = NetValues.getInstance(this);
        chartDatAdapter=new ChartDatAdapter();
        currDatAdapter=new CurrDatAdapter();
        CommonUtils.setRecycleVertical(rvChartData,this);
        CommonUtils.setRecycleVertical(rvCurrentData,this);
        rvChartData.setAdapter(chartDatAdapter);
        rvCurrentData.setAdapter(currDatAdapter);
        btnWeek.setBackgroundResource(R.color.colorAccent);
        btnWeek.setTextColor(Color.parseColor("#ffffff"));
        initcurrentdata();
        initchartdata();
    }

    private void initchartdata() {
        netValues.get_w_station_info(stationid, timetype, new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                super.onResponse(arg0);
                WstationBean wstationBean=(WstationBean)arg0;
                chartDatAdapter.setNewData(wstationBean.getData());
            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
    }


    private void initcurrentdata() {
        netValues.get_w_station_info_current(stationid, new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                NewSWBean wstationBean = (NewSWBean) arg0;
                currDatAdapter.setNewData(wstationBean.getData());

            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
    }

    @Override
    protected void saveToGallery() {

    }

    @OnClick({R.id.return_iv, R.id.iv_video, R.id.btn_week, R.id.btn_mouth, R.id.btn_season})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_iv:
                finish();
                break;
            case R.id.iv_video:
                CommonUtils.startActivity(context,VideoRecodeActivity.class,stationid,1);
                break;
            case R.id.btn_week:
                timetype = 1;
                resetbtn();
                btnWeek.setBackgroundResource(R.color.colorAccent);
                btnWeek.setTextColor(Color.parseColor("#ffffff"));
                initchartdata();
                break;
            case R.id.btn_mouth:
                timetype = 2;
                resetbtn();
                btnMouth.setBackgroundResource(R.color.colorAccent);
                btnMouth.setTextColor(Color.parseColor("#ffffff"));
                initchartdata();
                break;
            case R.id.btn_season:
                timetype = 3;
                resetbtn();
                btnSeason.setBackgroundResource(R.color.colorAccent);
                btnSeason.setTextColor(Color.parseColor("#ffffff"));
                initchartdata();
                break;
        }
    }

    private void resetbtn() {
        btnWeek.setBackgroundResource(R.color.common_d);
        btnWeek.setTextColor(Color.parseColor("#000000"));
        btnMouth.setBackgroundResource(R.color.common_d);
        btnMouth.setTextColor(Color.parseColor("#000000"));
        btnSeason.setBackgroundResource(R.color.common_d);
        btnSeason.setTextColor(Color.parseColor("#000000"));
    }
}
