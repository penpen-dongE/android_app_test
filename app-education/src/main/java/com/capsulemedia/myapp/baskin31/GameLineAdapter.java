package com.capsulemedia.myapp.baskin31;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.capsulemedia.myapp.R;

import java.util.ArrayList;

public class GameLineAdapter extends RecyclerView.Adapter<GameLineAdapter.ViewHolder> {

    ArrayList<GameLine> items = new ArrayList<GameLine>();

    public void addItem(GameLine item){
        items.add(item);
    }

    public void setItems(ArrayList<GameLine> items){
        this.items = items;
    }

    public GameLine getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, GameLine item){
        items.set(position, item);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.gameline_item, parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GameLine item = items.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView11;
        TextView textView22;

        public ViewHolder(View itemView){
            super (itemView);

            textView11 = itemView.findViewById(R.id.textView11);
            textView22 = itemView.findViewById(R.id.textView22);
        }

        public void setItem(GameLine item){
            textView11.setText(item.getName());
            textView22.setText(item.getResult());

        }


    }

}
