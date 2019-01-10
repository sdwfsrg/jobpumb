package com.wfwlf.mark.pumb;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.videogo.openapi.EZOpenSDK;

public class MjlApplication  extends Application{
    public static String AppKey = "4ce53359805c4675ba019ea88c547bcb";
    public static EZOpenSDK getOpenSDK() {
        return EZOpenSDK.getInstance();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);

        initSDK();
    }
    private void initSDK() {
        {
            /**
             * sdk日志开关，正式发布需要去掉
             */
            EZOpenSDK.showSDKLog(true);

            /**
             * 设置是否支持P2P取流,详见api
             */
            EZOpenSDK.enableP2P(true);

            /**
             * APP_KEY请替换成自己申请的
             */
            EZOpenSDK.initLib(this, AppKey);
        }
    }
}
