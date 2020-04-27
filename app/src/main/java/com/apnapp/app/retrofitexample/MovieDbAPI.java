package com.apnapp.app.retrofitexample;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MovieDbAPI {
    private static final String apikey = "992e56a2bfbe1354ba6bd8d2604cd4bd";
    private static final String URL = "https://api.themoviedb.org/3/";

    public static AllMoviesInterface allMoviesInterface=null;
    
    public static AllMoviesInterface getMovies()
    {
        if(allMoviesInterface==null)
        { 
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            allMoviesInterface = retrofit.create(AllMoviesInterface.class);
        }
        return allMoviesInterface;
    }

    public interface AllMoviesInterface
    {
        @GET("movie/now_playing?api_key="+apikey)
        Call<NowPlayingMovies> getNowPlayingMovies();
        //getAllMovies method returns a Call object
        //<> here we are expecting a NowPlayingMovies class object

    }
}

