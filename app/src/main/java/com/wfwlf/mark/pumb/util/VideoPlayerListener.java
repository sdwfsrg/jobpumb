package com.wfwlf.mark.pumb.util;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by marksong on 2018/12/3.
 */

public abstract class VideoPlayerListener implements IMediaPlayer.OnBufferingUpdateListener
        , IMediaPlayer.OnCompletionListener, IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener
        , IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnErrorListener
        , IMediaPlayer.OnSeekCompleteListener {
}