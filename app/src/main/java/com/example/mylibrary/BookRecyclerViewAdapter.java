package com.example.mylibrary;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mylibrary.Database.Entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>{

    List<Book> mBooks = new ArrayList<>();
    BookItemListener itemListener;
    Context mContext;
    int selectInx = -1;

    public BookRecyclerViewAdapter(List<Book> mBooks, BookItemListener itemListener, Context mContext) {
        this.mBooks = mBooks;
        this.itemListener = itemListener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(mContext).load(mContext.getResources().getIdentifier(mBooks.get(position).getImage(), "drawable", mContext.getPackageName()))
                .into(holder.imageView);
        holder.titleText.setText(mBooks.get(position).getTitle());
        holder.authorText.setText(mBooks.get(position).getAuthor());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemListener.onItemClicked(position);
                selectInx = position;
                notifyDataSetChanged();
            }
        });

        if(selectInx == position){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FF4081"));

        }else{
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

    }

    @Override
    public int getItemCount() {
        if(mBooks!= null)
            return mBooks.size();
        else
            return 0;
    }


    public interface BookItemListener{

        void onItemClicked(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView titleText;
        TextView authorText;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.bookImage);
            titleText = itemView.findViewById(R.id.title);
            authorText = itemView.findViewById(R.id.author);
            linearLayout = itemView.findViewById(R.id.bookLayout);

        }
    }
}
