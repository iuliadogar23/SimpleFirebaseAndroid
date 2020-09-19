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

    public void saveMovie(Movie movie, Context context)
    {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(gson.toJson(movie));

        ANRequest request = AndroidNetworking.post(Constant.LOCALHOST+"/createMovie")
                .addHeaders("Content-Type", "application/json; utf-8")
                .addApplicationJsonBody(jsonObject)
                .setPriority(Priority.HIGH)
                .build();
        ANResponse response = request.executeForString();
        if (response.isSuccess())
            Toast.makeText(context, "The movie was saved successfully", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(context, "The movie was unable to be saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateMovie(Movie movie, Context context)
    {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(gson.toJson(movie));

        ANRequest request = AndroidNetworking.put(Constant.LOCALHOST+"/updateMovie")

                .addHeaders("Content-Type", "application/json; utf-8")
                .addApplicationJsonBody(jsonObject)
                .setPriority(Priority.HIGH)
                .build();
        ANResponse response = request.executeForString();
        if (response.isSuccess())
            Toast.makeText(context, "The movie was updated successfully", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(context, "The movie was unable to be updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteMovie(String movieTitle, Context context)
    {

        ANRequest request = AndroidNetworking.delete(Constant.LOCALHOST+"/deleteMovie?title={title}")
                .addPathParameter("title", movieTitle)
                .setPriority(Priority.HIGH)
                .build();
        ANResponse response= request.executeForObject(boolean.class);
        if (response.isSuccess())
            Toast.makeText(context, "The movie was deleted successfully", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(context, "The movie was unable to be deleted", Toast.LENGTH_SHORT).show();
        }
    }

}
