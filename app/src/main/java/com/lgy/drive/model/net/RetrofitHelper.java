package com.lgy.drive.model.net;

import android.support.annotation.NonNull;

import com.lgy.drive.common.Constants;
import com.lgy.drive.model.http.api.UserApi;
import com.lgy.drive.model.net.conveter.CustomGsonConverterFactory;
import com.lgy.drive.utils.LogUtil;
import com.lgy.drive.utils.SystemUtils;
import com.lgy.drive.utils.sp.UserSpUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.android.BuildConfig;

/**
 * Description: RetrofitHelper1
 * Creator: yxc
 * date: 2016/9/21 10:03
 */
public class RetrofitHelper {

    private static OkHttpClient okHttpClient = null;
    private static UserApi userApi;

    public static UserApi getUserApi() {
        initOkHttp();
        if (userApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            userApi = retrofit.create(UserApi.class);
        }
        return userApi;
    }

    private static void initOkHttp() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                builder.addInterceptor(loggingInterceptor);
            }
            File cacheFile = new File(Constants.PATH_CACHE);
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (!SystemUtils.isNetworkConnected()) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                    }
                    String token = UserSpUtils.getToken();
                    request = request.newBuilder().addHeader("token", token).build();
                    Connection connection = chain.connection();
                    Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
                    String requestStartMessage =
                            "--> " + request.method() + ' ' + request.url() + ' ' + protocol(protocol) + request.header("token");
                    LogUtil.d("请求数据类型和链接" + requestStartMessage);
                    Headers headers = request.headers();
                    for (int i = 0, count = headers.size(); i < count; i++) {
                        String name = headers.name(i);
                        // Skip headers from the request body as they are explicitly logged above.
                        if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                            LogUtil.d(name + ": " + headers.value(i));
                        }
                    }
                    int tryCount = 0;
                    Response response = chain.proceed(request);
//                    while (!response.isSuccessful() && tryCount < 3) {
//                        tryCount++;
//                        // retry the request
//                        response = chain.proceed(request);
//                    }
                    // LogUtil.d("请求结果" + response.body().string());
                    if (SystemUtils.isNetworkConnected()) {
                        int maxAge = 0;
                        // 有网络时, 不缓存, 最大保存时长为0
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("Pragma")
                                .build();
                    } else {
                        // 无网络时，设置超时为4周
                        int maxStale = 60 * 60 * 24 * 28;
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .removeHeader("Pragma")
                                .build();
                    }
                    return response;
                }
            };
            //设置缓存
            builder.addNetworkInterceptor(cacheInterceptor);
            builder.addInterceptor(cacheInterceptor);
            builder.cache(cache);
            //设置超时
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);

            okHttpClient = builder.build();
        }
    }

    private static String protocol(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }

    // 封装请求体，可以看到这里和OkHttp的请求体封装基本上是一样的
    @NonNull
    public static MultipartBody.Part imagePart(String suffix,String partName, String path) {
        String mediaType="";
        if(suffix.toLowerCase().equals("png")){
            mediaType="image/png";
        }else  if(suffix.toLowerCase().equals("jpg")){
            mediaType="image/jpg";
        }
        File file = new File(path);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(mediaType), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
}
