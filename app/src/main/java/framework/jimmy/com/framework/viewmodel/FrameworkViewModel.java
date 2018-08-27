package framework.jimmy.com.framework.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import framework.jimmy.com.framework.dao.HistoryOvoDetailDao;
import framework.jimmy.com.framework.model.HistoryOvoDetail;
import framework.jimmy.com.framework.repository.FrameworkRepository;
import framework.jimmy.com.framework.room.FrameworkRoomDatabase;

public class FrameworkViewModel extends AndroidViewModel {
    private FrameworkRepository mRepository;
    //private LiveData<List<HistoryOvoDetail>> listHistoryOvoDetail;


    public FrameworkViewModel(Application application) {
        super(application);
        mRepository = new FrameworkRepository(application);
        //listHistoryOvoDetail = mRepository.getAllHistory();
    }

    public LiveData<List<HistoryOvoDetail>> getAllHistory() {
        //return listHistoryOvoDetail;
        return mRepository.getAllHistory();
    }

    public void insert(HistoryOvoDetail historyOvoDetail) {
        mRepository.insert(historyOvoDetail);

    }


}