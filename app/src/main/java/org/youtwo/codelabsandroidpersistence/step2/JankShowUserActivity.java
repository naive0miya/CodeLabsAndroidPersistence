package org.youtwo.codelabsandroidpersistence.step2;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import java.util.List;
import org.youtwo.codelabsandroidpersistence.R;
import org.youtwo.codelabsandroidpersistence.db.AppDatabase;
import org.youtwo.codelabsandroidpersistence.db.entity.Book;
import org.youtwo.codelabsandroidpersistence.db.utils.DatabaseInitializer;

/**
 * Created by Bill on 2017/8/28.
 */

public class JankShowUserActivity extends LifecycleActivity {

  private AppDatabase mDb;

  private TextView mBooksTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.db_activity);

    mBooksTextView = (TextView) findViewById(R.id.books_tv);

    // Note: Db references should not be in an activity.
    mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

    populateDb();

    fetchData();
  }

  @Override
  protected void onDestroy() {
    AppDatabase.destroyInstance();
    super.onDestroy();
  }

  private void populateDb() {
    DatabaseInitializer.populateSync(mDb);
  }

  private void fetchData() {
    // This activity is executing a query on the main thread, making the UI perform badly.
    List<Book> books = mDb.bookModel().findBooksBorrowedByNameSync("Mike");
    showListInUI(books, mBooksTextView);
  }

  private static void showListInUI(final @NonNull List<Book> books,
      final TextView booksTextView) {
    StringBuilder sb = new StringBuilder();
    for (Book book : books) {
      sb.append(book.title);
      sb.append("\n");
    }
    booksTextView.setText(sb.toString());
  }

  public void onRefreshBtClicked(View view) {
    mBooksTextView.setText("");
    fetchData();
  }
}
