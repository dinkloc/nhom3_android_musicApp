package com.dinklokcode.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dinklokcode.musicapp.Adapter.PlaylistAdapter;
import com.dinklokcode.musicapp.Model.PlaylistModel;
import com.dinklokcode.musicapp.R;
import com.dinklokcode.musicapp.Service.APIService;
import com.dinklokcode.musicapp.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibPListActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView DSallPlaylist;
    ImageView imgAdd;
    EditText playlistname;
    PlaylistAdapter PlaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_plist);
        anhxa();
        init();
    }

    public void  init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DataService db = new APIService().getService();
        Call<List<PlaylistModel>> callback = db.GetDSPlayListCaNhan("username");
        callback.enqueue(new Callback<List<PlaylistModel>>() {
            @Override
            public void onResponse(Call<List<PlaylistModel>> call, Response<List<PlaylistModel>> response) {
                ArrayList<PlaylistModel> mangplaylist = (ArrayList<PlaylistModel>) response.body();
                if(mangplaylist.size()>0){
                    PlaylistAdapter = new PlaylistAdapter(LibPListActivity.this, mangplaylist);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LibPListActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    DSallPlaylist.setLayoutManager(linearLayoutManager);
                    if(PlaylistAdapter.sỉze()>0){
                        DSallPlaylist.setAdapter(PlaylistAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PlaylistModel>> call, Throwable t) {

            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenplaylist = playlistname.getText().toString().trim();
                if (tenplaylist.length() < 1 || tenplaylist.length() > 36){
                    Toast.makeText(LibPListActivity.this, "Độ dài tên playlist từ 1 -> 36 ký tự", Toast.LENGTH_SHORT).show();
                }
                else {
                    DataService db = APIService.getService();
                    Call<String> callb = db.InsertPlayList("username",tenplaylist);
                    callb.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.body()=="OK"){
                                Toast.makeText(LibPListActivity.this,"Thêm thành công ",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LibPListActivity.this,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapstoolbar_plist);
        toolbar = findViewById(R.id.toolbardsplist);
        DSallPlaylist = findViewById(R.id.DSAllPlaylist);
        imgAdd = findViewById(R.id.imgAdd);
        playlistname = findViewById(R.id.playlistname);
    }
}