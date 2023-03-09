package com.example.localservicesfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class customer_form extends AppCompatActivity {

    // Variables.....
    EditText cname, cuname, cpassword, crepassword, cmobile, cemail;
    Button csignup;

    cdbs ctm;


    // Using SharedPreferences Library For Displaying Details In Profile Section.......
    // Here We Want To Display 3 Values (Name, Email, mobile number) In Profile Section
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "cname";
    private static final String KEY_EMAIL = "cemail";
    private static final String KEY_MOBILE = "cmobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);
        // Storing EditText And Button Values By Id In A Variable.......
        cname = (EditText) findViewById(R.id.cname);
        cuname = (EditText) findViewById(R.id.cuname);
        cpassword = (EditText) findViewById(R.id.cpassword);
        crepassword = (EditText) findViewById(R.id.crepassword);
        cmobile = (EditText) findViewById(R.id.cmobile);
        cemail = (EditText) findViewById(R.id.cmail);
        csignup = (Button) findViewById(R.id.csignup);




        // Checking The Condition For displaying In Profile.....
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        if(name != null)
        {
//            Intent intent = new Intent(customer.this, profile_customer.class);
//            startActivity(intent);
        }


        // Creating Object
        ctm = new cdbs(this);
        SQLiteDatabase cdb = ctm.getReadableDatabase();

        // Using OnclickListener (It Will Perform When A USer Click On SignUp Button).....
        csignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //When User Fill All Details In SignUp Form And Click On SignUp Button, SharedPreferences Send Details(Which we Want From SignUp Form) In our Profile
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME,cname.getText().toString());
                editor.putString(KEY_EMAIL,cemail.getText().toString());
                editor.putString(KEY_MOBILE,cmobile.getText().toString());
                editor.apply();
//                Intent intent = new Intent(customer.this, profile_customer.class);
//                startActivity(intent);
                Toast.makeText(customer_form.this,"Data Insert in Profile", Toast.LENGTH_SHORT).show();


                // Method For Checking That Email Is Valid Or Not(Must Contain "@" And ".")....
                // This Method Define In cdbs.java class...
                ctm.validateEmailAddress(cemail);


                // Storing Values in a String Variable Which is use to check condition below....s
                String name = cname.getText().toString();
                String user = cuname.getText().toString();
                String pass = cpassword.getText().toString();
                String repassword = crepassword.getText().toString();
                String mobile = cmobile.getText().toString();
                String email = cemail.getText().toString();


                // Checking SignUp Details...
                // Checking Condition For ALL (All Fields Are Require, Password And Confirm Password, Username, Email, Mobile ...)
                //All The Methods Define in cdbs.java Class...
                if (name.equals("") || user.equals("") || pass.equals("") || repassword.equals("") || mobile.equals("") || email.equals(""))
                {
                    Toast.makeText(customer_form.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (pass.equals(repassword))
                    {
                        Boolean i = ctm.ccheckusername(user);
                        if (i == false)
                        {
                            Boolean m = ctm.ccheckmobile(mobile);
                            if (m == false)
                            {
                                Boolean e = ctm.ccheckemail(email);
                                if (e == false)
                                {
                                    Boolean ce = ctm.validateEmailAddress(cemail);
                                    if (ce == true )
                                    {
                                        Boolean cm = ctm.cvalidateMobileNum(cmobile);
                                        if (cm == true ) {
                                            Boolean regResult = ctm.cinsertdata(name, user, pass, repassword, mobile, email);
                                            if (regResult == true) {
                                                Toast.makeText(customer_form.this, "Successfully Registered ", Toast.LENGTH_SHORT).show();
                                                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent1);
                                            } else {
                                                Toast.makeText(customer_form.this, "Registration Failed ", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else
                                        {
                                            Toast.makeText(customer_form.this, "Invalid Mobile Number !  ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(customer_form.this, "Invalid Email Address!  ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(customer_form.this, "Email Already used ", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(customer_form.this, "Mobile Already used ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(customer_form.this, "User Already Exixt ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(customer_form.this, "Confirm Password Not Match ", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }

}