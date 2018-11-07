package com.example.deal;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DealLab {

    private static DealLab sDealLab;
    private List<Deal> mDeals;
    public static final  String TAG = "MyPAIN";



    public static DealLab get(Context context) {
        Log.i(TAG,"DEALLAB");
        if (sDealLab == null) sDealLab = new DealLab(context);
        return sDealLab;

    }
    //Cоздание текстовых объектов Deal
    private DealLab(Context context) {
        mDeals = new ArrayList<>();
        for (int i =0; i < 100;i++){
            //Log.i(TAG,"пизда рулю и седлу");
            Deal deal = new Deal();
            deal.setTitle("Deal #"+i);
            deal.setSolved(i%2 ==0);
            mDeals.add(deal);

        }

    }
    public List<Deal> getDeals(){
        return mDeals;
    }

    public  Deal getDeal(UUID id){
        for (Deal deal: mDeals){
            if(deal.getId().equals(id)){
                Log.i(TAG, "сука почему ты выводишься только одна");
                return deal;

            }
        }
        return null;
    }


}
