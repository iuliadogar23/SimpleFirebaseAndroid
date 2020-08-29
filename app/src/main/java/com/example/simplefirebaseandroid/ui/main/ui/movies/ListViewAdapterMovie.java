package com.example.simplefirebaseandroid.ui.main.ui.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simplefirebaseandroid.R;
import com.example.simplefirebaseandroid.data.model.Movie;

import java.util.List;

public class ListViewAdapterMovie extends ArrayAdapter<Movie> {

    private static final String TAG = "ListViewAdapterMovie";

    private Context mContext;
    int mResource;

    public ListViewAdapterMovie(@NonNull Context context, int resource, @NonNull List<Movie> movies) {
        super(context, resource, movies);
        mContext = context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getTitle();
        String year = getItem(position).getYear().toString();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView titleTextView = convertView.findViewById(R.id.movieTitle);
        TextView yearTextView = convertView.findViewById(R.id.movieYear);

        titleTextView.setText(name);
        yearTextView.setText(year);

        return convertView;
    }
}
