package org.youtwo.codelabsandroidpersistence.step3;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import java.util.List;
import org.youtwo.codelabsandroidpersistence.R;
import org.youtwo.codelabsandroidpersistence.db.entity.Book;

/**
 * Created by Bill on 2017/8/28.
 */

public class BooksBorrowedByUserActivity extends LifecycleActivity {

  private BooksBorrowedByUserViewModel mViewModel;

  @SuppressWarnings("unused")
  private TextView mBooksTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.db_activity);
    mBooksTextView = (TextView) findViewById(R.id.books_tv);

    // Get a reference to the ViewModel for this screen.
    mViewModel = ViewModelProviders.of(this).get(BooksBorrowedByUserViewModel.class);

    // Update the UI whenever there's a change in the ViewModel's data.
    subscribeUiBooks();
  }

  public void onRefreshBtClicked(View view) {
    mViewModel.createDb();
  }

  private void subscribeUiBooks() {
    // TODO: refresh the list of books when there's new data
    // mViewModel.books.observe(...
  }

  @SuppressWarnings("unused")
  private static void showBooksInUi(final @NonNull List<Book> books,
      final TextView booksTextView) {
    StringBuilder sb = new StringBuilder();

    for (Book book : books) {
      sb.append(book.title);
      sb.append("\n");

    }
    booksTextView.setText(sb.toString());
  }
}
