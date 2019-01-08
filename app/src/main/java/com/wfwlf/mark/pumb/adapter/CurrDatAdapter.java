package com.wfwlf.mark.pumb.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.NewSWBean;
import com.wfwlf.mark.pumb.bean.WstationBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marksong on 2019/1/5.
 */

public class CurrDatAdapter extends BaseQuickAdapter<NewSWBean.DataBean, CurrDatAdapter.ViewHolder> {

    public CurrDatAdapter() {
        super(R.layout.item_data);
    }

    @Override
    protected void convert(ViewHolder helper, NewSWBean.DataBean item) {
                helper.tvTitle.setText(item.getName());
                helper.tvVal.setText(item.getValue()+item.getUnit());
                if("正常".equals(item.getStatus())){
                    helper.tvStatus.setTextColor(Color.parseColor("#66e97f"));
                }else {
                    helper.tvStatus.setTextColor(Color.parseColor("#df2d10"));
                }
                helper.tvStatus.setText(item.getStatus());
    }

    static class ViewHolder extends BaseViewHolder{
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_val)
        TextView tvVal;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
