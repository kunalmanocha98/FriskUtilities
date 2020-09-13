package com.frisk.friskutility.AdapterUtils;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class GeneralViewPagerAdapter extends FragmentPagerAdapter {
    /**
     * Created by Faizan for Netree  on 17,January,2019
     * Version foop 2.0
     * Revision foop 2.0
     */
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public GeneralViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    public GeneralViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
    }

    public void removeFragment(Fragment fragment, int position) {
        fragments.remove(position);
        titles.remove(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.size() > position ? fragments.get(position) : null;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragments.set(position, fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}