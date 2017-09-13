package com.payconiq.mohamedassignment.ui.component.repos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.payconiq.mohamedassignment.R;
import com.payconiq.mohamedassignment.data.remote.dto.Repo;
import com.payconiq.mohamedassignment.ui.base.listeners.RecyclerItemListener;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;


/**
 * Created by Mohamed Hemdan on 9/9/2017.
 */

public class ReposViewHolder extends RecyclerView.ViewHolder {

    private RecyclerItemListener onItemClickListener;

    @Bind(R.id.tv_caption)
    TextView tvCaption;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_repo_item)
    RelativeLayout repoItemLayout;


    public ReposViewHolder(View itemView, RecyclerItemListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position, Repo repoItem, RecyclerItemListener recyclerItemListener) {
        //need to move to mapper
        if (!isEmpty(repoItem.name)) {
            tvTitle.setText(repoItem.name);
        }
        if (!isEmpty(repoItem.description)) {
            tvCaption.setText(repoItem.description);
        }
        repoItemLayout.setOnClickListener(v -> recyclerItemListener.onItemSelected(position));
    }
}

