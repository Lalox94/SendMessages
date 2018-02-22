package com.example.lalo.sendmessages.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lalo.sendmessages.Fragments.MyProductListTab;
import com.example.lalo.sendmessages.Fragments.StoreContactTab;
import com.example.lalo.sendmessages.Fragments.StoreProductListTab;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int nNumberOfTabs;

    public PagerAdapter(FragmentManager fm, int nNumberOfTabs) {
        super(fm);
        this.nNumberOfTabs = nNumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MyProductListTab myProductListTab = new MyProductListTab();
                return myProductListTab;
            case  1:
                StoreProductListTab storeProductListTab = new StoreProductListTab();
                return storeProductListTab;
            case 2:
                StoreContactTab storeContactTab = new StoreContactTab();
                return storeContactTab;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return nNumberOfTabs;
    }
}
