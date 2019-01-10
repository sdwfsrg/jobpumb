package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

import java.util.List;

/**
 * Created by marksong on 2019/1/7.
 */

public class NewSWBean extends BaseVO {

    /**
     * data : [{"unit":"测试内容f6yp","code":"phValue","name":"PH值","status":"故障","value":"7.5"},{"unit":"测试内容f6yp","code":"waterTemp","name":"水温","status":"故障","value":"36.500"},{"unit":"测试内容f6yp","code":"turbidity","name":"浊度","status":"故障","value":"1.200"},{"unit":"测试内容f6yp","code":"conductivity","name":"电导率","status":"排放源停运","value":"2.300"},{"unit":"测试内容f6yp","code":"dissolvedOxygen","name":"溶解氧","status":"排放源停运","value":"0.050"},{"unit":"测试内容f6yp","code":"codmn","name":"CODmn","status":"设定值","value":"1.500"}]
     * errCode : 0
     * info : 1
     * success : true
     * userData : 1
     */

    private int errCode;
    private int info;
    private boolean success;
    private int userData;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * unit : 测试内容f6yp
         * code : phValue
         * name : PH值
         * status : 故障
         * value : 7.5
         */

        private String unit;
        private String code;
        private String name;
        private String status;
        private String value;
        private String dataTime;

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
