package com.tool.module_me.net;

import com.tool.net.RetrofitServiceManager;
import com.tool.net.RxSubcriber;
import com.tool.net.RxjavaHelper;

public class ApiService {

    public static void getMeData(RxSubcriber rxSubcriber){
//        RetrofitServiceManager.getInstance().create(UserService.class)
//                .getData()
//                .compose(RxjavaHelper.observeOnMainThread())
//                .subscribe(new RxSubcriber<List<BannerEntity>>(getActivity()) {
//                    @Override
//                    public void onSuccess(List<BannerEntity> bannerEntities) {
//                        Log.d("print", "->" + bannerEntities.toString());
//                        textView.setText(bannerEntities.toString());
//                    }
//                });

        RetrofitServiceManager.getInstance().create(UserService.class)
                .getData()
                .compose(RxjavaHelper.observeOnMainThread())
                .subscribe(rxSubcriber);
    }

}
