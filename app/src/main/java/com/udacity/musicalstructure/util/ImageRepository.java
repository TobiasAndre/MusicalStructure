package com.udacity.musicalstructure.util;

import com.udacity.musicalstructure.MusicalStructureApp;

import io.reactivex.Single;
import okhttp3.OkHttpClient;

/**
 * Created by Tobias Andre on 26/09/17.
 */

public class ImageRepository implements ImageDataSource {

    OkHttpClient okHttpClient;

    public ImageRepository() {
        this.okHttpClient = MusicalStructureApp.get().clientHttp;
    }

    @Override public Single<String> getImage(String recipeName) {
        String urlFromPrefs = MusicalStructureApp.get().preferenceUtils.getRecipeImage(recipeName);
        if(urlFromPrefs == null || urlFromPrefs.isEmpty()){
            return SearchImage.getFirstImage(recipeName);
        }else{
            return Single.just(urlFromPrefs);
        }
    }
}
