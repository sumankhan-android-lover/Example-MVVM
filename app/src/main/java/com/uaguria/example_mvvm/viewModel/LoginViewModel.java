package com.uaguria.example_mvvm.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener;
import com.google.gson.Gson;
import com.uaguria.example_mvvm.model.LoginModel;
import com.uaguria.example_mvvm.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class LoginViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    private LoginModel model;
    private MutableLiveData<LoginModel> loginLiveData;

    public LoginViewModel() {
        loginLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<LoginModel> getLoginLiveData(){
        return loginLiveData;
    }

    public void login(String username, String password) {
        try {
            Log.e(TAG, "login: username => "+username+" password => "+password );
            String url = Constants.DHEUSAGAR_ROOT+Constants.DHEUSAGAR_LOGIN;

            JSONObject outObject = new JSONObject();
            outObject.put("username", username);
            outObject.put("password", password);
            Log.e(TAG, "login: out json - " + outObject);

            AndroidNetworking.post(url)
                    .addJSONObjectBody(outObject)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsOkHttpResponseAndJSONObject(new OkHttpResponseAndJSONObjectRequestListener() {
                        @Override
                        public void onResponse(Response okHttpResponse, JSONObject response) {
                            Log.e(TAG, "onResponse: " + response);
                            parseLoginResponse(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.e(TAG, "onErrorResponse: details " + anError.getErrorDetail());
                            Log.e(TAG, "onErrorResponse: body " + anError.getErrorBody());
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseLoginResponse(JSONObject response) {
        try {
            if (response.getString("status").equalsIgnoreCase("1")){
                model = new Gson().fromJson(response.toString(), LoginModel.class);
                loginLiveData.setValue(model);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
