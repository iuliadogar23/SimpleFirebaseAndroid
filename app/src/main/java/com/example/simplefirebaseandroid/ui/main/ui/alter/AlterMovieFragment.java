package com.example.simplefirebaseandroid.ui.main.ui.alter;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplefirebaseandroid.R;

public class AlterMovieFragment extends Fragment {

    private AlterMovieViewModel mViewModel;

    public static AlterMovieFragment newInstance() {
        return new AlterMovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alter_movie_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AlterMovieViewModel.class);
        // TODO: Use the ViewModel
    }

}
