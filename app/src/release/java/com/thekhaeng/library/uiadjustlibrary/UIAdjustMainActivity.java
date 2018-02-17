package com.thekhaeng.library.uiadjustlibrary;

import android.view.View;

import com.thekhaeng.library.uiadjust.UIActivityAdjustment;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public class UIAdjustMainActivity extends UIActivityAdjustment<MainActivity>{


    public static UIAdjustMainActivity create( MainActivity activity, View button ){
        return new UIAdjustMainActivity( activity, button );
    }

    private UIAdjustMainActivity( MainActivity activity, View button ){
        super( activity, button );
    }

}
