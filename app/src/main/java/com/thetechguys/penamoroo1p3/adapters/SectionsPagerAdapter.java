package com.thetechguys.penamoroo1p3.adapters;

/**
 * Created by TheBank on 19/9/14.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;


import com.thetechguys.penamoroo1p3.ui.InboxFragment;
import com.thetechguys.penamoroo1p3.R;
import com.thetechguys.penamoroo1p3.ui.FriendsFragment;

import java.util.Locale;

/**
 * A {@link android.support.v13.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    protected Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }



    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        switch(position){
            case 0: return new InboxFragment();
            case 1: return new FriendsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return mContext.getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return mContext.getString(R.string.title_section2).toUpperCase(l);

        }
        return null;
    }
    public int getIcon(int position){
        switch (position) {
            case 0:
                return R.drawable.ic_tab_inbox;
            case 1:
                return R.drawable.ic_tab_friends;

        }
        return R.drawable.ic_tab_inbox;


    }
}