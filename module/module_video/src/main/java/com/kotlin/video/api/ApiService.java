package com.kotlin.video.api;



import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**

 * 文件名:    ApiService
 * 创建时间:  2019-03-27 on 14:55
 * 描述:     TODO 声明接口
 *
 */

public interface ApiService {

    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    String JUE_JIN_BASE_URL = "http://timeline-merger-ms.juejin.im/v1/";

    /**
     * 测试接口
     */
    @GET("news/latest")
    Observable<String> news();

    /**
     * 掘金接口
     */
    @GET("get_entry_by_timeline")
    Observable<String> jueJin(@Query("category") String category, @Query("limit") String limit,
                                  @Query("src") String src);

    @GET("download/{id}")
    Call<ResponseBody> downloadTorrentFile(
            @Path("id") long id
    );

}
