package com.uaguria.example_mvvm.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uaguria.example_mvvm.R;
import com.uaguria.example_mvvm.databinding.ItemHomeBinding;
import com.uaguria.example_mvvm.model.HomeModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = HomeAdapter.class.getSimpleName();
    private ArrayList<HomeModel> entriesList;

    public HomeAdapter(ArrayList<HomeModel> entriesList, Context context) {
        this.entriesList = entriesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeAdapter.EntriesViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeModel model = entriesList.get(position);
        Log.e(TAG, "onBindViewHolder: size "+entriesList.size() );
        Log.e(TAG, "onBindViewHolder: model data "+ entriesList.get(position).toString());
        setUpEntries(model,(HomeAdapter.EntriesViewHolder)holder);
    }

    private void setUpEntries(HomeModel model, EntriesViewHolder holder) {
        holder.binding.name.setText(model.getAPI());
        holder.binding.description.setText(model.getDescription());
        holder.binding.categories.setText(model.getCategory());
    }

    @Override
    public int getItemCount() {
        return entriesList.size();
    }

    public class EntriesViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeBinding binding;

        public EntriesViewHolder(ItemHomeBinding view) {
            super(view.getRoot());
            this.binding = view;
        }
    }
}
