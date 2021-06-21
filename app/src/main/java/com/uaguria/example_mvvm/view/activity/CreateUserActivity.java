package com.uaguria.example_mvvm.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.uaguria.example_mvvm.R;
import com.uaguria.example_mvvm.databinding.ActivityCreateUserBinding;

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCreateUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bindActivity();
    }

    private void bindActivity() {
        binding.createUser.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v==binding.createUser){

        }
    }
}