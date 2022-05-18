package com.example.wish.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wish.Adapter.PostAdapter;
import com.example.wish.Adapter.StoryAdapter;
import com.example.wish.Model.Post;
import com.example.wish.Model.StoryModel;
import com.example.wish.NewsActivity;
import com.example.wish.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView storyRv, dashboardRv;
    ArrayList<StoryModel> list;
    ArrayList<Post> postList;
    FirebaseAuth auth;
    FirebaseDatabase database;
    TextView discover;
    ShimmerFrameLayout shimmerFrameLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();


        storyRv = view.findViewById(R.id.storyRv);
        discover = view.findViewById(R.id.discover);

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getContext(), NewsActivity.class);
                startActivity(intent);
            }
        });

        list = new ArrayList<>();
        list.add(new StoryModel(R.drawable.modi, R.drawable.ic_live, R.drawable.modi, "India News"));
        list.add(new StoryModel(R.drawable.mobama, R.drawable.ic_live, R.drawable.mobama, "Aaj Tak"));
        list.add(new StoryModel(R.drawable.anjana, R.drawable.ic_live, R.drawable.aajtakanchor, "Anjana"));

        StoryAdapter adapter = new StoryAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        storyRv.setLayoutManager(linearLayoutManager);
        storyRv.setNestedScrollingEnabled(false);
        storyRv.setAdapter(adapter);

        dashboardRv = view.findViewById(R.id.dashboardRv);

        postList = new ArrayList<>();

        PostAdapter postAdapter = new PostAdapter(postList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        dashboardRv.setLayoutManager(layoutManager);
        dashboardRv.setNestedScrollingEnabled(false);
        dashboardRv.setAdapter(postAdapter);


        database.getReference().child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Post post = dataSnapshot.getValue(Post.class);
                    post.setPostId(dataSnapshot.getKey());
                    postList.add(post);
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}