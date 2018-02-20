package com.thekhaeng.library.uiadjustment.debug.adapter.holder;

import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.thekhaeng.library.uiadjustment.debug.R;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.StringAdjustment;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class StringAdjustHolder
        extends BaseAdjustViewHolder<StringAdjustment>{

    private AppCompatTextView tvCommon;
    private AppCompatTextView tvTittle;
    private AppCompatSpinner spinner;

    public StringAdjustHolder( ViewGroup parent){
        super(parent, R.layout.library_holder_adjust_string );
    }

    @Override
    public void onBindView( View view ){
        tvCommon = view.findViewById( R.id.library_holder_tv_common );
        tvTittle = view.findViewById( R.id.library_holder_tv_title );
        spinner = view.findViewById( R.id.library_holder_spinner );
    }

    @Override
    public void onBind( final StringAdjustment item ){
        super.onBind( item );
        if( item.isCommon() ){
            tvCommon.setVisibility( View.VISIBLE );
        }else{
            tvCommon.setVisibility( View.GONE );
        }
        tvTittle.setText( item.getTitle() );

        final String[] spinnerArray = new String[item.getMapString().size()];
        int index = 0;
        for( String key : item.getMapString().keySet() ){
            spinnerArray[index] = key;
            index++;
        }

        spinner.setAdapter( new ArrayAdapter<>( getContext(), R.layout.library_spinner_item_list_view, spinnerArray ) );
        spinner.setSelection( item.getCurrentIndex() );
        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int pos, long id ){
                item.unSelectAll();
                item.getMapString().get( spinnerArray[pos] ).setSelected( true );
            }

            @Override
            public void onNothingSelected( AdapterView<?> parent ){
            }
        } );
    }
}
