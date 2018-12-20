package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

import java.util.List;

/**
 * Created by marksong on 2018/11/30.
 */

public class SiteInfo extends BaseVO {


    /**
     * data : [{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e101676264bb1c0133","voltageAll":0,"totalWorkRate":0,"workRate":0.5,"waterFlow":1,"totalWaterFlow":0,"waterLevel":8,"voltageU":379,"voltageV":325,"voltageW":354,"dataTime":"2018-11-30 10:13:47"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e101676260d36a011d","voltageAll":0,"totalWorkRate":0,"workRate":25.3,"waterFlow":55,"totalWaterFlow":0,"waterLevel":1,"voltageU":368,"voltageV":391,"voltageW":356,"dataTime":"2018-11-30 10:09:31"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e101676260b3230107","voltageAll":0,"totalWorkRate":0,"workRate":7,"waterFlow":23,"totalWaterFlow":0,"waterLevel":5,"voltageU":319,"voltageV":349,"voltageW":349,"dataTime":"2018-11-30 10:09:23"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e101676260897800f1","voltageAll":0,"totalWorkRate":0,"workRate":8,"waterFlow":22,"totalWaterFlow":0,"waterLevel":6,"voltageU":354,"voltageV":347,"voltageW":357,"dataTime":"2018-11-30 10:09:13"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e10167626007b200db","voltageAll":0,"totalWorkRate":0,"workRate":8,"waterFlow":23,"totalWaterFlow":0,"waterLevel":7,"voltageU":349,"voltageV":316,"voltageW":312,"dataTime":"2018-11-30 10:08:39"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e10167625fa6c800c5","voltageAll":0,"totalWorkRate":0,"workRate":10,"waterFlow":20,"totalWaterFlow":0,"waterLevel":8,"voltageU":321,"voltageV":319,"voltageW":321,"dataTime":"2018-11-30 10:08:15"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e10167625e4fc400af","voltageAll":0,"totalWorkRate":0,"workRate":11,"waterFlow":10,"totalWaterFlow":0,"waterLevel":9,"voltageU":334,"voltageV":367,"voltageW":364,"dataTime":"2018-11-30 10:06:47"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e10167625dff530099","voltageAll":0,"totalWorkRate":0,"workRate":0.5,"waterFlow":1,"totalWaterFlow":0,"waterLevel":7,"voltageU":396,"voltageV":341,"voltageW":387,"dataTime":"2018-11-30 10:06:26"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e1016762522a490083","voltageAll":0,"totalWorkRate":0,"workRate":12,"waterFlow":42,"totalWaterFlow":0,"waterLevel":3,"voltageU":321,"voltageV":373,"voltageW":379,"dataTime":"2018-11-30 09:53:31"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e1016762510e0f006d","voltageAll":0,"totalWorkRate":0,"workRate":5,"waterFlow":11,"totalWaterFlow":0,"waterLevel":7,"voltageU":354,"voltageV":319,"voltageW":321,"dataTime":"2018-11-30 09:52:18"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e10167624df0280057","voltageAll":0,"totalWorkRate":0,"workRate":2,"waterFlow":12,"totalWaterFlow":0,"waterLevel":6,"voltageU":354,"voltageV":346,"voltageW":364,"dataTime":"2018-11-30 09:48:54"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e10167624ce52c0041","voltageAll":0,"totalWorkRate":0,"workRate":2,"waterFlow":20,"totalWaterFlow":0,"waterLevel":5,"voltageU":389,"voltageV":315,"voltageW":318,"dataTime":"2018-11-30 09:47:45"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e10167624aff11002b","voltageAll":0,"totalWorkRate":0,"workRate":6,"waterFlow":38,"totalWaterFlow":0,"waterLevel":7,"voltageU":359,"voltageV":347,"voltageW":349,"dataTime":"2018-11-30 09:45:41"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a676249e101676249ea120015","voltageAll":0,"totalWorkRate":0,"workRate":3,"waterFlow":12,"totalWaterFlow":0,"waterLevel":9,"voltageU":354,"voltageV":346,"voltageW":397,"dataTime":"2018-11-30 09:44:30"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a675e91d401675e9413540030","voltageAll":0,"totalWorkRate":0,"workRate":8,"waterFlow":35,"totalWaterFlow":0,"waterLevel":7,"voltageU":339,"voltageV":349,"voltageW":325,"dataTime":"2018-11-29 16:27:01"},{"pumpId":"297e6218674dd7b701674e1c07030006","pumpDataId":"8a9cee0a675e91d401675e91e850001a","voltageAll":0,"totalWorkRate":0,"workRate":6,"waterFlow":18,"totalWaterFlow":0,"waterLevel":8,"voltageU":365,"voltageV":380,"voltageW":376,"dataTime":"2018-11-29 16:24:39"}]
     * errCode : 0
     * info : null
     * userData : null
     * success : false
     */

    private int errCode;
    private Object info;
    private Object userData;
    private boolean success;
    private List<DataBean> data;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
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
         * pumpId : 297e6218674dd7b701674e1c07030006
         * pumpDataId : 8a9cee0a676249e101676264bb1c0133
         * voltageAll : 0
         * totalWorkRate : 0
         * workRate : 0.5
         * waterFlow : 1
         * totalWaterFlow : 0
         * waterLevel : 8
         * voltageU : 379
         * voltageV : 325
         * voltageW : 354
         * dataTime : 2018-11-30 10:13:47
         * controlType
         */


        private int controlType;
        private String pumpId;
        private String pumpDataId;
        private float voltageAll;
        private float totalWorkRate;
        private float workRate;
        private float waterFlow;
        private float totalWaterFlow;
        private float waterLevel;
        private float voltageU;
        private float voltageV;
        private float voltageW;
        private String dataTime;

        public String getPumpId() {
            return pumpId;
        }

        public void setPumpId(String pumpId) {
            this.pumpId = pumpId;
        }

        public int getControlType() {
            return controlType;
        }

        public void setControlType(int controlType) {
            this.controlType = controlType;
        }

        public String getPumpDataId() {
            return pumpDataId;
        }

        public void setPumpDataId(String pumpDataId) {
            this.pumpDataId = pumpDataId;
        }

        public float getVoltageAll() {
            return voltageAll;
        }

        public void setVoltageAll(float voltageAll) {
            this.voltageAll = voltageAll;
        }

        public float getTotalWorkRate() {
            return totalWorkRate;
        }

        public void setTotalWorkRate(float totalWorkRate) {
            this.totalWorkRate = totalWorkRate;
        }

        public float getWorkRate() {
            return workRate;
        }

        public void setWorkRate(float workRate) {
            this.workRate = workRate;
        }

        public float getWaterFlow() {
            return waterFlow;
        }

        public void setWaterFlow(float waterFlow) {
            this.waterFlow = waterFlow;
        }

        public float getTotalWaterFlow() {
            return totalWaterFlow;
        }

        public void setTotalWaterFlow(float totalWaterFlow) {
            this.totalWaterFlow = totalWaterFlow;
        }

        public float getWaterLevel() {
            return waterLevel;
        }

        public void setWaterLevel(float waterLevel) {
            this.waterLevel = waterLevel;
        }

        public float getVoltageU() {
            return voltageU;
        }

        public void setVoltageU(float voltageU) {
            this.voltageU = voltageU;
        }

        public float getVoltageV() {
            return voltageV;
        }

        public void setVoltageV(float voltageV) {
            this.voltageV = voltageV;
        }

        public float getVoltageW() {
            return voltageW;
        }

        public void setVoltageW(float voltageW) {
            this.voltageW = voltageW;
        }

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }
    }
}
