package com.example.caatulgupta.movietimes;

import android.content.ClipData;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static String API_KEY = "";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager homeViewPager, favViewPager, watchViewPager, recommendationViewPager;
    BottomNavigationView movieNavigation, tvNavigation;
    SearchView searchView;
    private TabLayout tabLayout;
    private FirebaseAuth auth;
    ArrayList<Item> searchedItems = new ArrayList<>();
    SearchedItemsAdapter searchedItemsAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
    }

//    public void createUser(){
//        Intent intent = getIntent();
//        String email = intent.getStringExtra("email");
//        String password = intent.getStringExtra("password");
//        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    FirebaseUser user = auth.getCurrentUser();
//                }else{
//                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        homeViewPager = (ViewPager)findViewById(R.id.container_home);
        homeViewPager.setAdapter(mSectionsPagerAdapter);

        auth = FirebaseAuth.getInstance();

        searchedItemsAdapter = new SearchedItemsAdapter(this,0,searchedItems);

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
        searchView = (SearchView)menuItem.getActionView();

        searchView.setQueryHint("Movie, TV Shows");
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.isEmpty()){
                    updateResults(s);
                }
                return false;
            }
        });
        return true;
    }

    private void updateResults(String s) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieTimesService service = retrofit.create(MovieTimesService.class);
        Call<SearchResponse> call = service.search(API_KEY,s);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                SearchResponse searchResponse = response.body();

                if(searchResponse != null){

                    ArrayList<SearchResult> searchResultsApi = searchResponse.results;
                    searchedItems.clear();
                    searchedItems.addAll(searchResultsApi);
                    searchedItemsAdapter.notifyDataSetChanged();



                }else{
                    Toast.makeText(MainActivity.this, "No results to show", Toast.LENGTH_LONG).show();
                    searchedItems.clear();
                    searchedItemsAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            homeViewPager.setVisibility(View.VISIBLE);
            favViewPager.setVisibility(View.GONE);
            watchViewPager.setVisibility(View.GONE);
            recommendationViewPager.setVisibility(View.GONE);
        } else if (id == R.id.favourite) {
            Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT).show();
            homeViewPager.setVisibility(View.GONE);
            favViewPager.setVisibility(View.VISIBLE);
            watchViewPager.setVisibility(View.GONE);
            recommendationViewPager.setVisibility(View.GONE);
            favViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(favViewPager));
        } else if (id == R.id.watched) {
            Toast.makeText(this, "Watched", Toast.LENGTH_SHORT).show();
            homeViewPager.setVisibility(View.GONE);
            favViewPager.setVisibility(View.GONE);
            watchViewPager.setVisibility(View.VISIBLE);
            recommendationViewPager.setVisibility(View.GONE);
            watchViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(watchViewPager));
        } else if (id == R.id.recommendations) {
            Toast.makeText(this, "Recommendations", Toast.LENGTH_SHORT).show();
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
                case 0: return new MoviesFragment();
                case 1: return new TVShowsFragment();
            }
            return null;
        }



    }
}
