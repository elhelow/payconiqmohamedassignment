package com.payconiq.mohamedassignment.ui.component.repos;


import com.payconiq.mohamedassignment.data.remote.dto.Repo;
import com.payconiq.mohamedassignment.ui.base.Presenter;

import java.util.List;

/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public interface HomeView extends Presenter.View {
    void initializerepoList(List<Repo> repo);

    void setLoaderVisibility(boolean isVisible);

    void navigateToDetailsScreen(int pos,Repo repo);

    void setNoDataVisibility(boolean isVisible);

    void setListVisibility(boolean isVisible);

    void incrementCountingIdlingResource();

    void decrementCountingIdlingResource();

    void updaterepoList(List<Repo> repoItems);

    void showLoadMoreInList();

    void hideLoadMoreInList();
}
