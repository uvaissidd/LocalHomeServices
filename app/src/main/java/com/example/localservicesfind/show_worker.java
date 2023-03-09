package com.example.localservicesfind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class show_worker extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseRecyclerAdapter databaseRecyclerAdapter;
    ArrayList<ModelClass> modelClassArrayList;
    customer_home address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_worker);
        recyclerView = findViewById(R.id.dataRV);
        modelClassArrayList = new ArrayList<>();
        showWorkerData(recyclerView);

    }

    // Method for showing data
    public void showWorkerData(View view)
    {
        try
        {
            wdbs WDB = new wdbs(this);
            SQLiteDatabase sqLiteDatabase = WDB.getReadableDatabase();
            if(sqLiteDatabase != null)
            {
                Cursor cursor = sqLiteDatabase.rawQuery("select * from worker where category = ?", new String[] {"cook"});
                if(cursor.getCount() == 0)
                {
                    Toast.makeText(this, "No Data Is Returned", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    while (cursor.moveToNext() )
                    {
                        modelClassArrayList.add(new ModelClass(cursor.getString(0),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getInt(1),
                                cursor.getInt(4),
                                cursor.getString(5)
                                ));
                    }
                    databaseRecyclerAdapter = new DatabaseRecyclerAdapter(modelClassArrayList);
                    recyclerView.hasFixedSize();

                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(databaseRecyclerAdapter);
                }
            }
            else
            {
                Toast.makeText(this, "Database Is Null", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Show Values From Database", Toast.LENGTH_SHORT).show();
        }
    }


}