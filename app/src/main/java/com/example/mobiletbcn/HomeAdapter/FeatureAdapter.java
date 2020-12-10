package com.example.mobiletbcn.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiletbcn.R;

import java.util.ArrayList;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder> {

    ArrayList<FeatureHelperClass> featureLocation;

    public FeatureAdapter(ArrayList<FeatureHelperClass> featureLocation) {
        this.featureLocation = featureLocation;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_card_design,parent,false);
        FeatureViewHolder featureViewHolder = new FeatureViewHolder(view);
        return featureViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        FeatureHelperClass featureHelperClass = featureLocation.get(position);

        holder.image.setImageResource(featureHelperClass.getImage());
        holder.title.setText(featureHelperClass.getTitle());
    }

    @Override
    public int getItemCount() {
        return featureLocation.size();
    }

    public static class FeatureViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;


        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);

            //hooks
            image = itemView.findViewById(R.id.feature_image);
            title = itemView.findViewById(R.id.feature_title);
        }
    }
}
