package com.example.caatulgupta.movietimes;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MovieNowShowingFragment.NowShowingFragmentCallBack {

    public static String API_KEY = "98247b6d9263ea7606524c339461f256";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    BottomNavigationView movieNavigation, tvNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieNavigation = (BottomNavigationView)findViewById(R.id.movieNavigation);


        tvNavigation = (BottomNavigationView)findViewById(R.id.tvNavigation);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {

        } else if (id == R.id.favourite) {

        } else if (id == R.id.watched) {

        }else if (id == R.id.nav_share) {

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
//                    tvNavigation.inflateMenu(R.menu.movie_navigation);


                    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                            findViewById(R.id.tvNavigation).setVisibility(View.GONE);
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
                    movieNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

                    findViewById(R.id.movieNavigation).setVisibility(View.VISIBLE);
//                    findViewById(R.id.tvNavigation).setVisibility(View.GONE);
                    return new MoviesFragment();
                case 1:


                    BottomNavigationView.OnNavigationItemSelectedListener tvOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                            findViewById(R.id.movieNavigation).setVisibility(View.GONE);
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
                    tvNavigation.setOnNavigationItemSelectedListener(tvOnNavigationItemSelectedListener);


                    findViewById(R.id.tvNavigation).setVisibility(View.VISIBLE);
//                    findViewById(R.id.movieNavigation).setVisibility(View.GONE);
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
