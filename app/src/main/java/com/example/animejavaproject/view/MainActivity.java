package com.example.animejavaproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.animejavaproject.adapter.AnimeTopAdapter;
import com.example.animejavaproject.adapter.AnimeTopClickListener;
import com.example.animejavaproject.databinding.ActivityMainBinding;
import com.example.animejavaproject.model.AnimeResponse;
import com.example.animejavaproject.model.Top;
import com.example.animejavaproject.viewmodel.AnimeViewModel;
import com.example.animejavaproject.viewmodel.MainViewModel;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AnimeTopClickListener {
    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setUpViews();
        initObservers();

        viewModel.getTopResponse(1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //viewModel.savePageData(binding.tvPageNum.toString());
    }
    private void initObservers() {
        viewModel.getTops().observe(this, new Observer<List<Top>>() {
            @Override
            public void onChanged(List<Top> tops) {
                AnimeTopAdapter animeTopAdapter = new AnimeTopAdapter(tops, MainActivity.this);
                binding.rvImg.setAdapter(animeTopAdapter);
            }
        });
    }
    private void setUpViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvImg.setLayoutManager(linearLayoutManager);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = Integer.parseInt(binding.tvPageNum.getText().toString());
                page++;
                viewModel.getTopResponse(page);
                binding.tvPageNum.setText(String.valueOf(page));
                Log.i("INFO:", "onClick: page is called");
            }
        });
        binding.btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = Integer.parseInt(binding.tvPageNum.getText().toString());
                if(page > 1) {
                    page--;
                    viewModel.getTopResponse(page);
                    binding.tvPageNum.setText(String.valueOf(page));
                } else {
                    Toast.makeText(binding.getRoot().getContext(), "You are on page 1 cannot go to previous page", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.rvImg.getLayoutManager().equals(linearLayoutManager)) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(binding.getRoot().getContext(), 2);
                    binding.rvImg.setLayoutManager(gridLayoutManager);
                    binding.btnLayout.setText("Linear Layout");
                } else {
                    binding.rvImg.setLayoutManager(linearLayoutManager);
                    binding.btnLayout.setText("Grid Layout");
                }
            }
        });

    }

    @Override
    public void itemClicked(Top top) {
        // TODO: Go to new activity passing the top
        //Toast.makeText(this, top.toString(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, AnimeActivity.class);
        intent.putExtra("TOP", top);
        startActivity(intent);
    }
}