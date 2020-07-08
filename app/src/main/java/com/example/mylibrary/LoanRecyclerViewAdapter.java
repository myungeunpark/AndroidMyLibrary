package com.example.mylibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mylibrary.Database.Entity.Book;

import java.util.List;
import java.util.zip.Inflater;

public class LoanRecyclerViewAdapter extends RecyclerView.Adapter<LoanRecyclerViewAdapter.ViewHolder>{

    List<Book> mBooks;
    Context mContext;

    public LoanRecyclerViewAdapter(List<Book> mBooks) {
        this.mBooks = mBooks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(mContext).load(mContext.getResources().getIdentifier(mBooks.get(position).getImage(), "drawable", mContext.getPackageName()))
                .into(holder.imageView);
        holder.titleText.setText(mBooks.get(position).getTitle());
        holder.authorText.setText(mBooks.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        if(mBooks == null)
            return 0;
        else
            return  mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView  titleText;
        TextView  authorText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.lbImage);
            titleText = itemView.findViewById(R.id.lbTitle);
            authorText = itemView.findViewById(R.id.lbAuthor);
        }
    }
}
