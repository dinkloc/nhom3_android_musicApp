package com.dinklokcode.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Activity.PlayNhacActivity;
import com.dinklokcode.musicapp.Activity.PlayNhacOfflineActivity;
import com.dinklokcode.musicapp.Adapter.PlayNhacAdapter;
import com.dinklokcode.musicapp.Adapter.PlayNhacOfflineAdapter;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.Model.BaiHatOffline;
import com.dinklokcode.musicapp.R;

import java.util.ArrayList;

public class Fragment_Play_Danh_Sach_Cac_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewplaynhac;
    PlayNhacAdapter playnhacAdapter;
    PlayNhacOfflineAdapter playNhacOfflineAdapter;
    ArrayList<BaiHatModel> mangbaihat;
    ArrayList<BaiHatOffline> mangbaihatoff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat,container,false);
        recyclerViewplaynhac = view.findViewById(R.id.recycleviewPlaybaihat);
        mangbaihat = PlayNhacActivity.mangbaihat;
        if(PlayNhacActivity.mangbaihat.size()>0){
            playnhacAdapter = new PlayNhacAdapter(getActivity(),(ArrayList<BaiHatModel>) PlayNhacActivity.mangbaihat);
            recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaynhac.setAdapter(playnhacAdapter);
        }
        mangbaihatoff = PlayNhacOfflineActivity.mangbaihat;
        if(PlayNhacOfflineActivity.mangbaihat.size()>0){
            playNhacOfflineAdapter = new PlayNhacOfflineAdapter(getActivity(), PlayNhacOfflineActivity.mangbaihat);
            recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaynhac.setAdapter(playNhacOfflineAdapter);
        }
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        PlayNhacActivity.mangbaihat.clear();
    }
}
