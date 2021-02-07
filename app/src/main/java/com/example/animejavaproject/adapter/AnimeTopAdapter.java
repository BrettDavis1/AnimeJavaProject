package com.example.animejavaproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animejavaproject.databinding.AnimeImageBinding;
import com.example.animejavaproject.model.Top;

import java.util.List;

public class AnimeTopAdapter extends RecyclerView.Adapter<AnimeTopAdapter.AnimeTopViewHolder> {
    private final List<Top> tops;
    private AnimeTopClickListener listener;

    public AnimeTopAdapter(List<Top> tops, AnimeTopClickListener listener) {
        this.tops = tops;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnimeTopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AnimeImageBinding binding = AnimeImageBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new AnimeTopViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeTopViewHolder holder, int position) {
        Top top = tops.get(position);
        holder.setAnimeTop(top);
    }

    @Override
    public int getItemCount() {
        return tops.size();
    }

    static class AnimeTopViewHolder extends RecyclerView.ViewHolder {
        private final AnimeImageBinding binding;
        private AnimeTopClickListener listener;

        public AnimeTopViewHolder(@NonNull AnimeImageBinding binding, AnimeTopClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }
        public void setAnimeTop(Top top) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.itemClicked(top);
                    }
                }
            });
            Glide.with(this.itemView).load(top.getImageUrl()).into(binding.ivUrl);
            String rank = "Rank: " + top.getRank();
            String title = "Title: " + top.getTitle();
            String type = "Type: " + top.getType();
            binding.tvRank.setText(rank);
            binding.tvTitle.setText(title);
            binding.tvType.setText(type);
        }
    }
}
