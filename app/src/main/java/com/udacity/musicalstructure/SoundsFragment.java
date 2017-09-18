package com.udacity.musicalstructure;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.udacity.musicalstructure.adapter.MusicAdapter;
import com.udacity.musicalstructure.model.Music;

import java.util.ArrayList;

/**
 * Created by Tobias Andre on 16/09/2017.
 */

public class SoundsFragment extends Fragment implements MusicAdapter.Callbacks {

    RecyclerView mRecyclerView;
    MusicAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sounds_fragment, container, false);

        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.rv_musics);


        ArrayList<Music> mMusics = new ArrayList<>();
        Music music = new Music();
        music.setAlbum("Reise,reise");
        music.setId(0);
        music.setName("Ohne Dich");
        music.setThumbnail("");
        mMusics.add(music);

        music = new Music();
        music.setAlbum("Mutter");
        music.setId(0);
        music.setName("Sohne");
        music.setThumbnail("rammstein.jpg");
        mMusics.add(music);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        mAdapter = new MusicAdapter(mMusics, this);
        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    @Override
    public void open(Music music, int position) {

    }
}
