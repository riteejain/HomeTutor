package com.example.hometutor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private Context mContext;
    private List<CardMenu> menuList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView thumbnail;
         public MyViewHolder(View view){
             super(view);
             thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
         }
    }
    public MenuAdapter(Context context,List<CardMenu> menuList){
        this.menuList = menuList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent , int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        CardMenu cardMenu = menuList.get(position);
        Glide.with(mContext).load(cardMenu.getThumbnail()).into(holder.thumbnail);
        holder.thumbnail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(mContext,"Opened",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public int getItemCount(){
        return menuList.size();
    }
}
