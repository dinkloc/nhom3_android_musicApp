package com.dinklokcode.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Activity.DanhSachBaiHat_Activity;
import com.dinklokcode.musicapp.Model.NgheSiModel;
import com.dinklokcode.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NgheSiAdapter extends RecyclerView.Adapter<NgheSiAdapter.ViewHolder> {
    Context context;
    ArrayList<NgheSiModel> mangnghesi;
    View view;

    public NgheSiAdapter(Context context, ArrayList<NgheSiModel> mangnghesi) {
        this.context = context;
        this.mangnghesi = mangnghesi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_nghesi,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        NgheSiModel ngheSi = mangnghesi.get(position);
        holder.txttennghesi.setText(ngheSi.getTenNgheSi());
        Picasso.with(context).load(ngheSi.getHinhNgheSi()).into(holder.imgnghesi);
        int a = position;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHat_Activity.class);
                intent.putExtra("itemnghesi", mangnghesi.get(a));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mangnghesi != null ? mangnghesi.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView imgnghesi;
        TextView txttennghesi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgnghesi = itemView.findViewById(R.id.imageviewnghesi);
            txttennghesi = itemView.findViewById(R.id.textviewnghesi);
        }
    }
}
