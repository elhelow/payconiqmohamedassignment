package com.payconiq.mohamedassignment.ui.component.details;

import android.widget.TextView;

import com.payconiq.mohamedassignment.App;
import com.payconiq.mohamedassignment.R;
import com.payconiq.mohamedassignment.data.remote.dto.Repo;
import com.payconiq.mohamedassignment.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.Bind;

import static android.text.TextUtils.isEmpty;

/**
 * Created by Mohamed hemdan on 10/09/17.
 */

public class DetailsActivity extends BaseActivity implements DetailsView {

    @Inject
    DetailsPresenter presenter;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_description)
    TextView tvDescription;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(DetailsActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.details_layout;
    }

    @Override
    public void initializeView(Repo repoItem) {
        if (!isEmpty(repoItem.description)) {
            tvDescription.setText(repoItem.description);
        }
        if (!isEmpty(repoItem.name)) {
            tvTitle.setText(repoItem.name);
        }
    }
}
