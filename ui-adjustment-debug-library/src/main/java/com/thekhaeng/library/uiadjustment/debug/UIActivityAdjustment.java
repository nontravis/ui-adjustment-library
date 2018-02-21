package com.thekhaeng.library.uiadjustment.debug;

import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.thekhaeng.library.uiadjustment.core.UIAdjustmentInterface;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.BaseAdjustItem;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public abstract class UIActivityAdjustment<A extends FragmentActivity>
        implements UIAdjustmentInterface{

    private final WeakReference<A> weakActivity;
    private final WeakReference<View> weakButton;
    private final UIAdjustmentDelegate uiAdjustmentDelegate;

    public UIActivityAdjustment( A activity, View button ){
        button.setVisibility( View.VISIBLE );
        weakActivity = new WeakReference<>( activity );
        weakButton = new WeakReference<>( button );
        uiAdjustmentDelegate = new UIAdjustmentDelegate( activity, this );
        setupButton();
    }

    private void setupButton(){
        getButton().setOnClickListener( setOnButtonClickListener() );
    }


    public A getActivity(){
        return weakActivity.get();
    }

    public View getButton(){
        return weakButton.get();
    }

    @Override
    public UIAdjustmentInterface setTitle(String title){
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
                bottomSheet.show( getActivity().getSupportFragmentManager(), getActivity().getClass().getSimpleName() );
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

}
