package com.wfwlf.mark.pumb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.Device;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marksong on 2018/11/30.
 */

public class DeviceAdapter extends BaseAdapter {
    Context context;
    List<Device.DataBean> mdata;

    int controtype=0;

    public int getControtype() {
        return controtype;
    }

    public void setControtype(int controtype) {
        this.controtype = controtype;
        notifyDataSetChanged();
    }

    public DeviceAdapter(Context context) {
        this.context = context;
        mdata = new ArrayList<>();
    }

    public List<Device.DataBean> getMdata() {
        return mdata;
    }

    public void setMdata(List<Device.DataBean> mdata) {
        this.mdata = mdata;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_device, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        try {
            viewHolder.tvName.setText(mdata.get(position).getDeviceName());
            if (mdata.get(position).getMotorStatus().equals("0")) {
                viewHolder.tvStatus.setText("关闭");
                viewHolder.ivIsoff.setImageResource(R.drawable.off);
            } else {
                viewHolder.ivIsoff.setImageResource(R.drawable.on);
                viewHolder.tvStatus.setText("开启");
            }

            if (mdata.get(position).getControlModel()==0) {
                viewHolder.ivIsremote.setImageResource(R.drawable.local);
            } else {
                viewHolder.ivIsoff.setImageResource(R.drawable.remote);
            }
            if (controtype==0) {
                viewHolder.ivIsremote.setImageResource(R.drawable.hand);
            } else {
                viewHolder.ivIsoff.setImageResource(R.drawable.auto);
            }
            viewHolder.tvTotalTime.setText(mdata.get(position).getTotalRunTime() + "");
            viewHolder.tvVU.setText("U:"+mdata.get(position).getCurrentU());
            viewHolder.tvVV.setText("V:"+mdata.get(position).getCurrentV());
            viewHolder.tvVW.setText("W:"+mdata.get(position).getCurrentW());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_v_u)
        TextView tvVU;
        @BindView(R.id.tv_v_v)
        TextView tvVV;
        @BindView(R.id.tv_v_w)
        TextView tvVW;
        @BindView(R.id.tv_total_time)
        TextView tvTotalTime;
        @BindView(R.id.iv_isremote)
        ImageView ivIsremote;
        @BindView(R.id.iv_isauto)
        ImageView ivIsauto;
        @BindView(R.id.iv_isoff)
        ImageView ivIsoff;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
