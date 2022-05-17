package com.example.wish.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wish.Model.Post;
import com.example.wish.Model.User;
import com.example.wish.R;
import com.example.wish.databinding.DashboardRvSampleBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder> {

    ArrayList<Post> list;
    Context context;

    public PostAdapter(ArrayList<Post> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // We have to Inflate Layout Here
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_rv_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        // We have to set Data here
        Post model = list.get(position);
        Picasso.get()
                .load(model.getPostImage())
                .placeholder(R.drawable.diya_singh)
                .into(holder.binding.postImage);
        holder.binding.postDescription.setText(model.getPostDescription());

//        FirebaseDatabase.getInstance().getReference().child("Users")
//                .child(model.getPostedBy()).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        User user = snapshot.getValue(User.class);
//                        Picasso.get()
//                                .load(user.getProfile())
//                                .placeholder(R.drawable.diya_singh)
//                                .into(holder.binding.profileImage);
//                        holder.binding.userName.setText(user.getName());
//                        holder.binding.bio.setText(user.getProfession());
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        DashboardRvSampleBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
binding = DashboardRvSampleBinding.bind(itemView);
        }
    }
}
