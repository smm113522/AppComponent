# AppComponent
jenkins 打包配置，组件化+模块化 demo一波
[]()

设置透明度（这是窗体本身的透明度，非背景）
 
 
1 WindowManager.LayoutParams lp=getWindow().getAttributes();  
 
2 lp.alpha=0.3f;  
 
3 getWindow().setAttributes(lp); 
 
alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明
 
 
设置黑暗度
 
 
1 WindowManager.LayoutParams lp=getWindow().getAttributes();  
 
2 lp.dimAmount=0.5f;  
 
3 getWindow().setAttributes(lp);  
 
4 getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); 
 
dimAmount在0.0f和1.0f之间，0.0f完全不暗，1.0f全暗
 
 
设置背景模糊
 
 
1 getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,   
 
2 WindowManager.LayoutParams.FLAG_BLUR_BEHIND); 
 
以上设置对dialog对话框同样有效


#ck-android-driver-app

2018年3月19号 进行 开发
1，添加net lib 和 style lib 添加

2，网络判断 app.java 中


## 用到的第三方

1，时间选择器

2，身份证

3，上传图片

4，设置价格

5，定位和上报，地图显示 （高德地图）


## 会使用

http://p.codekk.com/detail/Android/addappcn/android-pickers
http://p.codekk.com/detail/Android/wanliyang1990/AdViewPager

showdoc

https://www.showdoc.cc/web/#/help?page_id=14
https://github.com/star7th/showdoc

--
https://github.com/tbruyelle/RxPermissions


圆角

https://github.com/Kyogirante/RoundPictureDemo/blob/master/app/src/main/java/com/roundpicture/demo/MainActivity.java



接口地址

本地接口地址

签名
c409806a9d07878c6474579222844fb0
7B:40:5D:6F:41:F5:BB:72:E1:16:DA:0B:AC:B8:9B:FE:A6:B4:87:58

包名：com.ck.driver


https://blog.csdn.net/lxj1137800599/article/details/78095790


修改字体
https://www.zhihu.com/question/38615247
https://blog.csdn.net/pan861190079/article/details/72773549


司机端

- 接单界面 我来报价、车主出价的功能类似，希望合并
- 接单讨价还价详情界面，希望更直观的看到 客户出价和司机出价，对比排列，希望 价格信息先显示出来，
订单信息可以放在下面（既现有页面 详情和价格位置对掉）
- 我的订单中 状态的tab 页，表示的订单状态需要精简
- 司机的起运状态，是由系统后台监控，也就是说司机填完车辆备注单后就认为司机已经收到车进入发运状态
- 缺少司机结算的流程
- 增加订单结算 帐期


1,个人界面
2，登录
3，注册界面
4，认证界面
5，我的订单中添加备注界面。

腾讯应用宝：
账号：3285450671
密码：chuangke@

高德开放平台(关联的QQ)
账号：3285450671
密码：chuangke@

https://github.com/koush/AndroidAsync

https://github.com/vilyever/AndroidSocketClient

https://github.com/xuuhaoo/OkSocket

https://github.com/Curzibn/Luban

https://github.com/shaohui10086/ShareUtil

1
/**
 * 车辆交接状态 括号内为按钮名称
 *  0：未收车（去收车） 1:通知收车 （收到车） 2：已收车（起运---订单状态变为带交还） 3：已起运（上门交车）
 *  4：准备送达（送达）5 已送达（客户端显示‘确认完成’）--按钮消失 等待用户确认 订单状态不变
 *  6：已确认--订单状态变为已完成
 */
private Integer vehicleStatus;

/**
 * 长途费：所有的长途费用按照 匹配中的单价 * 车辆数
 * 短驳费：1.自行提送不收费，2.整车搬运判断是否有城市中心，3.闯客代价判断是否有城市中心，没有直接报错，有判断距离是否在运送范围内， * 车辆数
 * 附加费：通过车辆类型累加费用
 * 总金额：以上累加 不含折扣，和发票
 * 发票费：初始创建时没有
 * 优惠金额：初始创建时没有
 * 应付金额：总金额 算上优惠和发票所得
 * 实付金额：支付所收金额
 */
Long longAmount = 0L; // 长途费
Long shortAmount = 0L; // 短驳费
Long extraAmount = 0L; // 附加费
Long amount = 0L; // 总金额
Long invoiceAmount = 0L; // 发票费
Long discountAmount = 0L; // 优惠金额
Long preRealAmount = 0L; // 应付金额
Long realAmount = 0L; // 实际金额



https://juejin.im/post/5af26b51f265da0b93484904




正式下载地址

测试下载地址
https://www.pgyer.com/hAyu


自定义键盘
 Keyboard 和 KeyboardView .
private final View.OnFocusChangeListener editorFocusChangeListener = new View.OnFocusChangeListener() {
  @Override
  public void onFocusChange(final View v, boolean hasFocus) {
    if (v instanceof EditText) {
      if (hasFocus) {
       v.postDelayed(new Runnable() {
          @Override
          public void run() {
            showSoftKeyboard((EditText) v);
          }
        },300);
      } else {
        hideSoftKeyboard();
      }
    }
  }
};
 
public void showSoftKeyboard(EditText editText) {
  mRootView.addOnLayoutChangeListener(mOnLayoutChangeListener);
  BaseKeyboard keyboard = getBindKeyboard(editText);
  if (keyboard == null) {
    Log.e(TAG, "edit text not bind to keyboard");
    return;
  }
  keyboard.setEditText(editText);
  keyboard.setNextFocusView(mKeyboardWithSearchView.getEditText());
  initKeyboard(keyboard);
  ...
}
特殊字符过滤
https://blog.csdn.net/hepann44/article/details/53995676
editview 设置
和setFilters 设置
1.


返回到桌面

 Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);

SVG基础教程

Dagger


AppID: wxd837dfea6763eda7
AppSecret：8f9ee0cfde1d4238db31e28eced38ba2


模版是这个创客行司机端 3.0 开始实施。。

之后再看看就行了。。
