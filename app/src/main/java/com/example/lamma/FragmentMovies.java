package com.example.lamma;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentMovies extends Fragment {

    RecyclerView recyclerView;
    RecyclerView secondRecyclerView;
    ArrayList<String> source;
    ArrayList<Integer> images;
    private DatabaseReference mDatabaseRef =  FirebaseDatabase.getInstance().getReference();;
    private List<Upload> uploadList=new ArrayList<>();

    // Layout Manager
    RecyclerView.LayoutManager RecyclerViewLayoutManager;

    // adapter class object
    Adapter adapter;

    // Linear Layout Manager

    View ChildView;
    int RecyclerViewItemPosition;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_m, container, false);
        return rootView;




    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView
                = view.findViewById(
                R.id.firstRecyclerview);


        // Adding items to RecyclerView.
             // AddItemsToRecyclerViewArrayList();
            //AddImagesToRecyclerViewArrayList();
        usingFirebaseDatabase();



//-------------------------- Second ------------------------

        secondRecyclerView
                = view.findViewById(
                R.id.secondRecyclerview);

    }


    // Function to add items in RecyclerView.
    public void AddItemsToRecyclerViewArrayList()
    {
        // Adding items to ArrayList
        source = new ArrayList<>();
        source.add("Joker");
        source.add("AquaMan");
        source.add("Avengers");
        source.add("site");
        source.add("for");
        source.add("interview");
        source.add("preparation");
    }

    public void AddImagesToRecyclerViewArrayList()
    {
        // Adding images to ArrayList
        images = new ArrayList<>();
        images.add(R.drawable.joker);
        images.add(R.drawable.aquaman);
        images.add(R.drawable.avengers);
        images.add(R.drawable.joker);
        images.add(R.drawable.aquaman);
        images.add(R.drawable.avengers);
        images.add(R.drawable.avengers);
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

                            // calling constructor of adapter
                            // with source list as a parameter
                            adapter = new Adapter(getContext(),uploadList);

                            // Set Horizontal Layout Manager
                            // for Recycler view

                            LinearLayoutManager HorizontalLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            LinearLayoutManager HorizontalLayout2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            recyclerView.setLayoutManager(HorizontalLayout);

                            // Set adapter on recycler view
                            recyclerView.setAdapter(adapter);
                            secondRecyclerView.setLayoutManager(HorizontalLayout2);
                            secondRecyclerView.setAdapter(adapter);


                        } else {
                            Toast.makeText(getContext(), "No images in firebase", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), "NO images found \n" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
