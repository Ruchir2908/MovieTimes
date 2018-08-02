package com.example.caatulgupta.movietimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
    private ViewPager homeViewPager, favViewPager, watchViewPager, recommendationViewPager;
    BottomNavigationView movieNavigation, tvNavigation;
    SearchView searchView;
    boolean moviesTabSelected = false, TVShowsTabSelected = false;
    boolean homeSelected = true, favouriteSelected = false, watchedSelected = false, recommendationsSelected = false;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        homeViewPager = (ViewPager)findViewById(R.id.container_home);
        homeViewPager.setAdapter(mSectionsPagerAdapter);

        favViewPager = findViewById(R.id.container_fav);
        favViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return new FavouriteMovies();
                }else if(position == 1){
                    return new FavouriteShows();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        watchViewPager = findViewById(R.id.container_watched);
        watchViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return new WatchedMovies();
                }else if(position == 1){
                    return new WatchedShows();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        recommendationViewPager = findViewById(R.id.container_recommendations);
        recommendationViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return new RecommendedMovies();
                }else if(position == 1){
                    return new RecommendedShows();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });


        tabLayout = (TabLayout) findViewById(R.id.tabs);

        homeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(homeViewPager));

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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            homeSelected = true;
            favouriteSelected = false;
            watchedSelected = false;
            recommendationsSelected = false;

            homeViewPager.setVisibility(View.VISIBLE);
            favViewPager.setVisibility(View.GONE);
            watchViewPager.setVisibility(View.GONE);
            recommendationViewPager.setVisibility(View.GONE);
        } else if (id == R.id.favourite) {
            Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT).show();
            homeSelected = false;
            favouriteSelected = true;
            watchedSelected = false;
            recommendationsSelected = false;

            homeViewPager.setVisibility(View.GONE);
            favViewPager.setVisibility(View.VISIBLE);
            watchViewPager.setVisibility(View.GONE);
            recommendationViewPager.setVisibility(View.GONE);

            favViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(favViewPager));


        } else if (id == R.id.watched) {
            Toast.makeText(this, "Watched", Toast.LENGTH_SHORT).show();
            homeSelected = false;
            favouriteSelected = false;
            watchedSelected = true;
            recommendationsSelected = false;

            homeViewPager.setVisibility(View.GONE);
            favViewPager.setVisibility(View.GONE);
            watchViewPager.setVisibility(View.VISIBLE);
            recommendationViewPager.setVisibility(View.GONE);

            watchViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(watchViewPager));

        } else if (id == R.id.recommendations) {
            Toast.makeText(this, "Recommendations", Toast.LENGTH_SHORT).show();
            homeSelected = false;
            favouriteSelected = false;
            watchedSelected = false;
            recommendationsSelected = true;

            homeViewPager.setVisibility(View.GONE);
            favViewPager.setVisibility(View.GONE);
            watchViewPager.setVisibility(View.GONE);
            recommendationViewPager.setVisibility(View.VISIBLE);

            recommendationViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(recommendationViewPager));

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
//                    if(favouriteSelected) return new FavouriteMovies();
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
