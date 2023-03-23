package com.dinklokcode.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Activity.LibPListActivity;
import com.dinklokcode.musicapp.Activity.MainActivity;
import com.dinklokcode.musicapp.Adapter.BaiHatAdapter;
import com.dinklokcode.musicapp.Adapter.PlaylistAdapter;
import com.dinklokcode.musicapp.Model.BaiHat;
import com.dinklokcode.musicapp.Model.BaiHatModel;
import com.dinklokcode.musicapp.Model.ChuDeModel;
import com.dinklokcode.musicapp.Model.NgheSiModel;
import com.dinklokcode.musicapp.Model.PlaylistModel;
import com.dinklokcode.musicapp.Model.Quangcao;
import com.dinklokcode.musicapp.Model.RadioModel;
import com.dinklokcode.musicapp.Model.ThinhHanhModel;
import com.dinklokcode.musicapp.R;
import com.dinklokcode.musicapp.Service.AIPRetrofitClient;
import com.dinklokcode.musicapp.Service.APIService;
import com.dinklokcode.musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryFragment extends Fragment {
    View view;
    TextView txtCanhan,txtTaoPlaylist;
    PlaylistAdapter PlaylistAdapter;
    BaiHatAdapter baiHatAdapter;
    RecyclerView PlaylistCaNhan, DsNhacYeuThich;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_library,container,false);
        txtCanhan = view.findViewById(R.id.txtcanhan);
        PlaylistCaNhan = view.findViewById(R.id.playlistcanhan);
        txtTaoPlaylist = view.findViewById(R.id.taoplaylist);
        DsNhacYeuThich = view.findViewById(R.id.dsbaihatdathich);
        GetDataDSBaihat();
        GetDataPlaylist();
        txtTaoPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(getActivity(),LibPListActivity.class);
                startActivity(t);
            }
        });
        return view;
    }

    private void GetDataDSBaihat() {
        DataService db = APIService.getService();
        Call<List<BaiHat>> callback = db.GetDSBaiHatYTCaNhan("username");
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbh = (ArrayList<BaiHat>) response.body();
                Log.d("BBB", String.valueOf(mangbh.size()));
                if(mangbh.size()>0){
                    baiHatAdapter = new BaiHatAdapter(getActivity(),mangbh);
                    LinearLayoutManager ln = new LinearLayoutManager(getActivity());
                    ln.setOrientation(LinearLayoutManager.VERTICAL);
                    DsNhacYeuThich.setLayoutManager(ln);
                    Log.d("CCC", String.valueOf(baiHatAdapter.size()));
                    if(baiHatAdapter.size()>0){
                        DsNhacYeuThich.setAdapter(baiHatAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist() {
        DataService dataservice = APIService.getService();
        Call<List<PlaylistModel>> callback = dataservice.GetDSPlayListCaNhan("username");
        callback.enqueue(new Callback<List<PlaylistModel>>() {
            @Override
            public void onResponse(Call<List<PlaylistModel>> call, Response<List<PlaylistModel>> response) {
                ArrayList<PlaylistModel> mangplaylist = (ArrayList<PlaylistModel>) response.body();
                if(mangplaylist.size()>0){
                    PlaylistAdapter = new PlaylistAdapter(getActivity(), mangplaylist);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    PlaylistCaNhan.setLayoutManager(linearLayoutManager);
                    if(PlaylistAdapter.sỉze()>0){
                        PlaylistCaNhan.setAdapter(PlaylistAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PlaylistModel>> call, Throwable t) {

            }
        });
    }
}