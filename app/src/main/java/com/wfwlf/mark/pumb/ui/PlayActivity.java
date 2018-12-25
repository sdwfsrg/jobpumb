package com.wfwlf.mark.pumb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ezvizuikit.open.EZUIError;
import com.ezvizuikit.open.EZUIKit;
import com.ezvizuikit.open.EZUIPlayer;
import com.videogo.exception.BaseException;
import com.videogo.exception.ErrorCode;
import com.videogo.openapi.EZConstants;
import com.videogo.realplay.RealPlayStatus;
import com.videogo.util.LogUtil;
import com.wfwlf.mark.pumb.MjlApplication;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.bean.CameraInfo;
import com.wfwlf.mark.pumb.util.SPUtils;
import com.wfwlf.mark.pumb.util.WindowSizeChangeNotifier;

import java.util.Calendar;

import static com.wfwlf.mark.pumb.ui.EZRealPlayActivity.MSG_HIDE_PTZ_DIRECTION;

/**
 * 预览界面
 */
public class PlayActivity extends Activity implements View.OnClickListener,View.OnTouchListener,WindowSizeChangeNotifier.OnWindowSizeChangedListener, EZUIPlayer.EZUIPlayerCallBack {
    private static final String TAG = "PlayActivity";
    public static final String APPKEY = "AppKey";
    public static final String AccessToekn = "AccessToekn";
    public static final String PLAY_URL = "play_url";
    public static final String Global_AreanDomain = "global_arean_domain";
    private EZUIPlayer mEZUIPlayer;

    private Button mBtnPlay ,mBtnLeft,mBtnRight,mBtnup,mBtndown;
    private LinearLayout back;
    ImageButton ptzzominBtn ;
    ImageButton ptzzomoutBtn;
    /**
     * onresume时是否恢复播放
     */
    private boolean isResumePlay = false;

    private MyOrientationDetector mOrientationDetector;

    /**
     *  开发者申请的Appkey
     */
    private String appkey="4ce53359805c4675ba019ea88c547bcb";
    /**
     *  授权accesstoken
     */
    private String accesstoken="at.cvi8qky3951icbxf649k63e4dc4t9xno-58f07g4pjj-1ful4i4-3m4xzetq0";
    /**
     *  播放url：ezopen协议
     */
    private String playUrl="ezopen://open.ys7.com/C61656416/1.hd.live";

    /**
     * 海外版本areaDomin
     */
    private String mGlobalAreaDomain;

    int  controlltype=0;
    Thread lfthreadstart;
    Thread lfthreadstop;
    private String mDeviceSerial="C61656416";
    private int mCameraNo=1;
    private ImageView mRealPlayPtzDirectionIv = null;
    private LinearLayout mPtzControlLy = null;
    private RelativeLayout mRealPlaySv;
    CameraInfo.DataBean device;
    /**
     * 开启预览播放
     * @param context
     * @param appkey       开发者申请的appkey
     * @param accesstoken   开发者登录授权的accesstoken
     * @param url           预览url
     */
    public static void startPlayActivity(Context context, String appkey, String accesstoken, String url){
        Intent intent = new Intent(context, PlayActivity.class);
        intent.putExtra(APPKEY,appkey);
        intent.putExtra(AccessToekn,accesstoken);
        intent.putExtra(PLAY_URL,url);
        context.startActivity(intent);
    }

    /**
     * 开启海外版本预览播放
     * @param context
     * @param appkey       开发者申请的appkey
     * @param accesstoken   开发者登录授权的accesstoken
     * @param url           预览url
     * @param global_AreanDomain 海外版本域名
     */
    public static void startPlayActivityGlobal(Context context, String appkey, String accesstoken, String url, String global_AreanDomain){
        Intent intent = new Intent(context, PlayActivity.class);
        intent.putExtra(APPKEY,appkey);
        intent.putExtra(AccessToekn,accesstoken);
        intent.putExtra(PLAY_URL,url);
        intent.putExtra(Global_AreanDomain,global_AreanDomain);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
//                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG,"onCreate");
        setContentView(R.layout.activity_play);
        try {
            Intent intent = getIntent();
            device= (CameraInfo.DataBean) intent.getSerializableExtra("camera");
            playUrl=device.getEzopenAddr();
            mDeviceSerial=device.getSerial();
            mCameraNo=Integer.parseInt(device.getChannel());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        accesstoken=(String) SPUtils.get(this,"token","");
//        appkey = intent.getStringExtra(APPKEY);
//        accesstoken = intent.getStringExtra(AccessToekn);
//        playUrl = intent.getStringExtra(PLAY_URL);
//        mGlobalAreaDomain = intent.getStringExtra(Global_AreanDomain);
        if (TextUtils.isEmpty(appkey)
                || TextUtils.isEmpty(accesstoken)
                || TextUtils.isEmpty(playUrl)){
            Toast.makeText(this,"appkey,accesstoken or playUrl is null", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        initview();
        preparePlay();
        setSurfaceSize();
    }

    private void initview() {
        mOrientationDetector = new MyOrientationDetector(this);
        new WindowSizeChangeNotifier(this, this);
        mBtnPlay = (Button) findViewById(R.id.btn_play);
        mBtnLeft = (Button) findViewById(R.id.btn_left);
        mBtnRight = (Button) findViewById(R.id.btn_right);
        mBtndown = (Button) findViewById(R.id.btn_down);
        mBtnup = (Button) findViewById(R.id.btn_up);
        back=(LinearLayout)findViewById(R.id.return_iv);
        mRealPlaySv=(RelativeLayout)findViewById(R.id.player_layout);
        mRealPlayPtzDirectionIv = (ImageView) findViewById(R.id.realplay_ptz_direction_iv);
        mPtzControlLy = (LinearLayout) findViewById(R.id.ptz_control_ly);
        ImageButton ptzTopBtn = (ImageButton) findViewById(R.id.ptz_top_btn);
        ptzTopBtn.setOnTouchListener(mOnTouchListener);
        ImageButton ptzBottomBtn = (ImageButton) findViewById(R.id.ptz_bottom_btn);
        ptzBottomBtn.setOnTouchListener(mOnTouchListener);
        ImageButton ptzLeftBtn = (ImageButton) findViewById(R.id.ptz_left_btn);
        ptzLeftBtn.setOnTouchListener(mOnTouchListener);
        ImageButton ptzRightBtn = (ImageButton)findViewById(R.id.ptz_right_btn);
        ptzRightBtn.setOnTouchListener(mOnTouchListener);
        ImageButton ptzFlipBtn = (ImageButton) findViewById(R.id.ptz_flip_btn);


        ptzzominBtn = (ImageButton)findViewById(R.id.btn_zoomin);
        ptzzomoutBtn = (ImageButton)findViewById(R.id.btn_zoomout);
        ptzzominBtn.setOnTouchListener(mOnTouchListener);
        ptzzomoutBtn.setOnTouchListener(mOnTouchListener);
//        ptzFlipBtn.setOnClickListener(mOnPopWndClickListener);
        //获取EZUIPlayer实例
        mEZUIPlayer = (EZUIPlayer) findViewById(R.id.player_ui);
        //设置加载需要显示的view
        mEZUIPlayer.setLoadingView(initProgressBar());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnPlay.setOnTouchListener(this);
        mBtnLeft.setOnTouchListener(this);
        mBtnRight.setOnTouchListener(this);
        mBtndown.setOnTouchListener(this);
        mBtnup.setOnTouchListener(this);

        mBtnPlay.setText(R.string.string_stop_play);
    }

    private void setPtzDirectionIv(int command) {
        setPtzDirectionIv(command, 0);
    }

    private void setPtzDirectionIv(int command, int errorCode) {
        if (command != -1 && errorCode == 0) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            switch (command) {
                case RealPlayStatus.PTZ_LEFT:
                    mRealPlayPtzDirectionIv.setBackgroundResource(R.drawable.left_twinkle);
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    mRealPlayPtzDirectionIv.setLayoutParams(params);
                    break;
                case RealPlayStatus.PTZ_RIGHT:
                    mRealPlayPtzDirectionIv.setBackgroundResource(R.drawable.right_twinkle);
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    mRealPlayPtzDirectionIv.setLayoutParams(params);
                    break;
                case RealPlayStatus.PTZ_UP:
                    mRealPlayPtzDirectionIv.setBackgroundResource(R.drawable.up_twinkle);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    mRealPlayPtzDirectionIv.setLayoutParams(params);
                    break;
                case RealPlayStatus.PTZ_DOWN:
                    mRealPlayPtzDirectionIv.setBackgroundResource(R.drawable.down_twinkle);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.player_layout);
                    mRealPlayPtzDirectionIv.setLayoutParams(params);
                    break;
                default:
                    break;
            }
            mRealPlayPtzDirectionIv.setVisibility(View.VISIBLE);
//            mHandler.removeMessages(MSG_HIDE_PTZ_DIRECTION);
            Message msg = new Message();
            msg.what = MSG_HIDE_PTZ_DIRECTION;
            msg.arg1 = 1;
//            mHandler.sendMessageDelayed(msg, 500);
        } else if (errorCode != 0) {
            RelativeLayout.LayoutParams svParams = (RelativeLayout.LayoutParams) mRealPlaySv.getLayoutParams();
            RelativeLayout.LayoutParams params = null;
            switch (errorCode) {
                case ErrorCode.ERROR_CAS_PTZ_ROTATION_LEFT_LIMIT_FAILED:
                    params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, svParams.height);
                    mRealPlayPtzDirectionIv.setBackgroundResource(R.drawable.ptz_left_limit);
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    mRealPlayPtzDirectionIv.setLayoutParams(params);
                    break;
                case ErrorCode.ERROR_CAS_PTZ_ROTATION_RIGHT_LIMIT_FAILED:
                    params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, svParams.height);
                    mRealPlayPtzDirectionIv.setBackgroundResource(R.drawable.ptz_right_limit);
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    mRealPlayPtzDirectionIv.setLayoutParams(params);
                    break;
                case ErrorCode.ERROR_CAS_PTZ_ROTATION_UP_LIMIT_FAILED:
                    params = new RelativeLayout.LayoutParams(svParams.width, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    mRealPlayPtzDirectionIv.setBackgroundResource(R.drawable.ptz_top_limit);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    mRealPlayPtzDirectionIv.setLayoutParams(params);
                    break;
                case ErrorCode.ERROR_CAS_PTZ_ROTATION_DOWN_LIMIT_FAILED:
                    params = new RelativeLayout.LayoutParams(svParams.width, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    mRealPlayPtzDirectionIv.setBackgroundResource(R.drawable.ptz_bottom_limit);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.realplay_sv);
                    mRealPlayPtzDirectionIv.setLayoutParams(params);
                    break;
                default:
                    break;
            }
            mRealPlayPtzDirectionIv.setVisibility(View.VISIBLE);
//            mHandler.removeMessages(MSG_HIDE_PTZ_DIRECTION);
            Message msg = new Message();
            msg.what = MSG_HIDE_PTZ_DIRECTION;
            msg.arg1 = 1;
//            mHandler.sendMessageDelayed(msg, 500);
        } else {
            mRealPlayPtzDirectionIv.setVisibility(View.GONE);
//            mHandler.removeMessages(MSG_HIDE_PTZ_DIRECTION);
        }
    }

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent motionevent) {
            boolean ptz_result = false;
            int action = motionevent.getAction();
            final int speed = EZConstants.PTZ_SPEED_DEFAULT;
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    switch (view.getId()) {
                        case R.id.talkback_control_btn:
//                            mTalkRingView.setVisibility(View.VISIBLE);
//                            mEZPlayer.setVoiceTalkStatus(true);
                            break;
                        case R.id.ptz_top_btn:
                            mRealPlayPtzDirectionIv.setVisibility(View.VISIBLE);
                            mPtzControlLy.setBackgroundResource(R.drawable.ptz_up_sel);
                            setPtzDirectionIv(RealPlayStatus.PTZ_UP);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandUp, EZConstants.EZPTZAction.EZPTZActionSTART);
                            break;
                        case R.id.ptz_bottom_btn:
                            mRealPlayPtzDirectionIv.setVisibility(View.VISIBLE);
                            mPtzControlLy.setBackgroundResource(R.drawable.ptz_bottom_sel);
                            setPtzDirectionIv(RealPlayStatus.PTZ_DOWN);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandDown, EZConstants.EZPTZAction.EZPTZActionSTART);
                            break;
                        case R.id.ptz_left_btn:
                            mRealPlayPtzDirectionIv.setVisibility(View.VISIBLE);
                            mPtzControlLy.setBackgroundResource(R.drawable.ptz_left_sel);
                            setPtzDirectionIv(RealPlayStatus.PTZ_LEFT);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandLeft, EZConstants.EZPTZAction.EZPTZActionSTART);
                            break;
                        case R.id.ptz_right_btn:
                            mRealPlayPtzDirectionIv.setVisibility(View.VISIBLE);
                            mPtzControlLy.setBackgroundResource(R.drawable.ptz_right_sel);
                            setPtzDirectionIv(RealPlayStatus.PTZ_RIGHT);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandRight, EZConstants.EZPTZAction.EZPTZActionSTART);
                            break;

                        case R.id.btn_zoomin:
                            ptzzominBtn.setBackgroundResource(R.drawable.fangda_sel);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandZoomIn, EZConstants.EZPTZAction.EZPTZActionSTART);
                            break;

                        case R.id.btn_zoomout:
                            ptzzomoutBtn.setBackgroundResource(R.drawable.sxiao_sel);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandZoomOut, EZConstants.EZPTZAction.EZPTZActionSTART);
                            break;
                        default:
                            break;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    switch (view.getId()) {
                        case R.id.talkback_control_btn:
//                            mEZPlayer.setVoiceTalkStatus(false);
//                            mTalkRingView.setVisibility(View.GONE);
                            break;
                        case R.id.ptz_top_btn:
                            Toast.makeText(PlayActivity.this, "21", Toast.LENGTH_SHORT).show();
                            mRealPlayPtzDirectionIv.setVisibility(View.GONE);
                            mPtzControlLy.setBackgroundResource(R.drawable.ptz_bg);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandUp, EZConstants.EZPTZAction.EZPTZActionSTOP);
                            break;
                        case R.id.ptz_bottom_btn:
                            mRealPlayPtzDirectionIv.setVisibility(View.GONE);
                            mPtzControlLy.setBackgroundResource(R.drawable.ptz_bg);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandDown, EZConstants.EZPTZAction.EZPTZActionSTOP);
                            break;
                        case R.id.ptz_left_btn:
                            mRealPlayPtzDirectionIv.setVisibility(View.GONE);
                            mPtzControlLy.setBackgroundResource(R.drawable.ptz_bg);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandLeft, EZConstants.EZPTZAction.EZPTZActionSTOP);
                            break;
                        case R.id.ptz_right_btn:
                            mRealPlayPtzDirectionIv.setVisibility(View.GONE);
                            mPtzControlLy.setBackgroundResource(R.drawable.ptz_bg);
                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandRight, EZConstants.EZPTZAction.EZPTZActionSTOP);
                            break;

                        case R.id.btn_zoomin:
                            ptzzominBtn.setBackgroundResource(R.drawable.fangda);

                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandZoomIn, EZConstants.EZPTZAction.EZPTZActionSTOP);
                            break;

                        case R.id.btn_zoomout:
                            ptzzomoutBtn.setBackgroundResource(R.drawable.sxiao);

                            ptzOption(EZConstants.EZPTZCommand.EZPTZCommandZoomOut, EZConstants.EZPTZAction.EZPTZActionSTOP);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    };
    /**
     * 云台操作
     *
     * @param command ptz控制命令
     * @param action  控制启动/停止
     */
    private void ptzOption(final EZConstants.EZPTZCommand command, final EZConstants.EZPTZAction action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean ptz_result = false;
                try {
                    ptz_result = MjlApplication.getOpenSDK().controlPTZ(mDeviceSerial, mCameraNo, command,
                            action, EZConstants.PTZ_SPEED_DEFAULT);
                } catch (BaseException e) {
                    e.printStackTrace();
                }
                LogUtil.i(TAG, "controlPTZ ptzCtrl result: " + ptz_result);
            }
        }).start();
    }

    /**
     * 创建加载view
     * @return
     */
    private View initProgressBar() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(Color.parseColor("#000000"));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(lp);
        RelativeLayout.LayoutParams rlp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);//addRule参数对应RelativeLayout XML布局的属性
        ProgressBar mProgressBar = new ProgressBar(this);
        mProgressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress));
        relativeLayout.addView(mProgressBar,rlp);
        return relativeLayout;
    }

    /**
     * 准备播放资源参数
     */
    private void preparePlay(){
        //设置debug模式，输出log信息
        EZUIKit.setDebug(true);
        if (TextUtils.isEmpty(mGlobalAreaDomain)) {
            //appkey初始化
            EZUIKit.initWithAppKey(this.getApplication(), appkey);

        }else{
            //appkey初始化 海外版本
            EZUIKit.initWithAppKeyGlobal(this.getApplication(), appkey,mGlobalAreaDomain);
        }
        //设置授权accesstoken
        EZUIKit.setAccessToken(accesstoken);
        //设置播放资源参数
        mEZUIPlayer.setCallBack(this);
        mEZUIPlayer.setUrl(playUrl);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mOrientationDetector.enable();
        Log.d(TAG,"onResume");
        //界面stop时，如果在播放，那isResumePlay标志位置为true，resume时恢复播放
        if (isResumePlay) {
            isResumePlay = false;
            mBtnPlay.setText(R.string.string_stop_play);
            mEZUIPlayer.startPlay();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mOrientationDetector.disable();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop + "+mEZUIPlayer.getStatus());
        //界面stop时，如果在播放，那isResumePlay标志位置为true，以便resume时恢复播放
        if (mEZUIPlayer.getStatus() != EZUIPlayer.STATUS_STOP) {
            isResumePlay = true;
        }
        //停止播放
        mEZUIPlayer.stopPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");

        //释放资源
        mEZUIPlayer.releasePlayer();
    }

    @Override
    public void onPlaySuccess() {
        Log.d(TAG,"onPlaySuccess");
        // TODO: 2017/2/7 播放成功处理
        mBtnPlay.setText(R.string.string_pause_play);
    }

    @Override
    public void onPlayFail(EZUIError error) {
        Log.d(TAG,"onPlayFail");
        // TODO: 2017/2/21 播放失败处理
        if (error.getErrorString().equals(EZUIError.UE_ERROR_INNER_VERIFYCODE_ERROR)){

        }else if(error.getErrorString().equalsIgnoreCase(EZUIError.UE_ERROR_NOT_FOUND_RECORD_FILES)){
            // TODO: 2017/5/12
            //未发现录像文件
            Toast.makeText(this,getString(R.string.string_not_found_recordfile), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onVideoSizeChange(int width, int height) {
        // TODO: 2017/2/16 播放视频分辨率回调
        Log.d(TAG,"onVideoSizeChange  width = "+width+"   height = "+height);
    }

    @Override
    public void onPrepared() {
        Log.d(TAG,"onPrepared");
        //播放
        mEZUIPlayer.startPlay();
    }

    @Override
    public void onPlayTime(Calendar calendar) {
        Log.d(TAG,"onPlayTime");
        if (calendar != null) {
            // TODO: 2017/2/16 当前播放时间
            Log.d(TAG,"onPlayTime calendar = "+calendar.getTime().toString());
        }
    }

    @Override
    public void onPlayFinish() {
        // TODO: 2017/2/16 播放结束
        Log.d(TAG,"onPlayFinish");
    }


    @Override
    public void onClick(View view) {
        if (view == mBtnPlay){
            // TODO: 2017/2/14
            if (mEZUIPlayer.getStatus() == EZUIPlayer.STATUS_PLAY) {
                //播放状态，点击停止播放
                mBtnPlay.setText(R.string.string_start_play);
                mEZUIPlayer.stopPlay();
            } else if (mEZUIPlayer.getStatus() == EZUIPlayer.STATUS_STOP) {
                //停止状态，点击播放
                mBtnPlay.setText(R.string.string_stop_play);
                mEZUIPlayer.startPlay();
            }
        }

    }


    /**
     * 屏幕旋转时调用此方法
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG,"onConfigurationChanged");
        setSurfaceSize();
    }

    private void setSurfaceSize(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        boolean isWideScrren = mOrientationDetector.isWideScrren();
        //竖屏
        if (!isWideScrren) {
            //竖屏调整播放区域大小，宽全屏，高根据视频分辨率自适应
            mEZUIPlayer.setSurfaceSize(dm.widthPixels, 0);
        } else {
            //横屏屏调整播放区域大小，宽、高均全屏，播放区域根据视频分辨率自适应
            mEZUIPlayer.setSurfaceSize(dm.widthPixels,dm.heightPixels);
        }
    }

    @Override
    public void onWindowSizeChanged(int w, int h, int oldW, int oldH) {
        if (mEZUIPlayer != null) {
            setSurfaceSize();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            if(v== mBtnPlay){
                if(event.getAction() == MotionEvent.ACTION_UP){
                    lfthreadstart.start();
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    lfthreadstop.start();
                }
            }


            if(v== mBtnup){
                if(event.getAction() == MotionEvent.ACTION_UP){
                    MjlApplication.getOpenSDK().controlPTZ(mDeviceSerial,mCameraNo, EZConstants.EZPTZCommand.EZPTZCommandUp, EZConstants.EZPTZAction.EZPTZActionSTART, 1);

                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    MjlApplication.getOpenSDK().controlPTZ(mDeviceSerial,mCameraNo, EZConstants.EZPTZCommand.EZPTZCommandUp, EZConstants.EZPTZAction.EZPTZActionSTOP, 1);

                }
            }


            if(v== mBtndown){
                if(event.getAction() == MotionEvent.ACTION_UP){
                    MjlApplication.getOpenSDK().controlPTZ(mDeviceSerial,mCameraNo, EZConstants.EZPTZCommand.EZPTZCommandDown, EZConstants.EZPTZAction.EZPTZActionSTART, 1);

                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    MjlApplication.getOpenSDK().controlPTZ(mDeviceSerial,mCameraNo, EZConstants.EZPTZCommand.EZPTZCommandDown, EZConstants.EZPTZAction.EZPTZActionSTOP, 1);

                }
            }

            if(v== mBtnLeft){
                if(event.getAction() == MotionEvent.ACTION_UP){
                    MjlApplication.getOpenSDK().controlPTZ(mDeviceSerial,mCameraNo, EZConstants.EZPTZCommand.EZPTZCommandLeft, EZConstants.EZPTZAction.EZPTZActionSTART, 1);

                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    MjlApplication.getOpenSDK().controlPTZ(mDeviceSerial,mCameraNo, EZConstants.EZPTZCommand.EZPTZCommandLeft, EZConstants.EZPTZAction.EZPTZActionSTOP, 1);

                }
            }

            if(v== mBtnRight){
                if(event.getAction() == MotionEvent.ACTION_UP){
                    MjlApplication.getOpenSDK().controlPTZ(mDeviceSerial,mCameraNo, EZConstants.EZPTZCommand.EZPTZCommandRight, EZConstants.EZPTZAction.EZPTZActionSTART, 1);

                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                }
            }
        } catch (BaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public class MyOrientationDetector extends OrientationEventListener {

        private WindowManager mWindowManager;
        private int mLastOrientation = 0;

        public MyOrientationDetector(Context context) {
            super(context);
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }

        public boolean isWideScrren() {
            Display display = mWindowManager.getDefaultDisplay();
            Point pt = new Point();
            display.getSize(pt);
            return pt.x > pt.y;
        }
        @Override
        public void onOrientationChanged(int orientation) {
            int value = getCurentOrientationEx(orientation);
            if (value != mLastOrientation) {
                mLastOrientation = value;
                int current = getRequestedOrientation();
                if (current == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        || current == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
            }
        }

        private int getCurentOrientationEx(int orientation) {
            int value = 0;
            if (orientation >= 315 || orientation < 45) {
                // 0度
                value = 0;
                return value;
            }
            if (orientation >= 45 && orientation < 135) {
                // 90度
                value = 90;
                return value;
            }
            if (orientation >= 135 && orientation < 225) {
                // 180度
                value = 180;
                return value;
            }
            if (orientation >= 225 && orientation < 315) {
                // 270度
                value = 270;
                return value;
            }
            return value;
        }
    }
}