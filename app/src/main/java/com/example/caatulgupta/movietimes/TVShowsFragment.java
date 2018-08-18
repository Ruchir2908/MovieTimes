package com.example.caatulgupta.movietimes;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
public class TVShowsFragment extends Fragment {

    BottomNavigationView bottomNavigationView;

    public TVShowsFragment() {
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        android.support.v4.app.FragmentManager manager = getFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.tvCategoryContainer,childFragment);
    }

    BottomNavigationView.OnNavigationItemSelectedListener tvOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentManager manager = getFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.airingToday:
                    TVAiringTodayFragment tvAiringTodayFragment = new TVAiringTodayFragment();
                    transaction.replace(R.id.tvCategoryContainer, tvAiringTodayFragment);
                    break;
                case R.id.onAir:
                    TVOnAirFragment tvOnAirFragment = new TVOnAirFragment();
                    transaction.replace(R.id.tvCategoryContainer, tvOnAirFragment);
                    break;
                case R.id.tvPopular:
                    TVPopularFragment tvPopularFragment = new TVPopularFragment();
                    transaction.replace(R.id.tvCategoryContainer, tvPopularFragment);
                    break;
                case R.id.tvTopRated:
                    TVTopRatedFragment tvTopRatedFragment = new TVTopRatedFragment();
                    transaction.replace(R.id.tvCategoryContainer, tvTopRatedFragment);
                    break;
            }
            transaction.commit();
            return true;
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = view.findViewById(R.id.tvNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(tvOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshows, container, false);
    }

}
