package com.thekhaeng.library.uiadjust.adapter.holder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.thekhaeng.library.uiadjust.R;
import com.thekhaeng.library.uiadjust.adapter.item.BooleanAdjustment;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class BooleanAdjustHolder
        extends BaseAdjustViewHolder<BooleanAdjustment>{
    private AppCompatTextView tvTitle;
    private Switch swBoolean;

    public BooleanAdjustHolder( ViewGroup parent ){
        super( parent, R.layout.library_holder_adjust_boolean );
    }

    @Override
    public void onBindView( View view ){
        tvTitle = view.findViewById( R.id.library_holder_tv_title );
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
        tvTitle.setText( item.getTitle() );
        swBoolean.setChecked( item.getValue() );
    }
}
