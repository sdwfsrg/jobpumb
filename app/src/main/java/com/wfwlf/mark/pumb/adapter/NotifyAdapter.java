package com.wfwlf.mark.pumb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.WarnInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marksong on 2018/11/30.
 */

public class NotifyAdapter extends BaseAdapter {
    Context context;
    List<WarnInfo.DataBean> mdata;
    public NotifyAdapter(Context context,List<WarnInfo.DataBean> mdata) {
        this.context = context;
        this.mdata=mdata;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_notify, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(mdata.get(position).getWarnInfo());
        viewHolder.tvTime.setText(mdata.get(position).getWarnTime());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
