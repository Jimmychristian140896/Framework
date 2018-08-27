package framework.jimmy.com.framework.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import framework.jimmy.com.framework.dao.HistoryOvoDetailDao;
import framework.jimmy.com.framework.model.HistoryOvoDetail;
import framework.jimmy.com.framework.model.LokasiKiosOvo;
import framework.jimmy.com.framework.util.Converters;


@Database(entities = {
        HistoryOvoDetail.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class FrameworkRoomDatabase extends RoomDatabase {
    public abstract HistoryOvoDetailDao historyOvoDetailDao();

    private static FrameworkRoomDatabase INSTANCE;

    public static FrameworkRoomDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized ( FrameworkRoomDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FrameworkRoomDatabase.class,  "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                }
            };

}