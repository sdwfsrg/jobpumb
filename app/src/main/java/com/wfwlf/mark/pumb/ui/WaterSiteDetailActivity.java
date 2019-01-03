package com.wfwlf.mark.pumb.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.wfwlf.mark.pumb.R;
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
    int defautcount = 7;
    private final LineChart[] charts = new LineChart[6];

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_site);
        ButterKnife.bind(this);
        charts[0] = chart1;
        charts[1] = chart2;
        charts[2] = chart3;
        charts[3] = chart4;
        charts[4] = chart5;
        charts[5] = chart6;
        btnWeek.setBackgroundResource(R.color.colorAccent);
        btnWeek.setTextColor(Color.parseColor("#ffffff"));
        initchart();
    }

    private void initchart() {

        for (int i = 0; i < charts.length; i++) {
            charts[i].clear();
            LineData data = getData(defautcount, 100);


            // add some transparency to the color with "& 0x90FFFFFF"
            setupChart(charts[i], data, colors[i % colors.length]);
        }
    }


    private LineData getData(int count, float range) {

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
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(true);
        set1.setValueTextColor(Color.WHITE);
        // create a data object with the data sets
        return new LineData(set1);
    }

    private void setupChart(LineChart chart, LineData data, int color) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleHoleColor(color);

        // no description text
        chart.getDescription().setEnabled(false);

        // chart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
        chart.setDrawGridBackground(false);
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setBackgroundColor(color);

        // set custom chart offsets (automatic offset calculation is hereby disabled)
        chart.setViewPortOffsets(10, 0, 10, 0);

        // add data
        chart.clear();
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setEnabled(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisLeft().setSpaceTop(40);
        chart.getAxisLeft().setSpaceBottom(40);
        chart.getAxisRight().setEnabled(false);

        chart.getXAxis().setEnabled(false);

        // animate calls invalidate()...
        chart.animateX(1500);
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
                defautcount = 7;
                resetbtn();
                btnWeek.setBackgroundResource(R.color.colorAccent);
                btnWeek.setTextColor(Color.parseColor("#ffffff"));
                initchart();
                break;
            case R.id.btn_mouth:
                defautcount = 30;
                resetbtn();
                btnMouth.setBackgroundResource(R.color.colorAccent);
                btnMouth.setTextColor(Color.parseColor("#ffffff"));
                initchart();
                break;
            case R.id.btn_season:
                defautcount = 30 * 3;
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
