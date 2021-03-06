package com.example.simplefirebaseandroid.ui.main.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.simplefirebaseandroid.R;
import com.example.simplefirebaseandroid.data.model.Movie;
import com.example.simplefirebaseandroid.data.request.MovieRequest;

import java.util.List;

public class MoviesFragment extends Fragment {

    ListView movieListView;
    List<Movie> movies;
    ImageButton addMovie;
    MovieRequest movieRequest = new MovieRequest();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        movieListView = root.findViewById(R.id.moviesList);
        addMovie = root.findViewById(R.id.add_button);

        movies = movieRequest.getAllUsers();
        listViewSetup();
        addNewMovie();

        return root;
    }

    private void listViewSetup()
    {
        ListViewAdapterMovie adapter = new ListViewAdapterMovie(getContext(), R.layout.listview_items, movies);
        movieListView.setAdapter(adapter);
        movieListView.setOnItemClickListener((parent, view, position, id) -> {
            Movie movie = (Movie) movieListView.getAdapter().getItem(position);
            NavController navController = Navigation.findNavController(view);
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", movie);
            navController.navigate(R.id.action_navigation_home_to_movieDetailsFragment, bundle);
        });
    }

    private void addNewMovie()
    {
        addMovie.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            Bundle bundle = new Bundle();
            bundle.putString("action", "add");
            navController.navigate(R.id.action_navigation_home_to_alterMovieFragment, bundle);
        });
    }

}
