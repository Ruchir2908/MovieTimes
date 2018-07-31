package com.example.caatulgupta.movietimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MovieNowShowingFragment.NowShowingFragmentCallBack {

    public static String API_KEY = "98247b6d9263ea7606524c339461f256";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    BottomNavigationView movieNavigation, tvNavigation;
    SearchView searchView;
    boolean moviesTabSelected = false, TVShowsTabSelected = false;
    boolean homeSelected = true, favouriteSelected = false, watchedSelected = false, recommendationsSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        MenuItem fav = menu.findItem(R.id.favourite);
        MenuItem watch = menu.findItem(R.id.watched);
//        searchView = (SearchView)menuItem.getActionView();

//        searchView.setQueryHint("Movie, TV Shows");
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                if(!s.isEmpty()){
//                    updateResults(s);
//                }
//                return false;
//            }
//        });
        return true;
    }

    private void updateResults(String s) {

//        Call<SearchResponse> call =

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.favourite){
            if(moviesTabSelected){
                
            }else if(TVShowsTabSelected){

            }
        }
        if(id == R.id.watched){
            if(moviesTabSelected){

            }else if(TVShowsTabSelected){

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            homeSelected = true;
            favouriteSelected = false;
            watchedSelected = false;
            recommendationsSelected = false;
        } else if (id == R.id.favourite) {
            homeSelected = false;
            favouriteSelected = true;
            watchedSelected = false;
            recommendationsSelected = false;
        } else if (id == R.id.watched) {
            homeSelected = false;
            favouriteSelected = false;
            watchedSelected = true;
            recommendationsSelected = false;
        } else if (id == R.id.recommendations) {
            homeSelected = false;
            favouriteSelected = false;
            watchedSelected = false;
            recommendationsSelected = true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position){
                case 0:
                    moviesTabSelected = true;
                    TVShowsTabSelected = false;
                    if(favouriteSelected) return new FavouriteMovies();
//                    else if(watchedSelected) return new WatchedMovies();
//                    else if(recommendationsSelected) return new RecommendationMovies();
                    return new MoviesFragment();
                case 1:
                    TVShowsTabSelected = true;
                    moviesTabSelected = false;
//                    else if(favouriteSelected) return new FavouriteShows();
//                    else if(watchedSelected) return new WatchedShows();
//                    else if(recommendationsSelected) return new RecommendationShows();
                        return new TVShowsFragment();
            }
            return null;
        }
    }

    @Override
    public void onNowShowingMovieSelected(Movie movie) {
        Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show();
    }
}
