package com.dinklokcode.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Activity.DanhSachBaiHat_Activity;
import com.dinklokcode.musicapp.Model.PlaylistModel;
import com.dinklokcode.musicapp.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder>{

    Context context;
    ArrayList<PlaylistModel> mangplaylist;
    View view;
    String username="";
    public PlaylistAdapter(Context context, ArrayList<PlaylistModel> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    public PlaylistAdapter(Context context, ArrayList<PlaylistModel> mangplaylist, String username) {
        this.context = context;
        this.mangplaylist = mangplaylist;
        this.username = username;
    }

    public int sỉze(){
        return mangplaylist.size();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_playlist,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        PlaylistModel playlist = mangplaylist.get(position);
        holder.txttenplaylist.setText(playlist.getTen());
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imgplaylist);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHat_Activity.class);
                if(username!=""){
                    intent.putExtra("playlistcanhan",playlist);
                }
                else{
                    intent.putExtra("itemplaylist",playlist);
                }
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgplaylist;
        TextView txttenplaylist;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgplaylist = itemView.findViewById(R.id.imageviewplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewplaylist);
            cardView = itemView.findViewById(R.id.cardviewPlaylist);
        }
    }
}
