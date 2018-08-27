package framework.jimmy.com.framework.mvp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import framework.jimmy.com.framework.pojo.CryptoData;
import framework.jimmy.com.framework.retrofit.APIInterface;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PresenterImpl implements MainActivityContract.Presenter {

    APIInterface apiInterface;
    MainActivityContract.View mView;

    @Inject
    public PresenterImpl(APIInterface apiInterface, MainActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
    }

    @Override
    public void loadData() {

        mView.showProgress();

        apiInterface.getData("10").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<CryptoData>, List<CryptoData>>() {
                    @Override
                    public List<CryptoData> apply(List<CryptoData> cryptoData) throws Exception {
                        Collections.sort(cryptoData, new Comparator<CryptoData>() {
                            @Override
                            public int compare(CryptoData cryptoData1, CryptoData cryptoData2) {
                                return Integer.parseInt(cryptoData2.rank) - Integer.parseInt(cryptoData1.rank);
                            }
                        });
                        return cryptoData;
                    }
                })
                .subscribe(new Observer<List<CryptoData>>() {


                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                        mView.hideProgress();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CryptoData> data) {
                        mView.showData(data);
                    }
                });
    }
}
