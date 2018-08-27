package framework.jimmy.com.framework.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import framework.jimmy.com.framework.model.HistoryOvoDetail;

@Dao
public interface HistoryOvoDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HistoryOvoDetail historyOvoDetail);

    @Query("DELETE FROM history_ovo_detail")
    void deleteAll();

    @Query("SELECT * from history_ovo_detail ORDER BY date ASC ")
    LiveData<List<HistoryOvoDetail>> getAllHistory();
}
