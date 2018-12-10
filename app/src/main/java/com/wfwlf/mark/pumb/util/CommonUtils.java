/*
    ShengDao Android Client, CommonUtils
    Copyright (c) 2014 ShengDao Tech Company Limited
 */

package com.wfwlf.mark.pumb.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;



import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * [公共工具类，与Android API相关的辅助类]

 *
 **/
public class CommonUtils {

    @SuppressWarnings("unused")
    private static final String tag = CommonUtils.class.getSimpleName();
    private static Map<String,Activity> destoryMap = new HashMap<>();
    /** 网络类型 **/
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;



    public static void  startActivity(Context context, Class clt){
        Intent it=new Intent(context,clt);
        context.startActivity(it);
    }

    public static void  startActivity(Context context, Class clt, String str){
        Intent it=new Intent(context,clt);
        it.putExtra("str",str);
        context.startActivity(it);
    }
    public static void  goToWeb(Context context, Class clt, String str){
        Intent it=new Intent(context,clt);
        it.putExtra("load_url",str);
        context.startActivity(it);
    }
    public static void  startActivity(Context context, Class clt, int id){
        Intent it=new Intent(context,clt);
        it.putExtra("id",id);
        context.startActivity(it);
    }

    public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][3456789]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }


    /**
     * @param destinationLat 目的地维度
     * @param destinationLng 目的地经度
     * @param coord_type     坐标类型  允许的值为bd09ll、bd09mc、gcj02、wgs84。
     *                       bd09ll表示百度经纬度坐标，bd09mc表示百度墨卡托坐标，gcj02表示经过国测局加密的坐标，wgs84表示gps获取的坐标
     * @param mode           导航类型导航模式
     *                       可选transit（公交）、 driving（驾车）、 walking（步行）和riding（骑行）.
     * @param src            必选参数，格式为：andr.companyName.appName  不传此参数，不保证服务
     */
    public static void startBaiduNavi(Context context, String destinationLat, String destinationLng, String coord_type, String mode, String src) {
        Intent i1 = new Intent();
        i1.setData(Uri.parse("baidumap://map/direction?destination=" +
                destinationLat + "," + destinationLng + "&coord_type=" + coord_type +
                "&mode=" + mode + "&src=" + src + "#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end"));

        context.startActivity(i1);
    }


        /**
         * 添加到销毁队列
         *
         * @param activity 要销毁的activity
         */

    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName,activity);
    }
    /**
     *销毁指定Activity
     */
    public static void destoryActivity() {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            destoryMap.get(key).finish();
        }
        destoryMap.clear();
    }
        /**
         * 获取焦点
         * @param view
         */
    public static void requestViewFoucus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }
    /**
     * 检测网络是否可用
     *
     * @return
     */


    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */



    /**
     * 判断SDCard是否存在,并可写
     *
     * @return
     */
    public static boolean checkSDCard() {
        String flag = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(flag);
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取屏幕显示信息对象
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }

    /**
     * dp转pixel
     */
    public static float dpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    /**
     * pixel转dp
     */
    public static float pixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }


    /**
     * 隐藏软键盘
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     * @param activity
     */
    public static void showKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (!imm.isActive()) {
                imm.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * 是否横屏
     * @param context
     * @return true为横屏，false为竖屏
     */
    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 判断是否是平板
     * 这个方法是从 Google I/O App for Android 的源码里找来的，非常准确。
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean checkNull(String phone) {

        try {
            if(phone!=null){
              if("".equals(phone)){
                  return false;
              }else {
                  return true;
              }
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
