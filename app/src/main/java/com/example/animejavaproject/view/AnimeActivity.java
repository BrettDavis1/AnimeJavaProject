package com.example.animejavaproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import com.example.animejavaproject.databinding.AnimeMainBinding;
import com.example.animejavaproject.model.AnimeResponse;
import com.example.animejavaproject.model.Top;
import com.example.animejavaproject.viewmodel.AnimeViewModel;

public class AnimeActivity extends AppCompatActivity {

    private AnimeViewModel viewModel;
    private AnimeMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: possible change getLayoutInflater() to LayoutInflater.from(this)
        binding = AnimeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(AnimeViewModel.class);

        // get data from intent
        Top top = (Top) getIntent().getExtras().getParcelable("TOP");
        binding.tvSynopsis.setMovementMethod(new ScrollingMovementMethod());

        initObservers(top);
        viewModel.getAnimeResponse(top.getMalId());
    }
    private void initObservers(Top top) {
        viewModel.getAnime().observe(this, new Observer<AnimeResponse>() {
            @Override
            public void onChanged(AnimeResponse animeResponse) {
                binding.tvTitle.setText("Title: " + top.getTitle());
                Glide.with(binding.getRoot()).load(top.getImageUrl()).into(binding.ivUrl);
                binding.tvRank.setText("Rank: "  + top.getRank());
                binding.tvType.setText("Type: " + top.getType());
                if(top.getEpisodes() == 0) {
                    binding.tvEpisodes.setText("Episodes: Show is still going");
                } else {
                    binding.tvEpisodes.setText("Episodes: " + top.getEpisodes());
                }
                binding.tvRating.setText("Rating: " + animeResponse.getRating());
                if(top.getType().toLowerCase().equals("movie")) {
                    binding.tvDuration.setText("Duration of Movie: " + animeResponse.getDuration());
                    binding.tvStartDate.setText("Aired on: " + top.getStartDate());
                    binding.tvEndDate.setText("");
                } else {
                    binding.tvDuration.setText("Duration: " + animeResponse.getDuration());
                    binding.tvStartDate.setText("Started on: " + top.getStartDate());
                    if(top.getEndDate().equals("")) {
                        binding.tvEndDate.setText("Still Going");
                    } else {
                        binding.tvEndDate.setText("Ended on: " + top.getEndDate());
                    }

                }
                binding.tvSynopsis.setText(animeResponse.getSynopsis());
                binding.btnShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        String shareBody = top.toString() + animeResponse.getDuration() + animeResponse.getRating() + animeResponse.getSynopsis();
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, top.getTitle());
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(shareIntent, "Share via"));
                    }
                });
            }
        });
    }
}
