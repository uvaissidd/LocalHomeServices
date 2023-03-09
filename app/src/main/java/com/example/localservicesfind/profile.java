package com.example.localservicesfind;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {
    // Variables.....
    TextView cpname, cpemail, cpmobile;
    Button showworker;
    show_worker worker;

    // Using SharedPreferences Library For Displaying Details In Profile Section.......
    // Here We Want To Display 3 Values (Name, Email, mobile number) In Profile Section
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "cname";
    private static final String KEY_EMAIL = "cemail";
    private static final String KEY_MOBILE = "cmobile";



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Storing EditText And Button Values By Id In A Variable.......
        cpname = findViewById(R.id.cpname);
        cpemail = findViewById(R.id.cpemail);
        cpmobile = findViewById(R.id.cpmobile);
        showworker = findViewById(R.id.showWorkers);

        // Displaying User Details in Profile Session If Fields Are Not Null...
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String mobile = sharedPreferences.getString(KEY_MOBILE, null);
        if (name != null || email != null || mobile != null)
        {
            cpname.setText(""+name);
            cpemail.setText(""+email);
            cpmobile.setText(""+mobile);
        }
        showworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,show_worker.class);
                startActivity(intent);
//                worker.showWorkerData();
            }
        });
    }
}
