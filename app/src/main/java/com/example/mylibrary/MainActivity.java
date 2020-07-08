package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.Database.Entity.Book;
import com.example.mylibrary.Database.Entity.BookList;
import com.example.mylibrary.Database.Entity.Loan;
import com.example.mylibrary.Database.Entity.User;
import com.example.mylibrary.ViewModel.LibraryViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    static final int REQUEST_BOOK_CHECKOUT = 100;


    LibraryViewModel viewModel;
    List<User> mUsers;
    BookList mBooks;
    RecyclerView userRecycleView;
    Context mContext;
    int cutPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mUsers = TestCases.getUserTestCases();
        userRecycleView = findViewById(R.id.userList);
        mBooks = new BookList();
        mBooks.setBookList(TestCases.getBookTestCases());

        viewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

        TextView textView = findViewById(R.id.refresh);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // refresh user
                viewModel.insertUsers(mUsers);
                viewModel.insertBooks(mBooks.getBookList());
            }
        });

        Button bookBtn = findViewById(R.id.goBook);
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, LibraryBooks.class);
                intent.putExtra("book", mBooks);
                startActivityForResult(intent, REQUEST_BOOK_CHECKOUT);

            }
        });


        viewModel.getUsers().observe(this, new Observer<List<User>>() {

            @Override
            public void onChanged(List<User> users) {

                updateUser(users);

            }
        });


        viewModel.getBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                updateBooks(books);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_BOOK_CHECKOUT){
            if (resultCode == RESULT_OK) {

                Book book = data.getExtras().getParcelable("book");
                Log.d("onActivityResult" ,"Result Book id - " + book.getId());
                Log.d("onActivityResult" ,"Result Book title - " + book.getTitle());
                Log.d("onActivityResult" ,"Result Book author - " + book.getAuthor());
                Log.d("onActivityResult" ,"Result Book image - " + book.getImage());

                if(book != null) {
                    Loan loan = new Loan();
                    loan.setBookId(book.getId());
                    loan.setUserId(mUsers.get(cutPosition).getId());
                    loan.setTimestamp((int)System.currentTimeMillis());
                    viewModel.insertLoans(loan);
                }
            }
        }
    }

    private void updateUser(List<User> users){

        if(users.size() > 0) {
            mUsers = users;
            Log.d("MYLIBRARY", "SIZE - " + users.size());
            UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(this, userUserListener, users);
            userRecycleView.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            layoutManager.scrollToPosition(0);
            userRecycleView.setLayoutManager(layoutManager);
        }
    }

    private void updateBooks(List<Book> books){

        if(books.size() > 0){
            mBooks.setBookList(books);
        }
    }


    UserRecyclerViewAdapter.ViewItemListener userUserListener = new UserRecyclerViewAdapter.ViewItemListener() {
        @Override
        public void onItemClicked(int position) {
            cutPosition = position;
            UpdateBookRecyclerView(position);
        }
    };

    void UpdateBookRecyclerView(int position){

        User user = mUsers.get(position);
        viewModel.getUserLoanBook(user.getId()).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {


                LoanRecyclerViewAdapter recyclerViewAdapter = new LoanRecyclerViewAdapter(books);
                RecyclerView view = findViewById(R.id.bookList);
                view.setAdapter(recyclerViewAdapter);
                GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
                view.setLayoutManager(layoutManager);

            }
        });

    }
}
