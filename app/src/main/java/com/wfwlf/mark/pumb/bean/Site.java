package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marksong on 2018/11/30.
 */

public class Site extends BaseVO {

    /**
     * data : [{"code":"297e6218674dd7b701674e1c07030005","coordinate":"119.194023,36.711903","name":"软件园"},{"code":"297e6218674dd7b701674e1c07030006","coordinate":"119.105018,36.703197","name":"潍坊火车站"}]
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
         * code : 297e6218674dd7b701674e1c07030005
         * coordinate : 119.194023,36.711903
         * name : 软件园
         */

        private String code;
        private String coordinate;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(String coordinate) {
            this.coordinate = coordinate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
