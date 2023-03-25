package com.dinklokcode.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dinklokcode.musicapp.Adapter.DanhsachbaihatAdapter;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.Model.ChuDeModel;
import com.dinklokcode.musicapp.Model.NgheSiModel;
import com.dinklokcode.musicapp.Model.PlaylistModel;
import com.dinklokcode.musicapp.Model.Quangcao;
import com.dinklokcode.musicapp.Model.TheLoaiModel;
import com.dinklokcode.musicapp.Model.ThinhHanhModel;
import com.dinklokcode.musicapp.R;
import com.dinklokcode.musicapp.Service.APIService;
import com.dinklokcode.musicapp.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHat_Activity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    TextView txtcollapsing;
    ImageView imgdanhsachcakhuc;
    ArrayList<BaiHatModel> mangBaiHat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    Quangcao quangcao;
    ImageView btnThemnhac;
    SwipeRefreshLayout swipeRefreshLayout;
    PlaylistModel playlistModel,playlistCanhan;
    ChuDeModel chuDeModel;
    NgheSiModel ngheSiModel;
    ThinhHanhModel thinhHanhModel;
    TheLoaiModel theLoaiModel;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        AnhXa();
        DataIntent();
        eventClick();
        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            getDataQuangCao(quangcao.getIdQuangCao());
            txtcollapsing.setText(quangcao.getNoidung());
        }
        if (playlistModel != null && !playlistModel.getTen().equals("")) {
            setValueInView(playlistModel.getTen(), playlistModel.getHinhPlaylist());
            getDataPlayList(playlistModel.getIdPlaylist());
            txtcollapsing.setText(playlistModel.getTen());
        }
        if( playlistCanhan !=null && !playlistCanhan.getTen().equals("")){
            setValueInView(playlistCanhan.getTen(),playlistCanhan.getHinhPlaylist());
            getDataPlayListCanhan(playlistCanhan.getIdPlaylist());
        }
        if (ngheSiModel!=null && !ngheSiModel.getTenNgheSi().equals("")) {
            setValueInView(ngheSiModel.getTenNgheSi(), ngheSiModel.getHinhNgheSi());
            getDataNgheSi(ngheSiModel.getIdNgheSi());
            txtcollapsing.setText(ngheSiModel.getTenNgheSi());
        }

        if (chuDeModel!=null && !chuDeModel.getTenChuDe().equals("")) {
            setValueInView(chuDeModel.getTenChuDe(), chuDeModel.getHinhChuDe());
            getDataChuDe(chuDeModel.getIdChuDe());
            txtcollapsing.setText(chuDeModel.getTenChuDe());
        }

        if (thinhHanhModel!=null && !thinhHanhModel.getTenThinhHanh().equals("")) {
            setValueInView(thinhHanhModel.getTenThinhHanh(), thinhHanhModel.getHinhThinhHanh());
            getDataThinhHanh(thinhHanhModel.getIdThinhHanh());
            txtcollapsing.setText(thinhHanhModel.getTenThinhHanh());
        }

        if (theLoaiModel!=null && !theLoaiModel.getTenTheLoai().equals("")) {
            setValueInView(theLoaiModel.getTenTheLoai(), theLoaiModel.getHinhTheLoai());
            getDataTheLoai(theLoaiModel.getIdTheLoai());
            txtcollapsing.setText(theLoaiModel.getTenTheLoai());
        }
        event();
    }

    private void event() {
        if(playlistCanhan!=null){
            btnThemnhac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DanhSachBaiHat_Activity.this,InsertBH_playlistActivity.class);
                    String idplaylist = playlistCanhan.getIdPlaylist();
                    Bundle d = new Bundle();
                    d.putString("id",idplaylist);
                    intent.putExtra("playlist", d);
                    startActivity(intent);
                }
            });
        }
        else{
            btnThemnhac.setOnClickListener(null);
        }
    }

    private void getDataPlayListCanhan(String idPlaylist) {
        DataService db = APIService.getService();
        Call<List<BaiHatModel>> cb = db.GetBSBaiHat_Playlistcanhan(idPlaylist);
        cb.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangBaiHat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHat_Activity.this,mangBaiHat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHat_Activity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }

    private void getDataTheLoai(String idTheLoai) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatModel>> callback = dataService.GetDanhSachBaiHatTheoTheLoai(idTheLoai);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangBaiHat = (ArrayList<BaiHatModel>) response.body();
                DanhsachbaihatAdapter danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHat_Activity.this, mangBaiHat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHat_Activity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }

    private void getDataChuDe(String idChuDe) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatModel>> callback = dataService.GetDanhSachBaiHatChuDe(idChuDe);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangBaiHat = (ArrayList<BaiHatModel>) response.body();
                DanhsachbaihatAdapter danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHat_Activity.this, mangBaiHat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHat_Activity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }





    private void getDataPlayList(String idPlayList) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatModel>> callback = dataService.GetDanhSachBaiHatTheoPlayList(idPlayList);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangBaiHat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHat_Activity.this, mangBaiHat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHat_Activity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }

    private void getDataQuangCao(String idQuangCao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatModel>> callback = dataService.GetDanhSachBaiHatTheoQuangCao(idQuangCao);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangBaiHat = (ArrayList<BaiHatModel>) response.body();
                DanhsachbaihatAdapter danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHat_Activity.this, mangBaiHat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHat_Activity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }


    private void getDataNgheSi(String idNgheSi) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatModel>> callback = dataService.GetDanhsachbaihatnghesi(idNgheSi);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangBaiHat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHat_Activity.this, mangBaiHat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHat_Activity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }

    private void getDataThinhHanh(String idThinhHanh) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatModel>> callback = dataService.GetDanhSachBaiHatTheoThinhHanh(idThinhHanh);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangBaiHat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHat_Activity.this, mangBaiHat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHat_Activity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }


    private void AnhXa() {
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        txtcollapsing = findViewById(R.id.textViewcollapsing);
        btnThemnhac = findViewById(R.id.btnthemnhacthuvien);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        floatingActionButton.setEnabled(false);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemplaylist")) {
                playlistModel = (PlaylistModel) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("playlistcanhan")) {
                playlistCanhan = (PlaylistModel) intent.getSerializableExtra("playlistcanhan");
            }
            if (intent.hasExtra("itemnghesi")) {
                ngheSiModel = (NgheSiModel) intent.getSerializableExtra("itemnghesi");
            }
            if (intent.hasExtra("intentthinhhanh")) {
                thinhHanhModel = (ThinhHanhModel) intent.getSerializableExtra("intentthinhhanh");
            }
            if (intent.hasExtra("intenttheloai")) {
                theLoaiModel = (TheLoaiModel) intent.getSerializableExtra("intenttheloai");
            }
        }
    }

    private void eventClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHat_Activity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangBaiHat);
                startActivity(intent);
            }
        });
    }

}