package framework.jimmy.com.framework.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.FrameworkApplication;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.activity.MainMvpDaggerActivity;
import framework.jimmy.com.framework.activity.MainOvoActivity;
import framework.jimmy.com.framework.activity.MyProfileOvoActivity;
import framework.jimmy.com.framework.activity.NotificationOvoActivity;
import framework.jimmy.com.framework.model.BookingResponse;
import framework.jimmy.com.framework.model.BookingStore;
import framework.jimmy.com.framework.model.DeliveryLoginResponse;
import framework.jimmy.com.framework.model.LocationReceivedEvent;
import framework.jimmy.com.framework.model.MessageEvent;
import framework.jimmy.com.framework.model.RequestEncrypted;
import framework.jimmy.com.framework.model.UserLocationModel;
import framework.jimmy.com.framework.network.ApiClient;
import framework.jimmy.com.framework.network.ApiInterface;
import framework.jimmy.com.framework.util.image.PicassoUtil;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeOvoFragment extends Fragment {

    private FirebaseAnalytics mFirebaseAnalytics;
    @OnClick({R.id.layout_ovo_cash, R.id.layout_ovo_point})
    public void onClickLayoutOvoCashAndPoint() {

        startActivity(new Intent(this.getContext(), MainMvpDaggerActivity.class));
        //((MainOvoActivity) getActivity()).switchTab(4);
    }

    @OnClick(R.id.layout_ovo_investasi)
    public void onClickLayoutOvoInvestasi() {
        ((MainOvoActivity) getActivity()).switchTab(2);
    }


    @BindView(R.id.txt_customer_name_ovo)
    TextView txtCustomerNameOvo;

    @BindView(R.id.txt_ovo_cash)
    TextView txtOvoCash;

    @BindView(R.id.img_customer_ovo)
    ImageView imgCustomerOvo;

    @BindView(R.id.img_customer_ovo_type)
    ImageView imgCustomerOvoType;

    @OnClick(R.id.img_customer_ovo)
    public void onClickImageCustomerOvo() {
        startActivity(new Intent(this.getContext(), MyProfileOvoActivity.class));
    }

    public HomeOvoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.getContext());

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ID");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Name");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        mFirebaseAnalytics.logEvent("share_image", bundle);

        mFirebaseAnalytics.setUserProperty("favorite_food", "Favorite");
        mFirebaseAnalytics.setUserId("User Id");
        mFirebaseAnalytics.setCurrentScreen(this.getActivity(), this.getClass().getSimpleName(), this.getClass().getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_ovo, container, false);
        ButterKnife.bind(this, v);
        initValue();
        text();
        return v;
    }

    public void initValue() {
        PicassoUtil.Glide(getContext(),
                "https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg",
                //"http://i.imgur.com/DvpvklR.png",
                //"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-0t1mKo-uGl4qOxAfaaJ7qw3nP1oxQCu8BzCmig6XTXr2qYtQ",
                //"https://vignette.wikia.nocookie.net/sonic/images/9/9a/Sonic-adventure-12.png/revision/latest?cb=20111018154129",
                imgCustomerOvo);
        /*Picasso
                .with(this.getActivity())
                //.get()
                .load("https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg") //http://i.imgur.com/DvpvklR.png
                //.load("file:///android_asset/DvpvklR.png")
                //.load(R.drawable.andromeda)
                //.load(new File("path"))
                //.placeholder(R.drawable.ic_add_black_24dp)
                //.error(R.drawable.ic_expand_more_black_24dp)
                //.transform(new ColorFilterTransformation(color))
                //.transform(new CropCircleTransformation())
                //.resize(50, 50)
                //.centerCrop()
                //.into(imageView)
                .into(imgCustomerOvo);
                */
        PicassoUtil.Glide( getContext(), "https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg",imgCustomerOvoType);
/*
        Glide.with(this).load("https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg")
                //.apply(bitmapTransform(new BlurTransformation(25, 3)))
                //.load(R.drawable.andromeda)
                //.apply(bitmapTransform(multi))
                .into(imgCustomerOvoType);
*/
        /*DateTime dateTime = DateTime.now();
        LocalDate localDate = dateTime.toLocalDate();
        txtCustomerNameOvo.setText(localDate.toString("dd MMM yyyy"));
        LocalTime localTime = dateTime.toLocalTime();
        txtOvoCash.setText(localTime.toString());*/

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(this.getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        ApiInterface service = ApiClient.getClientApi(getContext()).create(ApiInterface.class);
        Call<DeliveryLoginResponse> call = service.deiverlogin("087476", "B 9279 GCB");
        call.enqueue(new Callback<DeliveryLoginResponse>() {
            @Override
            public void onResponse(Call<DeliveryLoginResponse> call, Response<DeliveryLoginResponse> response) {
                if(response.isSuccessful()) {
                    DeliveryLoginResponse deliveryLoginResponse = response.body();
                    txtCustomerNameOvo.setText(deliveryLoginResponse.getSessionid());
                    txtOvoCash.setText(deliveryLoginResponse.getJobid());
                }
                else
                {
                    Toast.makeText(getContext(), "Message : "+response.message()+ ", Error Body "+response.errorBody(), Toast.LENGTH_LONG).show();
                }
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<DeliveryLoginResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

        initValueBooking();
    }
    private CompositeDisposable disposable = new CompositeDisposable();
    private void initValueBooking()
    {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(this.getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        //FrameworkApplication.getInstance().getNetComponent().inject(this.getActivity());
        //ApiInterface service = retrofit.create(ApiInterface.class);
        ApiInterface service = ApiClient.getClientDemo(getContext()).create(ApiInterface.class);
        RequestEncrypted requestEncrypted = new RequestEncrypted("0B73041D102C082B5B7F5246636F64655F73746F7265223A223031222C22705F737672223A22424F4F4B222C22705F706172656E74223A22484F4D4543454E544552227D");

        Call<BookingResponse<BookingStore>> call = service.getStoreList(requestEncrypted);

        call.enqueue(new Callback<BookingResponse<BookingStore>>() {
            @Override
            public void onResponse(Call<BookingResponse<BookingStore>> call, Response<BookingResponse<BookingStore>> response) {
                if(response.isSuccessful()) {
                    BookingResponse<BookingStore> deliveryLoginResponse = response.body();
                    txtCustomerNameOvo.setText(deliveryLoginResponse.getRows().get(0).getCompCode());
                    //txtOvoCash.setText(deliveryLoginResponse.getRows().get(0).getSiteSap());
                }
                else
                {
                    Toast.makeText(getContext(), "Message : "+response.message()+ ", Error Body "+response.errorBody(), Toast.LENGTH_LONG).show();
                }
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<BookingResponse<BookingStore>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
        Single<BookingResponse<BookingStore>> call1 = service.getStoreList1(requestEncrypted);
        disposable.add(call1.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<BookingResponse<BookingStore>>() {

                @Override
                public void onSuccess(BookingResponse<BookingStore> bookingStoreBookingResponse) {
                    progressDoalog.dismiss();
                    txtCustomerNameOvo.setText(bookingStoreBookingResponse.getRows().get(0).getCompCode());
                    txtOvoCash.setText(bookingStoreBookingResponse.getRows().get(0).getSiteSap());
                }

                @Override
                public void onError(Throwable e) {
                    progressDoalog.dismiss();
                    Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }));

        Observable<BookingResponse<BookingStore>> call2 = service.getStoreList2(requestEncrypted);
        disposable.add(call2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<BookingResponse<BookingStore>>() {
                    @Override
                    public void onNext(BookingResponse<BookingStore> bookingStoreBookingResponse) {

                        txtCustomerNameOvo.setText(bookingStoreBookingResponse.getRows().get(0).getCompCode());
                        txtOvoCash.setText(bookingStoreBookingResponse.getRows().get(0).getSiteSap());
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDoalog.dismiss();
                        Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDoalog.dismiss();
                    }
                }));

        Single<Response<BookingResponse<BookingStore>>> call3 = service.getStoreList3(requestEncrypted);
        disposable.add(call3.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<BookingResponse<BookingStore>>>() {

                    @Override
                    public void onSuccess(Response<BookingResponse<BookingStore>> bookingStoreBookingResponse) {
                        progressDoalog.dismiss();
                        txtCustomerNameOvo.setText(bookingStoreBookingResponse.body().getRows().get(0).getCompCode());
                        txtOvoCash.setText(bookingStoreBookingResponse.body().getRows().get(0).getSiteSap());
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDoalog.dismiss();
                        Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }));
        Observable<Response<ResponseBody>> call4 = service.getStoreList4(requestEncrypted);
        /*disposable.add(call4.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
                    @Override
                    public void onNext(Response<ResponseBody> bookingStoreBookingResponse) {



                        BookingResponse<BookingStore> a = new Gson().fromJson(bookingStoreBookingResponse.body().charStream(), BookingResponse.class);
                        txtCustomerNameOvo.setText(a.getRows().get(0).getCompCode());
                        txtOvoCash.setText(a.getRows().get(0).getSiteSap());
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDoalog.dismiss();
                        Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDoalog.dismiss();
                    }
                }));
*/
    }

    public void text()
    {
        ((FrameworkApplication) this.getActivity().getApplication())
                .bus()
                .toObservable()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
        ((FrameworkApplication) this.getActivity().getApplication())
                .bus()
                .toObservable()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof MessageEvent) {
                            MessageEvent event= (MessageEvent)object;
                            txtOvoCash.setText( "Hey, my message" + event.getMessage());
                        }
                    }
                });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        //txtOvoCash.setText( "Hey, my message" + event.getMessage());
        Toast.makeText(getContext(), "Hey, my message" + event.getMessage(), Toast.LENGTH_LONG).show();
    }

    /*// UI updates must run on MainThread
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(LocationReceivedEvent event) {
        txtOvoCash.setText("Long : "+event.getLongitude()+", Lat : "+event.getLatitude());
    }*/
}





