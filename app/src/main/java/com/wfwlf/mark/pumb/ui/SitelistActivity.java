package com.wfwlf.mark.pumb.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
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
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marksong on 2019/1/3.
 */

public class SitelistActivity extends BaseActivity {

    @BindView(R.id.return_iv)
    AutoRelativeLayout returnIv;
    @BindView(R.id.rl_pumb)
    NoScrollListView rlPumb;
    @BindView(R.id.rl_watersite)
    NoScrollListView rlWatersite;
    MarkAdapter shopAdapter,shopAdapter1;
    List<Site.DataBean> mdata=new ArrayList<>();
    List<Site.DataBean> mdata1=new ArrayList<>();
    NetValues netValues;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumb_list);
        ButterKnife.bind(this);
        netValues = NetValues.getInstance(this);
        shopAdapter = new MarkAdapter(this);
        shopAdapter1 = new MarkAdapter(this);
        rlPumb.setAdapter(shopAdapter);
        rlWatersite.setAdapter(shopAdapter1);
        rlPumb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CommonUtils.startActivity(SitelistActivity.this, PumpDetailActivity.class,mdata.get(position).getCode(),mdata.get(position).getName());
            }
        });
        rlWatersite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CommonUtils.startActivity(SitelistActivity.this,StationDetailActivity.class,mdata1.get(position).getCode(),mdata1.get(position).getNickName());
            }
        });
        netValues.get_site_list(new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                Site site=(Site) arg0;
                mdata=site.getData();
                shopAdapter.setMdata(mdata);
            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
        netValues.get_w_station_list(new MyReponseListener() {
            @Override
            public void onResponse(BaseVO arg0) {
                Site site=(Site) arg0;
                mdata1=site.getData();
                shopAdapter1.setMdata(mdata1);
            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });
    }

    @Override
    protected void saveToGallery() {

    }

    @OnClick(R.id.return_iv)
    public void onViewClicked() {
        finish();
    }
}
