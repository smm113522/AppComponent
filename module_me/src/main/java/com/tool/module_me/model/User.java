package com.tool.module_me.model;

import java.util.List;

public class User {

    /**
     * reason : 查询成功
     * result : [{"id":"1","province":"安徽"},{"id":"2","province":"澳门"},{"id":"3","province":"北京"},{"id":"4","province":"福建"},{"id":"5","province":"甘肃"},{"id":"6","province":"广东"},{"id":"7","province":"广西"},{"id":"8","province":"贵州"},{"id":"9","province":"海南"},{"id":"10","province":"河北"},{"id":"11","province":"河南"},{"id":"12","province":"黑龙江"},{"id":"13","province":"湖北"},{"id":"14","province":"湖南"},{"id":"15","province":"吉林"},{"id":"16","province":"江苏"},{"id":"17","province":"江西"},{"id":"18","province":"辽宁"},{"id":"19","province":"内蒙古"},{"id":"20","province":"宁夏"},{"id":"21","province":"青海"},{"id":"22","province":"山东"},{"id":"23","province":"山西"},{"id":"24","province":"陕西"},{"id":"25","province":"上海"},{"id":"26","province":"四川"},{"id":"27","province":"台湾"},{"id":"28","province":"天津"},{"id":"29","province":"西藏"},{"id":"30","province":"香港"},{"id":"31","province":"新疆"},{"id":"32","province":"云南"},{"id":"33","province":"浙江"},{"id":"34","province":"重庆"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * province : 安徽
         */

        private String id;
        private String province;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
