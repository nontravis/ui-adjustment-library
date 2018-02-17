package com.thekhaeng.library.uiadjustlibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.view.View;

import com.thekhaeng.library.uiadjust.UIActivityAdjustment;
import com.thekhaeng.library.uiadjust.adapter.item.BaseAdjustItem;
import com.thekhaeng.library.uiadjust.adapter.item.BooleanAdjustment;
import com.thekhaeng.library.uiadjust.adapter.item.ColorAdjustment;
import com.thekhaeng.library.uiadjust.adapter.item.IntegerAdjustment;
import com.thekhaeng.library.uiadjust.adapter.item.RangeFloatAdjustment;
import com.thekhaeng.library.uiadjust.adapter.item.StringAdjustment;
import com.thekhaeng.library.uiadjust.adapter.model.AdjustColor;
import com.thekhaeng.library.uiadjust.adapter.model.AdjustInteger;
import com.thekhaeng.library.uiadjust.adapter.model.AdjustRangeFloat;
import com.thekhaeng.library.uiadjust.adapter.model.AdjustString;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public class UIAdjustMainActivity extends UIActivityAdjustment<MainActivity>{


    private static final int THEME_ID = 100;

    public static UIAdjustMainActivity create( MainActivity activity, View button ){
        return new UIAdjustMainActivity( activity, button );
    }

    private UIAdjustMainActivity( MainActivity activity, View button ){
        super( activity, button );
    }

    @NonNull
    @Override
    public List<BaseAdjustItem> createAdjustItemList(){
        List<BaseAdjustItem> itemList = new ArrayList<>();
        Map<String, AdjustColor> mapColor = new LinkedHashMap<>();
        mapColor.put( "1", new AdjustColor( "#F44336" ) );
        mapColor.put( "2", new AdjustColor( "#E91E63", true ) );
        mapColor.put( "3", new AdjustColor( "#9C27B0" ) );
        mapColor.put( "4", new AdjustColor( "#673AB7" ) );
        mapColor.put( "5", new AdjustColor( "#3F51B5" ) );
        mapColor.put( "6", new AdjustColor( "#2196F3" ) );
        mapColor.put( "7", new AdjustColor( "#03A9F4" ) );
        mapColor.put( "8", new AdjustColor( "#00BCD4" ) );
        mapColor.put( "9", new AdjustColor( "#009688" ) );
        mapColor.put( "10", new AdjustColor( "#4CAF50" ) );

        Map<String, AdjustInteger> mapInteger = new LinkedHashMap<>();
        mapInteger.put( "Theme 1", new AdjustInteger( MainActivity.THEME_1, true ) );
        mapInteger.put( "Theme 2", new AdjustInteger( MainActivity.THEME_2 ) );
        mapInteger.put( "Theme 3", new AdjustInteger( MainActivity.THEME_3 ) );

        Map<String, AdjustString> mapString = new LinkedHashMap<>();
        mapString.put( "Message 1", new AdjustString( getActivity().getString( R.string.message_1 ), true ) );
        mapString.put( "Message 2", new AdjustString( getActivity().getString( R.string.message_2 ) ) );
        mapString.put( "Message 3", new AdjustString( getActivity().getString( R.string.message_3 ) ) );


        itemList.add( ColorAdjustment.create( R.id.tv_color, "Color View", mapColor ) );
        itemList.add( BooleanAdjustment.create( R.id.tv_show, "Show View", true ) );
        itemList.add( IntegerAdjustment.create( THEME_ID, "Choose Theme", mapInteger ) );
        itemList.add( RangeFloatAdjustment.create( R.id.tv_size, "Text Size", new AdjustRangeFloat( 12, 42, 1, 16 ) ) );
        itemList.add( StringAdjustment.create( R.id.tv_message, "Message", mapString ) );
        return itemList;
    }


    @Override
    protected void onColor( Activity activity, int id, @ColorInt int color ){
        super.onColor( activity, id, color );
        activity.findViewById( id ).setBackgroundColor( color );
    }

    @SuppressLint( "SetTextI18n" )
    @Override
    protected void onBoolean( Activity activity, int id, boolean value ){
        super.onBoolean( activity, id, value );

        if( R.id.tv_show == id ){
            AppCompatTextView tvShow = activity.findViewById( id );
            if( value ){
                tvShow.setText( "True" );
                tvShow.setAlpha( 1.0f );
            }else{
                tvShow.setText( "False" );
                tvShow.setAlpha( 0.54f );
            }

        }
    }

    @Override
    protected void onInteger( Activity activity, int id, int value ){
        super.onInteger( activity, id, value );
        if( THEME_ID == id ){
            getActivity().restart( value );
        }
    }

    @Override
    protected void onRangeFloat( Activity activity, int id, float value ){
        super.onRangeFloat( activity, id, value );
        if( R.id.tv_size == id ){
            ( (AppCompatTextView) activity.findViewById( R.id.tv_size ) )
                    .setTextSize(
                            TypedValue.COMPLEX_UNIT_SP,
                            value );
        }
    }

    @Override
    protected void onString( Activity activity, int id, String value ){
        super.onString( activity, id, value );
        if( R.id.tv_message == id ){
            ( (AppCompatTextView) activity.findViewById( R.id.tv_message ) )
                    .setText( value );
        }
    }
}
