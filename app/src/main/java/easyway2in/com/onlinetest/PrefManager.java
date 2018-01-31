package easyway2in.com.onlinetest;

/**
 * Created by ASHISH on 12/29/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Class for Shared Preference
 */
public class PrefManager {
    // Shared Preferences
    SharedPreferences pref;
    Context context;
    public static final String LOGIN_DETAILS = "LoginDetails";
    // public static final String MyPREFERENCES = LOGIN_DETAILS;
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_Notification = "IsNotifaction";
    public static final String Password = "Password";
    public static final String Username = "usernameKey";
    public static final String notifycount ="notifyyy";
    // Shared pref mode
    int PRIVATE_MODE = 0;


    public PrefManager(Context context) {
        this.context = context;

        pref = context.getSharedPreferences(LOGIN_DETAILS, PRIVATE_MODE);

    }



    public void saveLoginDetails(String username, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN_DETAILS, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(Username, username);
        editor.putString(Password, password);
        editor.commit();
    }

    public String getUsername() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN_DETAILS,PRIVATE_MODE);
        return sharedPreferences.getString(Username, pref.getString(Username, null));
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void savenotificationcount(int count){

     //   Toast.makeText(context, "Push notification: " + count, Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN_DETAILS, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        count = count + getNotification();
              editor.putInt(notifycount, count) ;
        editor.putBoolean(IS_Notification, true);
        editor.commit();
    }
    public int getNotification(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN_DETAILS,PRIVATE_MODE);
        return sharedPreferences.getInt(notifycount, pref.getInt(notifycount, 0) );
    }

    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        }

    }
    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN_DETAILS,PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
    public boolean isNotication(){
        return pref.getBoolean(IS_Notification, false);
    }


}
