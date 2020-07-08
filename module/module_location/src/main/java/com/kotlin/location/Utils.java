//package com.kotlin.location;
//
//import android.content.Context;
//
//import com.tencent.map.geolocation.TencentLocationManager;
//
///*
//
//初始化东西
//key
//5SABZ-BYXCF-XCSJF-NRRBC-EOTNT-IUFZZ
//
// */
//
//public class Utils {
//
//    private Utils() {
//    }
//
//    private Context context;
//    public static Utils utils;
//
//    public static Utils getInstance() {
//        if (utils == null) {
//            utils = new Utils();
//        }
//        return utils;
//    }
//
//    /**
//     * 全局定义，用于定位请求
//     */
//    private TencentLocationManager mLocationManager;
//
//    public void initApp(Context context) {
//        this.context = context;
//        if (isMainProc(context)) {
//            mLocationManager = TencentLocationManager.getInstance(context);
//            // 设置坐标系为 gcj-02, 缺省坐标为 gcj-02, 所以通常不必进行如下调用
//            mLocationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
//        }
//    }
//
//    private boolean isMainProc(Context context) {
//        String packageName = context.getPackageName();
//        //需要对比的不是manifest里的packageName，而是gralde里面的applicationId
//        return packageName.equals("com.daolai");
//    }
//
//}
