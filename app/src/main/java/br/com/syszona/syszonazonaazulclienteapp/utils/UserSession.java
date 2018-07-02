package br.com.syszona.syszonazonaazulclienteapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    private static UserSession userSession;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor = null;

    public UserSession(Context context) {
        sharedPreferences = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static UserSession getInstance(Context context) {
        if (userSession != null) {
            return userSession;
        }
        userSession = new UserSession(context);
        return userSession;
    }

    public void setUserToken(String token) {
        editor.putString("token", token);
        editor.commit();
    }

    public String getUserToken() {
        return sharedPreferences.getString("token", "");
    }

    public void setUserKeepConnected(Boolean keepConected){
        editor.putBoolean("keepConnected", keepConected);
        editor.commit();
    }

    public Boolean getUserKeepConnected(){
        return sharedPreferences.getBoolean("keepConnected",false);
    }

    public Boolean isUserLoggedIn() {
        if(sharedPreferences.getString("token", "").length() > 0 && sharedPreferences.getBoolean("keepConnected",false)){
            return true;
        }
        return false;
    }

    public void clearSession() {
//        editor.clear().commit();
        editor.remove("token").commit();
        editor.remove("keepConnected").commit();
    }
}
