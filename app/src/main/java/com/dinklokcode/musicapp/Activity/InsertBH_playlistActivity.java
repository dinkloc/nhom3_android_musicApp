package com.dinklokcode.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinklokcode.musicapp.Adapter.BaiHatAdapter;
import com.dinklokcode.musicapp.Model.BaiHat;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.Model.PlaylistModel;
import com.dinklokcode.musicapp.R;
import com.dinklokcode.musicapp.Service.APIService;
import com.dinklokcode.musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertBH_playlistActivity extends AppCompatActivity {

    BaiHatAdapter arrayBH;
    RecyclerView DSBaiHat;
    EditText txtSearch;
    ImageView back;
    TextView txtnotfound;
    String idPlaylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_bh_playlist);
        DSBaiHat = findViewById(R.id.lstBaihat);
        txtSearch = findViewById(R.id.txtSearch);
        back = findViewById(R.id.back);
        txtnotfound = findViewById(R.id.notfound);
        getData();
        setEvent();
    }

    private void setEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = txtSearch.getText().toString();
                if(search!=""){
                    DataService db = APIService.getService();
                    Call<List<BaiHatModel>> cb = db.GetSearchBaiHat(search);
                    cb.enqueue(new Callback<List<BaiHatModel>>() {
                        @Override
                        public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                            ArrayList<BaiHatModel> mangbh = (ArrayList<BaiHatModel>) response.body();
                            arrayBH = new BaiHatAdapter(InsertBH_playlistActivity.this,mangbh);
                            DSBaiHat.setAdapter(arrayBH);
                            if(mangbh.size()>0){
                                txtnotfound.setVisibility(View.INVISIBLE);
                            }
                            else{
                                txtnotfound.setVisibility(View.VISIBLE);
                                txtnotfound.setText("Không tìm thấy bài hát "+search);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getData() {
        DataService db = APIService.getService();
        Call<List<BaiHatModel>> callback = db.GetAllBaihat();
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                ArrayList<BaiHatModel> mangbh = (ArrayList<BaiHatModel>) response.body();
                Intent t = getIntent();
                if(t!=null){
                    Bundle plist = t.getBundleExtra("playlist");
                    arrayBH = new BaiHatAdapter(InsertBH_playlistActivity.this,mangbh,plist.getString("id"),"playlist");
                }else{
                    arrayBH = new BaiHatAdapter(InsertBH_playlistActivity.this,mangbh);
                }
                if(mangbh.size()>0){
                    LinearLayoutManager ln = new LinearLayoutManager(InsertBH_playlistActivity.this);
                    ln.setOrientation(LinearLayoutManager.VERTICAL);
                    DSBaiHat.setLayoutManager(ln);
                    if(arrayBH.getItemCount()>0){
                        DSBaiHat.setAdapter(arrayBH);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }
}