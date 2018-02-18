package com.thekhaeng.library.uiadjust;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.thekhaeng.library.uiadjust.adapter.AdjustAdapter;
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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public abstract class UIFragmentAdjustment<A extends Fragment> implements UIAdjustmentInterface{

    private final WeakReference<A> weakFragment;
    private final WeakReference<View> weakButton;
    private final UIAdjustmentLocalStorage storage;
    private boolean isUseLocalStorage = false;
    private long delay = 0L;
    private boolean bindDataImmediately = false;

    public UIFragmentAdjustment( A fragment, View button ){
        button.setVisibility( View.VISIBLE );
        weakFragment = new WeakReference<>( fragment );
        weakButton = new WeakReference<>( button );
        storage = DefaultLocalStorage.getInstance( fragment.getContext() );
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
    public UIAdjustmentInterface setDelayMillisTime( long delay ){
        this.delay = delay;
        return this;
    }

    @Override
    public UIAdjustmentInterface setUseLocalStorage( boolean useLocalStorage, boolean bindDataImmediately ){
        this.isUseLocalStorage = useLocalStorage;
        if( bindDataImmediately ){
            bindData( getItemList( createAdjustItemList() ) );
        }
        return this;
    }

    protected String getStorageKey( int id, int type ){
        return getClass().getSimpleName() + "_" + id + "_" + type;
    }

    protected void onBoolean( Fragment activity, @IdRes int id, boolean value ){

    }

    protected void onColor( Fragment activity, @IdRes int id, @ColorInt int value ){

    }


    protected void onInteger( Fragment activity, @IdRes int id, int value ){

    }

    protected void onRangeFloat( Fragment activity, @IdRes int id, float value ){

    }

    protected void onString( Fragment activity, @IdRes int id, String value ){

    }

    private List<BaseAdjustItem> copyItemList( List<BaseAdjustItem> itemList ){
        List<BaseAdjustItem> copyItemList = new ArrayList<>();
        for( BaseAdjustItem item : itemList ){
            copyItemList.add( item.copy() );
        }
        return copyItemList;
    }

    private List<BaseAdjustItem> getItemList( List<BaseAdjustItem> defaultItemList ){
        if( isUseLocalStorage ){
            for( BaseAdjustItem item : defaultItemList ){
                Object data = storage.load(
                        getStorageKey( item.getId(), item.getType() ),
                        item.getStorageClass() );
                if( data != null ){
                    item.selectValue( data );
                }
            }
        }
        return defaultItemList;

    }

    private Context getContext(){
        return getFragment().getContext();
    }

    private void bindData( List<BaseAdjustItem> itemList ){
        if( itemList != null ){
            for( BaseAdjustItem item : itemList ){
                int id = item.getId();
                if( item instanceof BooleanAdjustment ){
                    boolean value = ( (BooleanAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        storage.save( getStorageKey( id, AdjustAdapter.BOOLEAN_ITEM ), value );
                    }
                    onBoolean( getFragment(), id, value );
                }else if( item instanceof ColorAdjustment ){
                    AdjustColor color = ( (ColorAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        storage.save( getStorageKey( id, AdjustAdapter.COLOR_ITEM ), color );
                    }
                    if( color != null ){
                        UIFragmentAdjustment.this.onColor( getFragment(), id, color.getColor( getContext() ) );
                    }
                }else if( item instanceof IntegerAdjustment ){
                    AdjustInteger value = ( (IntegerAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        storage.save( getStorageKey( id, AdjustAdapter.INTEGER_ITEM ), value );
                    }
                    if( value != null ){
                        UIFragmentAdjustment.this.onInteger( getFragment(), id, value.getValue() );
                    }
                }else if( item instanceof RangeFloatAdjustment ){
                    AdjustRangeFloat value = ( (RangeFloatAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        storage.save( getStorageKey( id, AdjustAdapter.RANGE_FLOAT_ITEM ), value );
                    }
                    UIFragmentAdjustment.this.onRangeFloat( getFragment(), id, value.getCurrentValue() );
                }else if( item instanceof StringAdjustment ){
                    AdjustString value = ( (StringAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        storage.save( getStorageKey( id, AdjustAdapter.STRING_ITEM ), value );
                    }

                    if( value != null ){
                        UIFragmentAdjustment.this.onString( getFragment(), id, value.getValue() );
                    }
                }
            }
        }
    }


    @NonNull
    private View.OnClickListener setOnButtonClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                List<BaseAdjustItem> itemList = createAdjustItemList();
                UIAdjustBottomSheet bottomSheet = UIAdjustBottomSheet.create(
                        copyItemList( itemList ),
                        getItemList( itemList ) );
                bottomSheet.setOnDismissBottomSheetListener( dismissListener );
                bottomSheet.setDelayTime( delay );
                bottomSheet.show( getFragment().getChildFragmentManager(), getFragment().getClass().getSimpleName() );
            }

        };
    }

    private UIAdjustBottomSheet.OnDismissBottomSheetListener dismissListener = new UIAdjustBottomSheet.OnDismissBottomSheetListener(){

        @Override
        public void onDone( List<BaseAdjustItem> itemList ){
            bindData( itemList );
        }

        @Override
        public void onCancel(){

        }
    };


    @NonNull
    protected abstract List<BaseAdjustItem> createAdjustItemList();

}
