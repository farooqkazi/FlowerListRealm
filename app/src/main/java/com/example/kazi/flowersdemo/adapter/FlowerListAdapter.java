package com.example.kazi.flowersdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kazi.flowersdemo.R;
import com.example.kazi.flowersdemo.constant.Constants;
import com.example.kazi.flowersdemo.model.FlowerModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kazi on 12/07/2017.
 */

public class FlowerListAdapter extends RecyclerView.Adapter<FlowerListAdapter.FlowerViewHolder>{

    List<FlowerModel> flowers;
    Context context; //Used for Glide

    public FlowerListAdapter(List<FlowerModel> flowerModels, Context context) {
        this.flowers = flowerModels;
        this.context = context;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.flower_row, parent, false);

        return new FlowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlowerViewHolder holder, int position) {

        FlowerModel flower = flowers.get(position);

        holder.category.setText(flower.getCategory());
        holder.price.setText(Double.toString(flower.getPrice()));
        holder.instructions.setText(flower.getInstructions());
        holder.productId.setText(flower.getProductId().toString());
        holder.name.setText(flower.getName());

        //Glide needs to be used to process the Image
        /*Glide.with(context)
                .load(flower.getPhoto())//Pass the photo value here
                .apply(RequestOptions.fitCenterTransform())//Scale the photo
                .into(holder.photo);//Set the Image View Reference here, that's it!*/

        Picasso.with(context)
                .load(Constants.PHOTO_URL+flower.getPhoto())
                .resize(500, 250)
                .centerCrop()
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return flowers.size();
    }

    public class FlowerViewHolder extends RecyclerView.ViewHolder{

        TextView category;
        TextView price;
        TextView instructions;
        TextView productId;
        TextView name;
        ImageView photo;

        public FlowerViewHolder(View itemView) {
            super(itemView);
            category        = itemView.findViewById(R.id.tvCategory);
            price           = itemView.findViewById(R.id.tvPrice);
            instructions    = itemView.findViewById(R.id.tvInstructions);
            photo           = itemView.findViewById(R.id.ivPhoto);
            productId       = itemView.findViewById(R.id.tvProductId);
            name            = itemView.findViewById(R.id.tvName);
            photo           = itemView.findViewById(R.id.ivPhoto);
        }
    }
}
