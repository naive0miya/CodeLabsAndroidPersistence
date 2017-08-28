package org.youtwo.codelabsandroidpersistence.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Bill on 2017/8/28.
 */
@Entity(tableName = "user", indices = {@Index(value = {"id"}, unique = true)})
public class User {

  public @PrimaryKey String id;
  public String name;
  public String lastName;
  public int age;
}
