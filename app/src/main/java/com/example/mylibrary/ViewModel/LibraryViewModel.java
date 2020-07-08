package com.example.mylibrary.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mylibrary.Database.Entity.Book;
import com.example.mylibrary.Database.Entity.Loan;
import com.example.mylibrary.Database.Entity.User;
import com.example.mylibrary.Repository.Repository;

import java.util.List;

public class LibraryViewModel extends AndroidViewModel {

    private LiveData<List<User>> mUsers;
    private LiveData<List<Book>> mBooks;
    private Repository repository;

    public LibraryViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        mUsers = repository.getAllUsers();
        mBooks = repository.getAllBooks();

    }

    public LiveData<List<User>> getUsers(){
        return mUsers;
    }


    public LiveData<List<Book>> getBooks(){
        return mBooks;
    }

    public void insertUsers(List<User> users){
        repository.insertUsers(users);
    }

    public void insertLoans(Loan loan){
        repository.insertLoan(loan);
    }

    public void insertBooks(List<Book> books){
        repository.insertBooks(books);
    }

    public LiveData<List<Book>> getUserLoanBook(int userId){
        return repository.getUserLoanBooks(userId);
    }

}
