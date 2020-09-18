package com.example.training.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class SessionManager {
    private SharedPreferences prefs;

    public SessionManager(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setSession(String token) throws JSONException {
        JWT jwt = new JWT(token);
        Map<String, Claim> getClaims = jwt.getClaims();
//        String name = getClaims.get("iss").asString();
//        Log.d("name", name);

        prefs.edit().putString("userName", getClaims.get("name").asString()).apply();
        prefs.edit().putString("agencyId", getClaims.get("agencyId").asString()).apply();
        prefs.edit().putString("userId", getClaims.get("userId").asString()).apply();
        prefs.edit().putString("userEmail", getClaims.get("email").asString()).apply();
//        JSONObject json = new JSONObject(name);
//        Log.d("json", json.getString("userId"));
//        prefs.edit().putString("userName", json.getString("userName")).apply();
//        prefs.edit().putString("agencyId", json.getString("agencyId")).apply();
//        prefs.edit().putString("userId", json.getString("userId")).apply();
//        prefs.edit().putString("userEmail", json.getString("userEmail")).apply();
    }
    public String getByKey(String key) {
        String token = prefs.getString(key, "");
        return token;
    }

    public String getAgencyId() {
        String agencyId = prefs.getString("agencyId", "");
        return agencyId;
    }
    public String getUserId() {
        String agencyId = prefs.getString("userId", "");
        return agencyId;
    }
    public void deleteAllSharedPrefs(){
        prefs.edit().clear().commit();
    }
}
