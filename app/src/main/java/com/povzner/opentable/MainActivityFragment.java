package com.povzner.opentable;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.povzner.opentable.model.Movie;
import com.povzner.opentable.model.NYTInterface;
import com.povzner.opentable.model.ResponseBody;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment  implements Callback<ResponseBody> {

    private ArrayAdapter<String> adapter=null;
    private ListView listView =null;
    private ListViewAdapter listViewAdapter =null;
    List<Movie> movies=null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final MainActivity activity = (MainActivity) getActivity();

        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        this.listView = (ListView) layout.findViewById(R.id.list);

        listViewAdapter = new ListViewAdapter(activity, null);
        listView.setAdapter(listViewAdapter);

        loadData();

        return layout;
    }


    public void loadData() {
        RestAdapter restAdapter=
                new RestAdapter.Builder()
                        .setEndpoint("http://api.nytimes.com/svc/movies/v2")
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build();
        NYTInterface nytInterface=
                restAdapter.create(NYTInterface.class);

        nytInterface.movies(this);
    }

    @Override
    public void success(ResponseBody responseBody, Response response) {
        List<Movie> movies = responseBody.results;
        Log.d(getClass().getSimpleName(),
                "Got " + movies.size() + " movies");

        this.movies = movies;
        listView.setAdapter(new ListViewAdapter(getActivity(), movies));
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(getActivity(), error.getMessage(),
                Toast.LENGTH_LONG).show();

        Log.e(getClass().getSimpleName(),
                "Exception from Retrofit request to NYT", error);
    }
}
