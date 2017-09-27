package com.udacity.musicalstructure.sync;

import android.os.AsyncTask;

import com.udacity.musicalstructure.model.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobias Andre on 26/09/17.
 */

public class GetMusicTask extends AsyncTask<Void, Void, List<Music>> {

    public static String TAG = GetMusicTask.class.getSimpleName();
    private final NotifyTaskCompletedCommand mCommand;

    public interface Listener {
        void onGetFinished(CommandExec command);
    }


    public static class NotifyTaskCompletedCommand implements CommandExec {
        private GetMusicTask.Listener mListener;
        private List<Music> mMusics;

        public NotifyTaskCompletedCommand(GetMusicTask.Listener listener) {
            mListener = listener;
        }

        @Override
        public void execute() {
            mListener.onGetFinished(this);
        }

        public List<Music> getMusics() {
            return mMusics;
        }
    }

    public GetMusicTask(NotifyTaskCompletedCommand command) {
        mCommand = command;
    }

    @Override
    protected void onPostExecute(List<Music> musics) {
        if (musics != null) {
            mCommand.mMusics = musics;
        } else {
            mCommand.mMusics = new ArrayList<>();
        }
        mCommand.execute();
    }


    @Override
    protected List<Music> doInBackground(Void... params) {
        return null;
    }
}
