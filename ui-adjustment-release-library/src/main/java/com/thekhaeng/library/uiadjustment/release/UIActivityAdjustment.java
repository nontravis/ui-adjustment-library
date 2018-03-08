package com.thekhaeng.library.uiadjustment.release;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.thekhaeng.library.uiadjustment.core.UIAdjustmentInterface;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public abstract class UIActivityAdjustment<A extends FragmentActivity>
        implements UIAdjustmentInterface{

    public UIActivityAdjustment( A activity, View button ){
        button.setVisibility( View.GONE );
    }


    @Override
    public UIAdjustmentInterface setTitle( String title ){
        // do nothing
        return this;
    }

    @Override
    public UIActivityAdjustment setDelayMillisTime( long delay ){
        // do nothing
        return this;
    }

    @Override
    public UIActivityAdjustment setUseLocalStorage( boolean useLocalStorage, boolean bindDataImmediately ){
        // do nothing
        return this;
    }

    @Override
    public UIAdjustmentInterface showKeepActivityGlobalSetting( TextView textView ){
        showKeepActivityGlobalSetting( textView, Color.TRANSPARENT );
        return this;
    }

    @SuppressLint( "SetTextI18n" )
    @Override
    public UIAdjustmentInterface showKeepActivityGlobalSetting( TextView textView, @ColorInt int textColor ){
        textView.setVisibility( View.GONE );
        return this;
    }

    @Override
    public void onBoolean( int id, boolean value ){
        // do nothing
    }

    @Override
    public void onColor( int id, int value ){
        // do nothing
    }

    @Override
    public void onInteger( int id, int value ){
        // do nothing
    }

    @Override
    public void onRangeFloat( int id, float value ){
        // do nothing
    }

    @Override
    public void onString( int id, String value ){
        // do nothing
    }


}
