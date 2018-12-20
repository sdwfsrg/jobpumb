package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

import java.util.List;

/**
 * Created by marksong on 2018/11/30.
 */

public class Device extends BaseVO {


    /**
     * data : [{"deviceName":"电机1B","currentU":"0.00","currentV":"0.00","currentW":"0.00","startTime":"2018-11-29 16:24:38","motorStatus":"0","runTime":"6天 18:19:10","totalRunTime":"12天 02:39:10"},{"deviceName":"电机2B","currentU":"0.00","currentV":"0.00","currentW":"0.00","startTime":"2018-11-29 16:24:38","motorStatus":"0","runTime":"6天 18:19:10","totalRunTime":"6天 18:19:10"},{"deviceName":"电机3B","currentU":"0.00","currentV":"0.00","currentW":"0.00","startTime":"2018-11-29 16:24:38","motorStatus":"0","runTime":"6天 18:19:10","totalRunTime":"6天 18:19:10"},{"deviceName":"电机4B","currentU":"0.00","currentV":"0.00","currentW":"0.00","startTime":"2018-11-29 16:24:38","motorStatus":"0","runTime":"13:18:10","totalRunTime":"13:18:10"}]
     * errCode : 0
     * info : null
     * userData : null
     * success : true
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
         * deviceName : 电机1B
         * currentU : 0.00
         * currentV : 0.00
         * currentW : 0.00
         * startTime : 2018-11-29 16:24:38
         * motorStatus : 0
         * runTime : 6天 18:19:10
         * totalRunTime : 12天 02:39:10
         * controlModel
         */

        private int controlModel;
        private String deviceName;
        private String currentU;
        private String currentV;
        private String currentW;
        private String startTime;
        private String motorStatus;
        private String runTime;
        private String totalRunTime;

        public int getControlModel() {
            return controlModel;
        }

        public void setControlModel(int controlModel) {
            this.controlModel = controlModel;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getCurrentU() {
            return currentU;
        }

        public void setCurrentU(String currentU) {
            this.currentU = currentU;
        }

        public String getCurrentV() {
            return currentV;
        }

        public void setCurrentV(String currentV) {
            this.currentV = currentV;
        }

        public String getCurrentW() {
            return currentW;
        }

        public void setCurrentW(String currentW) {
            this.currentW = currentW;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getMotorStatus() {
            return motorStatus;
        }

        public void setMotorStatus(String motorStatus) {
            this.motorStatus = motorStatus;
        }

        public String getRunTime() {
            return runTime;
        }

        public void setRunTime(String runTime) {
            this.runTime = runTime;
        }

        public String getTotalRunTime() {
            return totalRunTime;
        }

        public void setTotalRunTime(String totalRunTime) {
            this.totalRunTime = totalRunTime;
        }
    }
}
