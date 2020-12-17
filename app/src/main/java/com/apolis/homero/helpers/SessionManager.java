package com.apolis.homero.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.apolis.homero.app.MyApplication;
import com.apolis.homero.data.models.User;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static SessionManager instance = null;

    private static final String FILE_NAME = "my_pref";
    private static final String KEY_USER_ID = "userID";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    // Made private so that no class can instantiate this class
    private SessionManager() {
        this.sharedPreferences =  MyApplication.Companion.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
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
        editor.putString(KEY_USER_ID, user.get_id());
        editor.putString(KEY_NAME, user.getName());     // will need this if creating a user profile
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public User getUser() {
        String id = sharedPreferences.getString(KEY_USER_ID, null);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        User user = new User(id, name, email);
        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
