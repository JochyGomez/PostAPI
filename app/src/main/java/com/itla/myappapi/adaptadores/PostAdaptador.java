package com.itla.myappapi.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.myappapi.R;
import com.itla.myappapi.entity.Post;

import java.util.ArrayList;

public class PostAdaptador extends RecyclerView.Adapter {
        ArrayList<Post> posts;
        static TextView tvbody, tvcomments, tvcreatedAt, tvid, tvliked, tvlikes, tvtags, tvtitle, tvuserEmail, tvuserName, tvviews, tvtag;

        public PostAdaptador(ArrayList<Post>posts) {
            this.posts=posts;
        }

        public static class MyHolder extends RecyclerView.ViewHolder{
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                tvbody = itemView.findViewById(R.id.tvbody);
                tvcomments = itemView.findViewById(R.id.tvcomments);
                tvlikes = itemView.findViewById(R.id.tvlikes);
                tvtitle = itemView.findViewById(R.id.tvtitle);
                tvuserEmail = itemView.findViewById(R.id.userEmail);
                tvviews = itemView.findViewById(R.id.tvviews);
                tvuserName = itemView.findViewById(R.id.tvuserName);
                tvtag = itemView.findViewById(R.id.tvtag);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_post,parent ,false);

            PostAdaptador.MyHolder myHolder = new MyHolder(view);

            return myHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            Post post = posts.get(position);
            tvbody.setText(post.getBody());
            tvcomments.setText(post.getComments());
            tvlikes.setText(post.getLikes());
            tvviews.setText(post.getViews());
            tvtitle.setText(post.getTitle());
            tvuserEmail.setText(post.getUserEmail());
            tvuserName.setText(post.getUserName());
            tvtag.setText(post.getTag());
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }
}
