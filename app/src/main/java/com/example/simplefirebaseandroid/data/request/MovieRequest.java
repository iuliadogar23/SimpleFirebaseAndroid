package com.example.simplefirebaseandroid.data.request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.example.simplefirebaseandroid.data.Constant;
import com.example.simplefirebaseandroid.data.model.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class MovieRequest {

    public List<Movie> getAllUsers()
    {
        ANRequest request = AndroidNetworking.get(Constant.LOCALHOST +"/getAllMovies")
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

    public ANResponse saveMovie(Movie movie)
    {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(gson.toJson(movie));

        ANRequest request = AndroidNetworking.post(Constant.LOCALHOST+"/createMovie")
                .addHeaders("Content-Type", "application/json; utf-8")
                .addApplicationJsonBody(jsonObject)
                .setPriority(Priority.HIGH)
                .build();
        return request.executeForString();
    }

    public ANResponse updateMovie(Movie movie)
    {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(gson.toJson(movie));

        ANRequest request = AndroidNetworking.put(Constant.LOCALHOST+"/updateMovie")

                .addHeaders("Content-Type", "application/json; utf-8")
                .addApplicationJsonBody(jsonObject)
                .setPriority(Priority.HIGH)
                .build();
        return request.executeForString();

    }

    public ANResponse deleteMovie(String movieTitle)
    {

        ANRequest request = AndroidNetworking.delete(Constant.LOCALHOST+"/deleteMovie?title={title}")
                .addPathParameter("title", movieTitle)
                .setPriority(Priority.HIGH)
                .build();
        return request.executeForObject(boolean.class);
    }

    public Movie getMovie(String title)
    {
        ANRequest request = AndroidNetworking.get(Constant.LOCALHOST+"/getMovieDetails?title={title}")
                .addPathParameter("title", title)
                .setPriority(Priority.HIGH)
                .build();
        ANResponse response = request.executeForObject(Movie.class);
        if (response.isSuccess())
            return (Movie) response.getResult();
        else {
            Log.println(Log.ERROR, "getAllMovies", String.valueOf(response.getError()));
        }
        return null;
    }

}
