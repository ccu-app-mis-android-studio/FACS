package com.example.jessl.alarm.schedule.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jessl.alarm.schedule.Product;

import java.util.ArrayList;
import java.util.List;

public class SqliteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	5;
    private	static final String DATABASE_NAME = "task";
    private	static final String TABLE_TASKS = "tasks";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASKNAME = "taskname";
    private static final String COLUMN_TASKDESC = "taskdesc";
    private static final String COLUMN_TASKDUEDATE = "taskduedate";
    private static final String COLUMN_TASKDUETIME = "taskduetime";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE	TABLE " + TABLE_TASKS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TASKNAME + " TEXT," + COLUMN_TASKDESC + " TEXT," + COLUMN_TASKDUEDATE + " TEXT," + COLUMN_TASKDUETIME + " TEXT" + ")";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    public List<Product> listProducts(){
        String sql = "select * from " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                String date = cursor.getString(3);
                String time = cursor.getString(4);
                storeProducts.add(new Product(id, name, desc, date, time));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }

    public void addProduct(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME, product.getName());
        values.put(COLUMN_TASKDESC, product.getDesc());
        values.put(COLUMN_TASKDUEDATE, product.getDate());
        values.put(COLUMN_TASKDUETIME, product.getTime());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_TASKS, null, values);
    }

    public void updateProduct(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME, product.getName());
        values.put(COLUMN_TASKDESC, product.getDesc());
        values.put(COLUMN_TASKDUEDATE, product.getDate());
        values.put(COLUMN_TASKDUETIME, product.getTime());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_TASKS, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(product.getId())});
    }

    public Product findProduct(String name){
        String query = "Select * FROM "	+ TABLE_TASKS + " WHERE " + COLUMN_TASKNAME + " = " + "name" + COLUMN_TASKDESC + " = " + "desc" + COLUMN_TASKDUEDATE + " = " + "date" + COLUMN_TASKDUETIME + " = " + "time" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Product mProduct = null;
        Cursor cursor = db.rawQuery(query,	null);
        if	(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String productName = cursor.getString(1);
            String productDesc = cursor.getString(2);
            String productDate = cursor.getString(3);
            String productTime = cursor.getString(4);
            mProduct = new Product(id, productName, productDesc, productDate, productTime);
        }
        cursor.close();
        return mProduct;
    }

    public void deleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
