package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marksong on 2018/12/1.
 */

public class WarnInfo extends BaseVO implements Serializable {

    /**
     * data : [{"pumpId":"297e6218674dd7b701674e1c07030006","deviceId":"8a9cee4f6759155501675919dc970002","ruleId":"8a9cee04675840a00167584c69c40002","warnInfo":"测试信息：出错啦","status":"0","remark":null,"infoId":"1","warnType":"1","warnTime":"2018-11-29 16:24:38","handleUser":"","handleTime":null,"handleOpinion":null}]
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

    public static class DataBean  implements Serializable{
        /**
         * pumpId : 297e6218674dd7b701674e1c07030006
         * deviceId : 8a9cee4f6759155501675919dc970002
         * ruleId : 8a9cee04675840a00167584c69c40002
         * warnInfo : 测试信息：出错啦
         * status : 0
         * remark : null
         * infoId : 1
         * warnType : 1
         * warnTime : 2018-11-29 16:24:38
         * handleUser :
         * handleTime : null
         * handleOpinion : null
         */

        private String pumpId;
        private String deviceId;
        private String ruleId;
        private String warnInfo;
        private String status;
        private Object remark;
        private String infoId;
        private String warnType;
        private String warnTime;
        private String handleUser;
        private Object handleTime;
        private Object handleOpinion;

        public String getPumpId() {
            return pumpId;
        }

        public void setPumpId(String pumpId) {
            this.pumpId = pumpId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getRuleId() {
            return ruleId;
        }

        public void setRuleId(String ruleId) {
            this.ruleId = ruleId;
        }

        public String getWarnInfo() {
            return warnInfo;
        }

        public void setWarnInfo(String warnInfo) {
            this.warnInfo = warnInfo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getInfoId() {
            return infoId;
        }

        public void setInfoId(String infoId) {
            this.infoId = infoId;
        }

        public String getWarnType() {
            return warnType;
        }

        public void setWarnType(String warnType) {
            this.warnType = warnType;
        }

        public String getWarnTime() {
            return warnTime;
        }

        public void setWarnTime(String warnTime) {
            this.warnTime = warnTime;
        }

        public String getHandleUser() {
            return handleUser;
        }

        public void setHandleUser(String handleUser) {
            this.handleUser = handleUser;
        }

        public Object getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(Object handleTime) {
            this.handleTime = handleTime;
        }

        public Object getHandleOpinion() {
            return handleOpinion;
        }

        public void setHandleOpinion(Object handleOpinion) {
            this.handleOpinion = handleOpinion;
        }
    }
}
