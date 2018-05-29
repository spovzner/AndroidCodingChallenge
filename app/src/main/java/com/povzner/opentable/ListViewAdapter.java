package com.povzner.opentable;

import android.content.Context;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.povzner.opentable.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


final class ListViewAdapter extends BaseAdapter {

    private final Context context;
    private List<Movie> mMovies = new ArrayList<>();

    public ListViewAdapter(Context context, List<Movie> movies) {
        this.context = context;

        if (movies != null) {
            mMovies = new ArrayList<>();
            for (int i = 0; i < movies.size(); i++) {
                mMovies.add(movies.get(i));
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if (row == null) {
            row = new View(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row, null);
        }

        ViewHolder holder = (ViewHolder) row.getTag();
        if (holder == null) {
            holder = new ViewHolder(row);
            row.setTag(holder);
        }

        // Get the image URL for the current position.
        String url = getItem(position);

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        });

        holder.display_title.setText(mMovies.get(position).display_title);
        holder.headline.setText(mMovies.get(position).headline);
        holder.summary_short.setText(mMovies.get(position).summary_short);
        holder.publication_date.setText(mMovies.get(position).publication_date);
        holder.mpaa_rating.setText(mMovies.get(position).mpaa_rating);
        holder.byline.setText(mMovies.get(position).byline);

        // Trigger the download of the URL asynchronously into the image view.
        builder.build()
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .tag(context)
                .into(holder.image);

        return row;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public String getItem(int position) {
        return mMovies.get(position).multimedia.src;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
