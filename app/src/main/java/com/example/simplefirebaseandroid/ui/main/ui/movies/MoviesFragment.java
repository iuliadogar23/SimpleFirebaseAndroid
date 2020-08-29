package com.example.simplefirebaseandroid.ui.main.ui.movies;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.example.simplefirebaseandroid.R;
import com.example.simplefirebaseandroid.data.model.Movie;

import java.util.List;

public class MoviesFragment extends Fragment {

    private static final String LOCALHOST = "http://10.0.2.2:8080";

    ListView movieListView;
    List<Movie> movies;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        movieListView = root.findViewById(R.id.moviesList);

        movies = getAllUsers();

        ListViewAdapterMovie adapter = new ListViewAdapterMovie(getContext(), R.layout.listview_items, movies);
        movieListView.setAdapter(adapter);

        return root;
    }

    private List<Movie> getAllUsers()
    {
        ANRequest request = AndroidNetworking.get(LOCALHOST+"/getAllMovies")
                .setPriority(Priority.HIGH)
                .build();
        ANResponse response = request.executeForObjectList(Movie.class);
        if (response.isSuccess())
            return (List<Movie>) response.getResult();
        else {
            Log.println(Log.ERROR, "getAllMovies", String.valueOf(response.getError()));
        }
        return null;
    }

}
