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
public class MoviesFragment extends Fragment {


    public MoviesFragment() {
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        android.support.v4.app.FragmentManager manager = getFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.movieCategoryContainer,childFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.fragment_movies, container, false);

        return output;
    }

}
