package com.uaguria.example_mvvm.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.uaguria.example_mvvm.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}