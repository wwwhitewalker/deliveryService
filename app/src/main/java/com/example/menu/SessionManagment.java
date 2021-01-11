package com.example.menu;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.menu.data.model.LoggedInUser;

public class SessionManagment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    public SessionManagment(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(LoggedInUser user){
        int id = user.getUserId();
        String userName = user.getUserName();
        String userEmail = user.getUserEmail();
        editor.putInt("userId",id).commit();
        editor.putInt(SESSION_KEY,id).commit();
        editor.putString("userName",userName).commit();
        editor.putString("userEmail",userEmail).commit();
    }

    public int getSESSION(){
      return sharedPreferences.getInt("userId",-1);
    }

    public int getUserId(){
        return sharedPreferences.getInt("userId",-1);
    }

    public String getUserName(){
        return sharedPreferences.getString("userName","Demo User");
    }

    public String getUserEmail(){
        return sharedPreferences.getString("userEmail","demo@user.com");
    }

    public void destroySession(){
        editor.putInt(SESSION_KEY,-1).commit();
        editor.putInt("userId",-1).commit();
        editor.putString("userName","").commit();
        editor.putString("userEmail","").commit();

    }
}
