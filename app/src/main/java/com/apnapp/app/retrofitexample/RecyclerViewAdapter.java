package com.apnapp.app.retrofitexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>
{

    private List<Result> results;
    private Context context;

    public RecyclerViewAdapter(MainActivity context, List<Result> results)
    {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Result movie = results.get(position);

        holder.movieTitle.setText(movie.getTitle());
        holder.movieDescription.setText(movie.getOverview());
        String movieImageUrl="https://image.tmdb.org/t/p/original"+movie.getPosterPath();
        Picasso.get().load(movieImageUrl).into(holder.movieImage);
        holder.movieReleaseDate.setText("Release Date: "+movie.getReleaseDate());
        holder.moviePopularity.setText("Popularity : "+String.valueOf(movie.getPopularity()));
        holder.movieLanguage.setText("Language : "+movie.getOriginalLanguage());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        ImageView movieImage;
        TextView movieTitle,movieDescription,movieReleaseDate,movieLanguage,moviePopularity;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImage);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieDescription = itemView.findViewById(R.id.movieDescription);
            movieReleaseDate = itemView.findViewById(R.id.movieRelease);
            movieLanguage = itemView.findViewById(R.id.movieOriginalLanguage);
            moviePopularity = itemView.findViewById(R.id.moviePopularity);
        }
    }
}
