package com.dinklokcode.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dinklokcode.musicapp.Adapter.ViewPagerPlayListNhac;
import com.dinklokcode.musicapp.Fragment.Fragment_Dia_Nhac;
import com.dinklokcode.musicapp.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarPlayNhac;
    TextView txtTimeSong, txtTotalTimeSong;
    SeekBar sktime;
    ImageButton imgPlay, imgRepeat, imgnext, imgpre, imgrandom;
    ViewPager viewPagerPlayNhac;
    public static ArrayList<BaiHatModel> mangbaihat = new ArrayList<>();
    public static ViewPagerPlayListNhac adapternhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    MediaPlayer mediaPlayer;

    public static int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;

    public static boolean next = false;

    public static boolean checkmenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        init();
        getDataFromIntent();
        eventClick();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHatModel baiHatModel = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHatModel);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHatModel> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;
            }
        }

        if (mangbaihat.size() > 0) {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.iconlove);
        }

    }

    private void init() {
        toolbarPlayNhac = findViewById(R.id.toolbarplaynhac);
        txtTimeSong = findViewById(R.id.textviewtimesong);
        txtTotalTimeSong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgRepeat = findViewById(R.id.imagebuttonrepeat);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgpre = findViewById(R.id.imagebuttonpreview);
        viewPagerPlayNhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);
        adapternhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac.AddFragment(fragment_dia_nhac);
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        viewPagerPlayNhac.setAdapter(adapternhac);
//        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
    }
    class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            TimeSong();
        }
    }


    private void eventClick() {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (adapternhac.getItem(1) != null) {
//                    if (mangbaihat.size() > 0) {
//                        fragment_dia_nhac.PlayNhac(mangbaihat.get(0).getHinhBaiHat());
//                        handler.removeCallbacks(this);
//                    } else {
//                        handler.postDelayed(this, 300);
//                    }
//                }
//            }
//        }, 500);
//        imgPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.pause();
//                    imgPlay.setImageResource(R.drawable.iconlove);
//                } else {
//                    mediaPlayer.start();
//                    imgPlay.setImageResource(R.drawable.iconnext);
//                }
//            }
//        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                    if(mangbaihat.size() >0 ){
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else{
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.nutplay);
                }else{
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.nutpause);
                }
            }
        });

        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!repeat){
                    if(checkrandom){
                        checkrandom = false;
                        imgRepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else{
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkrandom){
                    if(repeat){
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else{
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.nutplay);
                }else{
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.nutpause);
                }
                checkmenu = true;
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())) {
                        imgPlay.setImageResource(R.drawable.nutpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                        //fragment_loi_bai_hat.ShowLoi();
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() >0){
                    if(mediaPlayer.isPlaying() || mediaPlayer !=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < (mangbaihat.size())){
                        imgPlay.setImageResource(R.drawable.nutpause);
                        position--;
                        if(position <0){ position = mangbaihat.size()-1; }
                        if(repeat == true){ position +=1; }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){ position = index-1; }
                            position = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                        //fragment_loi_bai_hat.ShowLoi();
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true) {
                    if (position < (mangbaihat.size())) {
                        imgPlay.setImageResource(R.drawable.nutpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                        //fragment_loi_bai_hat.ShowLoi();
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                    }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    },5000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else{
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}