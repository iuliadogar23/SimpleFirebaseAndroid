package com.example.simplefirebaseandroid.ui.main.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.androidnetworking.common.ANResponse;
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_details_fragment, container, false);

        movie = (Movie) getArguments().getSerializable("movie");
        setPartsForFragment(view);

        populateMovieWithValues();
        deleteMovie(movie.getTitle());
        updateMovie(movie);

        return view;
    }

    private void setPartsForFragment(View view)
    {
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar_id);

        showCategory = view.findViewById(R.id.show_movie_type);
        showYear = view.findViewById(R.id.show_year);
        showDuration = view.findViewById(R.id.show_running_time);
        showDescription = view.findViewById(R.id.movie_description);
        deleteButton = view.findViewById(R.id.delete_button);
        editButton = view.findViewById(R.id.edit_button);
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
        deleteButton.setOnClickListener(v -> {
            ANResponse response = movieRequest.deleteMovie(title);
            if (response.isSuccess())
                Toast.makeText(getContext(), "The movie was deleted successfully", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(getContext(), "The movie was unable to be deleted", Toast.LENGTH_SHORT).show();
            }
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_movieDetailsFragment_to_navigation_home);
        });
    }

    private void updateMovie(Movie movie)
    {
        editButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            Bundle bundle = new Bundle();
            bundle.putString("action", "update");
            bundle.putSerializable("movie", movie);
            navController.navigate(R.id.action_movieDetailsFragment_to_alterMovieFragment, bundle);
        });
    }

}
