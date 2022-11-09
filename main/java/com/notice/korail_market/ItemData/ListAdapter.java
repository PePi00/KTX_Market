package com.notice.korail_market.ItemData;

import static android.media.CamcorderProfile.get;

import com.notice.korail_market.ItemData.Food;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.notice.korail_market.MainActivity;
import com.notice.korail_market.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public static Context context;
        public int var;
        TextView itemName;
        TextView itemPrice,itemCount;
        ImageButton checkbox,itemAdd,itemOut;
        TextView totalPay;


        MyViewHolder(View view){
            super(view);
            itemName = view.findViewById(R.id.itemName);
            itemPrice = view.findViewById(R.id.itemPrice);
            checkbox = view.findViewById(R.id.checkbox);
            itemAdd = view.findViewById(R.id.itemAdd);
            itemOut = view.findViewById(R.id.itemOut);
            itemCount = view.findViewById(R.id.itemCount);


            totalPay = view.findViewById(R.id.totalPay);
        }
    }

    private ArrayList<Food> foodInfoArrayList;
    public ListAdapter(ArrayList<Food> foodInfoArrayList){
        this.foodInfoArrayList = foodInfoArrayList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);

        return new MyViewHolder(v);

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.itemName.setText(foodInfoArrayList.get(position).name);
        myViewHolder.itemPrice.setText(""+foodInfoArrayList.get(position).price+"원");
        myViewHolder.checkbox.setImageResource(foodInfoArrayList.get(position).resId);
        myViewHolder.itemCount.setText(""+foodInfoArrayList.get(position).count);
        myViewHolder.totalPay.setText(""+foodInfoArrayList.get(position).count*foodInfoArrayList.get(position).price);

        myViewHolder.itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.itemCount.setText(""+foodInfoArrayList.get(position).count++);
                myViewHolder.itemPrice.setText(""+foodInfoArrayList.get(position).price*foodInfoArrayList.get(position).count+"원");
            }
        });

        myViewHolder.itemOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.itemCount.setText(""+foodInfoArrayList.get(position).count--);
                myViewHolder.itemPrice.setText(""+foodInfoArrayList.get(position).price*foodInfoArrayList.get(position).count/1+"원");
            }

        });
        int totalPrice = myViewHolder.getAbsoluteAdapterPosition();

    }

    @Override
    public int getItemCount() {
        return foodInfoArrayList.size();
    }




}


