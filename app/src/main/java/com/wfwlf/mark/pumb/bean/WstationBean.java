package com.wfwlf.mark.pumb.bean;

import com.wfwlf.mark.pumb.volley.BaseVO;

/**
 * Created by marksong on 2019/1/4.
 */

public class WstationBean extends BaseVO {

    /**
     * data : {"ammoniaNitrogen":{"code":"ammoniaNitrogen","name":"氨氮","status":1,"unit":"","value":0.05},"chlorineDioxide":{"code":"chlorineDioxide","name":"","status":1,"unit":"","value":0.05},"cod":{"code":"cod","name":"COD","status":1,"unit":"","value":0.05},"codmn":{"code":"codmn","name":"CODmn","status":"D","unit":"","value":36.5},"conductivity":{"code":"conductivity","name":"","status":"D","unit":"","value":36.5},"dataTime":"2018-12-31 17:55:00","dissolvedOxygen":{"code":"dissolvedOxygen","name":"","status":1,"unit":"","value":0.05},"phValue":{"code":"phValue","name":"PH值","status":"D","unit":"","value":7.5},"totalNitrogen":{"code":"totalNitrogen","name":"总氮","status":1,"unit":"","value":0.05},"totalPhosphorus":{"code":"totalPhosphorus","name":"","status":1,"unit":"","value":0.05},"turbidity":{"code":"turbidity","name":"浊物","status":"D","unit":"","value":36.5},"volume":{"code":"volume","name":"污泥","status":"D","unit":"","value":36.5},"waterTemp":{"code":"waterTemp","name":"水温","status":"D","unit":"","value":36.5}}
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
         * ammoniaNitrogen : {"code":"ammoniaNitrogen","name":"氨氮","status":1,"unit":"","value":0.05}
         * chlorineDioxide : {"code":"chlorineDioxide","name":"","status":1,"unit":"","value":0.05}
         * cod : {"code":"cod","name":"COD","status":1,"unit":"","value":0.05}
         * codmn : {"code":"codmn","name":"CODmn","status":"D","unit":"","value":36.5}
         * conductivity : {"code":"conductivity","name":"","status":"D","unit":"","value":36.5}
         * dataTime : 2018-12-31 17:55:00
         * dissolvedOxygen : {"code":"dissolvedOxygen","name":"","status":1,"unit":"","value":0.05}
         * phValue : {"code":"phValue","name":"PH值","status":"D","unit":"","value":7.5}
         * totalNitrogen : {"code":"totalNitrogen","name":"总氮","status":1,"unit":"","value":0.05}
         * totalPhosphorus : {"code":"totalPhosphorus","name":"","status":1,"unit":"","value":0.05}
         * turbidity : {"code":"turbidity","name":"浊物","status":"D","unit":"","value":36.5}
         * volume : {"code":"volume","name":"污泥","status":"D","unit":"","value":36.5}
         * waterTemp : {"code":"waterTemp","name":"水温","status":"D","unit":"","value":36.5}
         */

        private CurrentBean ammoniaNitrogen;
        private CurrentBean chlorineDioxide;
        private CurrentBean cod;
        private CurrentBean codmn;
        private CurrentBean conductivity;
        private String dataTime;
        private CurrentBean dissolvedOxygen;
        private CurrentBean phValue;
        private CurrentBean totalNitrogen;
        private CurrentBean totalPhosphorus;
        private CurrentBean turbidity;
        private CurrentBean volume;
        private CurrentBean waterTemp;

        public CurrentBean getAmmoniaNitrogen() {
            return ammoniaNitrogen;
        }

        public void setAmmoniaNitrogen(CurrentBean ammoniaNitrogen) {
            this.ammoniaNitrogen = ammoniaNitrogen;
        }

        public CurrentBean getChlorineDioxide() {
            return chlorineDioxide;
        }

        public void setChlorineDioxide(CurrentBean chlorineDioxide) {
            this.chlorineDioxide = chlorineDioxide;
        }

        public CurrentBean getCod() {
            return cod;
        }

        public void setCod(CurrentBean cod) {
            this.cod = cod;
        }

        public CurrentBean getCodmn() {
            return codmn;
        }

        public void setCodmn(CurrentBean codmn) {
            this.codmn = codmn;
        }

        public CurrentBean getConductivity() {
            return conductivity;
        }

        public void setConductivity(CurrentBean conductivity) {
            this.conductivity = conductivity;
        }

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }

        public CurrentBean getDissolvedOxygen() {
            return dissolvedOxygen;
        }

        public void setDissolvedOxygen(CurrentBean dissolvedOxygen) {
            this.dissolvedOxygen = dissolvedOxygen;
        }

        public CurrentBean getPhValue() {
            return phValue;
        }

        public void setPhValue(CurrentBean phValue) {
            this.phValue = phValue;
        }

        public CurrentBean getTotalNitrogen() {
            return totalNitrogen;
        }

        public void setTotalNitrogen(CurrentBean totalNitrogen) {
            this.totalNitrogen = totalNitrogen;
        }

        public CurrentBean getTotalPhosphorus() {
            return totalPhosphorus;
        }

        public void setTotalPhosphorus(CurrentBean totalPhosphorus) {
            this.totalPhosphorus = totalPhosphorus;
        }

        public CurrentBean getTurbidity() {
            return turbidity;
        }

        public void setTurbidity(CurrentBean turbidity) {
            this.turbidity = turbidity;
        }

        public CurrentBean getVolume() {
            return volume;
        }

        public void setVolume(CurrentBean volume) {
            this.volume = volume;
        }

        public CurrentBean getWaterTemp() {
            return waterTemp;
        }

        public void setWaterTemp(CurrentBean waterTemp) {
            this.waterTemp = waterTemp;
        }

        public static class CurrentBean {
            /**
             * code : ammoniaNitrogen
             * name : 氨氮
             * status : 1
             * unit :
             * value : 0.05
             */

            private String code;
            private String name;
            private int status;
            private String unit;
            private double value;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }
        }

    }
}
