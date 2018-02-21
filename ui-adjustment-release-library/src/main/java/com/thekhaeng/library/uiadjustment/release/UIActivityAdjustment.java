package com.thekhaeng.library.uiadjustment.release;

import android.support.v4.app.FragmentActivity;
import android.view.View;

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
