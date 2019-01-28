package com.anyacht.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by vignesh on 11-07-2018.
 */
public class SaveSharedPreference {
    static final String PREF_USERID= "id";
    static final String PREF_USERNAME= "name";
    static final String PREF_EMAILID= "email_id";
    static final String PREF_PHONE= "phone";
    static final String PREF_PASSWORD= "password";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setID(Context ctx, String Id)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USERID, Id);
        editor.commit();
    }

    public static String getID(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USERID, "");
    }



    public static void setUsername(Context ctx, String UserName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USERNAME, UserName);
        editor.commit();
    }

    public static String getUsername(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USERNAME, "");
    }

    public static void setEMailID(Context ctx, String EMailId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_EMAILID, EMailId);
        editor.commit();
    }

    public static String getEMailID(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_EMAILID, "");
    }

    public static void setPhone(Context ctx, String PhoNe)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PHONE, PhoNe);
        editor.commit();
    }

    public static String getPhone(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_PHONE, "");
    }

    public static void setPass(Context ctx, String PAss)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PASSWORD, PAss);
        editor.commit();
    }

    public static String getPass(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_PASSWORD, "");
    }





    public  static void removePrefs(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USERID, "");
        editor.putString(PREF_USERNAME, "");
        editor.putString(PREF_EMAILID, "");
        editor.putString(PREF_PHONE, "");
        editor.clear();
        editor.commit();
    }

}
