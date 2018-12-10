package com.wfwlf.mark.pumb.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.videogo.constant.Constant;
import com.videogo.openapi.bean.EZAccessToken;
import com.wfwlf.mark.pumb.MjlApplication;
import com.wfwlf.mark.pumb.ui.PlayActivity;
import com.wfwlf.mark.pumb.ui.cameralist.EZCameraListActivity;

/**
 * Created by marksong on 2018/12/4.
 */

public class YSBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "EzvizBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Constant.OAUTH_SUCCESS_ACTION)) {
            Log.i(TAG, "onReceive: OAUTH_SUCCESS_ACTION");
            Intent toIntent = new Intent(context, EZCameraListActivity.class);
            toIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            /*******   获取登录成功之后的EZAccessToken对象   *****/
            EZAccessToken token = MjlApplication.getOpenSDK().getEZAccessToken();
            context.startActivity(toIntent);
        }
    }
}
