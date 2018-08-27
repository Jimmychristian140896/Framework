package framework.jimmy.com.framework.network;

import java.util.List;

import framework.jimmy.com.framework.config.Const;
import framework.jimmy.com.framework.model.BookingResponse;
import framework.jimmy.com.framework.model.BookingStore;
import framework.jimmy.com.framework.model.DeliveryLoginResponse;
import framework.jimmy.com.framework.model.Message;
import framework.jimmy.com.framework.model.RequestEncrypted;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET(Const.BASE_URL_API+"inbox.json")
    Call<List<Message>> getInbox();

    @FormUrlEncoded
    @POST("driverlogin")
    Call<DeliveryLoginResponse> deiverlogin(@Field("drivernip") String drivernip, @Field("nopolice") String nopolice);

    @POST("getStoreList")
    Call<BookingResponse<BookingStore>> getStoreList(@Body RequestEncrypted requestEncrypted);

    @POST("getStoreList")
    Single<BookingResponse<BookingStore>> getStoreList1(@Body RequestEncrypted requestEncrypted);

    @POST("getStoreList")
    Observable<BookingResponse<BookingStore>> getStoreList2(@Body RequestEncrypted requestEncrypted);

    @POST("getStoreList")
    Single<Response<BookingResponse<BookingStore>>> getStoreList3(@Body RequestEncrypted requestEncrypted);

    @POST("getStoreList")
    Observable<Response<ResponseBody>> getStoreList4(@Body RequestEncrypted requestEncrypted);
}
