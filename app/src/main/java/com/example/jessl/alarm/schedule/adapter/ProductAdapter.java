package com.example.jessl.alarm.schedule.adapter;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jessl.alarm.schedule.Product;
import com.example.jessl.alarm.R;
import com.example.jessl.alarm.schedule.Schedule;
import com.example.jessl.alarm.schedule.database.SqliteDatabase;

import java.util.Calendar;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    private List<Product> listProducts;

    private EditText taskdate;
    private EditText tasktime;
    private String selectedTime;
    private String selectedDate;

    private SqliteDatabase mDatabase;

    public ProductAdapter(Context context, List<Product> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
        mDatabase = new SqliteDatabase(context);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Product singleProduct = listProducts.get(position);

        holder.taskname.setText(singleProduct.getName());
        holder.taskdate.setText(singleProduct.getDate());

        holder.editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(singleProduct);
            }
        });

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database
                mDatabase.deleteProduct(singleProduct.getId());

                //refresh the activity page.
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }


    private void editTaskDialog(final Product product) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.activity_add_task, null);

        final EditText taskname = (EditText) subView.findViewById(R.id.add_task_name);
        final EditText taskdesc = (EditText) subView.findViewById(R.id.add_task_description);
        taskdate = (EditText) subView.findViewById(R.id.add_task_ending);
        tasktime = (EditText) subView.findViewById(R.id.add_task_ending_time);

        if (product != null) {
            taskname.setText(product.getName());
            taskdesc.setText(product.getDesc());
            taskdate.setText(product.getDate());
            tasktime.setText(product.getTime());
        }

        //add task date
        final ImageView addTaskDate = (ImageView) subView.findViewById(R.id.add_task_date);
        addTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                taskdate.setText( year + " - " + (monthOfYear + 1) + " - " + dayOfMonth);

                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        // delete task date
        final ImageView deleteTaskDate = (ImageView) subView.findViewById(R.id.delete_task_date);
        deleteTaskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskdate.setText("");
            }
        });
        final ImageView addTaskTime = (ImageView) subView.findViewById(R.id.add_task_time);
        addTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                selectedTime =  String.valueOf(hourOfDay) + " : " + String.valueOf(minute);
                                 tasktime.setText(String.valueOf(hourOfDay) + " : " + String.valueOf(minute));
                            }
                        }, hour, minute, DateFormat.is24HourFormat(context));
                timePickerDialog.show();
            }
        });
        final ImageView deleteTaskTime = (ImageView) subView.findViewById(R.id.delete_task_time);
        deleteTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasktime.setText("");
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Task");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDIT TASK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = taskname.getText().toString();
                final String desc = taskdesc.getText().toString();
                final String date = taskdate.getText().toString();
                final String time = tasktime.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc)) {
                    Toast.makeText(context, R.string.invalid_input_values, Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(date) || TextUtils.isEmpty(time)) {
                    Toast.makeText(context, R.string.task_date_and_time, Toast.LENGTH_LONG).show();
                } else {
                    mDatabase.updateProduct(new Product(product.getId(), name, desc, date, time));
                    //refresh the activity
                    ((Activity) context).finish();
                    context.startActivity(((Activity) context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

}