package com.dinklokcode.musicapp.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Model.BaiHatOffline;

import java.util.ArrayList;

public class PlayNhacOfflineAdapter extends RecyclerView.Adapter {


    public PlayNhacOfflineAdapter(FragmentActivity activity, ArrayList<BaiHatOffline> mangbaihat) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
