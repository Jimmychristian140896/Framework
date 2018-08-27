package framework.jimmy.com.framework.retrofit;

import java.util.List;

import framework.jimmy.com.framework.pojo.CryptoData;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("ticker/?")
    Observable<List<CryptoData>> getData(@Query("limit") String limit);
}
