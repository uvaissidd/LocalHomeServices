package com.example.localservicesfind;

import android.content.Context;
import android.content.SharedPreferences;



// (Customer) Creating A Class For Manage Session Between Login And LogOut.....Using SharedPreferences Library....

public class CsessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY ="session_user";

    public CsessionManagement(Context context)
    {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences. edit();
    }

    // Method for save session when user logged in....
    public void saveSession(C_user user)
    {
        int id =  user.getId();

        editor.putInt(SESSION_KEY, id).commit();
    }

    public int getSession()
    {
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }

    public void removeSession()
    {
        editor.putInt(SESSION_KEY, -1).commit();
    }

}