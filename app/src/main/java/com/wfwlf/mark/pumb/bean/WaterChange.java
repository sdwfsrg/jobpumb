package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

import java.util.List;

/**
 * Created by marksong on 2018/12/21.
 */

public class WaterChange extends BaseVO{

    /**
     * data : [{"dataTime":"2018-12-20 19:08:48","pumpId":"2c90808667c446b20167c4476dea0001","waterLevel":"0.90"},{"dataTime":"2018-12-20 19:03:51","pumpId":"2c90808667c446b20167c4476dea0001","waterLevel":"0.80"}]
     * info : 1
     * success : true
     */

    private int info;
    private boolean success;
    private List<DataBean> data;

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dataTime : 2018-12-20 19:08:48
         * pumpId : 2c90808667c446b20167c4476dea0001
         * waterLevel : 0.90
         */

        private String dataTime;
        private String pumpId;
        private float waterLevel;

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }

        public String getPumpId() {
            return pumpId;
        }

        public void setPumpId(String pumpId) {
            this.pumpId = pumpId;
        }

        public float getWaterLevel() {
            return waterLevel;
        }

        public void setWaterLevel(float waterLevel) {
            this.waterLevel = waterLevel;
        }
    }
}
