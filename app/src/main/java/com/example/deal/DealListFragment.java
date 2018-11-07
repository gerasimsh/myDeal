package com.example.deal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class DealListFragment extends Fragment {

    private RecyclerView mDealRecyclerView;
    private DealAdapter mAdapter;

    public static final String TAG = "MyPAIN";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal_list, container, false);
        mDealRecyclerView = (RecyclerView) view
                .findViewById(R.id.deal_recycle_view);
        mDealRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        Log.i(TAG, "updateUI");
        DealLab dealLab = DealLab.get(getActivity());
        List<Deal> deals = dealLab.getDeals();
        mAdapter = new DealAdapter(deals);
        mDealRecyclerView.setAdapter(mAdapter);
    }

    ////______________View holder_________________________!!отвечает за раздувание макета
    private class DealHolder extends RecyclerView.ViewHolder {


        public DealHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_deal, parent, false));
            Log.i(TAG, "dealHolder");
        }

    }

    ///////___________ADAPTER__________________________________________
    private class DealAdapter extends RecyclerView.Adapter<DealHolder> {

        private List<Deal> mDeals;

        public DealAdapter(List<Deal> deals) {
            mDeals = deals;
        }

        @NonNull
        @Override
        public DealHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new DealHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull DealHolder dealHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return mDeals.size();
        }
    }
}
