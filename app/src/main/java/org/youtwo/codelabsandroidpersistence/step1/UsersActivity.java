package org.youtwo.codelabsandroidpersistence.step1;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.List;
import java.util.Locale;
import org.youtwo.codelabsandroidpersistence.R;
import org.youtwo.codelabsandroidpersistence.db.AppDatabase;
import org.youtwo.codelabsandroidpersistence.db.entity.User;
import org.youtwo.codelabsandroidpersistence.db.utils.DatabaseInitializer;

/**
 * Created by Bill on 2017/8/28.
 */

public class UsersActivity extends LifecycleActivity {

  private AppDatabase mDb;

  private TextView mYoungUsersTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.db_activity1);

    mYoungUsersTextView = (TextView) findViewById(R.id.young_users_tv);

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
    // Note: this kind of logic should not be in an activity.
    StringBuilder sb = new StringBuilder();
    List<User> youngUsers = mDb.userModel().findYoungerThan(35);
    for (User youngUser : youngUsers) {
      sb.append(String.format(Locale.US,
          "%s, %s (%d)\n", youngUser.lastName, youngUser.name, youngUser.age));
    }
    mYoungUsersTextView.setText(sb);
  }
}
