package com.example.deal;

import android.support.v4.app.Fragment;

public class DealListActivity extends SingleFragmentActivity {




    @Override
    protected Fragment createFragment() {
        return new DealListFragment();
    }
}
