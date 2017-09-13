package com.payconiq.mohamedassignment.ui.component.details;


import com.payconiq.mohamedassignment.data.remote.dto.Repo;
import com.payconiq.mohamedassignment.ui.base.Presenter;

/**
 * Created by AhmedEltaher on 11/12/16.
 */

public interface DetailsView extends Presenter.View {
    void initializeView(Repo repoItem);
}
