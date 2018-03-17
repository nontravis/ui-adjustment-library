package com.thekhaeng.library.uiadjustment.debug;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.thekhaeng.library.uiadjustment.core.UIAdjustmentLocalStorage;

/**
 * Created by The Khaeng on 16 Feb 2018 :)
 */

public class DefaultLocalStorage implements UIAdjustmentLocalStorage{

    private static DefaultLocalStorage instance;
    private Context context;

    public static DefaultLocalStorage getInstance( Context context ){
        if( instance == null ){
            instance = new DefaultLocalStorage( context );
        }
        return instance;
    }

    private DefaultLocalStorage( Context context ){
        this.context = context;
    }

    @Override
    public void save( String key, Object object ){
        if( object instanceof Integer ){
            persistInt( context, key, (Integer) object, true );
        }else if( object instanceof String ){
            persistString( context, key, (String) object, true );
        }else if( object instanceof Float ){
            persistFloat( context, key, (Float) object, true );
        }else if( object instanceof Boolean ){
            persistBoolean( context, key, (Boolean) object, true );
        }else{
            persistString( context, key, convertObjectToString( object ), true );
        }
    }

    @Override
    @Nullable
    public <T> Object load( String key, Class<T> clazz ){
        if( clazz == Integer.class ){
            return getPersistedInt( context, key, -1, true  );
        }else if( clazz == String.class){
            return getPersistedString( context, key, null, true  );
        }else if( clazz == Float.class){
            return getPersistedFloat( context, key, 0.0f, true  );
        }else if( clazz == Boolean.class){
            return getPersistedBoolean( context, key, false, true  );
        }else{
            return convertStringToObject( getPersistedString( context, key, null, true ), clazz );
        }
    }

    /* =========================== Private method =============================================== */
    private String convertObjectToString( Object object ){
        return getDefaultGson().toJson( object );
    }

    private <T> T convertStringToObject( String json, Class<T> clazz ){
        if( json != null ){
            return getDefaultGson().fromJson( json, clazz );
        }
        return null;
    }

    private Gson getDefaultGson(){
        return new Gson();
    }

    private  boolean persistInt(Context context, String key, int value, boolean shouldPersist) {
        if (shouldPersist) {
            if (value == getPersistedInt(context, key, ~value, shouldPersist)) {
                // It's already there, so the same as persisting
                return true;
            }

            SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            editor.putInt(key, value);
            editor.apply();
            return true;
        }
        return false;
    }

    private int getPersistedInt(Context context,String key, int defaultValue, boolean shouldPersist) {
        if (!shouldPersist) {
            return defaultValue;
        }
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    private boolean persistString( Context context, String key, String value, boolean shouldPersist ){
        if( shouldPersist ){
            // Shouldn't store null
            if( TextUtils.equals( value, getPersistedString( context, key, null, shouldPersist ) ) ){
                // It's already there, so the same as persisting
                return true;
            }

            SharedPreferences.Editor editor = getSharedPreferences( context ).edit();
            editor.putString( key, value );
            editor.apply();
            return true;
        }
        return false;
    }

    private String getPersistedString( Context context, String key, String defaultValue, boolean shouldPersist ){
        if( !shouldPersist ){
            return defaultValue;
        }
        return getSharedPreferences( context ).getString( key, defaultValue );
    }

    private  boolean persistFloat(Context context,String key, float value, boolean shouldPersist) {
        if (shouldPersist) {
            if (value == getPersistedFloat(context, key, Float.NaN, shouldPersist)) {
                // It's already there, so the same as persisting
                return true;
            }

            SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            editor.putFloat(key, value);
            editor.apply();
            return true;
        }
        return false;
    }

    private  float getPersistedFloat(Context context,String key, float defaultReturnValue, boolean shouldPersist) {
        if (!shouldPersist) {
            return defaultReturnValue;
        }
        return getSharedPreferences(context).getFloat(key, defaultReturnValue);
    }

    private boolean persistBoolean( Context context, String key, boolean value, boolean shouldPersist ){
        if( shouldPersist ){

            if( value == getPersistedBoolean( context, key, !value, shouldPersist ) ){
                // It's already there, so the same as persisting
                return true;
            }
            SharedPreferences.Editor editor = getSharedPreferences( context ).edit();
            editor.putBoolean( key, value );
            editor.apply();
            return true;
        }
        return false;
    }


    private boolean getPersistedBoolean( Context context, String key, boolean defaultValue, boolean shouldPersist ){
        if( !shouldPersist ){
            return defaultValue;
        }
        return getSharedPreferences( context ).getBoolean( key, defaultValue );
    }

    private SharedPreferences getSharedPreferences( Context context ){
        return PreferenceManager.getDefaultSharedPreferences( context );
    }

}
