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