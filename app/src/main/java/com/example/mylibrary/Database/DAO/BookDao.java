package com.example.mylibrary.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import com.example.mylibrary.Database.Entity.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBook(Book book);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBooks(List<Book> books);

    @Update
    void updateBook(Book book);

    @Query("SELECT * FROM BookTable WHERE id == :id")
    Book getBook(int id);

    @Query("SELECT * FROM BookTable")
    LiveData<List<Book>> getAllBooks();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM BookTable " +
            "INNER JOIN LoanTable ON LoanTable.bookId == BookTable.id " +
            "WHERE LoanTable.userId == :userId ")
    LiveData<List<Book>> getBooks(int userId);


}
