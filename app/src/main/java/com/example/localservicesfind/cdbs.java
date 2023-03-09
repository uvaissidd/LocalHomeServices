package com.example.localservicesfind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Patterns;
import android.widget.EditText;

public class cdbs extends SQLiteOpenHelper {
    // (Customer) Create Database csignup.db

    private static final String dbname = "csignup.db";

    public cdbs(Context context) {
        super(context, dbname, null, 1);
    }


    //(Customer) Create A Table In csignup Database....
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL (" create table customer ( username varchar primary key, name text not null, password varchar not null, repassword varchar not null, mobile integer(10) not null, email varchar not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists customer");
    }


    // (Customer) Method for Insert Data In Table......
    public boolean cinsertdata( String name, String username, String password, String repassword, String mobile, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put("name", name);
        c.put("username", username);
        c.put("password", password);
        c.put("repassword", repassword);
        c.put("mobile", mobile);
        c.put("email", email);


        long r = db.insert("customer", null, c);
        if (r == -1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    // (Customer) Create a Method For Checking Username ...(Username Should Not be Double or Same For Two Other Person It is Unique)

    public Boolean ccheckusername(String username)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from customer where username = ?", new String[] {username});
        if(cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }

    // (Customer) Create a Method For Checking Mobile Number... (Mobile Number Should Not be Double or Same For Two Other Person It is Unique)

    public Boolean ccheckmobile(String mobile)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from customer where mobile = ?", new String[] {mobile});
        if(cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }

    // (Customer) Create a Method For Checking Email..... (Email Should Not be Same For Two Other Person It is Unique)
    public Boolean ccheckemail(String email)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from customer where email = ?", new String[] {email});
        if(cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }

    // (Customer) Create a Method For Checking Username & Email..... (Same User C'nt Create Account With Last Username)
    public Boolean ccheckusernamepassword(String username, String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from customer where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0) {
            return true;
        }
        else {
            return false;
        }
    }

    // (Customer) Create a Method For Validation Email..... (Email Should be Contain "@" And "." Symbols)
    public boolean validateEmailAddress(EditText cemail) {
        String emailInput = cemail.getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    // (Customer) Create a Method For Validation Mobile Number.....
    public boolean cvalidateMobileNum(EditText cmobile)
    {
        String mobileInput = cmobile.getText().toString();
        if(!mobileInput.isEmpty() && Patterns.PHONE.matcher(mobileInput).matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
