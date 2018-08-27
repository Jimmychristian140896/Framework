package framework.jimmy.com.framework.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import framework.jimmy.com.framework.dao.HistoryOvoDetailDao;
import framework.jimmy.com.framework.model.HistoryOvoDetail;
import framework.jimmy.com.framework.room.FrameworkRoomDatabase;


public class FrameworkRepository {
    private HistoryOvoDetailDao historyOvoDetailDao;
    //private LiveData<List<HistoryOvoDetail>> listHistoryOvoDetail;

    public FrameworkRepository(Application application) {
        FrameworkRoomDatabase db = FrameworkRoomDatabase.getDatabase(application);
        historyOvoDetailDao = db.historyOvoDetailDao();
        //listHistoryOvoDetail = historyOvoDetailDao.getAllHistory();
    }

    public LiveData<List<HistoryOvoDetail>> getAllHistory() {
        //return listHistoryOvoDetail;
        return historyOvoDetailDao.getAllHistory();
    }

    public void insert(HistoryOvoDetail historyOvoDetail) {
        new insertAsyncTask(historyOvoDetailDao).execute(historyOvoDetail);

    }

    private static class insertAsyncTask extends AsyncTask<HistoryOvoDetail, Void, Void> {
        private HistoryOvoDetailDao mAsyncTaskDao;

        insertAsyncTask(HistoryOvoDetailDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final HistoryOvoDetail... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}