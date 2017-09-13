package com.payconiq.mohamedassignment.ui.component.repos;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.payconiq.mohamedassignment.R;
import com.payconiq.mohamedassignment.data.remote.dto.Repo;
import com.payconiq.mohamedassignment.ui.base.listeners.OnLoadMoreListener;
import com.payconiq.mohamedassignment.ui.base.listeners.RecyclerItemListener;

import java.util.List;


/**
 * Created by Mohamed Hemdan on 9/9/2017.
 */

public class ReposAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final List<Repo> repo;
    private RecyclerItemListener onItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private int totalItemCount;
    private int lastVisibleItem;
    private boolean isLoading;
    private int visibleThreshold = 10;


    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public ReposAdapter(RecyclerView recyclerView,
                        RecyclerItemListener onItemClickListener, List<Repo> repo) {
        this.onItemClickListener = onItemClickListener;
        this.repo = repo;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return repo.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
            return new ReposViewHolder(view, onItemClickListener);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ReposViewHolder) {
            ReposViewHolder viewHolder = (ReposViewHolder) holder;
            viewHolder.bind(position, repo.get(position), onItemClickListener);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemCount() {
        return repo.size();
    }

    public void addAll(List<Repo> repoItems) {
        repo.remove(null);
        repo.addAll(repoItems);
        new Handler().post(() ->notifyDataSetChanged());
    }

    public List<Repo> getData() {
        return repo;
    }

    public void add(Repo o) {
        repo.add(getItemCount() - 1, o);
        new Handler().post(() -> notifyItemChanged(getItemCount() - 1));
    }

    public void remove(Repo o) {
        repo.remove(o);
        new Handler().post(() ->notifyDataSetChanged());
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar1);
        }
    }
}

