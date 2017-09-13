package com.payconiq.mohamedassignment.ui.component.repos;

import android.os.Bundle;

import com.payconiq.mohamedassignment.data.remote.dto.Repo;
import com.payconiq.mohamedassignment.ui.base.Presenter;
import com.payconiq.mohamedassignment.ui.base.listeners.RecyclerItemListener;
import com.payconiq.mohamedassignment.usecase.RepoUseCase;
import com.payconiq.mohamedassignment.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.payconiq.mohamedassignment.utils.ObjectUtil.isNull;

/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public class HomePresenter extends Presenter<HomeView> {

    private final RepoUseCase repoUseCase;
    private ArrayList<Repo> reposModelList;
    private int pageIndex = 0;

    @Inject
    public HomePresenter(RepoUseCase repoUseCase) {
        this.repoUseCase = repoUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getData();
    }

    public void getData() {
        loadmore = true;
        pageIndex = 0;
        getView().setLoaderVisibility(true);
        getView().setNoDataVisibility(false);
        getView().setListVisibility(false);
        getView().incrementCountingIdlingResource();
        repoUseCase.getData(callback, "" + pageIndex++);
    }

    public RecyclerItemListener getRecyclerItemListener() {
        return recyclerItemListener;
    }

    private void showList(boolean isVisible) {
        getView().setNoDataVisibility(!isVisible);
        getView().setListVisibility(isVisible);
    }

    private final RecyclerItemListener recyclerItemListener = position -> {
        getView().navigateToDetailsScreen(position, reposModelList.get(position));
    };

    private final RepoUseCase.Callback callback = new RepoUseCase.Callback() {
        @Override
        public void onSuccess(ArrayList<Repo> reposModel) {
            getView().decrementCountingIdlingResource();
            HomePresenter.this.reposModelList = reposModel;
            List<Repo> repoItems = reposModel;
            if (!isNull(repoItems) && !repoItems.isEmpty()) {
                getView().initializerepoList(repoItems);
                showList(true);
                if (repoItems.size() < Constants.PAGESIZE)
                    loadmore = false;
            } else {
                showList(false);
            }
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onFail() {
            getView().decrementCountingIdlingResource();
            showList(false);
            getView().setLoaderVisibility(false);
        }
    };
    private boolean loadmore = true;
    private final RepoUseCase.Callback callback2 = new RepoUseCase.Callback() {
        @Override
        public void onSuccess(ArrayList<Repo> reposModel) {
            getView().decrementCountingIdlingResource();
            HomePresenter.this.reposModelList.addAll(reposModel);
            List<Repo> repoItems = reposModel;
            if (!isNull(repoItems) && !repoItems.isEmpty()) {
                getView().updaterepoList(repoItems);
                if (repoItems.size() < Constants.PAGESIZE)
                    loadmore = false;
            } else {
                if (!isNull(repoItems) && repoItems.isEmpty())
                    loadmore = false;
            }
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onFail() {
            getView().decrementCountingIdlingResource();
            getView().hideLoadMoreInList();
            //            showList(false);
            //            getView().setLoaderVisibility(false);
        }
    };

    public void getMoreData() {
        //getView().setLoaderVisibility(true);
        if (loadmore) {
            getView().showLoadMoreInList();
            getView().setNoDataVisibility(false);
            //        getView().setListVisibility(false);
            getView().incrementCountingIdlingResource();
            repoUseCase.getData(callback2, "" + pageIndex++);
        }
    }
}
