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
import com.dinklokcode.musicapp.Model.ChuDeModel;

import com.dinklokcode.musicapp.Model.PlaylistModel;
import com.dinklokcode.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChuDeAdapter extends RecyclerView.Adapter<ChuDeAdapter.ViewHolder>{

    Context context;
    ArrayList<ChuDeModel> mangchude;
    View view;

    public ChuDeAdapter(Context context, ArrayList<ChuDeModel> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ChuDeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_chude,parent, false);
        return new ChuDeAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ChuDeAdapter.ViewHolder holder, final int position) {
        ChuDeModel chuDe = mangchude.get(position);
        holder.txttenchude.setText(chuDe.getTenChuDe());
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imgchude);
        int a = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHat_Activity.class);
                intent.putExtra("itemchude", mangchude.get(a));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangchude != null ? mangchude.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgchude;
        TextView txttenchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewchude);
            txttenchude = itemView.findViewById(R.id.textviewchude);
        }
    }
}
