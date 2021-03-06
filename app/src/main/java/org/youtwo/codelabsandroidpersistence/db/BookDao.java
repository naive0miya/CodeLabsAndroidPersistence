package org.youtwo.codelabsandroidpersistence.db;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;
import java.util.Date;
import java.util.List;
import org.youtwo.codelabsandroidpersistence.db.entity.Book;

/**
 * Created by Bill on 2017/8/28.
 */
@Dao
public interface BookDao {

  @Query("SELECT * FROM Book where id = :id")
  Book loadUserById(int id);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Book " +
      "INNER JOIN Loan ON Loan.book_id = Book.id " +
      "INNER JOIN User on User.id = Loan.user_id " +
      "WHERE User.name LIKE :userName"
  )
  LiveData<List<Book>> findBooksBorrowedByName(String userName);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Book " +
      "INNER JOIN Loan ON Loan.book_id = Book.id " +
      "INNER JOIN User on User.id = Loan.user_id " +
      "WHERE User.name LIKE :userName " +
      "AND Loan.endTime > :after "
  )
  LiveData<List<Book>> findBooksBorrowedByNameAfter(String userName, Date after);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Book " +
      "INNER JOIN Loan ON Loan.book_id = Book.id " +
      "INNER JOIN User on User.id = Loan.user_id " +
      "WHERE User.name LIKE :userName"
  )
  List<Book> findBooksBorrowedByNameSync(String userName);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Book " +
      "INNER JOIN Loan ON Loan.book_id LIKE Book.id " +
      "WHERE Loan.user_id LIKE :userId "
  )
  LiveData<List<Book>> findBooksBorrowedByUser(String userId);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Book " +
      "INNER JOIN Loan ON Loan.book_id LIKE Book.id " +
      "WHERE Loan.user_id LIKE :userId " +
      "AND Loan.endTime > :after "
  )
  LiveData<List<Book>> findBooksBorrowedByUserAfter(String userId, Date after);

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT * FROM Book " +
      "INNER JOIN Loan ON Loan.book_id LIKE Book.id " +
      "WHERE Loan.user_id LIKE :userId "
  )
  List<Book> findBooksBorrowedByUserSync(String userId);

  @Query("SELECT * FROM Book")
  LiveData<List<Book>> findAllBooks();

  @Query("SELECT * FROM Book")
  List<Book> findAllBooksSync();

  @Insert(onConflict = IGNORE)
  void insertBook(Book book);

  @Update(onConflict = REPLACE)
  void updateBook(Book book);

  @Query("DELETE FROM Book")
  void deleteAll();
}
