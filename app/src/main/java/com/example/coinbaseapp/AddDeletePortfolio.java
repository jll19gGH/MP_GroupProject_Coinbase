package com.example.coinbaseapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class AddDeletePortfolio extends Fragment{

    Button add;
    Button delete;
    TickerViewModel mViewModel;
    String AddTicker = "";
    String AddAmount = "";

    View.OnClickListener addButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddDialogFragment dialog = new AddDialogFragment();
            dialog.show(getActivity().getSupportFragmentManager(), "dialog");
        }
    };

    View.OnClickListener deleteButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.clearList();
        }
    };

    public AddDeletePortfolio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate =  inflater.inflate(R.layout.fragment_add_delete_portfolio, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);

        add = inflate.findViewById(R.id.addTicker);
        delete = inflate.findViewById(R.id.deleteTicker);

        add.setOnClickListener(addButton);
        delete.setOnClickListener(deleteButton);

        return inflate;
    }

}