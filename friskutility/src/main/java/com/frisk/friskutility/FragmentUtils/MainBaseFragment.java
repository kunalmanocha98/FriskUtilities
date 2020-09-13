package com.frisk.friskutility.FragmentUtils;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class MainBaseFragment extends Fragment {

    public View fragmentView;
    protected abstract int getLayoutId();
    public  Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            throw new IllegalArgumentException("No resource id associated in getLayoutId()");
        }

        fragmentView = inflater.inflate(getLayoutId(), container, false);
        initViews();
        setHasOptionsMenu(true);
        return fragmentView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }

    protected abstract void initViews();

}
