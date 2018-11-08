package com.example.deal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.view.View.GONE;


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
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        Log.i(TAG, "updateUI");
        DealLab dealLab = DealLab.get(getActivity());
        List<Deal> deals = dealLab.getDeals();
        if(mAdapter==null) {
            mAdapter = new DealAdapter(deals);
            mDealRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }

    ////______________View holder_________________________!!отвечает за раздувание макета
    private class DealHolder extends RecyclerView.ViewHolder {



        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Deal mDeal;




        public DealHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_deal, parent, false));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),
                            mDeal.getTitle()+" clicked!", Toast.LENGTH_SHORT).show();
                    Intent intent = DealPagerActivity.newIntent(getActivity(),mDeal.getId());
                }
            });

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.deal_title);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.deal_date);
            Log.i(TAG, "dealHolder");
//            mSolvedImageView = (ImageView)
//                    itemView.findViewById(R.id.deal_solved);
        }
            public void bind (Deal deal){
            mDeal = deal;
            mTitleTextView.setText(mDeal.getTitle());
            mDateTextView.setText(mDeal.getDate().toString());

        }
        /*@Override
        public void onClick(View view){
            Toast.makeText(getActivity(),
                    mDeal.getTitle()+" clicked!", Toast.LENGTH_SHORT).show();
        }*/

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
            Deal deal = mDeals.get(i);
            dealHolder.bind(deal);

        }

        @Override
        public int getItemCount() {
            return mDeals.size();
        }
    }
}
