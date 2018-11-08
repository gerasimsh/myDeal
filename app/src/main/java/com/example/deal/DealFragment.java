package com.example.deal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;

import static java.text.DateFormat.FULL;


public class DealFragment extends Fragment {
    private static final String ARG_DEAL_ID = "deal_id";
    private Deal mDeal;
    private EditText mTitleField;
    private Button mDateBtn;
    private CheckBox mSolvedChb;

    public static DealFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DEAL_ID, crimeId);
        DealFragment fragment = new DealFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDeal = new Deal();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deal, container, false);
        mTitleField = (EditText) v.findViewById(R.id.deal_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDeal.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Calendar calendar = Calendar.getInstance();
        String mCurrentDate = DateFormat.getDateInstance(FULL).format(calendar.getTime());

        mDateBtn = (Button) v.findViewById(R.id.deal_date);
        mDateBtn.setText(mCurrentDate);
        mDateBtn.setEnabled(false);

        mSolvedChb = (CheckBox) v.findViewById(R.id.deal_solved);
        mSolvedChb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDeal.setSolved(isChecked);
            }
        });


        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
