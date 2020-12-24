package com.apolis.homero.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.apolis.homero.app.MyApplication;
import com.apolis.homero.data.models.User;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static SessionManager instance = null;



    // Made private so that no class can instantiate this class
    private SessionManager() {
        this.sharedPreferences =  MyApplication.Companion.getInstance().getSharedPreferences(Constants.FILE_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    // To track only one instance of this class is created
    public static SessionManager getInstance() {
        if(instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void saveUserInfo(User user) {
        editor.putString(Constants.KEY_USER_ID, user.get_id());
        editor.putString(Constants.KEY_NAME, user.getName());     // will need this if creating a user profile
        editor.putString(Constants.KEY_EMAIL, user.getEmail());
        editor.putString(Constants.KEY_PASSWORD, user.getPassword());
        editor.putString(Constants.KEY_TYPE, user.getType());
        editor.putBoolean(Constants.KEY_IS_LOGGED_IN, true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(Constants.KEY_IS_LOGGED_IN, false);
    }

    public User getUser() {
        String id = sharedPreferences.getString(Constants.KEY_USER_ID, null);
        String name = sharedPreferences.getString(Constants.KEY_NAME, null);
        String email = sharedPreferences.getString(Constants.KEY_EMAIL, null);
        User user = new User(id, name, email);
        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
