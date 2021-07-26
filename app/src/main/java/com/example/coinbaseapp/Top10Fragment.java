package com.example.coinbaseapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class Top10Fragment extends Fragment {

    private ListView glistView;
    private ListView llistView;
    private SharedViewModel sharedViewModel;
    CustomWatchlist customWatchlist;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference coinsRef = database.getReference("/"+user.getUid()+"/coins");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_top10, container, false);


        glistView = (ListView) inflate.findViewById(R.id.gainersList);
        llistView = (ListView) inflate.findViewById(R.id.losersList);

        glistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                APIQuote quote = (APIQuote) parent.getItemAtPosition(position);
                //sharedViewModel.setCurrentCoin(quote);

                CustomDialogFragmentNoAdd d=new CustomDialogFragmentNoAdd(quote);
                d.show(getChildFragmentManager(),"dialog3");
            }
        });

        llistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                APIQuote quote = (APIQuote) parent.getItemAtPosition(position);
                //sharedViewModel.setCurrentCoin(quote);

                CustomDialogFragmentNoAdd d=new CustomDialogFragmentNoAdd(quote);
                d.show(getChildFragmentManager(),"dialog4");
            }
        });



        Query sortCoinsAscending=coinsRef.orderByChild("changesPercentage").limitToLast(5);

        sortCoinsAscending.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
               for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                   sharedViewModel.addToGainers(postSnapshot.getValue(APIQuote.class));
               }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        Query sortCoinsDescending=coinsRef.orderByChild("changesPercentage");

        sortCoinsDescending.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    sharedViewModel.addToLosers(postSnapshot.getValue(APIQuote.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getGainers().observe(getViewLifecycleOwner(), new Observer<LinkedList<APIQuote>>() {
            @Override
            public void onChanged(LinkedList<APIQuote> coins) {
               CustomWatchlist customWatchlist = new CustomWatchlist(getActivity(), R.layout.list_row, sharedViewModel.getGainers());
                glistView.setAdapter(customWatchlist);
            }
        });

        sharedViewModel.getLosers().observe(getViewLifecycleOwner(), new Observer<LinkedList<APIQuote>>() {
            @Override
            public void onChanged(LinkedList<APIQuote> coins) {
                CustomWatchlist customWatchlist = new CustomWatchlist(getActivity(), R.layout.list_row, sharedViewModel.getLosers());
                llistView.setAdapter(customWatchlist);
            }
        });
    }
}