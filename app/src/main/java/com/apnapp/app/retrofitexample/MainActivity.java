package com.apnapp.app.retrofitexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class MainActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    boolean isScrolling = false;
    int currentItems,scrolledOutItems,totalItems;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.movieList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getData();
    }

    private void getData()
    {
        final Call<NowPlayingMovies> nowPlayingMovies = MovieDbAPI.getMovies().getNowPlayingMovies();
        nowPlayingMovies.enqueue(new Callback<NowPlayingMovies>() {
            @Override
            public void onResponse(Call<NowPlayingMovies> call, Response<NowPlayingMovies> response) {
                 NowPlayingMovies movies = response.body();
                 recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this,movies.getResults()));
                 recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                     @Override
                     public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                         super.onScrollStateChanged(recyclerView, newState);
                         if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                         {
                             isScrolling= true;
                         }
                     }

                     @Override
                     public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                         super.onScrolled(recyclerView, dx, dy);
                         currentItems = layoutManager.getChildCount();
                         totalItems = layoutManager.getItemCount();
                        // scrolledOutItems = layoutManager.findViewByPosition();   //I am having error at this line, saw from some tutorial, here I need to find the number of scrolled out items
                         // and the method they showed is not working
//                         if(isScrolling && currentItems+scrolledOutItems==totalItems)
//                         {
//                             isScrolling = false;
//                         }
                     }
                 });

                 Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NowPlayingMovies> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
