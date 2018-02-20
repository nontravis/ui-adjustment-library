package com.thekhaeng.library.uiadjustment.debug;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.thekhaeng.library.uiadjustment.debug.adapter.AdjustAdapter;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.BaseAdjustItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class UIAdjustBottomSheet
        extends BottomSheetDialogFragment{

    public static final String KEY_DEFAULT_ITEM_LIST = "key_default_item_list";
    public static final String KEY_LOADED_ITEM_LIST = "key_loaded_item_list";

    private static final int CANCEL = 1;
    private static final int DONE = 2;

    private AppCompatImageView btnCancel;
    private AppCompatImageView btnDone;
    private AppCompatImageView btnReset;
    private AppCompatTextView tvTitle;
    private RecyclerView rvAdjust;
    private LinearLayoutManager layoutManager;
    private AdjustAdapter adapter;
    private OnDismissBottomSheetListener dismissListener;
    private int exitState = CANCEL;
    private long delay = 0L;

    interface OnDismissBottomSheetListener{
        void onDone( List<BaseAdjustItem> itemList );

        void onCancel();
    }

    public static UIAdjustBottomSheet create( List<BaseAdjustItem> defaultItemList,
                                              List<BaseAdjustItem> loadedItemList ){
        UIAdjustBottomSheet fragment = new UIAdjustBottomSheet();
        Bundle args = new Bundle();
        args.putParcelableArrayList( KEY_DEFAULT_ITEM_LIST, (ArrayList<? extends Parcelable>) defaultItemList );
        args.putParcelableArrayList( KEY_LOADED_ITEM_LIST, (ArrayList<? extends Parcelable>) loadedItemList );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );
        if( savedInstanceState != null ){
            cancel();
        }
    }

    @SuppressLint( "RestrictedApi" )
    @Override
    public void setupDialog( Dialog dialog, int style ){
        super.setupDialog( dialog, style );
        View contentView = View.inflate( getContext(), R.layout.library_bottom_sheet_ui_adjust, null );
        dialog.setContentView( contentView );

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ( (View) contentView.getParent() ).getLayoutParams();
        final CoordinatorLayout.Behavior behavior = params.getBehavior();

        if( behavior != null && behavior instanceof BottomSheetBehavior ){
            ( (BottomSheetBehavior) behavior ).setBottomSheetCallback( new BottomSheetBehavior.BottomSheetCallback(){
                @Override
                public void onStateChanged( @NonNull View bottomSheet, int newState ){
                    if( newState == BottomSheetBehavior.STATE_HIDDEN ){
                        dismiss();
                        ( (BottomSheetBehavior) behavior ).setState( BottomSheetBehavior.STATE_COLLAPSED );
                    }
                }

                @Override
                public void onSlide( @NonNull View bottomSheet, float slideOffset ){

                }
            } );
        }

        onBindView( contentView );
        setupView();
    }

    @Override
    public void onDismiss( DialogInterface dialog ){
        if( dismissListener != null ){
            if( exitState == CANCEL ){
                dismissListener.onCancel();
            }else if( exitState == DONE ){
                if( delay > 0L ){
                    delay( new Runnable(){
                        @Override
                        public void run(){
                            dismissListener.onDone( adapter.getItemList() );
                        }
                    }, delay );
                }else{
                    dismissListener.onDone( adapter.getItemList() );
                }
            }
        }
        super.onDismiss( dialog );
    }

    private void reset(){
        List<BaseAdjustItem> copyItemList = new ArrayList<>();
        for( BaseAdjustItem item : getDefaultItemList() ){
            copyItemList.add( item.copy() );
        }
        adapter.updateItemList( copyItemList );
    }


    private void cancel(){
        exitState = CANCEL;
        dismiss();
    }

    private void done(){
        exitState = DONE;
        dismiss();
    }

    private void onBindView( View view ){
        btnCancel = view.findViewById( R.id.library_btn_cancel );
        tvTitle = view.findViewById( R.id.library_tv_title );
        btnDone = view.findViewById( R.id.library_btn_done );
        btnReset = view.findViewById( R.id.library_btn_reset );
        rvAdjust = view.findViewById( R.id.library_rv_adjust );

        btnCancel.setOnClickListener( setOnClickListener() );
        btnDone.setOnClickListener( setOnClickListener() );
        btnReset.setOnClickListener( setOnClickListener() );
    }


    private void setupView(){
        layoutManager = new LinearLayoutManager( getContext() );
        adapter = new AdjustAdapter();
        rvAdjust.setLayoutManager( layoutManager );
        rvAdjust.setAdapter( adapter );
        adapter.setItemList( getLoadedItemList() );
    }


    public void setDelayTime( long delay ){
        this.delay = delay;
    }

    public void setOnDismissBottomSheetListener( OnDismissBottomSheetListener dismissListener ){
        this.dismissListener = dismissListener;
    }

    public List<BaseAdjustItem> getDefaultItemList(){
        return getItemList( KEY_DEFAULT_ITEM_LIST );
    }

    public List<BaseAdjustItem> getLoadedItemList(){
        return getItemList( KEY_LOADED_ITEM_LIST );
    }

    public List<BaseAdjustItem> getItemList( String key ){
        if( getArguments() != null ){
            List<BaseAdjustItem> itemList = getArguments().getParcelableArrayList( key );
            if( itemList != null ){
                return itemList;
            }else{
                return new ArrayList<>();
            }

        }else{
            return new ArrayList<>();
        }
    }

    private void delay( Runnable runnable, long delay ){
        final Handler handler = new Handler();
        handler.postDelayed( runnable, delay );
    }

    @NonNull
    private View.OnClickListener setOnClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( v == btnCancel ){
                    cancel();
                }else if( v == btnReset ){
                    reset();
                }else if( v == btnDone ){
                    done();
                }
            }
        };
    }


}
