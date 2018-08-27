package framework.jimmy.com.framework.network;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import framework.jimmy.com.framework.config.Const;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofitDemo = null;
    private static Retrofit retrofitApi = null;
    private static int REQUEST_TIMEOUT = 60;
    private static OkHttpClient okHttpClient;

    private static OkHttpClient okHttpClientDemo;

    public static Retrofit getClientDemo(Context context) {

        if (okHttpClientDemo == null)
            initOkHttpDemo(context);

        if (retrofitDemo == null) {
            retrofitDemo = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL_DEMO)
                    .client(okHttpClientDemo)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitDemo;
    }
    public static Retrofit getClientApi(Context context) {

        if (okHttpClient == null)
            initOkHttp(context);

        if (retrofitApi == null) {
            retrofitApi = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL_API)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitApi;
    }

    private static void initOkHttp(final Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("User-Agent", "Informa/1.0 Android/15")
                        .addHeader("Request-Type", "Android")
                        .addHeader("MFLATLON", "-6.227507,106.818741")
                        .addHeader("MFDEVID", "sadasdasd")
                        .addHeader("MFSESSID", "1234567890;9baeed55ecb4e2baed9b570edf7404b2");

                // Adding Authorization token (API Key)
                // Requests will be denied without API key
               /* if (!TextUtils.isEmpty(PrefUtils.getApiKey(context))) {
                    requestBuilder.addHeader("Authorization", PrefUtils.getApiKey(context));
                }
*/
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        okHttpClient = httpClient.build();
    }

    private static void initOkHttpDemo(final Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "token 297D195D-683D-4BFA-8465-60EDA7D1B4B4")
                        .addHeader("application", "MobileBooking")
                        .addHeader("device_id", "353110080226020")
                        .addHeader("BU", "HCI")
                        .addHeader("CompanyCode", "01");

                // Adding Authorization token (API Key)
                // Requests will be denied without API key
               /* if (!TextUtils.isEmpty(PrefUtils.getApiKey(context))) {
                    requestBuilder.addHeader("Authorization", PrefUtils.getApiKey(context));
                }
*/
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        okHttpClientDemo = httpClient.build();
    }

    public static void resetApiClient() {
        retrofitDemo = null;
        okHttpClient = null;

        retrofitApi = null;
        okHttpClientDemo = null;
    }
}
