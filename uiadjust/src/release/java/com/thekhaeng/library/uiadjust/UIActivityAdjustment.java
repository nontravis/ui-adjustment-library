package com.thekhaeng.library.uiadjust;

import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public abstract class UIActivityAdjustment<A extends FragmentActivity> implements UIAdjustmentInterface{

    public UIActivityAdjustment( A activity, View button ){
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

}
