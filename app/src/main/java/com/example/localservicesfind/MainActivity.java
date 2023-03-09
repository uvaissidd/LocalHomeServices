package com.example.localservicesfind;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Variables
    TextView textView;
    EditText cuname, cpassword, crepassword;
    Button cloginbtn;
    cdbs CDB;
    wdbs WDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Storing EditText And Button Values By Id In A Variable.......
        cuname= findViewById(R.id.cloguname);
        cpassword= findViewById(R.id.clogpassword);
        cloginbtn= findViewById(R.id.cloginbtn);
        textView = findViewById(R.id.signup);


        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, customer_form.class);
                startActivity(intent);
            }
        });

        // Creating Object
        CDB = new cdbs(this);

        // Using OnclickListener (It Will Perform When A USer Click On SignIn Button).....
        cloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Storing Values in a String Variable Which is use to check condition below....
                String user = cuname.getText().toString();
                String pass = cpassword.getText().toString();

                //Checking That Username And Password are filled or not...
                //Checking Username And Password with Database....Using ccheckusernamepassword Method Which is Define in cdbs.java class
                if(user.equals("") || pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please Enter All Fields..", Toast.LENGTH_SHORT ).show();
                else {
                    Boolean checkuserpass = CDB.ccheckusernamepassword(user, pass);
                    if (checkuserpass == true)
                    {
                        Toast.makeText(MainActivity.this, "Successfully SignIN ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), com.example.localservicesfind.customer_home.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "SignIN Failed ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    // All The Function is Use For Make Session Between Login and LogOut
    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }
    private void checkSession()
    {
        CsessionManagement csessionManagement = new CsessionManagement(MainActivity.this);
        int userID = csessionManagement.getSession();

        if(userID != -1)
        {
            moveToMainActivity();
        }
    }
    private void moveToMainActivity()
    {
        Intent intent = new Intent(MainActivity.this, com.example.localservicesfind.customer_home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}