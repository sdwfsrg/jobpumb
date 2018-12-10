package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

/**
 * Created by marksong on 2018/12/8.
 */

public class Token extends BaseVO {
    /**
     * data : at.crk6igzv7nroaulf9ydq1xnw5657hrc4-2kpjvotcps-02mdgti-bmjvxvzbl
     * errCode : 0
     * info : 1
     * success : true
     * userData : 1
     */

    private String data;
    private int errCode;
    private int info;
    private boolean success;
    private int userData;

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
}
