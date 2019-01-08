package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

import java.util.List;

/**
 * Created by marksong on 2019/1/4.
 */

public class WstationBean extends BaseVO {

    /**
     * data : [{"data":[{"dataTime":"2019-01-03 09:55:00","dataValue":"0.000"}],"dataTypeName":"PH值"},{"data":[{"dataTime":"2019-01-04 09:55:00","dataValue":"0.000"}],"dataTypeName":"水温"},{"data":[{"dataTime":"2019-01-03 09:55:00","dataValue":"0.000"}],"dataTypeName":"浊度"},{"data":[{"dataTime":"2019-01-04 09:55:00","dataValue":"0.000"}],"dataTypeName":"电导率"},{"data":[{"dataTime":"2019-01-03 09:55:00","dataValue":"0.000"}],"dataTypeName":"溶解氧"},{"data":[{"dataTime":"2019-01-04 09:55:00","dataValue":"0.000"}],"dataTypeName":"CODmn"}]
     * errCode : 0
     * info : 1
     * success : true
     * userData : 1
     */

    private int errCode;
    private int info;
    private boolean success;
    private int userData;
    private List<DataBeanX> data;

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

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : [{"dataTime":"2019-01-03 09:55:00","dataValue":"0.000"}]
         * dataTypeName : PH值
         */

        private String dataTypeName;
        private List<DataBean> data;

        public String getDataTypeName() {
            return dataTypeName;
        }

        public void setDataTypeName(String dataTypeName) {
            this.dataTypeName = dataTypeName;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * dataTime : 2019-01-03 09:55:00
             * dataValue : 0.000
             */

            private String dataTime;
            private String dataValue;

            public String getDataTime() {
                return dataTime;
            }

            public void setDataTime(String dataTime) {
                this.dataTime = dataTime;
            }

            public String getDataValue() {
                return dataValue;
            }

            public void setDataValue(String dataValue) {
                this.dataValue = dataValue;
            }
        }
    }
}
