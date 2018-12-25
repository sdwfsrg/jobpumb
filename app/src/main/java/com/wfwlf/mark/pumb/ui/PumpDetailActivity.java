package com.wfwlf.mark.pumb.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wfwlf.mark.pumb.NetValues;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.adapter.DeviceAdapter;
import com.wfwlf.mark.pumb.bean.Device;
import com.wfwlf.mark.pumb.bean.SiteInfo;
import com.wfwlf.mark.pumb.bean.WarnInfo;
import com.wfwlf.mark.pumb.manager.LineChartManager;
import com.wfwlf.mark.pumb.util.CommonUtils;
import com.wfwlf.mark.pumb.volley.BaseVO;
import com.wfwlf.mark.pumb.volley.MyErrorListener;
import com.wfwlf.mark.pumb.volley.MyReponseListener;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marksong on 2018/11/29.
 */

public class PumpDetailActivity extends BaseActivity implements
        OnChartValueSelectedListener {

    @BindView(R.id.return_iv)
    AutoRelativeLayout returnIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.iv_video)
    ImageView ivVideo;
    @BindView(R.id.iv_notify)
    ImageView ivNotify;
    @BindView(R.id.tv_notify_num)
    TextView tvNotifyNum;
    @BindView(R.id.rl_notify)
    AutoRelativeLayout rlNotify;
    @BindView(R.id.chart2)
    LineChart chart2;
    @BindView(R.id.chart1)
    LineChart chart;
    @BindView(R.id.lv_nap)
    NoScrollListView lvNap;
    @BindView(R.id.tv_cur_lev)
    TextView tvCurLev;
    @BindView(R.id.tv_cur_qul)
    TextView tvCurQul;
    List<SiteInfo.DataBean> chartdatas = new ArrayList<>();
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_cur_v)
    TextView tvCurV;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.iv_4)
    ImageView iv4;
    @BindView(R.id.ll_pump3)
    LinearLayout llPump3;
    @BindView(R.id.ll_pump4)
    LinearLayout llPump4;
    @BindView(R.id.tv_pump1)
    TextView tvPump1;
    @BindView(R.id.tv_pump2)
    TextView tvPump2;
    @BindView(R.id.tv_pump3)
    TextView tvPump3;
    @BindView(R.id.tv_pump4)
    TextView tvPump4;
    private LineChartManager lineChartManager2;
    DeviceAdapter deviceAdapter;
    NetValues netValues;
    String pumpID;
    List<WarnInfo.DataBean> warnlist = new ArrayList<>();
    YAxis yAxis;
    XAxis xAxis;
    final ArrayList<Entry> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump_detail);
        ButterKnife.bind(this);
//        setTitle("LineChartActivity1");
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                tvCurV.setText("");
                chart.getLineData().clearValues();
                getRednot();
                initData();
            }
        });
        pumpID = getIntent().getStringExtra("str");
        netValues = NetValues.getInstance(this);
        deviceAdapter = new DeviceAdapter(this);
        lvNap.setAdapter(deviceAdapter);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.startActivity(PumpDetailActivity.this, ChartDetailActivity.class, pumpID);
            }
        });
        CommonUtils.requestViewFoucus(rlNotify);
        {   // // Chart Style // //
            lineChartManager2 = new LineChartManager(chart2);
            // background color
            chart.setBackgroundColor(Color.WHITE);
            chart2.setBackgroundColor(Color.WHITE);
            // disable description text
            chart.getDescription().setEnabled(false);
            chart2.getDescription().setEnabled(true);
            // enable touch gestures
            chart.setTouchEnabled(true
            );
            chart2.setTouchEnabled(true);
            // set listeners
            chart2.setOnChartValueSelectedListener(this);
            chart.setDrawGridBackground(false);
            chart2.setDrawGridBackground(false);
            // create marker to display box when values are selected
//            MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//
//            // Set the marker to the chart
//            mv.setChartView(chart);
//            chart.setMarker(mv);

            // enable scaling and dragging
            chart.setDragEnabled(false);
            chart.setScaleEnabled(false);
            chart2.setDragEnabled(false);
            chart2.setScaleEnabled(false);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart.setPinchZoom(false);
            chart2.setPinchZoom(true);
        }

        initData();

    }

    private void initchart1() {
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            xAxis.setAxisMinimum(0f);
            xAxis.setGranularity(1f);


            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }
        xAxis.setLabelCount(10);

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
        chart2.animateX(1500);
        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);
        Legend l2 = chart2.getLegend();

        // draw legend entries as lines
        l2.setForm(Legend.LegendForm.LINE);
    }

    private void initData() {

        initchart1();
        getChartData();
        getListData();
    }

    public float getMaxWaterLevel(List<SiteInfo.DataBean> chartdata) {
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

    public float getMinWaterLevel(List<SiteInfo.DataBean> chartdata) {
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

    public float getMaxVUL(List<SiteInfo.DataBean> chartdata) {
        if (chartdata.size() < 1) {
            return 0;
        }
        float max = 0;
        for (int i = 0; i < chartdata.size(); i++) {
            if (max < chartdata.get(i).getVoltageU()) {
                max = chartdata.get(i).getVoltageU();
            }
        }
        max = max + 1f;
        max = (int) Math.ceil((double) max);
        return max;
    }

    public float getMinVUL(List<SiteInfo.DataBean> chartdata) {
        if (chartdata.size() < 1) {
            return 0;
        }
        float min = chartdata.get(0).getVoltageV();
        for (int i = 0; i < chartdata.size(); i++) {
            if (min > chartdata.get(i).getVoltageV()) {
                min = chartdata.get(i).getVoltageV();
            }
        }
        min = min - 1f;
        min = (int) Math.floor((double) min);
        return min;
    }

    /**
     * 获取图表数据
     */
    private void getChartData() {
        netValues.get_site_info(pumpID, 10, new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                SiteInfo info = (SiteInfo) arg0;
                chartdatas = info.getData();
                if (chartdatas.size() > 0) {
                    deviceAdapter.setControtype(chartdatas.get(0).getControlType());
                    tvCurLev.setText(chartdatas.get(0).getWaterLevel() + " m");
                    tvCurQul.setText(chartdatas.get(0).getWaterFlow() + " m³/h");
                    yAxis.setAxisMaximum(getMaxWaterLevel(chartdatas));
                    yAxis.setAxisMinimum(getMinWaterLevel(chartdatas));
                    setChartData(chartdatas);
                    refreshLayout.finishRefresh();
                }
            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });

    }

    private void setChartData(List<SiteInfo.DataBean> chartdatas) {
        values.clear();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return values.get((int) (value)).getData() + "";
            }
        });


        for (int i = 0; i < chartdatas.size(); i++) {
            float val = chartdatas.get(chartdatas.size() - 1 - i).getWaterLevel();

            String date = chartdatas.get(chartdatas.size() - 1 - i).getDataTime().substring(10, 16);
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
        set1.setValueTextSize(10f);

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


        setLineChartData();
    }

    @Override
    protected void onResume() {
        getRednot();
        super.onResume();
    }

    private void getRednot() {
        netValues.get_notify_list(new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                WarnInfo info = (WarnInfo) arg0;
                warnlist = info.getData();
                if (warnlist.size() > 0) {
                    tvNotifyNum.setVisibility(View.VISIBLE);
                    tvNotifyNum.setText(warnlist.size() + "");
                } else {
                    tvNotifyNum.setVisibility(View.GONE);
                }
            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
    }

    /**
     * 获取列表数据
     */
    private void getListData() {
        netValues.get_device_list(pumpID, new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                Device device = (Device) arg0;
                showstatus(device);
            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
    }

    private void showstatus(Device device) {
        tvPump1.setText(device.getData().get(0).getDeviceName());
        if(device.getData().get(0).getMotorStatus().equals("0")){
            Glide.with(getApplicationContext()).load(R.drawable.stop).into(iv1);
        }else {
            Glide.with(getApplicationContext()).load(R.drawable.dongtai).into(iv1);
        }
        tvPump2.setText(device.getData().get(1).getDeviceName());
        if(device.getData().get(1).getMotorStatus().equals("0")){
            Glide.with(getApplicationContext()).load(R.drawable.stop).into(iv2);
        }else {
            Glide.with(getApplicationContext()).load(R.drawable.dongtai).into(iv2);
        }
        if (device.getData().size() > 2 && device.getData().size() > 0) {
            tvPump3.setText(device.getData().get(2).getDeviceName());
            if(device.getData().get(2).getMotorStatus().equals("0")){
                Glide.with(getApplicationContext()).load(R.drawable.stop).into(iv3);
            }else {
                Glide.with(getApplicationContext()).load(R.drawable.dongtai).into(iv3);
            }
        }
        if (device.getData().size() == 3 ) {
            llPump4.setVisibility(View.GONE);
        }
        if (device.getData().size() < 3 && device.getData().size() > 0) {
            llPump4.setVisibility(View.GONE);
            llPump3.setVisibility(View.GONE);
        }
        if (device.getData().size() >3  && device.getData().size() > 0) {
            tvPump4.setText(device.getData().get(3).getDeviceName());
            if(device.getData().get(3).getMotorStatus().equals("0")){
                Glide.with(getApplicationContext()).load(R.drawable.stop).into(iv4);
            }else {
                Glide.with(getApplicationContext()).load(R.drawable.dongtai).into(iv4);
            }
        }
    }

    private void setLineChartData() {
        //设置X轴数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i < chartdatas.size(); i++) {
            xValues.add((float) i);
        }
        ArrayList<String> Values = new ArrayList<>();
        for (int i = 0; i < chartdatas.size(); i++) {
            Values.add(chartdatas.get(chartdatas.size() - 1 - i).getDataTime().substring(10, 16));
        }
        //设置Y轴数据
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Float> yValue = new ArrayList<>();
            //一条曲线模拟数据
            for (int j = 0; j < chartdatas.size(); j++) {
                if (i == 0) {
                    yValue.add((float) chartdatas.get(chartdatas.size() - 1 - j).getVoltageU());
                } else if (i == 1) {
                    yValue.add((float) chartdatas.get(chartdatas.size() - 1 - j).getVoltageV());
                } else {
                    yValue.add((float) chartdatas.get(chartdatas.size() - 1 - j).getVoltageW());
                }
            }
            yValues.add(yValue);
        }
        //颜色集合
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#ff5230"));
        colors.add(Color.parseColor("#00bb6e"));
        colors.add(Color.parseColor("#ffa200"));
        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("U ");
        names.add("V ");
        names.add("W ");

        //显示多条曲线
//        lineChart2.setBackgroundColor(Color.GRAY);//表格背景
        lineChartManager2.showLineChart(xValues, yValues, names, colors, Values);
        lineChartManager2.setYAxis(getMaxVUL(chartdatas), getMinVUL(chartdatas), 4);
        lineChartManager2.setDescription("");
    }

    @Override
    protected void saveToGallery() {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        e.getX();
        tvCurV.setText("  U:" + chartdatas.get(chartdatas.size() - 1 - (int) e.getX()).getVoltageU() + "  V:" + chartdatas.get(chartdatas.size() - 1 - (int) e.getX()).getVoltageV() + "  W:" + chartdatas.get(chartdatas.size() - 1 - (int) e.getX()).getVoltageW());
        Log.i("Entry selected", e.toString());
        Log.i("LOW HIGH", "low: " + chart.getLowestVisibleX() + ", high: " + chart.getHighestVisibleX());
        Log.i("MIN MAX", "xMin: " + chart.getXChartMin() + ", xMax: " + chart.getXChartMax() + ", yMin: " + chart.getYChartMin() + ", yMax: " + chart.getYChartMax());

    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    @OnClick({R.id.return_iv, R.id.iv_video, R.id.rl_notify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.return_iv:
                finish();
                break;
            case R.id.iv_video:
                CommonUtils.startActivity(this, VideoRecodeActivity.class, pumpID);
                break;
            case R.id.rl_notify:
                if (warnlist.size() > 0) {
                    Intent it = new Intent(this, NotifyActivity.class);
                    it.putExtra("list", (Serializable) warnlist);
                    startActivity(it);
                } else {
                    Toast.makeText(this, "暂无预警信息", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
