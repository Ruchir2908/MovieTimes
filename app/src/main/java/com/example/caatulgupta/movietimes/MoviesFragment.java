package com.example.caatulgupta.movietimes;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

public class MoviesFragment extends Fragment {

    BottomNavigationView bottomNavigationView;

    public MoviesFragment() {
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        android.support.v4.app.FragmentManager manager = getFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.movieCategoryContainer,childFragment);
    }

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentManager manager = getFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.nowShowing:
                    MovieNowShowingFragment movieNowShowingFragment = new MovieNowShowingFragment();
                    transaction.replace(R.id.movieCategoryContainer, movieNowShowingFragment);
                    break;
                case R.id.upcoming:
                    MovieUpcomingFragment movieUpcomingFragment = new MovieUpcomingFragment();
                    transaction.replace(R.id.movieCategoryContainer, movieUpcomingFragment);
                    break;
                case R.id.popular:
                    MoviePopularFragment moviePopularFragment = new MoviePopularFragment();
                    transaction.replace(R.id.movieCategoryContainer, moviePopularFragment);
                    break;
                case R.id.topRated:
                    MovieTopRatedFragment movieTopRatedFragment = new MovieTopRatedFragment();
                    transaction.replace(R.id.movieCategoryContainer, movieTopRatedFragment);
                    break;
            }
            transaction.commit();
            return true;
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = view.findViewById(R.id.movieNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //bottomNavigationView.removeShiftMode(bottomNavigationView);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View output = inflater.inflate(R.layout.fragment_movies, container, false);
        bottomNavigationView = output.findViewById(R.id.movieNavigation);
        return output;
    }
}

//class BottomNavigationViewHelper {
//
//    static void removeShiftMode(BottomNavigationView view) {
//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
//        try {
//            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
//            shiftingMode.setAccessible(true);
//            shiftingMode.setBoolean(menuView, false);
//            shiftingMode.setAccessible(false);
//            for (int i = 0; i < menuView.getChildCount(); i++) {
//                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
//                item.setShiftingMode(false);
//                // set once again checked value, so view will be updated
//                item.setChecked(item.getItemData().isChecked());
//            }
//        } catch (NoSuchFieldException e) {
//            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
//        } catch (IllegalAccessException e) {
//            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
//        }
//    }
//}
