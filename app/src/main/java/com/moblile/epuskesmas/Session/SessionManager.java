package com.moblile.epuskesmas.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.moblile.epuskesmas.Activity.DashboardActivity;
import com.moblile.epuskesmas.Activity.LoginActivity;
import com.moblile.epuskesmas.Activity.MainActivity;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;



    // Sharedpref file name
    private static final String PREF_NAME = "Mobile-Epuskesmas";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String s_idpasien = "idlogin";
    public static final String s_noktp = "noktp";
    public static final String s_idlogin = "idlogin";

    public SessionManager( Context _context) {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        this._context = _context;
        editor.apply();
    }

    public void createLoginSession(String idpasien, String idlogin, String noktp){

        // Storing name in pref

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(s_idpasien, idpasien);
        editor.putString(s_idlogin, idlogin);
        editor.putString(s_noktp, noktp);
        // commit changes
        editor.apply();
    }



    public void checkLogin(){
        // Check login status
        if(isLoggedIn() == true){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, DashboardActivity.class);
            // Closing all the Activities
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            _context.startActivity(i);
        }
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.remove(s_idpasien);
        editor.remove(s_noktp);
        editor.remove(s_idlogin);
        editor.remove(IS_LOGIN);
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    public void exit(){
        editor.remove(s_idpasien);
        editor.remove(s_noktp);
        editor.remove(s_idlogin);
        editor.remove(IS_LOGIN);
        editor.commit();


    }


    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


    public String getIdpasien() {
        return pref.getString(s_idpasien, null   ) ;
    }

    public String getNoktp() {
        return pref.getString(s_noktp, null   ) ;
    }

    public  String getIdlogin() {
        return pref.getString(s_idlogin, null   ) ;
    }
}
