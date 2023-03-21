package com.dinklokcode.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Activity.PlayNhacActivity;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHatModel> mangbaihat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHatModel> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHatModel baihat = mangbaihat.get(position);
        holder.txttencasi.setText(baihat.getTenCaSi());
        holder.txtindex.setText(position + 1 + "");
        holder.txttenbaihat.setText(baihat.getTenBaiHat());
        holder.txt.setText(baihat.getTenCaSi()+"");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtindex, txttenbaihat, txttencasi,txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttencasi = itemView.findViewById(R.id.textviewplaynhactencasi);
            txtindex = itemView.findViewById(R.id.textviewplaynhacindex);
            txttenbaihat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
            txt = itemView.findViewById(R.id.textviewplaynhacindex1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
//                    if(PlayNhacActivity.mangbaihat.size()>0 && next == false){
//                        if(mediaPlayer.isPlaying() || mediaPlayer !=null){
//                            mediaPlayer.stop();
//                            mediaPlayer.release();
//                            mediaPlayer = null;
//                        }
//                    }
                }
            });
        }
    }
}
