package com.thekhaeng.library.uiadjustment.debug.adapter.holder;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.thekhaeng.library.uiadjustment.debug.R;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.RangeFloatAdjustment;
import com.thekhaeng.library.uiadjustment.debug.widget.FloatSeekBar;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class RangeFloatAdjustHolder
        extends BaseAdjustViewHolder<RangeFloatAdjustment>{

    private AppCompatTextView tvTitle;
    private AppCompatTextView tvCommon;
    private AppCompatTextView tvCurrentValue;
    private FloatSeekBar seekbar;

    public RangeFloatAdjustHolder( ViewGroup parent ){
        super( parent, R.layout.library_holder_adjust_range_float );
    }

    @Override
    public void onBindView( View view ){
        tvCommon = view.findViewById( R.id.library_holder_tv_common );
        tvTitle = view.findViewById( R.id.library_holder_tv_title );
        tvCurrentValue = view.findViewById( R.id.library_holder_tv_current_value );
        seekbar = view.findViewById( R.id.library_holder_seekbar );
    }

    @Override
    public void onBind( RangeFloatAdjustment item ){
        super.onBind( item );
        if( item.isCommon() ){
            tvCommon.setVisibility( View.VISIBLE );
        }else{
            tvCommon.setVisibility( View.GONE );
        }

        tvTitle.setText( item.getTitle() );
        seekbar.setBoundaries( item.getValue().getMinValue(), item.getValue().getMaxValue(), item.getValue().getIncrement() );
        seekbar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener(){
            @SuppressLint( "DefaultLocale" )
            @Override
            public void onProgressChanged( SeekBar seekBar, int progress, boolean fromUser ){
                tvCurrentValue.setText( String.format( "%.2f", seekbar.getValue() ) );
                getItem().getValue().setCurrentValue( seekbar.getValue() );
            }

            @Override
            public void onStartTrackingTouch( SeekBar seekBar ){
            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar ){
            }
        } );
        seekbar.setValue( item.getValue().getCurrentValue() );
    }
}
