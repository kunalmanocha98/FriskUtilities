package com.frisk.friskutility.FragmentUtils;

import androidx.recyclerview.widget.RecyclerView;

import com.frisk.friskutility.InterfaceUtils.RefreshCallback;
import com.frisk.friskutility.PaginationUtils.PaginationScrollListener;


public abstract class PaginationBaseFragment extends ListBaseFragment implements RefreshCallback {
    private int currentPage = getInitialPageNumber();
    private PaginationScrollListener paginationScrollListener;


    protected abstract int getInitialPageNumber();

    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    protected void onConfigurationSet() {
        paginationScrollListener = new PaginationScrollListener(getOffest(), recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                System.out.println("page " + page);
                currentPage++;
                fetchData();

            }
        };
        recyclerView.addOnScrollListener(paginationScrollListener);
    }


    void resetPagination() {
        if (paginationScrollListener != null) {
            currentPage = getInitialPageNumber();
            paginationScrollListener.resetState();
        }

    }

    @Override
    protected void fetchData() {
        if (currentPage == getInitialPageNumber()) {
            if (getGenericRecyclerViewAdapter() != null)
                getGenericRecyclerViewAdapter().reset();
        }
    }


    int getOffest() {
        return 10;
    }


    @Override
    public void callRefresh() {
        resetPagination();
        fetchData();
        // new TinyDB(getActivity()).putBoolean("isFirstCall", true);
    }


}
