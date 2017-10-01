package com.udacity.musicalstructure.sync;

import com.udacity.musicalstructure.model.Music;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tobias Andre on 26/09/17.
 */

public interface MusicService {
    @GET("musics")
    Call<ArrayList<Music>> discoverMusics();
}
