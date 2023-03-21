package com.dinklokcode.musicapp.Adapter;

import android.content.Context;
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
//    String username = ""
    public BaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArraylist) {
        this.context = context;
        this.baiHatArraylist = baiHatArraylist;
//        username = context.getString()
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
                    Call<String> str = db.UpdateBaiHatYT("username",currentbh,"add");
                    str.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.body()=="OK"){
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
