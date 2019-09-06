package Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import Dao.UserDao;
import Entiry.User;

@Database(entities = {User.class}, version = 1)
public class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    UserDao userDao;

    public static AppDatabase getAppDatabase(Context context){
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user_database")
            .allowMainThreadQueries().build();
        }
        return appDatabase;
    }

    public static void destroyInstance(){
        if(appDatabase != null){
            appDatabase = null;
        }
    }

    public static void addUser(User user){
        appDatabase.userDao.insertAll(user);
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
