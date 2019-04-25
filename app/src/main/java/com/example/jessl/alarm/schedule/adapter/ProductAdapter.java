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
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessl.alarm.schedule.Product;
import com.example.jessl.alarm.R;
import com.example.jessl.alarm.schedule.database.SqliteDatabase;

import java.util.Calendar;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{

    private Context context;
    private List<Product> listProducts;

    private EditText taskName;
    private EditText taskDescription;
    private static EditText taskDueDate;
    private static EditText taskDueTime;
    private CheckBox reminder;
    private boolean isAlarmSet = false;
    private static String selectedTime;
    private static String selectedDate;

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

        holder.taskName.setText(singleProduct.getName());
        holder.taskDueDate.setText(singleProduct.getDate());

//        holder.editProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editTaskDialog(singleProduct);
//            }
//        });

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database

                mDatabase.deleteProduct(singleProduct.getId());

                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }


//    private void editTaskDialog(final Product product){
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View subView = inflater.inflate(R.layout.add_product_layout, null);
//
//        final EditText taskName = (EditText) subView.findViewById(R.id.enter_name);
//        final EditText taskDescription = (EditText) subView.findViewById(R.id.enter_desc);
//        final EditText taskDueDate = (EditText) subView.findViewById(R.id.add_task_ending);
//        final EditText taskDueTime = (EditText) subView.findViewById(R.id.add_task_ending_time);
//
//        reminder = (CheckBox) subView.findViewById(R.id.set_task_alarm);
//        reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    isAlarmSet = true;
//                } else {
//                    isAlarmSet = false;
//                }
//            }
//        });
//
//        if(product != null){
//            taskName.setText(product.getName());
//            taskDescription.setText(product.getDesc());
//            taskDueDate.setText(product.getDate());
//            taskDueTime.setText(product.getTime());
//
//        }
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Edit Task");
//        builder.setView(subView);
//        builder.create();
//
//        builder.setPositiveButton("EDIT PRODUCT", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                final String name = taskName.getText().toString();
//                final String desc = taskDescription.getText().toString();
//                final String dueDate = taskDueDate.getText().toString();
//                final String dueTime = taskDueTime.getText().toString();
//
//                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc)) {
//                    Toast.makeText(context, R.string.invalid_input_values, Toast.LENGTH_LONG).show();
//                } else if (TextUtils.isEmpty(dueDate) || TextUtils.isEmpty(dueTime)) {
//                    Toast.makeText(context, R.string.task_date_and_time, Toast.LENGTH_LONG).show();
//                }else {
//                    Product newProduct = new Product(product.getId(), name, desc, dueDate, dueTime);
//                    mDatabase.addProduct(newProduct);
//
//                    //refresh the activity
//                    ((Activity) context).finish();
//                    context.startActivity(((Activity) context).getIntent());
//                }
//            }
//        });
//
//        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
//            }
//        });
//        builder.show();
//    }
//    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            final Calendar c = Calendar.getInstance();
//            int hour = c.get(Calendar.HOUR_OF_DAY);
//            int minute = c.get(Calendar.MINUTE);
//            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
//        }
//        @Override
//        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
//            selectedTime =  String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
//            taskDueTime.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
//        }
//    }
//    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            final Calendar c = Calendar.getInstance();
//            int year = c.get(Calendar.YEAR);
//            int month = c.get(Calendar.MONTH);
//            int day = c.get(Calendar.DAY_OF_MONTH);
//            return new DatePickerDialog(getActivity(), this, year, month, day);
//        }
//        public void onDateSet(DatePicker view, int year, int month, int day) {
//            selectedDate = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
//            taskDueDate.setText(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day));
//        }
//    }
}
