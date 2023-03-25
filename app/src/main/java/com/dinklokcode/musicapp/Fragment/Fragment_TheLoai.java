package com.dinklokcode.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Adapter.TheloaiAdapther;
import com.dinklokcode.musicapp.Model.TheLoaiModel;
import com.dinklokcode.musicapp.R;
import com.dinklokcode.musicapp.Service.APIService;
import com.dinklokcode.musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TheLoai extends Fragment {
    View view;
    TheloaiAdapther theloaiAdapther;
    RecyclerView recyclerViewTheLoai;
    TextView textViewTheLoai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_theloai, container, false);
        recyclerViewTheLoai = view.findViewById(R.id.recyclerviewtheloai);
        textViewTheLoai = view.findViewById(R.id.textviewtheloai);
        getData();
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoaiModel>> callback = dataService.GetTheLoai();
        callback.enqueue(new Callback<List<TheLoaiModel>>() {
            @Override
            public void onResponse(Call<List<TheLoaiModel>> call, Response<List<TheLoaiModel>> response) {
                ArrayList<TheLoaiModel> mangtheloai = (ArrayList<TheLoaiModel>) response.body();
                theloaiAdapther = new TheloaiAdapther(getActivity(), mangtheloai);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
                recyclerViewTheLoai.setLayoutManager(linearLayoutManager);
                recyclerViewTheLoai.setAdapter(theloaiAdapther);
            }

            @Override
            public void onFailure(Call<List<TheLoaiModel>> call, Throwable t) {

            }
        });
    }
}
