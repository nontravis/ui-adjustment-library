package com.thekhaeng.library.uiadjustment.debug.adapter.holder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.thekhaeng.library.uiadjustment.debug.R;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.BooleanAdjustment;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class BooleanAdjustHolder
        extends BaseAdjustViewHolder<BooleanAdjustment>{
    private AppCompatTextView tvTitle;
    private AppCompatTextView tvCommon;
    private Switch swBoolean;

    public BooleanAdjustHolder( ViewGroup parent ){
        super( parent, R.layout.library_holder_adjust_boolean );
    }

    @Override
    public void onBindView( View view ){
        tvTitle = view.findViewById( R.id.library_holder_tv_title );
        tvCommon = view.findViewById( R.id.library_holder_tv_common );
        swBoolean = view.findViewById( R.id.library_holder_switch );
        swBoolean.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ){
                getItem().setValue( isChecked );
            }
        } );
    }

    @Override
    public void onBind( BooleanAdjustment item ){
        super.onBind( item );
        if( item.isCommon() ){
            tvCommon.setVisibility( View.VISIBLE );
        }else{
            tvCommon.setVisibility( View.GONE );
        }
        tvTitle.setText( item.getTitle() );
        swBoolean.setChecked( item.getValue() );
    }
}
