package com.uaguria.example_mvvm.view.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uaguria.example_mvvm.databinding.ActivityHomeBinding;
import com.uaguria.example_mvvm.model.HomeModel;
import com.uaguria.example_mvvm.view.adapter.HomeAdapter;
import com.uaguria.example_mvvm.viewModel.HomeViewModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding binding;
    private Context context;

    private HomeViewModel viewModel;
    private HomeAdapter entriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        bindActivity();

    }

    private void bindActivity() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getEntriesMutableLiveData().observe(this, this::setDataOnRecyclerView);

    }

    private void setDataOnRecyclerView(ArrayList<HomeModel> homeModels) {
        binding.recycler.setLayoutManager(new LinearLayoutManager(context));
        entriesAdapter = new HomeAdapter(homeModels, context);
        binding.recycler.setAdapter(entriesAdapter);
    }

    //TODO: this is normal way to implement recycler view
//    private void getEntriesData() {
//        String url = Constants.BASE_URL+Constants.ENTRIES;
//
//        AndroidNetworking.get(url)
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsOkHttpResponseAndJSONObject(new OkHttpResponseAndJSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(Response okHttpResponse, JSONObject response) {
//                        Log.e(TAG, "onResponse: " + response);
//                        parseEntriesResponse(response);
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.e(TAG, "onErrorResponse: " + anError.getErrorDetail());
//                    }
//                });
//    }
//
//    private void parseEntriesResponse(JSONObject response) {
//        try {
//            JSONArray array = response.getJSONArray("entries");
//            entriesList = new Gson().fromJson(array.toString(), new TypeToken<ArrayList<HomeModel>>() {}.getType());
//            //entriesAdapter.notifyDataSetChanged();
//            entriesAdapter = new HomeAdapter(entriesList, context);
//            binding.recycler.setAdapter(entriesAdapter);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}