package com.udacity.musicalstructure.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.musicalstructure.R;
import com.udacity.musicalstructure.model.Music;

import java.util.ArrayList;

/**
 * Created by tobia on 17/09/2017.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private final ArrayList<Music> mMusics;
    private final Callbacks mCallbacks;

    public interface Callbacks {
        void open(Music music, int position);
    }

    public MusicAdapter(ArrayList<Music> musics, Callbacks callbacks) {
        mMusics = musics;
        this.mCallbacks = callbacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_music, parent, false);
        final Context context = view.getContext();



        return new ViewHolder(view);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cleanUp();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Music music = mMusics.get(position);
        final Context context = holder.mView.getContext();

        holder.mTitleAlbum.setText(music.getAlbum());
        holder.mTitleMusic.setText(music.getName());
        if(!music.getThumbnail().isEmpty()){
            //Bitmap myBitmap = BitmapFactory.decodeFile(music.getThumbnail());
            //holder.mPosterView.setImageBitmap(myBitmap);
        }
    }

    @Override
    public int getItemCount() {
        return mMusics.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        ImageView mPosterView;
        TextView mTitleAlbum;
        TextView mTitleMusic;

        public ViewHolder(View view) {
            super(view);
            mTitleMusic = (TextView)view.findViewById(R.id.tv_music_tittle);
            mTitleAlbum = (TextView)view.findViewById(R.id.tv_album_tittle);
            mView = view;
        }

        public void cleanUp() {
            final Context context = mView.getContext();
            Picasso.with(context).cancelRequest(mPosterView);
            mPosterView.setImageBitmap(null);
            mPosterView.setVisibility(View.INVISIBLE);
            mTitleAlbum.setVisibility(View.GONE);
            mTitleMusic.setVisibility(View.GONE);
        }
    }

}
