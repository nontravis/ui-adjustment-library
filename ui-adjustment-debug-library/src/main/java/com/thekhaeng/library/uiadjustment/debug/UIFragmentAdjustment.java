package com.thekhaeng.library.uiadjustment.debug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.thekhaeng.library.uiadjustment.core.UIAdjustmentInterface;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.BaseAdjustItem;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public abstract class UIFragmentAdjustment<A extends Fragment> implements UIAdjustmentInterface{

    private final WeakReference<A> weakFragment;
    private final WeakReference<View> weakButton;
    private final UIAdjustmentDelegate uiAdjustmentDelegate;

    public UIFragmentAdjustment( A fragment, View button ){
        button.setVisibility( View.VISIBLE );
        weakFragment = new WeakReference<>( fragment );
        weakButton = new WeakReference<>( button );
        uiAdjustmentDelegate = new UIAdjustmentDelegate( fragment.getContext(), this );
        setupButton();
    }

    private void setupButton(){
        getButton().setOnClickListener( setOnButtonClickListener() );
    }


    public A getFragment(){
        return weakFragment.get();
    }

    public View getButton(){
        return weakButton.get();
    }

    @Override
    public UIAdjustmentInterface setTitle( String title ){
        uiAdjustmentDelegate.setTitle( title );
        return this;
    }

    @Override
    public UIAdjustmentInterface setDelayMillisTime( long delay ){
        uiAdjustmentDelegate.setDelay( delay );
        return this;
    }


    @Override
    public UIAdjustmentInterface setUseLocalStorage( boolean useLocalStorage, boolean bindDataImmediately ){
        uiAdjustmentDelegate.setUseLocalStorage( useLocalStorage );
        uiAdjustmentDelegate.setBindDataImmediately( bindDataImmediately );
        if( bindDataImmediately ){
            uiAdjustmentDelegate.bindData(
                    uiAdjustmentDelegate.getItemList( createAdjustItemList() ) );
        }
        return this;
    }

    @Override
    public UIAdjustmentInterface showKeepActivityGlobalSetting( TextView textView ){
        showKeepActivityGlobalSetting( textView, Color.RED );
        return this;
    }

    @SuppressLint( "SetTextI18n" )
    @Override
    public UIAdjustmentInterface showKeepActivityGlobalSetting( TextView textView, @ColorInt int textColor ){
        if( isKeepActivitiesOptionEnabled( getFragment().getContext() ) ){
            textView.setVisibility( View.GONE );
        }else{
            textView.setTextColor( textColor );
            textView.setText( "Don't keep activity mode." );
        }
        return this;
    }


    @Override
    public void onBoolean( @IdRes int id, boolean value ){

    }

    @Override
    public void onColor( @IdRes int id, @ColorInt int value ){

    }


    @Override
    public void onInteger( @IdRes int id, int value ){

    }

    @Override
    public void onRangeFloat( @IdRes int id, float value ){

    }

    @Override
    public void onString( @IdRes int id, String value ){

    }


    @NonNull
    private View.OnClickListener setOnButtonClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                List<BaseAdjustItem> itemList = createAdjustItemList();
                UIAdjustBottomSheet bottomSheet = UIAdjustBottomSheet.create(
                        uiAdjustmentDelegate.getTitle(),
                        uiAdjustmentDelegate.copyItemList( itemList ),
                        uiAdjustmentDelegate.getItemList( itemList ) );
                bottomSheet.setOnDismissBottomSheetListener( dismissListener );
                bottomSheet.setDelayTime( uiAdjustmentDelegate.getDelay() );
                bottomSheet.show( getFragment().getChildFragmentManager(), getFragment().getClass().getSimpleName() );
            }

        };
    }

    private UIAdjustBottomSheet.OnDismissBottomSheetListener dismissListener = new UIAdjustBottomSheet.OnDismissBottomSheetListener(){

        @Override
        public void onDone( List<BaseAdjustItem> itemList ){
            uiAdjustmentDelegate.bindData( itemList );
        }

        @Override
        public void onCancel(){

        }
    };


    @NonNull
    protected abstract List<BaseAdjustItem> createAdjustItemList();


    private boolean isKeepActivitiesOptionEnabled( Context context ){
        int result = Settings.Global.getInt( context.getContentResolver(), Settings.Global.ALWAYS_FINISH_ACTIVITIES, 0 );
        return result == 0;
    }
}
