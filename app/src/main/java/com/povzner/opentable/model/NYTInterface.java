package com.povzner.opentable.model;

import retrofit.Callback;
import retrofit.http.GET;

//http://api.nytimes.com/svc/movies/v2/reviews/dvd-picks.json?order=by-date&api-key=b75da00e12d54774a2d362adddcc9bef

public interface NYTInterface {
    @GET("/reviews/dvd-picks.json?order=by-date&api-key=b75da00e12d54774a2d362adddcc9bef")
    void movies(Callback<ResponseBody> cb);
}
