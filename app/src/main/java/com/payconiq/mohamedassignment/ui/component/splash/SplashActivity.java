package com.payconiq.mohamedassignment.ui.component.splash;

import android.content.Intent;
import android.os.Handler;

import com.payconiq.mohamedassignment.App;
import com.payconiq.mohamedassignment.R;
import com.payconiq.mohamedassignment.ui.base.BaseActivity;
import com.payconiq.mohamedassignment.ui.component.repos.HomeActivity;

import javax.inject.Inject;



/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(SplashActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    protected void initializeRealm() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.splash_layout;
    }


    @Override
    public void NavigateToMainScreen() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
