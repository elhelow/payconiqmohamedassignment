package com.payconiq.mohamedassignment;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.multidex.MultiDexApplication;

import com.payconiq.mohamedassignment.di.DaggerMainComponent;
import com.payconiq.mohamedassignment.di.MainComponent;


/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public class App extends MultiDexApplication {
    private MainComponent mainComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.create();
        context = getApplicationContext();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    public static Context getContext() {
        return context;
    }

    @VisibleForTesting
    public void setComponent(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }
}
