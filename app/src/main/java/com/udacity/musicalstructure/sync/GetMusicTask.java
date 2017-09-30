package com.udacity.musicalstructure.sync;

import android.os.AsyncTask;
import android.util.Log;

import com.udacity.musicalstructure.model.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.102/musics")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MusicService service = retrofit.create(MusicService.class);
        Call<List<Music>> call = service.discoverMusics();
        try {
            Response<List<Music>> response = call.execute();
            List<Music> musics = response.body();
            return musics;

        } catch (IOException e) {
            Log.e(TAG, "Ocorreu um problema ao listar as Musicas:", e);
        }
        return null;
    }
}
