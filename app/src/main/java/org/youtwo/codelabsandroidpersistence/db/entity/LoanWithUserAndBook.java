package org.youtwo.codelabsandroidpersistence.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.TypeConverters;
import java.util.Date;
import org.youtwo.codelabsandroidpersistence.db.utils.DateConverter;

/**
 * Created by Bill on 2017/8/28.
 */

public class LoanWithUserAndBook {

  public String id;
  @ColumnInfo(name="title")
  public String bookTitle;
  @ColumnInfo(name="name")
  public String userName;
  @TypeConverters(DateConverter.class)
  public Date startTime;
  @TypeConverters(DateConverter.class)
  public Date endTime;
}
