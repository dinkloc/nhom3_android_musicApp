package com.dinklokcode.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Model.RadioModel;
import com.dinklokcode.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder>  {
    Context context;
    ArrayList<RadioModel> mangradio;
    View view;

    public RadioAdapter(Context context, ArrayList<RadioModel> mangradio) {
        this.context = context;
        this.mangradio = mangradio;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_radio,parent, false);
        return new RadioAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RadioModel radioModel = mangradio.get(position);
        holder.txtTenRadio.setText(radioModel.getNoiDungRadio());
        Picasso.with(context).load(radioModel.getHinhAnhRadio()).into(holder.imgRadio);


    }

    @Override
    public int getItemCount() {
        return mangradio.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRadio;
        TextView txtTenRadio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRadio = itemView.findViewById(R.id.imageviewradio);
            txtTenRadio = itemView.findViewById(R.id.textviewradio);
        }
    }
}
