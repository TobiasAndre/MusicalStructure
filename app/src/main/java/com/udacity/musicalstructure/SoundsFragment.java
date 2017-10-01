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
import com.udacity.musicalstructure.sync.CommandExec;
import com.udacity.musicalstructure.sync.GetMusicTask;
import com.udacity.musicalstructure.util.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobias Andre on 16/09/2017.
 */

public class SoundsFragment extends Fragment implements MusicAdapter.Callbacks, GetMusicTask.Listener {

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


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        getMusics();


        return rootView;
    }

    @Override
    public void open(Music music, int position) {

    }

    private void getMusics(){
        if(NetworkUtils.isNetworkConnected(this.getContext())){

            GetMusicTask.NotifyTaskCompletedCommand command =
                    new GetMusicTask.NotifyTaskCompletedCommand(this);
            new GetMusicTask(command).execute();

        }else{

        }
    }

    @Override
    public void onGetFinished(CommandExec command) {
        if (command instanceof GetMusicTask.NotifyTaskCompletedCommand) {
            ArrayList<Music> musicList = ((GetMusicTask.NotifyTaskCompletedCommand) command).getMusics();
            String musicas = "";
            mAdapter = new MusicAdapter(musicList, this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
