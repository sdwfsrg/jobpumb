package com.wfwlf.mark.pumb.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.dl7.player.media.IjkPlayerView;
import com.wfwlf.mark.pumb.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by marksong on 2018/12/3.
 */

public class VideoActivity extends AppCompatActivity {
    String url;
    @BindView(R.id.player_view)
    IjkPlayerView mPlayerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        url=getIntent().getStringExtra("str");
        mPlayerView.init().setTitle("泵站监控").setVideoPath(url).alwaysFullScreen()
                .start();

        mPlayerView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlayerView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mPlayerView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.onDestroy();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

}
