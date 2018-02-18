package com.thekhaeng.library.uiadjust.adapter.holder;

import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.thekhaeng.library.uiadjust.R;
import com.thekhaeng.library.uiadjust.adapter.item.IntegerAdjustment;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class IntegerAdjustHolder
        extends BaseAdjustViewHolder<IntegerAdjustment>{

    private AppCompatTextView tvTittle;
    private AppCompatSpinner spinner;

    public IntegerAdjustHolder( ViewGroup parent ){
        super( parent, R.layout.library_holder_adjust_integer );
    }

    @Override
    public void onBindView( View view ){
        tvTittle = view.findViewById( R.id.library_holder_tv_title );
        spinner = view.findViewById( R.id.library_holder_spinner );
    }

    @Override
    public void onBind( final IntegerAdjustment item ){
        super.onBind( item );
        tvTittle.setText( item.getTitle() );

        final String[] spinnerArray = new String[item.getMapInteger().size()];
        int index = 0;
        for( String key : item.getMapInteger().keySet() ){
            spinnerArray[index] = key;
            index++;
        }

        spinner.setAdapter( new ArrayAdapter<>( getContext(), R.layout.library_spinner_item_list_view, spinnerArray ) );
        spinner.setSelection( item.getCurrentIndex() );
        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int pos, long id ){
                item.unSelectAll();
                item.getMapInteger().get( spinnerArray[pos] ).setSelected( true );
            }

            @Override
            public void onNothingSelected( AdapterView<?> parent ){
            }
        } );
    }
}
