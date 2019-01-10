package com.wfwlf.mark.pumb.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.wfwlf.mark.pumb.R;
import com.wfwlf.mark.pumb.adapter.MarkAdapter;
import com.wfwlf.mark.pumb.bean.Site;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StationPop extends PopupWindow {
    private final ViewHolder vh;
    View mMenuView;
    DianpopListner dianpopListner;
    MarkAdapter markAdapter;
    public StationPop(final Context context , final DianpopListner dianpopListner, final List<Site.DataBean> mdata) {
        super(context);
        this.dianpopListner=dianpopListner;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.view_pick_pop, null);
        AutoUtils.autoSize(mMenuView);
        vh = new ViewHolder(mMenuView);
        mMenuView.setTag(vh);
        markAdapter=new MarkAdapter(context);
        markAdapter.setMdata(mdata);
        vh.RvStation.setAdapter(markAdapter);
        vh.RvStation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dianpopListner.confirm(mdata.get(position).getCode(),mdata.get(position).getNickName());
                dismiss();
            }
        });

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
//            设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//            设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.pick_animstyle);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(80000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if( v.getHeight()>vh.RvStation.getTop()){
                   dismiss();
                }
                return true;
            }
        });
    }
public interface  DianpopListner{
        void confirm(String stationid,String name);
    }
    static class ViewHolder {
        @BindView(R.id.rl_station)
        ListView RvStation;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
