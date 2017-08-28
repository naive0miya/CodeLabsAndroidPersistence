package org.youtwo.codelabsandroidpersistence.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

/**
 * Created by Bill on 2017/8/28.
 */

@Entity(tableName = "loan",
    foreignKeys = {
        @ForeignKey(entity = Book.class,
            parentColumns = "id",
            childColumns = "book_id",
            onDelete = ForeignKey.CASCADE
        ),

        @ForeignKey(entity = User.class,
            parentColumns = "id",
            childColumns = "user_id",
            onDelete = ForeignKey.CASCADE
        )},
    indices = {@Index("book_id"), @Index("user_id")}
)
public class Loan {

  // Fields can be public or private with getters and setters.
  public
  @PrimaryKey
  String id;
  public Date startTime;
  public Date endTime;
  @ColumnInfo(name = "book_id")
  public String bookId;
  @ColumnInfo(name = "user_id")
  public String userId;
}
