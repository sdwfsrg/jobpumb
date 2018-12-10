package com.wfwlf.mark.pumb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.Site;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marksong on 2018/11/30.
 */

public class MarkAdapter extends BaseAdapter {
    Context context;
    List<Site.DataBean> mdata;

    public MarkAdapter(Context context) {
        this.context = context;
        mdata=new ArrayList<>();
    }

    public List<Site.DataBean> getMdata() {
        return mdata;
    }

    public void setMdata(List<Site.DataBean> mdata) {
        this.mdata = mdata;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Site.DataBean getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pump, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(mdata.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
