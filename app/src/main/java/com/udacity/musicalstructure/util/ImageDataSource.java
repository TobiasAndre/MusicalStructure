package com.udacity.musicalstructure.util;

import io.reactivex.Single;

/**
 * Created by Tobias Andre on 26/09/17.
 */

public interface ImageDataSource {
    Single<String> getImage(String searchName);
}
