package com.dinklokcode.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinklokcode.musicapp.Adapter.SearchBaiHatAdapter;
import com.dinklokcode.musicapp.Model.BaiHat;
import com.dinklokcode.musicapp.R;
import com.dinklokcode.musicapp.Service.APIService;
import com.dinklokcode.musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    View view;
    Toolbar toolbar;
    RecyclerView  recyclerViewsearchbaihat;
    TextView txtkhongcodulieu;
    SearchBaiHatAdapter searchBaiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search,container,false);
        toolbar = view.findViewById(R.id.toolbarsearchbaihat);
        recyclerViewsearchbaihat = view.findViewById(R.id.recyclerviewsearchbaihat);
        txtkhongcodulieu = view.findViewById(R.id.textviewkhongcodulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTuKhoaBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SearchTuKhoaBaiHat(String query){
        DataService dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetSearchBaiHat(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {

                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat.size()>0){
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(),mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewsearchbaihat.setLayoutManager(linearLayoutManager);
                    recyclerViewsearchbaihat.setAdapter(searchBaiHatAdapter);
                    txtkhongcodulieu.setVisibility(View.INVISIBLE);
                    recyclerViewsearchbaihat.setVisibility(View.VISIBLE);
                }else{
                    recyclerViewsearchbaihat.setVisibility(View.GONE);
                    txtkhongcodulieu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}