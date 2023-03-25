package com.dinklokcode.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Activity.DanhSachBaiHat_Activity;
import com.dinklokcode.musicapp.Model.TheLoaiModel;
import com.dinklokcode.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TheloaiAdapther extends RecyclerView.Adapter<TheloaiAdapther.ViewHolder> {
    Context context;
    ArrayList<TheLoaiModel> mangtheloai;

    View view;

    public TheloaiAdapther(Context context, ArrayList<TheLoaiModel> mangtheloai) {
        this.context = context;
        this.mangtheloai = mangtheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_theloai, parent, false);
        return new TheloaiAdapther.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoaiModel theLoaiModel = mangtheloai.get(position);
        holder.textViewTheLoai.setText(theLoaiModel.getTenTheLoai());
        Picasso.with(context).load(theLoaiModel.getHinhTheLoai()).into(holder.imageViewTheLoai);
        int a = position;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachBaiHat_Activity.class);
                intent.putExtra("intenttheloai", mangtheloai.get(a));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mangtheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewTheLoai;
        TextView textViewTheLoai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewTheLoai = itemView.findViewById(R.id.imageviewtheloai);
            textViewTheLoai = itemView.findViewById(R.id.textviewtheloai);
        }
    }
}
