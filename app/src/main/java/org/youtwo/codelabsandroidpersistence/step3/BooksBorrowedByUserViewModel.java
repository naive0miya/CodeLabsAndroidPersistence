package org.youtwo.codelabsandroidpersistence.step3;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.List;
import org.youtwo.codelabsandroidpersistence.db.AppDatabase;
import org.youtwo.codelabsandroidpersistence.db.entity.Book;
import org.youtwo.codelabsandroidpersistence.db.utils.DatabaseInitializer;

/**
 * Created by Bill on 2017/8/28.
 */

public class BooksBorrowedByUserViewModel extends AndroidViewModel {

  public final LiveData<List<Book>> books;

  private AppDatabase mDb;

  public BooksBorrowedByUserViewModel(Application application) {
    super(application);
    createDb();

    // TODO: Assign books to the 'findBooksBorrowedByName' query.
    books = null;
  }

  public void createDb() {
    mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

    // Populate it with initial data
    DatabaseInitializer.populateAsync(mDb);
  }
}
