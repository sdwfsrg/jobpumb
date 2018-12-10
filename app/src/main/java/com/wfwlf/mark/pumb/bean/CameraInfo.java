package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marksong on 2018/12/3.
 */

public class CameraInfo extends BaseVO{


    /**
     * data : [{"channel":null,"status":"1","createTime":"20181122","createUserName":"超级管理员","updateUserName":"超级管理员","cameraName":"摄像头1","pumpId":"297e6218674dd7b701674e1c07030006","hlsAddr":"http://hls.open.ys7.com/openlive/870b7b1eec2e4de993321598af8884b1.m3u8","rtmpAddr":"rtmp://rtmp.open.ys7.com/openlive/870b7b1eec2e4de993321598af8884b1","cameraId":"1","remark":"摄像头1","pumpName":"潍坊火车站","createUser":"1","ezopenAddr":"ezopen://open.ys7.com/C61656404/2.live","serial":null,"statusName":"正常","updateUser":"1","updateTime":"2018-11-30 17:39:52"}]
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

    public static class DataBean implements Serializable{
        /**
         * channel : null
         * status : 1
         * createTime : 20181122
         * createUserName : 超级管理员
         * updateUserName : 超级管理员
         * cameraName : 摄像头1
         * pumpId : 297e6218674dd7b701674e1c07030006
         * hlsAddr : http://hls.open.ys7.com/openlive/870b7b1eec2e4de993321598af8884b1.m3u8
         * rtmpAddr : rtmp://rtmp.open.ys7.com/openlive/870b7b1eec2e4de993321598af8884b1
         * cameraId : 1
         * remark : 摄像头1
         * pumpName : 潍坊火车站
         * createUser : 1
         * ezopenAddr : ezopen://open.ys7.com/C61656404/2.live
         * serial : null
         * statusName : 正常
         * updateUser : 1
         * updateTime : 2018-11-30 17:39:52
         */

        private String channel;
        private String status;
        private String createTime;
        private String createUserName;
        private String updateUserName;
        private String cameraName;
        private String pumpId;
        private String hlsAddr;
        private String rtmpAddr;
        private String cameraId;
        private String remark;
        private String pumpName;
        private String createUser;
        private String ezopenAddr;
        private String serial;
        private String statusName;
        private String updateUser;
        private String updateTime;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public String getUpdateUserName() {
            return updateUserName;
        }

        public void setUpdateUserName(String updateUserName) {
            this.updateUserName = updateUserName;
        }

        public String getCameraName() {
            return cameraName;
        }

        public void setCameraName(String cameraName) {
            this.cameraName = cameraName;
        }

        public String getPumpId() {
            return pumpId;
        }

        public void setPumpId(String pumpId) {
            this.pumpId = pumpId;
        }

        public String getHlsAddr() {
            return hlsAddr;
        }

        public void setHlsAddr(String hlsAddr) {
            this.hlsAddr = hlsAddr;
        }

        public String getRtmpAddr() {
            return rtmpAddr;
        }

        public void setRtmpAddr(String rtmpAddr) {
            this.rtmpAddr = rtmpAddr;
        }

        public String getCameraId() {
            return cameraId;
        }

        public void setCameraId(String cameraId) {
            this.cameraId = cameraId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPumpName() {
            return pumpName;
        }

        public void setPumpName(String pumpName) {
            this.pumpName = pumpName;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getEzopenAddr() {
            return ezopenAddr;
        }

        public void setEzopenAddr(String ezopenAddr) {
            this.ezopenAddr = ezopenAddr;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
