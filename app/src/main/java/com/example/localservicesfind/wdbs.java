package com.example.localservicesfind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class wdbs extends SQLiteOpenHelper {
    // (Worker) Create Database workerdbs.db

    private static final String dbname = "workerdbs.db";

    public wdbs (Context context) {
        super(context, dbname, null, 1);
    }


    // (Worker) Create Table In workerdbs Database....
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (" create table worker (name text not null, mobile int(10) primary key, address varchar not null, street varchar not null, pin integer not null, category text not null)");
        winsertData("Rahul", 1234567890, "Kehri Gaon", "Near GRD Girls College", 248007, "electrical" ,db);
        winsertData("Monu", 1234582890, "Kehri Gaon", "Near GRD Girls College", 248006, "mechanic" , db);
        winsertData("Gopal", 1245678902, "Kehri Gaon", "Near GRD Girls College", 244007, "cook", db);
        winsertData("Mohan", 1445600902, "Kehri Gaon", "Prem Nagar", 244007, "cook", db);

    }


    private  void winsertData(String name, Integer mobile, String address, String street, Integer pin, String category, SQLiteDatabase db)
    {
        ContentValues c = new ContentValues();
        c.put("name",name);
        c.put("mobile",mobile);
        c.put("address",address);
        c.put("street",street);
        c.put("pin",pin);
        c.put("category",category);

        db.insert("worker", null, c);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists worker");
    }

    public Boolean cook (String cook)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from worker where mobile = ?", new String[] {cook});
        if(cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }

}

