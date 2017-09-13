package com.payconiq.mohamedassignment.ui.component.details;

import android.os.Bundle;

import com.payconiq.mohamedassignment.data.remote.dto.Repo;
import com.payconiq.mohamedassignment.ui.base.Presenter;

import javax.inject.Inject;

import static com.payconiq.mohamedassignment.utils.Constants.repo_ITEM_KEY;

/**
 * Created by AhmedEltaher on 11/12/16.
 */

public class DetailsPresenter extends Presenter<DetailsView> {

    Repo repoItem;

    @Inject
    public DetailsPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        repoItem = extras.getParcelable(repo_ITEM_KEY);
        getView().initializeView(repoItem);
    }

    public String getMainImageURL() {
        String url = null;
//        if (!isNull(repoItem.getMultimedia()) && !repoItem.getMultimedia().isEmpty()) {
//            String mainImageURL = repoItem.getMultimedia().get(repoItem.getMultimedia().size() - 1).getUrl();
//            url = mainImageURL.equals("") ? null : mainImageURL;
//        }
        return url;
    }
}
