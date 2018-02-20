package com.thekhaeng.library.uiadjustment.core;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public interface UIAdjustmentInterface{

    UIAdjustmentInterface setDelayMillisTime( long delay );

    UIAdjustmentInterface setUseLocalStorage( boolean useLocalStorage, boolean bindDataImmediately );

    void onBoolean( int id, boolean value );

    void onColor( int id, int value );

    void onInteger( int id, int value );

    void onRangeFloat( int id, float value );

    void onString( int id, String value );


}
