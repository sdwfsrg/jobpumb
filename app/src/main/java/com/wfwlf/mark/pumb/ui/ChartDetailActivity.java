package com.wfwlf.mark.pumb.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.wfwlf.mark.pumb.NetValues;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.SiteInfo;
import com.wfwlf.mark.pumb.bean.WaterChange;
import com.wfwlf.mark.pumb.volley.BaseVO;
import com.wfwlf.mark.pumb.volley.MyErrorListener;
import com.wfwlf.mark.pumb.volley.MyReponseListener;
import com.wfwlf.mark.pumb.widget.MyMarkerView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marksong on 2018/12/20.
 */

public class ChartDetailActivity extends BaseActivity implements OnChartValueSelectedListener {
    @BindView(R.id.return_iv)
    AutoRelativeLayout returnIv;
    @BindView(R.id.tv_w)
    TextView tvW;
    @BindView(R.id.tv_m)
    TextView tvM;
    @BindView(R.id.tv_tm)
    TextView tvTm;
    @BindView(R.id.tv_y)
    TextView tvY;
    @BindView(R.id.chart1)
    LineChart chart;
    YAxis yAxis;
    XAxis xAxis;
    NetValues netValues;
    String pumpID;
    final ArrayList<Entry> values = new ArrayList<>();
    List<WaterChange.DataBean> chartdatas = new ArrayList<>();
    String type="1";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartdetail);
        ButterKnife.bind(this);
        pumpID = getIntent().getStringExtra("str");
        netValues = NetValues.getInstance(this);
        tvW.setBackgroundResource(R.color.colorAccent);
        tvW.setTextColor(Color.parseColor("#ffffff"));
        initchart();
    }

    private void initchart() {
        {   // // Chart Style // //
            // background color
            chart.setBackgroundColor(Color.WHITE);

            // disable description text
            chart.getDescription().setEnabled(false);

            // enable touch gestures
            chart.setTouchEnabled(true
            );
            // set listeners
            chart.setOnChartValueSelectedListener(this);
            chart.setDrawGridBackground(true);
            // create marker to display box when values are selected
            MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

            // Set the marker to the chart
            mv.setChartView(chart);
            chart.setMarker(mv);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(true);
             chart.setScaleXEnabled(true);
             chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart.setPinchZoom(true);

        }

        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            xAxis.setAxisMinimum(0f);
            xAxis.setGranularity(1f);

            xAxis.setLabelCount(5);
            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }
        xAxis.setLabelCount(5);

        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
        }


        // add data
        // draw points over time
        chart.animateX(1500);
        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);

        initdata();
    }

    private void initdata() {
        if(chart.getLineData()!=null){
            chart.getLineData().clearValues();
        }
        netValues.get_wvchange_list(pumpID, type, new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                WaterChange info = (WaterChange) arg0;
                chartdatas = info.getData();
                if (chartdatas.size() > 0) {
                    chart.setTouchEnabled(true
                    );
                    yAxis.setAxisMaximum(getMaxWaterLevel(chartdatas));
                    yAxis.setAxisMinimum(getMinWaterLevel(chartdatas));
                    setChartData(chartdatas);
                }else {
                    chart.setTouchEnabled(false
                    );
                }
            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
    }

    private void setChartData(List<WaterChange.DataBean> chartdatas) {
        values.clear();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return values.get((int) (value)).getData() + "";
            }
        });
        xAxis.setLabelCount(5);

        for (int i = 0; i < chartdatas.size(); i++) {
            float val = chartdatas.get(chartdatas.size() - 1 - i).getWaterLevel();

            String date = chartdatas.get(chartdatas.size() - 1 - i).getDataTime();
            values.add(new Entry(i, val, date));
        }
        LineDataSet set1;

//        if (chart.getData() != null &&
//                chart.getData().getDataSetCount() > 0) {
//            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
//            set1.setValues(values);

//        } else {
        // create a dataset a nd give it a type
        set1 = new LineDataSet(values, null);
        set1.setLabel(null);
        set1.setDrawIcons(false);
//            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        // draw dashed line
//            set1.enableDashedLine(10f, 5f, 0f);
        final DecimalFormat mFormat = new DecimalFormat("###,###,##.#");
        set1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return mFormat.format(value);
            }
        });

        // black lines and points
        set1.setColor(Color.GREEN);
        set1.setCircleColor(Color.GREEN);

        // line thickness and point size
//      set1.setLineWidth(1f);
//      set1.setCircleRadius(3f);

        // draw points as solid circles
        set1.setDrawCircleHole(true);

        // customize legend entry
        set1.setFormLineWidth(1f);
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set1.setFormSize(15.f);
        // text size of values
        set1.setValueTextSize(0f);

        // draw selection line as dashed
//            set1.enableDashedHighlightLine(10f, 5f, 0f);

        // set the filled area
        set1.setDrawFilled(true);
        set1.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return chart.getAxisLeft().getAxisMinimum();
            }
        });

        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
            set1.setFillDrawable(drawable);
        } else {
            set1.setFillColor(Color.GREEN);
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        // add the data sets
        // create a data object with the data sets
        LineData data = new LineData(dataSets);
        // set data
        chart.setData(data);
//        }
    }

    @Override
    protected void saveToGallery() {

    }
    public float getMaxWaterLevel(List<WaterChange.DataBean> chartdata) {
        if (chartdata.size() < 1) {
            return 0;
        }
        float max = 0;
        for (int i = 0; i < chartdata.size(); i++) {
            if (max < chartdata.get(i).getWaterLevel()) {
                max = chartdata.get(i).getWaterLevel();
            }
        }
        max = max + 0.5f;
        return max;
    }

    public float getMinWaterLevel(List<WaterChange.DataBean> chartdata) {
        if (chartdata.size() < 1) {
            return 0;
        }
        float min = chartdata.get(0).getWaterLevel();
        for (int i = 0; i < chartdata.size(); i++) {
            if (min > chartdata.get(i).getWaterLevel()) {
                min = chartdata.get(i).getWaterLevel();
            }
        }
        min = min - 0.5f;
        return min;
    }
    @OnClick({R.id.return_iv, R.id.tv_w, R.id.tv_m, R.id.tv_tm, R.id.tv_y})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_iv:
                finish();
                break;
            case R.id.tv_w:
                type="1";
                resetbtn();
                tvW.setBackgroundResource(R.color.colorAccent);
                tvW.setTextColor(Color.parseColor("#ffffff"));
                initdata();
                break;
            case R.id.tv_m:
                type="2";
                resetbtn();
                tvM.setBackgroundResource(R.color.colorAccent);
                tvM.setTextColor(Color.parseColor("#ffffff"));
                initdata();
                break;
            case R.id.tv_tm:
                type="3";
                resetbtn();
                tvTm.setBackgroundResource(R.color.colorAccent);
                tvTm.setTextColor(Color.parseColor("#ffffff"));
                initdata();
                break;
            case R.id.tv_y:
                type="4";
                resetbtn();
                tvY.setBackgroundResource(R.color.colorAccent);
                tvY.setTextColor(Color.parseColor("#ffffff"));
                initdata();
                break;
        }
    }

    private void resetbtn() {
        tvM.setBackgroundResource(R.color.common_d);
        tvM.setTextColor(Color.parseColor("#a6b3bc"));
        tvTm.setBackgroundResource(R.color.common_d);
        tvTm.setTextColor(Color.parseColor("#a6b3bc"));
        tvW.setBackgroundResource(R.color.common_d);
        tvW.setTextColor(Color.parseColor("#a6b3bc"));
        tvY.setBackgroundResource(R.color.common_d);
        tvY.setTextColor(Color.parseColor("#a6b3bc"));
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
