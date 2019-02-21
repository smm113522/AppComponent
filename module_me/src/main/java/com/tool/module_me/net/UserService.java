package com.tool.module_me.net;

import com.tool.net.BaseResponse;
import com.tool.module_me.model.BannerEntity;
import com.tool.module_me.model.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserService {

    @Headers("Cache-Control: public, max-age=3600")
    @GET("appapi/index/banner/id/1?cmd=home_slider_top&limit=5")
    Observable<BaseResponse<List<BannerEntity>>> getData();

}
