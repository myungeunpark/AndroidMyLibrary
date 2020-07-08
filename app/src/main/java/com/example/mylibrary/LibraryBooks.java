package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mylibrary.Database.Entity.Book;
import com.example.mylibrary.Database.Entity.BookList;

import java.util.List;

public class LibraryBooks extends AppCompatActivity {

    private List<Book> mBooks;
    private Context mContext;
    private int selectInx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_books);

        BookList list = getIntent().getExtras().getParcelable("book");
        mBooks = list.getBookList();
        mContext = getApplicationContext();

        Log.d("LibraryBooks", "Total book size is " + mBooks.size());

        Button checkout = findViewById(R.id.checkOut);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ret = new Intent();
                if(selectInx >= 0)
                    ret.putExtra("book", mBooks.get(selectInx));
                setResult(RESULT_OK, ret);

                finish();
            }
        });

        initRecyclerView();

    }

    private void initRecyclerView(){

        BookRecyclerViewAdapter recyclerViewAdaptor =
                new BookRecyclerViewAdapter(mBooks, listener, mContext);
        RecyclerView recyclerView = findViewById(R.id.bookList);
        recyclerView.setAdapter(recyclerViewAdaptor);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,3);
        recyclerView.setLayoutManager(layoutManager);

    }

    BookRecyclerViewAdapter.BookItemListener listener = new BookRecyclerViewAdapter.BookItemListener() {
        @Override
        public void onItemClicked(int position) {
            selectInx = position;
        }
    };

}
