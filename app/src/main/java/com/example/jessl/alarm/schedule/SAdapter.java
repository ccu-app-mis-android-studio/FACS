package com.example.jessl.alarm.schedule;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jessl.alarm.R;

import java.util.ArrayList;

public class SAdapter extends RecyclerView.Adapter<SAdapter.MyViewHolder> {

    Context context;
    ArrayList<SDoes> myDoes;

    public SAdapter(Context c, ArrayList<SDoes> p) {
        context = c;
        myDoes = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_sdoes, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.iddoes.setText(myDoes.get(i).getIddoes());
        myViewHolder.descdoes.setText(myDoes.get(i).getDescdoes());
        myViewHolder.datedoes.setText(myDoes.get(i).getDatedoes());
//
//        final String getIdDoes = myDoes.get(i).getIddoes();
//        final String getDescDoes = myDoes.get(i).getDescdoes();
//        final String getDateDoes = myDoes.get(i).getDatedoes();
//        final String getKeyDoes = myDoes.get(i).getKeydoes();
//
//        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent aa = new Intent(context,EditTaskDesk.class);
//                aa.putExtra("iddoes", getIdDoes);
//                aa.putExtra("descdoes", getDescDoes);
//                aa.putExtra("datedoes", getDateDoes);
//                aa.putExtra("keydoes", getKeyDoes);
//                context.startActivity(aa);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView iddoes, descdoes, datedoes, keydoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iddoes = (TextView) itemView.findViewById(R.id.iddoes);
            descdoes = (TextView) itemView.findViewById(R.id.descdoes);
            datedoes = (TextView) itemView.findViewById(R.id.datedoes);
        }
    }
}