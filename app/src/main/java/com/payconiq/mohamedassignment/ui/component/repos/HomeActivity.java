package com.payconiq.mohamedassignment.ui.component.repos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.payconiq.mohamedassignment.App;
import com.payconiq.mohamedassignment.R;
import com.payconiq.mohamedassignment.data.remote.dto.Repo;
import com.payconiq.mohamedassignment.ui.base.BaseActivity;
import com.payconiq.mohamedassignment.ui.component.details.DetailsActivity;
import com.payconiq.mohamedassignment.utils.TransitionHelper;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.payconiq.mohamedassignment.utils.Constants.repo_ITEM_KEY;
import static com.payconiq.mohamedassignment.utils.EspressoIdlingResource.decrement;
import static com.payconiq.mohamedassignment.utils.EspressoIdlingResource.getIdlingResource;
import static com.payconiq.mohamedassignment.utils.EspressoIdlingResource.increment;


/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public class HomeActivity extends BaseActivity implements
        com.payconiq.mohamedassignment.ui.component.repos.HomeView {
    @Inject
    com.payconiq.mohamedassignment.ui.component.repos.HomePresenter presenter;
    @Bind(R.id.rv_repo_list)
    RecyclerView rvrepo;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_no_data)
    TextView tvNoData;
    @Bind(R.id.rl_repo_list)
    RelativeLayout rlrepoList;
    private ReposAdapter repoAdapter;
    Realm realm;
    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(HomeActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    public void initializerepoList(List<Repo> repos) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvrepo.setLayoutManager(layoutManager);
        repoAdapter = new ReposAdapter(rvrepo, presenter.getRecyclerItemListener(), repos);
        //set load more listener for the RecyclerView adapter
        repoAdapter.setOnLoadMoreListener(() -> {

            presenter.getMoreData();
        });
        rvrepo.setHasFixedSize(true);
        rvrepo.setAdapter(repoAdapter);
    }

    @Override
    protected void initializeRealm() {
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();

        // Clear the realm from last time
        Realm.deleteRealm(realmConfiguration);

        // Create a new empty instance of Realm
        realm = Realm.getInstance(realmConfiguration);
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void navigateToDetailsScreen(int pos,Repo repo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(repo_ITEM_KEY, repo);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtras(bundle);
        final Pair<View, String>[] pairs =
                TransitionHelper.createSafeTransitionParticipants(this, false,
                new Pair<>(((ReposViewHolder)rvrepo.
                        getChildViewHolder(rvrepo.getChildAt(pos))).tvTitle, "title"),
                        new Pair<>(((ReposViewHolder)rvrepo.
                                getChildViewHolder(rvrepo.getChildAt(pos))).tvCaption, "details"));
        ActivityOptionsCompat transitionActivityOptions =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent,transitionActivityOptions.toBundle());
    }

    @Override
    public void setNoDataVisibility(boolean isVisible) {
        tvNoData.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void setListVisibility(boolean isVisible) {
        rlrepoList.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void incrementCountingIdlingResource() {
        increment();
    }

    @Override
    public void decrementCountingIdlingResource() {
        decrement();
    }

    @Override
    public void updaterepoList(List<Repo> repoItems) {
//        repoAdapter.remove(repoAdapter.getItemCount() - 1);
        realm.beginTransaction();
        Collection<Repo> storedItems = realm.copyToRealm(repoItems);
        realm.commitTransaction();

        repoAdapter.addAll(repoItems);
        repoAdapter.setLoaded();
    }

    @Override
    public void showLoadMoreInList() {
        repoAdapter.add(null);
    }

    @Override
    public void hideLoadMoreInList() {
        repoAdapter.remove(null);
    }

    @OnClick({R.id.ic_toolbar_setting, R.id.ic_toolbar_refresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_toolbar_refresh:
                presenter.getData();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return getIdlingResource();
    }
}
