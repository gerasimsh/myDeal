package com.example.deal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;


public class DealActivity extends SingleFragmentActivity {

    private static final String EXTRA_DEAL_ID =
            "com.example.deal.deal_id";

    public static Intent newIntent(Context packageContext, UUID dealID){
        Intent intent = new Intent(packageContext, DealActivity.class);
        intent.putExtra(EXTRA_DEAL_ID,dealID);
        return intent;
    }



    @Override
    protected Fragment createFragment() {

        UUID dealId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_DEAL_ID);
        return  DealFragment.newInstance(dealId);
           }
}
