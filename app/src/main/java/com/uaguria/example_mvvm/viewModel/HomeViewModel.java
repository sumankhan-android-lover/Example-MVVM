package com.uaguria.example_mvvm.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uaguria.example_mvvm.model.HomeModel;
import com.uaguria.example_mvvm.util.Constants;
import com.uaguria.example_mvvm.view.adapter.HomeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Response;

public class HomeViewModel extends ViewModel {
    private static final String TAG = HomeViewModel.class.getSimpleName();
    private ArrayList<HomeModel> entriesList;
    MutableLiveData<ArrayList<HomeModel>> entriesLiveData;

    public HomeViewModel(){
        entriesList = new ArrayList<>();
        entriesLiveData = new MutableLiveData<>();

        //this method call for api
        init();
    }

    public MutableLiveData<ArrayList<HomeModel>> getEntriesMutableLiveData() {
        return entriesLiveData;
    }

    private void init() {
        String url = Constants.BASE_URL+Constants.ENTRIES;

        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsOkHttpResponseAndJSONObject(new OkHttpResponseAndJSONObjectRequestListener() {
                    @Override
                    public void onResponse(Response okHttpResponse, JSONObject response) {
                        Log.e(TAG, "onResponse: " + response);
                        parseEntriesResponse(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "onErrorResponse: " + anError.getErrorDetail());
                    }
                });
    }

    private void parseEntriesResponse(JSONObject response) {
        try {
            JSONArray array = response.getJSONArray("entries");
            entriesList = new Gson().fromJson(array.toString(), new TypeToken<ArrayList<HomeModel>>() {}.getType());
            entriesLiveData.setValue(entriesList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
