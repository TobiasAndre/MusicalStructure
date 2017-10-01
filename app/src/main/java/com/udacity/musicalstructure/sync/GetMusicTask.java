package com.udacity.musicalstructure.sync;

import android.os.AsyncTask;
import android.util.Log;

import com.udacity.musicalstructure.model.Music;
import com.udacity.musicalstructure.util.ImageRepository;

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

public class GetMusicTask extends AsyncTask<Void, Void, ArrayList<Music>> {

    public static String TAG = GetMusicTask.class.getSimpleName();

    private final NotifyTaskCompletedCommand mCommand;

    public interface Listener {
        void onGetFinished(CommandExec command);
    }

    public static class NotifyTaskCompletedCommand implements CommandExec {
        private GetMusicTask.Listener mListener;
        private ArrayList<Music> mMusics;

        public NotifyTaskCompletedCommand(GetMusicTask.Listener listener) {
            mListener = listener;
        }

        @Override
        public void execute() {
            mListener.onGetFinished(this);
        }

        public ArrayList<Music> getMusics() {
            return mMusics;
        }
    }

    public GetMusicTask(NotifyTaskCompletedCommand command) {
        mCommand = command;
    }


    @Override
    protected void onPostExecute(ArrayList<Music> movies) {
        if (movies != null) {
            mCommand.mMusics = movies;
        } else {
            mCommand.mMusics = new ArrayList<>();
        }
        mCommand.execute();

    }

    @Override
    protected ArrayList<Music> doInBackground(Void... params) {

        ImageRepository mImageRepository = new ImageRepository();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.102:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MusicService service = retrofit.create(MusicService.class);
        Call<ArrayList<Music>> call = service.discoverMusics();
        try {
            Response<ArrayList<Music>> response = call.execute();
            ArrayList<Music> musics = response.body();
            for(Music m:musics){
                String imgUrl = mImageRepository.getImage(m.getAlbum()).blockingGet();
                m.setThumbnail(imgUrl);
            }

            return musics;

        } catch (IOException e) {
            Log.e(TAG, "Ocorreu um problema ao listar as Musicas:", e);
        }
        return null;
    }
}
