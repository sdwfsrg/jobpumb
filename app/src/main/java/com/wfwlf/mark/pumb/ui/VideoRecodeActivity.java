package com.wfwlf.mark.pumb.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.wfwlf.mark.pumb.NetValues;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.adapter.VideoAdapter;
import com.wfwlf.mark.pumb.bean.CameraInfo;
import com.wfwlf.mark.pumb.bean.Token;
import com.wfwlf.mark.pumb.util.CommonUtils;
import com.wfwlf.mark.pumb.util.SPUtils;
import com.wfwlf.mark.pumb.volley.BaseVO;
import com.wfwlf.mark.pumb.volley.MyErrorListener;
import com.wfwlf.mark.pumb.volley.MyReponseListener;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by marksong on 2018/11/30.
 */

public class VideoRecodeActivity extends AppCompatActivity {
    public static final int RESULT_CODE =1001 ;
    @BindView(R.id.return_iv)
    AutoRelativeLayout returnIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.lv_video)
    GridView lvVideo;
    VideoAdapter videoAdapter;
    NetValues netValues;
    String pumpID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recode);
        ButterKnife.bind(this);
        pumpID=getIntent().getStringExtra("str");
        netValues=NetValues.getInstance(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        videoAdapter = new VideoAdapter(this);
        lvVideo.setAdapter(videoAdapter);
        netValues.get_Camera_list(pumpID, new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                CameraInfo info=(CameraInfo)arg0;
                videoAdapter.setMdata(info.getData());

            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
        netValues.get_access_token(new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                Token token=(Token) arg0;
                if(CommonUtils.checkNull(token.getData())){
                    SPUtils.put(VideoRecodeActivity.this,"token",token.getData());
                }
            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });

////        loadVideo("http://hls.open.ys7.com/openlive/870b7b1eec2e4de993321598af8884b1.m3u8");
//        ijkPlayer.init() .setVideoPath("rtmp://rtmp.open.ys7.com/openlive/870b7b1eec2e4de993321598af8884b1").enableDanmaku().start();
    }


    @Override
    protected void onStop() {
        super.onStop();

    }



    @OnClick(R.id.return_iv)
    public void onViewClicked() {
        finish();
    }
}
