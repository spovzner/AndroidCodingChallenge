package com.povzner.opentable;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    ImageView image = null;
    TextView display_title = null;
    TextView headline = null;
    TextView mpaa_rating = null;
    TextView byline = null;
    TextView summary_short = null;
    TextView publication_date = null;


    ViewHolder(View row) {
        this.image = (ImageView) row.findViewById(R.id.image);
        this.display_title = (TextView) row.findViewById(R.id.display_title);
        this.headline = (TextView) row.findViewById(R.id.headline);
        this.mpaa_rating = (TextView) row.findViewById(R.id.mpaa_rating);
        this.byline = (TextView) row.findViewById(R.id.byline);
        this.summary_short = (TextView) row.findViewById(R.id.summary_short);
        this.publication_date = (TextView) row.findViewById(R.id.publication_date);

    }

}
