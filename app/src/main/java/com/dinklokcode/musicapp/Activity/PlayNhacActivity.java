package com.dinklokcode.musicapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.dinklokcode.musicapp.Adapter.ViewPagerPlaylistNhac;
import com.dinklokcode.musicapp.Fragment.Fragment_DiaNhac;
import com.dinklokcode.musicapp.Fragment.Fragment_Loi_Bai_Hat;
import com.dinklokcode.musicapp.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {
    public static int position = 0;

    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgrandom;

    ViewPager viewPagerplaynhac;
    Fragment_DiaNhac fragment_diaNhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    Fragment_Loi_Bai_Hat fragment_loi_bai_hat;
    public static MediaPlayer mediaPlayer;

    public static ArrayList<BaiHatModel> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistNhac adapternhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();
    }



    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if(intent != null){
            if(intent.hasExtra("cakhuc")){
                BaiHatModel baiHatModel = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHatModel);
            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<BaiHatModel> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;
            }
        }
    }

    private void init() {
        //position = 0;
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
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mangbaihat.clear();
                finish();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_diaNhac = new Fragment_DiaNhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        fragment_loi_bai_hat = new Fragment_Loi_Bai_Hat();
        adapternhac = new ViewPagerPlaylistNhac(getSupportFragmentManager(),1);
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        adapternhac.AddFragment(fragment_diaNhac);
        adapternhac.AddFragment(fragment_loi_bai_hat);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_diaNhac = (Fragment_DiaNhac) adapternhac.getItem(1);
        fragment_loi_bai_hat = (Fragment_Loi_Bai_Hat) adapternhac.getItem(2);
        if(mangbaihat.size()>0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }

    private void eventClick() {
    }

}
