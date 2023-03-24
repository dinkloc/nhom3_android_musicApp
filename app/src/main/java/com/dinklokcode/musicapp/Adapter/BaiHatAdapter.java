package com.dinklokcode.musicapp.Adapter;

import android.content.Context;
<<<<<<< Updated upstream
=======
import android.content.Intent;
import android.util.Log;
>>>>>>> Stashed changes
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Model.BaiHat;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.R;
import com.dinklokcode.musicapp.Service.APIService;
import com.dinklokcode.musicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatAdapter extends RecyclerView.Adapter<BaiHatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> baiHatArraylist;
    String currentbh = "";
<<<<<<< Updated upstream
//    String username = ""
    public BaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArraylist) {
        this.context = context;
        this.baiHatArraylist = baiHatArraylist;
//        username = context.getString()
=======
    String username = "";
    String idplaylist = "";
    public BaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArraylist) {
        this.context = context;
        this.baiHatArraylist = baiHatArraylist;
    }

    public int size(){
        return baiHatArraylist.size();
    }
    public BaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArraylist, String value,String key) {
        this.context = context;
        this.baiHatArraylist = baiHatArraylist;
        if(key=="username"){
            this.username = value;
        }
        else{
            this.idplaylist = value;
        }
>>>>>>> Stashed changes
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
        BaiHat baihat = baiHatArraylist.get(position);
        holder.txtTenCasi.setText(baihat.getCaSi());
        holder.txtTenBaihat.setText(baihat.getTenBaiHat());
        Picasso.with(context).load(baihat.getHinhBaiHat()).into(holder.imgBaihat);
<<<<<<< Updated upstream
        holder.imgThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService db = APIService.getService();
                if(currentbh != baihat.getIdBaiHat()){
                    currentbh = baihat.getIdBaiHat();
                    holder.imgThich.setImageResource(R.drawable.love);
                    Call<String> str = db.UpdateBaiHatYT("username",baihat.getIdBaiHat(),"delete");
                    str.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.body()=="OK"){
                                Toast.makeText(context,"Bỏ thích",Toast.LENGTH_SHORT);
=======
        if(username==""){
            holder.imgThich.setImageResource(R.drawable.addbh);
            holder.imgThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataService db = APIService.getService();
                    if(idplaylist!=null){
                        Call<String> cb = db.AddBaihatvaoPlaylist(idplaylist.toString(),baihat.getIdBaiHat().toString());
                        cb.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.body().equals("OK")){
                                    Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                                }
                                else if (response.body().equals("Fail")){
                                    Toast.makeText(context,"Đã có bài hát trong playlist này",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(context,"Thêm lỗi",Toast.LENGTH_SHORT).show();
                                }
>>>>>>> Stashed changes
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(context,"Thêm lỗi",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
<<<<<<< Updated upstream
                else{
                    holder.imgThich.setImageResource(R.drawable.loved);
                    Call<String> str = db.UpdateBaiHatYT("username",baihat.getIdBaiHat(),"add");
                    str.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.body()=="OK"){
                                Toast.makeText(context,"Thích",Toast.LENGTH_SHORT);
=======
            });
        }
        else {
            holder.imgThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataService db = APIService.getService();
                    if (currentbh != baihat.getIdBaiHat()) {
                        currentbh = baihat.getIdBaiHat();
                        holder.imgThich.setImageResource(R.drawable.love);
                        Call<String> str = db.UpdateBaiHatYT(username, baihat.getIdBaiHat());
                        str.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body() == "OK") {
                                    Toast.makeText(context, "Bỏ thích", Toast.LENGTH_SHORT);
                                } else {
                                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT);
                                }
>>>>>>> Stashed changes
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    } else {
                        holder.imgThich.setImageResource(R.drawable.loved);
                        Log.d("BBB", baihat.getTenBaiHat());
                        Call<String> str = db.UpdateBaiHatYT(username, baihat.getIdBaiHat());
                        str.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body() == "OK") {
                                    baiHatArraylist.add(baihat);
                                    Toast.makeText(context, "Thích", Toast.LENGTH_SHORT);
                                } else {
                                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT);
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
        return baiHatArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenBaihat,txtTenCasi;
        ImageView imgBaihat,imgThich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaihat = itemView.findViewById(R.id.txtTenBaihat);
            txtTenCasi = itemView.findViewById(R.id.txtCasiBaihai);
            imgBaihat = itemView.findViewById(R.id.imgBaihat);
            imgThich = itemView.findViewById(R.id.imgthich);
        }
    }
}
