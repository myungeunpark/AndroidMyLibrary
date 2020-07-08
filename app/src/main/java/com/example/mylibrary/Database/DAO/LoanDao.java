package com.example.mylibrary.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.example.mylibrary.Database.Entity.Book;
import com.example.mylibrary.Database.Entity.Loan;

import java.util.List;

@Dao
public interface LoanDao {

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLoan(Loan loan);


    @Query("SELECT * FROM LoanTable WHERE userId == :userId")
    LiveData<List<Loan>> getLoan(int userId);




}
