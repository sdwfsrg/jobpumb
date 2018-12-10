package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

public class LoginBean extends BaseVO {


    /**
     * data : {"deptName":"测试内容4syc","reason":1,"sessionId":"B139078816C719B0E94B23A7D68AE02D","success":true,"userId":"1","userName":"超级管理员"}
     * errCode : 0
     * info : 1
     * success : true
     * userData : 1
     */

    private DataBean data;
    private int errCode;
    private int info;
    private boolean success;
    private int userData;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

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

    public int getUserData() {
        return userData;
    }

    public void setUserData(int userData) {
        this.userData = userData;
    }

    public static class DataBean {
        /**
         * deptName : 测试内容4syc
         * reason : 1
         * sessionId : B139078816C719B0E94B23A7D68AE02D
         * success : true
         * userId : 1
         * userName : 超级管理员
         */

        private String deptName;
        private String reason;
        private String sessionId;
        private boolean success;
        private String userId;
        private String userName;

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
