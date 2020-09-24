package com.example.simplefirebaseandroid.data.request;

import android.content.Context;

import androidx.annotation.UiThread;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.simplefirebaseandroid.data.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MovieRequestTest {

    private MovieRequest movieRequest = new MovieRequest();
    Movie movie = new Movie("Testing movie", "Comedy", 2012, "we are testing the save using JUnit4", 7200000L);
    Context mockContext;

    @Before
    public void setUp()
    {
        mockContext = InstrumentationRegistry.getInstrumentation().getContext();
    }

    @Test
    public void getAllUsers() {
        assertTrue(!movieRequest.getAllUsers().isEmpty());
    }

    @Test
    @UiThread
    public void saveMovie() {
        movieRequest.saveMovie(movie);
        assertTrue(movieRequest.getMovie(movie.getTitle())!=null);
    }

    @Test
    public void updateMovie() {
        movie.setYear(2000);
        movieRequest.updateMovie(movie);
        assertEquals(java.util.Optional.ofNullable(movieRequest.getMovie(movie.getTitle()).getYear()),java.util.Optional.ofNullable(2000));
    }

    @Test
    public void deleteMovie() {
        movieRequest.deleteMovie(movie.getTitle());
        assertTrue(movieRequest.getMovie(movie.getTitle())==null);
    }
}