package com.thekhaeng.library.uiadjustment.core;


import android.widget.TextView;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public interface UIAdjustmentInterface{

    UIAdjustmentInterface setTitle( String title );

    UIAdjustmentInterface setDelayMillisTime( long delay );

    UIAdjustmentInterface setUseLocalStorage( boolean useLocalStorage, boolean bindDataImmediately );

    UIAdjustmentInterface showKeepActivityGlobalSetting( TextView textView );

    UIAdjustmentInterface showKeepActivityGlobalSetting( TextView textView, int textColor );

    void onBoolean( int id, boolean value );

    void onColor( int id, int value );

    void onInteger( int id, int value );

    void onRangeFloat( int id, float value );

    void onString( int id, String value );


}
