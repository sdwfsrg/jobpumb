package com.wfwlf.mark.pumb;

import android.content.Context;


import com.wfwlf.mark.pumb.bean.CameraInfo;
import com.wfwlf.mark.pumb.bean.Device;
import com.wfwlf.mark.pumb.bean.LoginBean;
import com.wfwlf.mark.pumb.bean.NewSWBean;
import com.wfwlf.mark.pumb.bean.Site;
import com.wfwlf.mark.pumb.bean.SiteInfo;
import com.wfwlf.mark.pumb.bean.Token;
import com.wfwlf.mark.pumb.bean.WarnInfo;
import com.wfwlf.mark.pumb.bean.WaterChange;
import com.wfwlf.mark.pumb.bean.WstationBean;
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


    private final static String ROOT_PATH = "http://106.13.47.98:2001/psom/";

    private final static String ROOT_PATH_W = "http://10.28.110.134:9091/szsw/";

//private final static String ROOT_PATH = "http://10.28.110.134:9091/psom/";
    private final static String CAMERA_API_URL = ROOT_PATH + "JobCameraController/";
    private final static String USER_URL = ROOT_PATH + "app/";
    private final static String SITE_URL = ROOT_PATH + "JobPumpController/";

    private final static String API_URL = ROOT_PATH + "JobDataTypeController/";
    private final static String CHART_URL = ROOT_PATH + "JobPumpDataController/";
    private final static String CHART_URL2 = ROOT_PATH + "JobDataProcessController/";
    private final static String WARN_URL = ROOT_PATH + "JobWarnInfoController/";
    private final static String CHART_URL3 = ROOT_PATH + "web/common/JobSpinnerController/queryPumpWaterLevelList.do";

    private final static String WATER_URL = ROOT_PATH_W + "station/JobWaterStationController/";
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
    public void get_WCamera_list(String stationId ,MyReponseListener myReponseListener, MyErrorListener errorListener){
        Map<String, Object> map = new HashMap<>();
        map.put("stationId", stationId);
        VolleyGet(ROOT_PATH_W+"camera/JobCameraController/cameraList.do", map, CameraInfo.class,myReponseListener,errorListener);
    }
    public void get_access_token(MyReponseListener myReponseListener, MyErrorListener errorListener){
        VolleyGet(CAMERA_API_URL+"accesstoken.do", null, Token.class,myReponseListener,errorListener);
    }

    /**
     * 水位变化曲线
     * @param pumpId
     * @param type
     * @param myReponseListener
     * @param errorListener
     */
    public void get_wvchange_list(String pumpId ,String type,MyReponseListener myReponseListener, MyErrorListener errorListener){
        Map<String, Object> map = new HashMap<>();
        map.put("pumpId", pumpId);
        map.put("timeType", type);
        VolleyGet(CHART_URL3, map, WaterChange.class,myReponseListener,errorListener);
    }


    /**
     * 获取水站列表
     * @param myReponseListener
     * @param errorListener
     */
    public void  get_w_station_list( MyReponseListener myReponseListener, MyErrorListener errorListener){
        VolleyGet(WATER_URL+"waterStationList.do", null, Site.class,myReponseListener,errorListener);
    }

    public void  get_w_station_map_list( MyReponseListener myReponseListener, MyErrorListener errorListener){
        VolleyGet(WATER_URL+"waterStationMapList.do", null, Site.class,myReponseListener,errorListener);
    }

    /**
     * 最新水站数据
     * @param stationId
     * @param myReponseListener
     * @param errorListener
     */
    public  void get_w_station_info_current(String stationId ,MyReponseListener myReponseListener, MyErrorListener errorListener){
        Map<String, Object> map = new HashMap<>();
        map.put("stationId", stationId);
        VolleyGet(WATER_URL+"waterStationLatestData.do", map, NewSWBean.class,myReponseListener,errorListener);
    }

    /**
     *
     * @param stationId
     * @param timeType
     * @param myReponseListener
     * @param errorListener
     */
    public  void get_w_station_info(String stationId ,int timeType,MyReponseListener myReponseListener, MyErrorListener errorListener){
        Map<String, Object> map = new HashMap<>();
        map.put("stationId", stationId);
        map.put("timeType", timeType);
        VolleyGet(WATER_URL+"waterStationData.do", map, WstationBean.class,myReponseListener,errorListener);
    }
}



