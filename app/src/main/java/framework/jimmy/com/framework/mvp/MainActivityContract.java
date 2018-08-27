package framework.jimmy.com.framework.mvp;

import java.util.List;

import framework.jimmy.com.framework.pojo.CryptoData;

public interface MainActivityContract {
    interface View {
        void showData(List<CryptoData> data);
        void showError(String message);
        void showComplete();
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void loadData();
    }
}
