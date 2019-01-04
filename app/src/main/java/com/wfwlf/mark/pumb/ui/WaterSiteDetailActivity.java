package com.wfwlf.mark.pumb.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.wfwlf.mark.pumb.NetValues;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.WstationBean;
import com.wfwlf.mark.pumb.util.CommonUtils;
import com.wfwlf.mark.pumb.volley.BaseVO;
import com.wfwlf.mark.pumb.volley.MyErrorListener;
import com.wfwlf.mark.pumb.volley.MyReponseListener;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marksong on 2019/1/3.
 */

public class WaterSiteDetailActivity extends BaseActivity {


    @BindView(R.id.return_iv)
    AutoRelativeLayout returnIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.iv_video)
    ImageView ivVideo;
    @BindView(R.id.chart1)
    LineChart chart1;
    @BindView(R.id.chart2)
    LineChart chart2;
    @BindView(R.id.chart3)
    LineChart chart3;
    @BindView(R.id.chart4)
    LineChart chart4;
    @BindView(R.id.chart5)
    LineChart chart5;
    @BindView(R.id.chart6)
    LineChart chart6;
    int defautcount = 24;
    private final LineChart[] charts = new LineChart[6];
    NetValues netValues;
    private final int[] colors = new int[]{
            Color.rgb(137, 230, 81),
            Color.rgb(240, 240, 30),
            Color.rgb(89, 199, 250),
            Color.rgb(250, 104, 104)
            ,
            Color.rgb(230, 164, 114)
            ,
            Color.rgb(150, 154, 194)
    };
    @BindView(R.id.btn_week)
    TextView btnWeek;
    @BindView(R.id.btn_mouth)
    TextView btnMouth;
    @BindView(R.id.btn_season)
    TextView btnSeason;
    @BindView(R.id.btn_year)
    TextView btnYear;

    String stationid;
    @BindView(R.id.tv_andan)
    TextView tvAndan;
    @BindView(R.id.tv_status_andan)
    TextView tvStatusAndan;
    @BindView(R.id.tv_cloo)
    TextView tvCloo;
    @BindView(R.id.tv_status_cloo)
    TextView tvStatusCloo;
    @BindView(R.id.tv_cod)
    TextView tvCod;
    @BindView(R.id.tv_status_cod)
    TextView tvStatusCod;
    @BindView(R.id.tv_codm)
    TextView tvCodm;
    @BindView(R.id.tv_status_codm)
    TextView tvStatusCodm;
    @BindView(R.id.tv_ddl)
    TextView tvDdl;
    @BindView(R.id.tv_status_ddl)
    TextView tvStatusDdl;
    @BindView(R.id.tv_rjo)
    TextView tvRjo;
    @BindView(R.id.tv_status_rjy)
    TextView tvStatusRjy;
    @BindView(R.id.tv_ph)
    TextView tvPh;
    @BindView(R.id.tv_status_ph)
    TextView tvStatusPh;
    @BindView(R.id.tv_zd)
    TextView tvZd;
    @BindView(R.id.tv_status_zd)
    TextView tvStatusZd;
    @BindView(R.id.tv_zl)
    TextView tvZl;
    @BindView(R.id.tv_status_zl)
    TextView tvStatusZl;
    @BindView(R.id.tv_zhd)
    TextView tvZhd;
    @BindView(R.id.tv_status_zhd)
    TextView tvStatusZhd;
    @BindView(R.id.tv_wnl)
    TextView tvWnl;
    @BindView(R.id.tv_status_wnl)
    TextView tvStatusWnl;
    @BindView(R.id.tv_sw)
    TextView tvSw;
    @BindView(R.id.tv_status_sw)
    TextView tvStatusSw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_site);
        ButterKnife.bind(this);
        stationid = getIntent().getStringExtra("str");
        netValues = NetValues.getInstance(this);
        charts[0] = chart1;
        charts[1] = chart2;
        charts[2] = chart3;
        charts[3] = chart4;
        charts[4] = chart5;
        charts[5] = chart6;
        btnWeek.setBackgroundResource(R.color.colorAccent);
        btnWeek.setTextColor(Color.parseColor("#ffffff"));
        initcurrentdata();
        initchart();
    }

    private void initcurrentdata() {
        netValues.get_w_station_info_current(stationid, new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                WstationBean wstationBean = (WstationBean) arg0;
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvCloo,wstationBean.getData().getChlorineDioxide().getValue()+wstationBean.getData().getChlorineDioxide().getUnit());
                setVal(tvCod,wstationBean.getData().getCod().getValue()+wstationBean.getData().getCod().getUnit());
                setVal(tvCodm,wstationBean.getData().getCodmn().getValue()+wstationBean.getData().getCodmn().getUnit());
                setVal(tvDdl,wstationBean.getData().getConductivity().getValue()+wstationBean.getData().getConductivity().getUnit());
                setVal(tvRjo,wstationBean.getData().getDissolvedOxygen().getValue()+wstationBean.getData().getDissolvedOxygen().getUnit());
                setVal(tvPh,wstationBean.getData().getPhValue().getValue()+wstationBean.getData().getPhValue().getUnit());
                setVal(tvZd,wstationBean.getData().getTotalNitrogen().getValue()+wstationBean.getData().getTotalNitrogen().getUnit());
                setVal(tvZl,wstationBean.getData().getTotalPhosphorus().getValue()+wstationBean.getData().getTotalPhosphorus().getUnit());
                setVal(tvZhd,wstationBean.getData().getTurbidity().getValue()+wstationBean.getData().getTurbidity().getUnit());
                setVal(tvWnl,wstationBean.getData().getVolume().getValue()+wstationBean.getData().getVolume().getUnit());
                setVal(tvSw,wstationBean.getData().getWaterTemp().getValue()+wstationBean.getData().getWaterTemp().getUnit());


                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
                setVal(tvAndan,wstationBean.getData().getAmmoniaNitrogen().getValue()+wstationBean.getData().getAmmoniaNitrogen().getUnit());
            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
    }

    void  setVal(TextView tv,String val){
        if(CommonUtils.checkNull(val)){
            tv.setText(val);
        }else {
            tv.setText("--");
        }
    }

    void  setColorVal(TextView tv,String val){
        if(CommonUtils.checkNull(val)){
            if("正常".equals(val)){
                tv.setTextColor(Color.parseColor("#66e97f"));
            }else {
                tv.setTextColor(Color.parseColor("#ff0000"));
            }
            tv.setText(val);
        }else {
            tv.setTextColor(Color.parseColor("#333333"));
            tv.setText("--");
        }
    }
    private void initchart() {

        for (int i = 0; i < charts.length; i++) {
            charts[i].clear();
            LineData data = getData(defautcount, 100, colors[i % colors.length]);


            // add some transparency to the color with "& 0x90FFFFFF"
            setupChart(charts[i], data, colors[i % colors.length]);
        }
    }


    private LineData getData(int count, float range, int color) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            values.add(new Entry(i, val));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(values, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        set1.setLineWidth(1.0f);
        set1.setDrawCircleHole(false);
        set1.setDrawCircles(true);
        set1.setColor(color);
        set1.setCircleColor(color);
        set1.setHighLightColor(color);
        set1.setDrawValues(true);
        set1.setValueTextColor(color);

        set1.setFormLineWidth(1f);
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set1.setFormSize(15.f);
        // create a data object with the data sets
        return new LineData(set1);
    }

    private void setupChart(LineChart chart, LineData data, int color) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleHoleColor(color);

        // no description text
        chart.getDescription().setEnabled(false);

        chart.setDrawGridBackground(true);
//        YAxis yAxis;
//        XAxis xAxis;
//         // // X-Axis Style // //
//            xAxis = chart.getXAxis();
//            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
////            xAxis.setAxisMinimum(0f);
//            xAxis.setDrawGridLines(true);
//            xAxis.setGranularity(1f);
//
//            xAxis.setLabelCount(5);
//            // vertical grid lines
//            xAxis.enableGridDashedLine(10f, 10f, 0f);
//
//            xAxis.setLabelCount(5);
//
//         // // Y-Axis Style // //
//            yAxis = chart.getAxisLeft();
//
//            // disable dual axis (only use LEFT axis)
//            chart.getAxisRight().setEnabled(false);
//
//            // horizontal grid lines
//            yAxis.enableGridDashedLine(10f, 10f, 0f);

        // axis range
//         chart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        chart.setScaleXEnabled(true);
        chart.setScaleYEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);

//        chart.setBackgroundColor(color);

        // set custom chart offsets (automatic offset calculation is hereby disabled)
        chart.setViewPortOffsets(10, 0, 10, 0);

        // add data
        chart.clear();
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setEnabled(false);

        chart.getAxisLeft().setEnabled(true);
        chart.getAxisLeft().setSpaceTop(40);
        chart.getAxisLeft().setSpaceBottom(40);
        chart.getAxisRight().setEnabled(false);
//
//        chart.getXAxis().setEnabled(false);
        chart.setGridBackgroundColor(Color.WHITE);
        // animate calls invalidate()...
        chart.animateX(1500);
        chart.setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void saveToGallery() {

    }

    @OnClick({R.id.return_iv, R.id.iv_video, R.id.btn_week, R.id.btn_mouth, R.id.btn_season, R.id.btn_year})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_iv:
                finish();
                break;
            case R.id.iv_video:
                break;
            case R.id.btn_week:
                defautcount = 24;
                resetbtn();
                btnWeek.setBackgroundResource(R.color.colorAccent);
                btnWeek.setTextColor(Color.parseColor("#ffffff"));
                initchart();
                break;
            case R.id.btn_mouth:
                defautcount = 168;
                resetbtn();
                btnMouth.setBackgroundResource(R.color.colorAccent);
                btnMouth.setTextColor(Color.parseColor("#ffffff"));
                initchart();
                break;
            case R.id.btn_season:
                defautcount = 30 * 24;
                resetbtn();
                btnSeason.setBackgroundResource(R.color.colorAccent);
                btnSeason.setTextColor(Color.parseColor("#ffffff"));
                initchart();
                break;
            case R.id.btn_year:
                defautcount = 30 * 3 * 4;
                initchart();
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
        btnYear.setBackgroundResource(R.color.common_d);
        btnYear.setTextColor(Color.parseColor("#000000"));
    }

}
