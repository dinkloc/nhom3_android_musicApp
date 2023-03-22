package com.dinklokcode.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.util.Log;

import com.dinklokcode.musicapp.Adapter.ViewPagerAdapter;
import com.dinklokcode.musicapp.Fragment.HomeFragment;
import com.dinklokcode.musicapp.Fragment.LibraryFragment;
import com.dinklokcode.musicapp.Fragment.SearchFragment;
import com.dinklokcode.musicapp.R;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private TabLayout myTablayout;
    private ViewPager myViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTablayout = findViewById(R.id.myTablayout);
        myViewPager = findViewById(R.id.view_Pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new HomeFragment(), "Home");
        viewPagerAdapter.addFragment(new SearchFragment(), "Search");
        viewPagerAdapter.addFragment(new LibraryFragment(), "Library");
        myViewPager.setAdapter(viewPagerAdapter);
        myTablayout.setupWithViewPager(myViewPager);
        myTablayout.getTabAt(0).setIcon(R.drawable.ic_home);
        myTablayout.getTabAt(1).setIcon(R.drawable.ic_search);
        myTablayout.getTabAt(2).setIcon(R.drawable.ic_library);

    }
}