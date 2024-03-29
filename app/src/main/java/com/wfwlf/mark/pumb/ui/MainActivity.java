package com.wfwlf.mark.pumb.ui;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.wfwlf.mark.pumb.NetValues;
import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.adapter.MarkAdapter;
import com.wfwlf.mark.pumb.bean.Site;
import com.wfwlf.mark.pumb.util.CommonUtils;
import com.wfwlf.mark.pumb.volley.BaseVO;
import com.wfwlf.mark.pumb.volley.MyErrorListener;
import com.wfwlf.mark.pumb.volley.MyReponseListener;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener,StationPop.DianpopListner {

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.person_iv)
    AutoRelativeLayout personIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.header)
    AutoRelativeLayout header;
    @BindView(R.id.more)
    AutoLinearLayout more;
    @BindView(R.id.lv_pump)
    ListView lvPump;
    @BindView(R.id.ll_pump_list)
    AutoLinearLayout llPumpList;
    @BindView(R.id.rl_info)
    AutoRelativeLayout rlInfo;
    StationPop.DianpopListner dianpopListner;
    private LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private static final int accuracyCircleFillColor = 0xAAFFFF88;
    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
    private SensorManager mSensorManager;
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;


    BaiduMap mBaiduMap;
    NetValues netValues;
    // UI相关
    OnCheckedChangeListener radioButtonListener;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private float direction;
    LatLng northeast;
    LatLng southwest;
    List<LatLng> latLngs;
    private Point mScreenCenterPoint;
    private int flag = 1;
    private String scid = "-1";
    MarkAdapter shopAdapter;
    List<Site.DataBean> mdata=new ArrayList<>();
    float zoom=0;
    StationPop stationPop;
    // 初始化全局 bitmap 信息，不用时及时 recycle
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        dianpopListner=this;
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        mCurrentMode = LocationMode.NORMAL;
        netValues = NetValues.getInstance(this);
        shopAdapter = new MarkAdapter(this);
        latLngs = new ArrayList<>();
        lvPump.setAdapter(shopAdapter);
        lvPump.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CommonUtils.startActivity(MainActivity.this, PumpDetailActivity.class,mdata.get(position).getCode(),mdata.get(position).getName());
            }
        });
        initdata();
        //加载so文件



        mBaiduMap = mMapView.getMap();
        mMapView.showZoomControls(false);
        mBaiduMap.showMapPoi(false);
        mBaiduMap.setTrafficEnabled(false);
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                float zoom = mapStatus.zoom;

                if(Math.abs(MainActivity.this.zoom-zoom)>0.000001){

                    if(zoom>14.5f){
                        mBaiduMap.showMapPoi(true);
                    }else {
                        mBaiduMap.showMapPoi(false);
                    }
                    MainActivity.this.zoom =zoom;
                    Log.d("zoom","缩放起了变化，现在缩放等级为"+zoom);
                }
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

            }
        });


        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//        // 定位初始化
//        mLocClient = new LocationClient(this);
//        mLocClient.registerLocationListener(myListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
//        mLocClient.setLocOption(option);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Site.DataBean listBean = (Site.DataBean) marker.getExtraInfo().getSerializable("BEAN");
                if(listBean.getType()==1){
                    CommonUtils.startActivity(MainActivity.this, PumpDetailActivity.class,listBean.getCode(),listBean.getName());
                }else if(listBean.getType()==2){
                    if(listBean.getQty()>1){
                        List<Site.DataBean> mdata=new ArrayList<>();
                        String[] codes =listBean.getCode().split(",");
                        String[]  names=listBean.getNickName().split(",");
                        for(int i=0;i<codes.length;i++){
                            Site.DataBean dataBean=new Site.DataBean();
                            dataBean.setCode(codes[i]);
                            if(names.length>i){
                                dataBean.setName(names[i]);
                            }
                            mdata.add(dataBean);
                        }
                        stationPop=new StationPop(MainActivity.this,dianpopListner,mdata);
                        stationPop.showAtLocation(getWindow().getDecorView(), Gravity.CENTER,0,0);
//                        Toast.makeText(MainActivity.this, ""+listBean.getCode()+listBean.getNickName(), Toast.LENGTH_SHORT).show();
                    }else {
                        CommonUtils.startActivity(MainActivity.this, StationDetailActivity.class,listBean.getCode(),listBean.getNickName());
                    }
                }


                return false;
            }
        });
        List<Marker> markers = mBaiduMap.getMarkersInBounds(mBaiduMap.getMapStatus().bound);
//        mLocClient.start();
    }

    private void initdata() {
        netValues.get_site_list(new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                Site site=(Site) arg0;
                mdata=site.getData();
                shopAdapter.setMdata(mdata);
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (Site.DataBean bean : mdata) {
                    String[] position=bean.getCoordinate().split(",");
                    double lng=Double.parseDouble(position[0]);
                    double lat=Double.parseDouble(position[1]);
                    builder.include(new LatLng(lat,lng));
                    LatLng latLng = new LatLng(lat, lng);
                    Bundle bundle = new Bundle();
                    latLngs.add(latLng);
                    bundle.putSerializable("BEAN", bean);
                    View view = View.inflate(getApplicationContext(), R.layout.item_bean, null);
                    TextView tView = (TextView)view.findViewById(R.id.tv_name);
                    tView.setText(bean.getName()+ "");
                    //将View转化为Bitmap
                    BitmapDescriptor descriptor = BitmapDescriptorFactory.fromView(view);
                    OverlayOptions options = new MarkerOptions().position(latLng).icon(descriptor).extraInfo(bundle).draggable(true);
                    Marker marker = (Marker) mBaiduMap.addOverlay(options);
                }

                for(LatLng latLng : latLngs){
                    builder = builder.include(latLng);
                }
                LatLngBounds latlngBounds = builder.build();
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds,mMapView.getWidth(),mMapView.getHeight());
                mBaiduMap.animateMapStatus(u);
                getwlaction();
//                mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
//                    @Override
//                    public void onMapLoaded() {
//                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//                        for(LatLng latLng : latLngs){
//                            builder = builder.include(latLng);
//                        }
//                        LatLngBounds latlngBounds = builder.build();
//                        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds,mMapView.getWidth(),mMapView.getHeight());
//                        mBaiduMap.animateMapStatus(u);
//                    }
//                });


            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });


        getwlaction();
    }

    private void getwlaction() {
        netValues.get_w_station_map_list(new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                Site site=(Site) arg0;
                mdata=site.getData();
                shopAdapter.setMdata(mdata);
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (Site.DataBean bean : mdata) {
                    String[] position=bean.getCoordinate().split(",");
                    if(position!=null&&position.length>1){
                        double lng=Double.parseDouble(position[0]);
                        double lat=Double.parseDouble(position[1]);
                        builder.include(new LatLng(lat,lng));
                        LatLng latLng = new LatLng(lat, lng);
                        Bundle bundle = new Bundle();
                        latLngs.add(latLng);
                        bundle.putSerializable("BEAN", bean);
                        View view = View.inflate(getApplicationContext(), R.layout.item_bean, null);
                        TextView tView = (TextView)view.findViewById(R.id.tv_name);
                        ImageView imageView = (ImageView)view.findViewById(R.id.iv_position);
                        imageView.setImageResource(R.mipmap.w_position);
                        tView.setText(bean.getName()+ "");
                        //将View转化为Bitmap
                        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromView(view);
                        OverlayOptions options = new MarkerOptions().position(latLng).icon(descriptor).extraInfo(bundle).draggable(true);
                        Marker marker = (Marker) mBaiduMap.addOverlay(options);
                    }

                }

                for(LatLng latLng : latLngs){
                    builder = builder.include(latLng);
                }
                LatLngBounds latlngBounds = builder.build();
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds,mMapView.getWidth(),mMapView.getHeight());
                mBaiduMap.animateMapStatus(u);
//                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(builder.build());
//
//                mBaiduMap.setMapStatus(u);
//
//
//                LatLng llCentre = mBaiduMap.getMapStatus().target;
//                MapStatus.Builder builder1 = new MapStatus.Builder();
//                builder1.target(llCentre )//缩放中心点
//                        .zoom(14.5f);
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory
//                        .newMapStatus(builder1.build()));
//                mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
//                    @Override
//                    public void onMapLoaded() {
//                        Toast.makeText(MainActivity.this, "11111", Toast.LENGTH_SHORT).show();
//                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//                        for(LatLng latLng : latLngs){
//                            builder = builder.include(latLng);
//                        }
//                        LatLngBounds latlngBounds = builder.build();
//                        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds,mMapView.getWidth(),mMapView.getHeight());
//                        mBaiduMap.animateMapStatus(u);
//                    }
//                });

            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @OnClick({R.id.person_iv, R.id.btn_go_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_iv:
                CommonUtils.startActivity(this, AccountActivity.class);
                break;
            case R.id.btn_go_list:
                CommonUtils.startActivity(this, SitelistActivity.class);
                break;
        }
    }

    @Override
    public void confirm(String stationid,String name) {
        CommonUtils.startActivity(MainActivity.this, StationDetailActivity.class,stationid,name);
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(12.5f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                northeast = mBaiduMap.getMapStatus().bound.northeast;
                southwest = mBaiduMap.getMapStatus().bound.southwest;
//                initOverlay(northeast.longitude + "," + northeast.latitude, southwest.longitude + "," + southwest.latitude);

                mBaiduMap.setOnMapRenderCallbadk(new BaiduMap.OnMapRenderCallback() {
                    @Override
                    public void onMapRenderFinished() {


                        northeast = mBaiduMap.getMapStatus().bound.northeast;
                        southwest = mBaiduMap.getMapStatus().bound.southwest;
//                        initOverlay(northeast.longitude + "," + northeast.latitude, southwest.longitude + "," + southwest.latitude);

                        Log.d("srgslocation", "ne:" + northeast.latitude + "-----" + northeast.longitude);
                        Log.d("srgslocation", "sw:" + southwest.latitude + "-----" + southwest.longitude);

                    }
                });
            }


        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


    private void setUserMapCenter(double lat,double lon) {
        Log.v("pcw","setUserMapCenter : lat : "+ lat+" lon : " + lon);
        LatLng cenpt = new LatLng(lat,lon);
//定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
//定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
//改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }
    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
//        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }


}
