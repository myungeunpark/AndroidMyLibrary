package com.example.mylibrary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mylibrary.Database.Entity.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private Context context =  null;
    ViewItemListener itemListener =  null;
    List<User> mUser =  null;
    int selectInx = -1;

    public UserRecyclerViewAdapter(Context context, ViewItemListener itemListener, List<User> mUser) {
        this.context = context;
        this.itemListener = itemListener;
        this.mUser = mUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        Glide.with(context).load(context.getResources().getIdentifier(mUser.get(position).getImage(), "drawable", context.getPackageName()))
                        .into(holder.imageView);

        holder.name.setText(mUser.get(position).getName());
        holder.phone.setText(mUser.get(position).getPhone());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        return mUser.size();
    }


    public interface ViewItemListener{
        void onItemClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageView = null;
        TextView name;
        TextView phone;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.userImage);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            linearLayout = itemView.findViewById(R.id.userLayout);

        }

    }

}
