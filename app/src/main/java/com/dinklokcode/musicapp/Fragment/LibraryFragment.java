package com.dinklokcode.musicapp.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.ArrayRes;
import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Activity.LibPListActivity;
import com.dinklokcode.musicapp.Activity.LoginActivity;
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
    static String username ="username";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_library,container,false);
        anhxa();
        if(username==""){
            txtTaoPlaylist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            event();
        }
        else{
            txtCanhan.setText(username);
            init();
        }
        return view;

    }

    private void anhxa() {
        txtCanhan = view.findViewById(R.id.txtcanhan);
        PlaylistCaNhan = view.findViewById(R.id.playlistcanhan);
        DsNhacYeuThich = view.findViewById(R.id.dsbaihatdathich);
        txtTaoPlaylist = view.findViewById(R.id.taoplaylist);
    }

    private void init() {
        GetDataDSBaihat();
        GetDataPlaylist();
        txtTaoPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LibPListActivity.class);
                Bundle d = new Bundle();
                d.putString("username",username);
                intent.putExtra("user",d);
                startActivity(intent);
            }
        });
        txtCanhan.setOnClickListener(null);
    }

    private void event() {
        txtCanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                mGetResultLauncher.launch(intent);
            }
        });
    }

    private void GetDataDSBaihat() {
        DataService db = APIService.getService();
        Call<List<BaiHatModel>> callback = db.GetDSBaiHatYTCaNhan(username);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                ArrayList<BaiHatModel> mangbh = (ArrayList<BaiHatModel>) response.body();
                Log.d("CCC",username);
                if(mangbh.size()>0){
                    baiHatAdapter = new BaiHatAdapter(getActivity(),mangbh,username,"username");
                    LinearLayoutManager ln = new LinearLayoutManager(getActivity());
                    ln.setOrientation(LinearLayoutManager.VERTICAL);
                    DsNhacYeuThich.setLayoutManager(ln);
                    if(baiHatAdapter.getItemCount()>0){
                        DsNhacYeuThich.setAdapter(baiHatAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist() {
        DataService dataservice = APIService.getService();
        Call<List<PlaylistModel>> callback = dataservice.GetDSPlayListCaNhan(username);
        callback.enqueue(new Callback<List<PlaylistModel>>() {
            @Override
            public void onResponse(Call<List<PlaylistModel>> call, Response<List<PlaylistModel>> response) {
                ArrayList<PlaylistModel> mangplaylist = (ArrayList<PlaylistModel>) response.body();
                PlaylistAdapter = new PlaylistAdapter(getActivity(), mangplaylist,username);
                Log.d("ASize", String.valueOf(mangplaylist.size()));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                PlaylistCaNhan.setLayoutManager(linearLayoutManager);
                PlaylistCaNhan.setAdapter(PlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<PlaylistModel>> call, Throwable t) {

            }
        });
    }
    private ActivityResultLauncher<Intent> mGetResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Bundle a = data.getBundleExtra("acc");
            String rs = a.getString("username");
            txtCanhan.setText(rs);
            username = rs;
            init();
        }
    });
}