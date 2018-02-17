package com.thekhaeng.library.uiadjust;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by「 The Khaeng 」on 16 Feb 2018 :)
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
        persistString( context, key, convertObjectToString( object ), true );
    }

    @Override
    @Nullable
    public <T> T load( String key, Class<T> clazz ){
        return convertStringToObject( getPersistString( context, key, null, true ), clazz );
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


    private boolean persistString( Context context, String key, String value, boolean shouldPersist ){
        if( shouldPersist ){
            // Shouldn't store null
            if( TextUtils.equals( value, getPersistString( context, key, null, shouldPersist ) ) ){
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

    private String getPersistString( Context context, String key, String defaultValue, boolean shouldPersist ){
        if( !shouldPersist ){
            return defaultValue;
        }
        return getSharedPreferences( context ).getString( key, defaultValue );
    }

    private SharedPreferences getSharedPreferences( Context context ){
        return PreferenceManager.getDefaultSharedPreferences( context );
    }

}
