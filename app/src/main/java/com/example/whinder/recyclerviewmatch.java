package com.example.whinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class recyclerviewmatch extends RecyclerView.Adapter<recyclerviewmatch.MyViewholder> {

    Context context ;
    List<UserJID> mUsers ;

    public recyclerviewmatch(Context context ,List<UserJID> mUsers) {
        this.context = context;
        this.mUsers= mUsers ;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.whinder_match_item,parent,false);
        return new recyclerviewmatch.MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        UserJID user = mUsers.get(position);

          holder.textView.setText(user.getUsername());


    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    class MyViewholder extends RecyclerView.ViewHolder{
        public TextView textView ;
        public CircleImageView imageView;
        public ImageButton imageButton ;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.username);
            imageView = itemView.findViewById(R.id.profilepic);
            imageButton = itemView.findViewById(R.id.msg);

        }
    }
}
