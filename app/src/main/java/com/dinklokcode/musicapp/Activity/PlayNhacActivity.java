package com.dinklokcode.musicapp.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.dinklokcode.musicapp.Adapter.ViewPagerPlaylistNhac;
import com.dinklokcode.musicapp.Fragment.Fragment_Dia_Nhac;
import com.dinklokcode.musicapp.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {
    int position = 0;
    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgrandom;
    ViewPager viewPagerplaynhac;

    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;

    public static ArrayList<BaiHatModel> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistNhac adapternhac;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.share_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case  R.id.menu_main:
//                Dialog dialog = new Dialog(PlayNhacActivity.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.activity_menu);
//                CustomDialog customDialog = new CustomDialog(this,mangbaihat,position,dialog);
//                customDialog.init();
//                customDialog.eventClick();
//                customDialog.setData();
//                dialog.show();
//                Log.d("",mangbaihat.get(position).getTenbaihat()+"-"+
//                        mangbaihat.get(position).getCasi()+"-"+mangbaihat.get(position).getHinhbaihat());
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        imgpre = findViewById(R.id.imagebuttonpreview);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgnext = findViewById(R.id.imagebuttonnext);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);

//        setSupportActionBar(toolbarplaynhac);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setActionBar(toolbarplaynhac);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mangbaihat.clear();
                finish();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);

        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac = new ViewPagerPlaylistNhac(getSupportFragmentManager(),1);
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        adapternhac.AddFragment(fragment_dia_nhac);

        viewPagerplaynhac.setAdapter(adapternhac);
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if(intent!=null){
            if(intent.hasExtra("cakhuc")){
                BaiHatModel baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);
            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<BaiHatModel> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;
            }
        }
    }
}
