package com.example.simplefirebaseandroid.ui.main.ui.alter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.simplefirebaseandroid.R;
import com.example.simplefirebaseandroid.data.model.Movie;
import com.example.simplefirebaseandroid.data.request.MovieRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlterMovieFragment extends Fragment {

    private EditText title;
    private EditText description;
    private Spinner category;
    private EditText year;
    private EditText duration;
    private ImageButton finishButton;
    MovieRequest movieRequest = new MovieRequest();
    String action;
    Movie movie;

    public static AlterMovieFragment newInstance() {
        return new AlterMovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alter_movie_fragment, container, false);

        title = view.findViewById(R.id.title_edittext);
        description = view.findViewById(R.id.description_edit_text);
        category = view.findViewById(R.id.category_spinner);
        year = view.findViewById(R.id.year_edit_text);
        duration = view.findViewById(R.id.duration_edit_text);
        finishButton = view.findViewById(R.id.finish_button);
        action = getArguments().getString("action");

        changeBehaviourForAction(action);
        setCategoryForSpinner(category);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterChanges(view, action);
            }
        });

        return view;
    }

    private void changeBehaviourForAction(String action) {
        if (action.equals("add"))
            title.setEnabled(true);
        else
            {
                movie = (Movie) getArguments().getSerializable("movie");
                title.setText(movie.getTitle());
                title.setEnabled(false);
                description.setText(movie.getDescription());
                year.setText(movie.getYear().toString());
                duration.setText(movie.getDuration().toString());

            }
    }

    private void setCategoryForSpinner(Spinner spinner) {
        List<String> spinnerArray = new ArrayList<>();

        spinnerArray.addAll(Arrays.asList("Action", "Comedy", "Adventure", "Romance", "Thriller", "Horror"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (action.equals("update"))
            spinner.setSelection(adapter.getPosition(movie.getCategory()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void alterChanges(View view, String action) {
        Movie movie = new Movie();
        boolean checked = true;
        if (!title.getText().toString().isEmpty() && !year.getText().toString().isEmpty() && !description.getText().toString().isEmpty() && !duration.getText().toString().isEmpty()) {
            if (!year.getText().toString().matches("[0-9]+")) {
                checked = false;
                Toast.makeText(getActivity().getApplicationContext(), "The year contains letters or other characters", Toast.LENGTH_SHORT).show();
            } else {
                int convertedYear = Integer.parseInt(year.getText().toString());
                if (convertedYear < 1888 || convertedYear > 2020) {
                    checked = false;
                    Toast.makeText(getActivity().getApplicationContext(), "There is no movie created in year " + convertedYear, Toast.LENGTH_SHORT).show();
                } else
                    movie.setYear(convertedYear);
            }
            if (!duration.getText().toString().matches("[0-9]+")) {
                checked = false;
                Toast.makeText(getActivity().getApplicationContext(), "The running time contains letters or other characters", Toast.LENGTH_SHORT).show();
            } else
                movie.setDuration(TimeUnit.MINUTES.toMillis(Long.parseLong(duration.getText().toString())));
            movie.setDescription(description.getText().toString());
            movie.setTitle(title.getText().toString());
            movie.setCategory(category.getSelectedItem().toString());


        } else
            Toast.makeText(getActivity().getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        if (checked) {
            if (action.equals("add"))
                movieRequest.saveMovie(movie, getActivity().getApplicationContext());
            else
                movieRequest.updateMovie(movie, getActivity().getApplicationContext());
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_alterMovieFragment_to_navigation_home);
        }
    }

}
