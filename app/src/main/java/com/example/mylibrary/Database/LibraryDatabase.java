package com.example.mylibrary.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mylibrary.Database.DAO.BookDao;
import com.example.mylibrary.Database.DAO.LoanDao;
import com.example.mylibrary.Database.DAO.UserDao;
import com.example.mylibrary.Database.Entity.Book;
import com.example.mylibrary.Database.Entity.Loan;
import com.example.mylibrary.Database.Entity.User;

@Database(entities = {User.class, Book.class, Loan.class},  version = 1, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase {

        public abstract UserDao getUserDao();
        public abstract BookDao getBookDao();
        public abstract LoanDao getLoanDao();

        private static volatile LibraryDatabase instance = null;

        public static LibraryDatabase getInstance(final Context context){

            if(instance == null){

                synchronized(RoomDatabase.class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                                context.getApplicationContext(),
                                LibraryDatabase.class,
                                "MyLibraryDB"
                        ).build();
                    }
                }
            }

            return instance;
        }
}
