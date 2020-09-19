package com.example.simplefirebaseandroid.ui.main.ui.detail;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.simplefirebaseandroid.R;
import com.example.simplefirebaseandroid.data.model.Movie;
import com.example.simplefirebaseandroid.data.request.MovieRequest;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.concurrent.TimeUnit;

public class MovieDetailsFragment extends Fragment {

    private Movie movie;
    MovieRequest movieRequest = new MovieRequest();
    private ImageButton deleteButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    ImageButton editButton;

    TextView showCategory, showYear, showDuration, showDescription;

    public static MovieDetailsFragment newInstance() {
        return new MovieDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_details_fragment, container, false);

        movie = (Movie) getArguments().getSerializable("movie");
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar_id);

        showCategory = view.findViewById(R.id.show_movie_type);
        showYear = view.findViewById(R.id.show_year);
        showDuration = view.findViewById(R.id.show_running_time);
        showDescription = view.findViewById(R.id.movie_description);
        deleteButton = view.findViewById(R.id.delete_button);
        editButton = view.findViewById(R.id.edit_button);

        populateMovieWithValues();
        deleteMovie(movie.getTitle());
        updateMovie(movie);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void populateMovieWithValues()
    {
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(movie.getTitle());
        showCategory.setText(movie.getCategory());
        showDuration.setText(String.valueOf(TimeUnit.MILLISECONDS.toMinutes(movie.getDuration())) + " Minutes");
        showYear.setText(movie.getYear().toString());
        showDescription.setText(movie.getDescription());
    }

    private void deleteMovie(String title)
    {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieRequest.deleteMovie(title, getContext());
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_movieDetailsFragment_to_navigation_home);
            }
        });
    }

    private void updateMovie(Movie movie)
    {
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                Bundle bundle = new Bundle();
                bundle.putString("action", "update");
                bundle.putSerializable("movie", movie);
                navController.navigate(R.id.action_movieDetailsFragment_to_alterMovieFragment, bundle);
            }
        });
    }

}
