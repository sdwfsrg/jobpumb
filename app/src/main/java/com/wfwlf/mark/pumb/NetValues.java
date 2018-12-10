package com.wfwlf.mark.pumb;

import android.content.Context;


import com.wfwlf.mark.pumb.bean.CameraInfo;
import com.wfwlf.mark.pumb.bean.Device;
import com.wfwlf.mark.pumb.bean.LoginBean;
import com.wfwlf.mark.pumb.bean.Site;
import com.wfwlf.mark.pumb.bean.SiteInfo;
import com.wfwlf.mark.pumb.bean.Token;
import com.wfwlf.mark.pumb.bean.WarnInfo;
import com.wfwlf.mark.pumb.volley.MyErrorListener;
import com.wfwlf.mark.pumb.volley.MyReponseListener;
import com.wfwlf.mark.pumb.volley.VolleyRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/7.
 */
public class NetValues extends VolleyRequest {

    private static NetValues netValues;

    public NetValues(Context ctx) {
        super(ctx);
    }

    public static NetValues getInstance(Context contex) {
        if (netValues == null) {
            netValues = new NetValues(contex);
        }
        return netValues;
    }

    private final static String ROOT_PATH = "http://10.28.110.134:9091/psom/";

    private final static String CAMERA_API_URL = ROOT_PATH + "JobCameraController/";
    private final static String USER_URL = ROOT_PATH + "app/";
    private final static String SITE_URL = ROOT_PATH + "JobPumpController/";

    private final static String API_URL = ROOT_PATH + "JobDataTypeController/";
    private final static String CHART_URL = ROOT_PATH + "JobPumpDataController/";
    private final static String CHART_URL2 = ROOT_PATH + "JobDataProcessController/";
    private final static String WARN_URL = ROOT_PATH + "JobWarnInfoController/";

    public void get_site_list( MyReponseListener myReponseListener, MyErrorListener errorListener) {

        VolleyGet(SITE_URL+"queryPumpCombox.do", null, Site.class,myReponseListener,errorListener);
    }

    /**
     * 图表信息
     * @param pumpId
     * @param num
     * @param myReponseListener
     * @param errorListener
     */
    public  void  get_site_info(String pumpId,int num,MyReponseListener myReponseListener, MyErrorListener errorListener){
        Map<String, Object> map = new HashMap<>();
        map.put("pumpId", pumpId);
        map.put("num", num);
        VolleyGet(CHART_URL+"query.do", map, SiteInfo.class,myReponseListener,errorListener);
    }

    /**
     * 电机列表
     * @param pumpId
     * @param myReponseListener
     * @param errorListener
     */
    public void get_device_list(String pumpId ,MyReponseListener myReponseListener, MyErrorListener errorListener){
        Map<String, Object> map = new HashMap<>();
        map.put("pumpId", pumpId);
        VolleyGet(CHART_URL2+"queryMotorInstantList.do", map, Device.class,myReponseListener,errorListener);
    }

    public void login(String acount, String pwd, MyReponseListener myReponseListener, MyErrorListener errorListener) {
        Map<String, String> map = new HashMap<>();
        map.put("usercode", acount);
        map.put("passwd", pwd);
        VolleyPost(USER_URL+"login.do", map, LoginBean.class,myReponseListener,errorListener);
    }
    public void get_notify_list(MyReponseListener myReponseListener, MyErrorListener errorListener){
        Map<String, Object> map = new HashMap<>();
        map.put("status", "0");
        VolleyGet(WARN_URL+"queryWarn.do", map, WarnInfo.class,myReponseListener,errorListener);
    }

    public void get_Camera_list(String pumpId ,MyReponseListener myReponseListener, MyErrorListener errorListener){
        Map<String, Object> map = new HashMap<>();
        map.put("pumpId", pumpId);
        VolleyGet(CAMERA_API_URL+"queryCameraByPumpId.do", map, CameraInfo.class,myReponseListener,errorListener);
    }

    public void get_access_token(MyReponseListener myReponseListener, MyErrorListener errorListener){
        VolleyGet(CAMERA_API_URL+"accesstoken.do", null, Token.class,myReponseListener,errorListener);
    }
}



