package com.dinklokcode.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Activity.PlayNhacActivity;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.R;
import com.dinklokcode.musicapp.Service.APIService;
import com.dinklokcode.musicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatAdapter extends RecyclerView.Adapter<BaiHatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHatModel> bhArray;
    String currentbh = "";
    String username = "";
    String idplaylist = "";
    public BaiHatAdapter(Context context, ArrayList<BaiHatModel> bhAdapder) {
        this.context = context;
        this.bhArray = bhAdapder;
    }

    public BaiHatAdapter(Context context, ArrayList<BaiHatModel> bhArray, String value,String key) {
        this.context = context;
        this.bhArray = bhArray;
        if(key=="username"){
            this.username = value;
        }
        else{
            this.idplaylist = value;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHatModel baiHat = bhArray.get(position);
        holder.txtCasi.setText(baiHat.getTenCaSi());
        holder.txtTenbh.setText(baiHat.getTenBaiHat());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgHinhbh);
        holder.itemcakhuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("cakhuc", baiHat);
                context.startActivity(intent);
            }
        });
        if(username==""){
            holder.imgThich.setImageResource(R.drawable.addbh);
            holder.imgThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataService db = APIService.getService();
                    if (idplaylist != null) {
                        Call<String> cb = db.AddBaihatvaoPlaylist(idplaylist.toString(), baiHat.getIdBaiHat().toString());
                        cb.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body().equals("OK")) {
                                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                } else if (response.body().equals("Fail")) {
                                    Toast.makeText(context, "Đã có bài hát trong playlist này", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Thêm lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(context, "Thêm lỗi", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        else {
            holder.imgThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataService db = APIService.getService();
                    if(currentbh != baiHat.getIdBaiHat()){
                        currentbh = baiHat.getIdBaiHat();
                        holder.imgThich.setImageResource(R.drawable.love);
                        Call<String> str = db.UpdateBaiHatYT("username",baiHat.getIdBaiHat());
                        str.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.body().equals("OK")){
                                    bhArray.remove(baiHat);
                                    Toast.makeText(context,"Bỏ thích",Toast.LENGTH_SHORT);
                                }
                                else{
                                    Toast.makeText(context,"Lỗi",Toast.LENGTH_SHORT);
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }
                    else{
                        holder.imgThich.setImageResource(R.drawable.loved);
                        Call<String> str = db.UpdateBaiHatYT("username",baiHat.getIdBaiHat());
                        str.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.body().equals("OK")){
                                    bhArray.add(baiHat);
                                    Toast.makeText(context,"Thích",Toast.LENGTH_SHORT);
                                }
                                else{
                                    Toast.makeText(context,"Lỗi",Toast.LENGTH_SHORT);
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        currentbh = "";
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bhArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenbh,txtCasi;
        ImageView imgHinhbh,imgThich;
        RelativeLayout itemcakhuc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbh = itemView.findViewById(R.id.txtTenBaihat);
            txtCasi = itemView.findViewById(R.id.txtCasiBaihai);
            imgHinhbh = itemView.findViewById(R.id.imgBaihat);
            imgThich = itemView.findViewById(R.id.imgthich);
            itemcakhuc = itemView.findViewById(R.id.itemcakhuc);
        }
    }
}
