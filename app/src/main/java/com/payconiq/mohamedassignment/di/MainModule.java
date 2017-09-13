package com.payconiq.mohamedassignment.di;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.payconiq.mohamedassignment.data.local.LocalRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

@Module
public class MainModule {
    @Provides
    @Singleton
    public LocalRepository provideLocalRepository() {
        return new LocalRepository();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        Gson gson = new GsonBuilder().create();
        return gson;
    }
}
