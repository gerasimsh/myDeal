package com.example.deal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class DealPagerActivity extends AppCompatActivity {
    private static final String EXTRA_DEAL_ID =
            "com.example.deal.deal_id";

    private ViewPager mViewPager;
    private List<Deal> mDeals;

    public static Intent newIntent(Context pC, UUID dealId) {
        Intent intent = new Intent(pC, DealPagerActivity.class);
        intent.putExtra(EXTRA_DEAL_ID, dealId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_pager);

        UUID dealId = (UUID) getIntent().
                getSerializableExtra(EXTRA_DEAL_ID);


        mViewPager = (ViewPager) findViewById(R.id.deal_view_pager);

        mDeals = DealLab.get(this).getDeals();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                Deal deal = mDeals.get(i);

                return DealFragment.newInstance(deal.getId());
            }

            @Override
            public int getCount() {
                return mDeals.size();
            }
        });
    }
}
