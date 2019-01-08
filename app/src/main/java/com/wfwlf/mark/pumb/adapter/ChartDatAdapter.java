package com.wfwlf.mark.pumb.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.SiteInfo;
import com.wfwlf.mark.pumb.bean.WstationBean;
import com.wfwlf.mark.pumb.manager.LineChartManager;
import com.wfwlf.mark.pumb.util.CommonUtils;
import com.wfwlf.mark.pumb.widget.MyMarkerView;
import com.wfwlf.mark.pumb.widget.StationMarkerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marksong on 2019/1/5.
 */

public class ChartDatAdapter extends BaseQuickAdapter<WstationBean.DataBeanX, ChartDatAdapter.ViewHolder> {

    private LineChartManager lineChartManager;
    public ChartDatAdapter() {
        super(R.layout.item_chart);
    }

    @Override
    protected void convert(ViewHolder helper, WstationBean.DataBeanX item) {
            helper.tvTitle.setText(item.getDataTypeName()+"变化");
            initchart(helper.chart,helper.getPosition(),item);
         StationMarkerView mv = new StationMarkerView(this.mContext, R.layout.custom_marker_view,item.getData());

            // Set the marker to the chart
            mv.setChartView(helper.chart);
            helper.chart.setMarker(mv);
    }
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
    private void initchart(LineChart chart,int postion,WstationBean.DataBeanX item) {
        List<WstationBean.DataBeanX.DataBean> mdata=item.getData();
        lineChartManager=new LineChartManager(chart);
        lineChartManager.showLineChart(item.getData(),item.getDataTypeName(),colors[postion % colors.length]);
        lineChartManager.setYAxis(getMaxVUL(item.getData()), getMinVUL(item.getData()), 5);
//        if(mdata.size()>0){
//            setupChart(chart,getData(mdata,colors[2 % colors.length]));
//        }

    }
    public float getMaxVUL(List<WstationBean.DataBeanX.DataBean> chartdata) {
        if (chartdata.size() < 1) {
            return 0;
        }
        float max = 0;
        for (int i = 0; i < chartdata.size(); i++) {
            if (max < Float.parseFloat(chartdata.get(i).getDataValue())) {
                max = Float.parseFloat(chartdata.get(i).getDataValue());
            }
        }
        max = max + 2f;
        return max;
    }

    public float getMinVUL(List<WstationBean.DataBeanX.DataBean> chartdata) {
        if (chartdata.size() < 1) {
            return 0;
        }
        float min = Float.parseFloat(chartdata.get(0).getDataValue());
        for (int i = 0; i < chartdata.size(); i++) {
            if (min > Float.parseFloat(chartdata.get(i).getDataValue())) {
                min = Float.parseFloat(chartdata.get(i).getDataValue());
            }
        }
        min = min - 2f;
        return min;
    }
    private LineData getData( List<WstationBean.DataBeanX.DataBean> mdata,  int color) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < mdata.size(); i++) {
            if(CommonUtils.checkNull(mdata.get(i).getDataValue())){
                float val = Float.parseFloat(mdata.get(i).getDataValue());
                values.add(new Entry(i, val));
            }
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

    private void setupChart(LineChart chart, LineData data){

//        ((LineDataSet) data.getDataSetByIndex(0)).setCircleHoleColor(color);

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
    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.chart)
        LineChart chart;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
