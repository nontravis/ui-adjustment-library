package com.thekhaeng.library.uiadjust.adapter.holder;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.thekhaeng.library.uiadjust.R;
import com.thekhaeng.library.uiadjust.adapter.item.RangeFloatAdjustment;
import com.thekhaeng.library.uiadjust.widget.FloatSeekBar;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public class RangeFloatAdjustHolder
        extends BaseAdjustViewHolder<RangeFloatAdjustment>{

    private AppCompatTextView tvTitle;
    private AppCompatTextView tvCurrentValue;
    private FloatSeekBar seekbar;

    public RangeFloatAdjustHolder( ViewGroup parent ){
        super( parent, R.layout.library_holder_adjust_range_float );
    }

    @Override
    public void onBindView( View view ){
        tvTitle = view.findViewById( R.id.library_holder_tv_title );
        tvCurrentValue = view.findViewById( R.id.library_holder_tv_current_value );
        seekbar = view.findViewById( R.id.library_holder_seekbar );
    }

    @Override
    public void onBind( RangeFloatAdjustment item ){
        super.onBind( item );
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
