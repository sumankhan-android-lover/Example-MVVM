package com.uaguria.example_mvvm.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.uaguria.example_mvvm.R;
import com.uaguria.example_mvvm.databinding.ActivityLoginBinding;
import com.uaguria.example_mvvm.util.Validation;
import com.uaguria.example_mvvm.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bindActivity();
        initListener();
    }

    private void bindActivity() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        viewModel.getLoginLiveData().observe(this, loginModel -> {
            if (loginModel.getToken() != null && loginModel.getToken().length()>0){
                Toast.makeText(LoginActivity.this, "Login Successful\n"+loginModel.getName(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initListener() {
        binding.login.setOnClickListener(v -> {
            if (verifyLayout())
                viewModel.login(binding.username.getEditText().getText().toString(),binding.password.getEditText().getText().toString());
        });
    }

    private boolean verifyLayout() {
        if (!Validation.isStringEmpty(binding.username.getEditText().getText().toString())) {
            if (Validation.isPhoneValid(binding.username.getEditText().getText().toString())) {

                if (!Validation.isStringEmpty(binding.password.getEditText().getText().toString())) {
                    return true;
                } else
                    Toast.makeText(this, getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, getString(R.string.valid_email), Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, getString(R.string.enter_email), Toast.LENGTH_SHORT).show();

        return true;
    }
}