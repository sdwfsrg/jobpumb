package com.wfwlf.mark.pumb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dl7.player.media.IjkPlayerView;
import com.videogo.constant.IntentConsts;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.CameraInfo;
import com.wfwlf.mark.pumb.ui.EZRealPlayActivity;
import com.wfwlf.mark.pumb.ui.PlayActivity;
import com.wfwlf.mark.pumb.ui.VideoActivity;
import com.wfwlf.mark.pumb.util.CommonUtils;
import com.wfwlf.mark.pumb.util.VideoPlayerIJK;
import com.wfwlf.mark.pumb.util.VideoPlayerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by marksong on 2018/11/30.
 */

public class VideoAdapter extends BaseAdapter {
    Context context;
    List<CameraInfo.DataBean> mdata;
    public VideoAdapter(Context context) {
        this.context = context;
        this.mdata=new ArrayList<>();
    }

    public void setMdata(List<CameraInfo.DataBean> mdata) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder. ijkPlayer.init().setVideoPath(mdata.get(position).getRtmpAddr()).start();
//        viewHolder. ijkPlayer.setClickable(false);
        viewHolder.tvName.setText(mdata.get(position).getCameraName());
        viewHolder.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonUtils.startActivity(context, VideoActivity.class,mdata.get(position).getRtmpAddr());
//                CommonUtils.startActivity(context, PlayActivity.class);
                Intent it=new Intent(context, PlayActivity.class);
                it.putExtra("camera",mdata.get(position));
                context.startActivity(it);
            }
        });
        return convertView;
    }



    static class ViewHolder {

        @BindView(R.id.btn_show)
        RelativeLayout btnShow;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
