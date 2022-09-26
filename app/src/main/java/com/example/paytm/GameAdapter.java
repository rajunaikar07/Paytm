package com.example.paytm;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameHolder> {
    Context context;
    List<GameModel> gameModelList=new ArrayList<>();

    public GameAdapter(Context context, List<GameModel> gameModelList) {
        this.context = context;
        this.gameModelList = gameModelList;

    }

    @NonNull
    @Override
    public GameAdapter.GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclar,parent,false);
        return new GameHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.GameHolder holder, int position) {
        holder.shapeableImageView.setImageResource(gameModelList.get(position).getImage());
        holder.name.setText(gameModelList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return gameModelList.size();
    }

    public class GameHolder extends RecyclerView.ViewHolder {
        ShapeableImageView shapeableImageView;
        TextView name;
        public GameHolder(@NonNull View itemView) {
            super(itemView);
            shapeableImageView=itemView.findViewById(R.id.user_image);
            name=itemView.findViewById(R.id.name1);
        }
    }
}
