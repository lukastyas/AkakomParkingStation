package com.example.akakomparkingstation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterAdmin extends RecyclerView.Adapter<RecyclerViewAdapterAdmin.ViewHolder>{

    private ArrayList<String> nama;
    private ArrayList<String> user;
    public RecyclerViewAdapterAdmin(ArrayList<String> a, ArrayList<String> i){
        nama = a;
        user=i;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public TextView tvSubtitle;
        public TextView tvQwer;

        public ViewHolder(View v){
            super((v));
            tvTitle = (TextView)v.findViewById(R.id.tv_title);
            tvSubtitle = (TextView)v.findViewById(R.id.tv_subtitle);
            //tvQwer = (TextView)v.findViewById(R.id.tv_qwer);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final  String name = nama.get(position);
        holder.tvTitle.setText(nama.get(position));
        holder.tvSubtitle.setText(user.get(position));
        //holder.tvQwer.setText("uji" + position);
    }
    @Override
    public int getItemCount(){
        return nama.size();
    }


}

