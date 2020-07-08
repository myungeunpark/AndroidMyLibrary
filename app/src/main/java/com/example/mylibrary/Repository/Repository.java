package com.example.mylibrary.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.mylibrary.Database.Entity.Book;
import com.example.mylibrary.Database.Entity.Loan;
import com.example.mylibrary.Database.Entity.User;
import com.example.mylibrary.Database.LibraryDatabase;

import java.util.List;

public class Repository {

    LibraryDatabase db = null;
    LiveData<List<User>> mUsers;
    LiveData<List<Book>> mBooks;

    public Repository(Application application) {

        db = LibraryDatabase.getInstance(application);

        mUsers = db.getUserDao().getAllUsers();
        mBooks = db.getBookDao().getAllBooks();
    }

    public LiveData<List<User>> getAllUsers(){
        return mUsers;
    }

    public LiveData<List<Book>> getAllBooks(){
        return mBooks;
    }

    public LiveData<List<Book>> getUserLoanBooks(int useId){
        return db.getBookDao().getBooks(useId);
    }

    public void insertUsers(final List<User> users){

        new AsyncTask<List<User>, Void, Void>() {
            @Override
            protected Void doInBackground(List<User>... lists) {
                db.getUserDao().insertUsers(users);
                return null;
            }
        }.execute(users);

    }

    public void insertBooks(final List<Book> books){

        new AsyncTask<List<Book>, Void, Void>() {
            @Override
            protected Void doInBackground(List<Book>... lists) {
                db.getBookDao().insertBooks(books);
                return null;
            }
        }.execute(books);
    }

    public void insertLoan(final Loan loan){

        new AsyncTask<Loan, Void, Void>(){

            @Override
            protected Void doInBackground(Loan... loans) {
                db.getLoanDao().insertLoan(loan);
                return null;
            }
        }.execute(loan);
    }

}
