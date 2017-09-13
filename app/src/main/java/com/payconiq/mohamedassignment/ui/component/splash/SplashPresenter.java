package com.payconiq.mohamedassignment.ui.component.splash;

import android.os.Bundle;

import com.payconiq.mohamedassignment.ui.base.Presenter;

import javax.inject.Inject;

/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public class SplashPresenter extends Presenter<SplashView> {

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getView().NavigateToMainScreen();
    }
}
