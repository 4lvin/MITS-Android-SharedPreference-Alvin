package com.example.alpin.sharedpreferences.Doa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alpin.sharedpreferences.R;
import com.example.alpin.sharedpreferences.model.Doa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alpin on 23/07/17.
 */

public class DoaStaticAdapter extends RecyclerView.Adapter<DoaStaticAdapter.MyViewHolder> {

    private List<Doa> dataset;

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivImage = itemView.findViewById(R.id.iv_icon);
        }
    }
    public DoaStaticAdapter(List<Doa> inputData) {
        dataset = inputData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_static, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Doa doa = dataset.get(position);
        holder.tvTitle.setText(doa.getNama());
        holder.ivImage.setImageResource(doa.getImageAdrees());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public Doa getItem(int position) {
        return dataset.get(position);
    }

    public void setFilter(ArrayList<Doa> newList){
        dataset = new ArrayList<>();
        dataset.addAll(newList);
        notifyDataSetChanged();
    }

}

