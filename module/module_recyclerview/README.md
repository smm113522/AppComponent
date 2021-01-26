## 说明

1，topview
2，可以拖动
3，Skeleton --> https://www.jianshu.com/p/ae348227b92e
4, 引入flexbox流式布局

 implementation  com.google.android:flexbox:1.0.0

第2步:实现FlexboxLayoutManager
FlexboxLayoutManager  flexboxLayoutManager = new FlexboxLayoutManager(mContext, FlexDirection.ROW, FlexWrap.WRAP) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
第3步:为RecyclerView设置setLayoutManager

      recyclerView.setLayoutManager(flexboxLayoutManager);
 