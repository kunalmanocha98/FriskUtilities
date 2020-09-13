package com.frisk.friskutility.FragmentUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.frisk.friskutility.AdapterUtils.GenericRecyclerViewAdapter;
import com.frisk.friskutility.R;

/**
 * Created by Faizan for Netree  on 17,January,2019
 * Version foop 2.0
 * Revision foop 2.0
 */
public abstract class ListBaseFragment extends MainBaseFragment {
    protected RecyclerView recyclerView;
    protected FrameLayout flyt;
    private ProgressBar progressBar;

    private GenericRecyclerViewAdapter genericRecyclerViewAdapter;


    GenericRecyclerViewAdapter getGenericRecyclerViewAdapter() {
        return genericRecyclerViewAdapter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.common_recycler_view;
    }


    @Override
    protected void initViews() {

        flyt = fragmentView.findViewById(R.id.flyt);
        recyclerView = fragmentView.findViewById(R.id.recyclerView);
        progressBar = fragmentView.findViewById(R.id.progress_bar);

        if (shouldsizefixed()) {
            recyclerView.setHasFixedSize(true);
        } else {
            recyclerView.setHasFixedSize(false);
        }
        onViewCreate();
        addAdapter();
        if (shouldFetchData()) {
            fetchData();
        }
        onConfigurationSet();


    }

    boolean shouldsizefixed() {
        return false;
    }

    boolean shouldFetchData() {
        return true;
    }

    private void addAdapter() {
        recyclerView.setLayoutManager(getLayoutManager());
        genericRecyclerViewAdapter = getAdapter();
        recyclerView.setAdapter(genericRecyclerViewAdapter);
    }

    public void showProgress(boolean show) {
        if (progressBar != null)
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    int isProgressing() {
        return progressBar.getVisibility();
    }

    protected abstract GenericRecyclerViewAdapter getAdapter();

    protected abstract RecyclerView.LayoutManager getLayoutManager();


    protected abstract void fetchData();

    protected abstract void onViewCreate();

    protected abstract void onConfigurationSet();


}
