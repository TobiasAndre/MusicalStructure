package com.udacity.musicalstructure;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.udacity.musicalstructure.util.PreferenceUtils;

import okhttp3.OkHttpClient;

/**
 * Created by tobiasandreeggers on 26/09/17.
 */

public class MusicalStructureApp extends Application {

    private static MusicalStructureApp Instance;

    public OkHttpClient clientHttp = new OkHttpClient();
    public PreferenceUtils preferenceUtils;

    public static MusicalStructureApp get() {
        return Instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        Stetho.initializeWithDefaults(this);
        clientHttp = new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();
        preferenceUtils = new PreferenceUtils(this);
    }
}
