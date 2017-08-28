package org.youtwo.codelabsandroidpersistence.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import org.youtwo.codelabsandroidpersistence.db.entity.Book;
import org.youtwo.codelabsandroidpersistence.db.entity.Loan;
import org.youtwo.codelabsandroidpersistence.db.entity.User;
import org.youtwo.codelabsandroidpersistence.db.utils.DateConverter;

/**
 * Created by Bill on 2017/8/28.
 */
@Database(entities = {User.class, Book.class, Loan.class}, version = 1,  exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

  private static AppDatabase INSTANCE;

  public abstract UserDao userModel();
  public abstract BookDao bookModel();
  public abstract LoanDao loanModel();

  public static AppDatabase getInMemoryDatabase(Context context) {
    if (INSTANCE == null) {
      INSTANCE =
          Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
              // To simplify the codelab, allow queries on the main thread.
              // Don't do this on a real app! See PersistenceBasicSample for an example.
              .allowMainThreadQueries()
              .build();
    }
    return INSTANCE;
  }

  public static void destroyInstance() {
    INSTANCE = null;
  }
}
