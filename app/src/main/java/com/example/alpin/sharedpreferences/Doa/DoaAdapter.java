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

import java.util.List;

/**
 * Created by alpin on 23/07/17.
 */

public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.MyViewHolder> {

    private List<Doa> dataset;
    private Context context;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tv_name);
            image = (ImageView) itemView.findViewById(R.id.img_doa);
        }
    }

    public DoaAdapter(Context context, List<Doa> dataset){
        this.context = context;
        this.dataset = dataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Doa doa = dataset.get(position);

        holder.name.setText(doa.getNama());
        Glide.with(context).load(doa.getImageAddrees()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public Doa getItem(int position) {
        return dataset.get(position);
    }

    public void insert(Doa newDoa) {
        dataset.add(0, newDoa);
        notifyItemInserted(0);
    }

    public void remove(int position){
        dataset.remove(position);
        notifyItemRemoved(position);
    }

    public void update(int position, Doa doa){
        dataset.set(position, doa);
        notifyItemChanged(position);
    }

}

