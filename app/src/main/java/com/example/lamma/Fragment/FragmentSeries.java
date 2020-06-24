package com.example.lamma.Fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lamma.Adapters.Adapter;
import com.example.lamma.Model.Upload;
import com.example.lamma.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentSeries extends Fragment {

    RecyclerView recyclerView;
    RecyclerView secondRecyclerView;
    private DatabaseReference mDatabaseRef =  FirebaseDatabase.getInstance().getReference();;
    private List<Upload> uploadList=new ArrayList<>();

    // Layout Manager, adapter class object, Linear Layout Manager
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    Adapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_s, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.firstRecyclerview);
        secondRecyclerView = view.findViewById(R.id.secondRecyclerview);
        usingFirebaseDatabase();

    }

    private void usingFirebaseDatabase() {

        mDatabaseRef.child("uploads")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            uploadList.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Upload model = snapshot.getValue(Upload.class);

                                uploadList.add(model);
                            }

                            // calling constructor of adapter with source list as a parameter
                            adapter = new Adapter(getContext(),uploadList);

                            // Set Horizontal Layout Manager for Recycler view

                            LinearLayoutManager HorizontalLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            LinearLayoutManager HorizontalLayout2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            recyclerView.setLayoutManager(HorizontalLayout);

                            // Set adapter on recycler view
                            recyclerView.setAdapter(adapter);
                            secondRecyclerView.setLayoutManager(HorizontalLayout2);
                            secondRecyclerView.setAdapter(adapter);


                        } else {
                            Toast.makeText(getContext(), "No Movies found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), "NO Movies found \n" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}