package com.example.jessl.alarm.schedule.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jessl.alarm.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    public TextView taskName;
    public TextView taskDueDate;
    public ImageView deleteProduct;
    public ImageView editProduct;

    public ProductViewHolder(View itemView) {
        super(itemView);
        taskName = (TextView)itemView.findViewById(R.id.task_name);
        taskDueDate = (TextView)itemView.findViewById(R.id.task_due_date);
        deleteProduct = (ImageView)itemView.findViewById(R.id.delete_product);
        editProduct = (ImageView)itemView.findViewById(R.id.edit_product);
    }
}
