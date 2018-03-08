package com.thekhaeng.library.uiadjustment.release;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.thekhaeng.library.uiadjustment.core.UIAdjustmentInterface;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public abstract class UIFragmentAdjustment<A extends Fragment>
        implements UIAdjustmentInterface{


    public UIFragmentAdjustment( A fragment, View button ){
        button.setVisibility( View.GONE );
    }

    @Override
    public UIAdjustmentInterface setTitle( String title ){
        // do nothing
        return this;
    }


    @Override
    public UIAdjustmentInterface setDelayMillisTime( long delay ){
        return this;
    }

    @Override
    public UIAdjustmentInterface setUseLocalStorage( boolean useLocalStorage, boolean bindDataImmediately ){
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
