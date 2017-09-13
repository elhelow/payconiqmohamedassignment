package com.payconiq.mohamedassignment.di;


import com.payconiq.mohamedassignment.ui.component.details.DetailsActivity;
import com.payconiq.mohamedassignment.ui.component.repos.HomeActivity;
import com.payconiq.mohamedassignment.ui.component.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mohamed Hemdan on 9/9/2017
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(SplashActivity activity);

    void inject(HomeActivity activity);

    void inject(DetailsActivity activity);
}
