
package com.wfwlf.mark.pumb.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.WstationBean;

import java.text.DecimalFormat;
import java.util.List;


/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
@SuppressLint("ViewConstructor")
public class StationMarkerView extends MarkerView {

    private final TextView tvContent;
    List<WstationBean.DataBeanX.DataBean> data;
    public StationMarkerView(Context context, int layoutResource, List<WstationBean.DataBeanX.DataBean> data) {
        super(context, layoutResource);
        this.data=data;
        tvContent = findViewById(R.id.tvContent);
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText(Utils.formatNumber(ce.getHigh(), 0, true));
        } else {
            final DecimalFormat mFormat = new DecimalFormat("###,###,##.#");
            String time=data.get((int)e.getX()).getDataTime().substring(11,16);
            String val=data.get((int)e.getX()).getDataValue();
            tvContent.setText(val);
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
