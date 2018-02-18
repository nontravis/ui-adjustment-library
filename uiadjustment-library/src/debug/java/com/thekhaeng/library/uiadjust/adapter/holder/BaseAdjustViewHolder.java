package com.thekhaeng.library.uiadjust.adapter.holder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thekhaeng.library.uiadjust.adapter.item.BaseAdjustItem;


/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public abstract class BaseAdjustViewHolder<I extends BaseAdjustItem>
        extends RecyclerView.ViewHolder{

    private I item;

    public BaseAdjustViewHolder( ViewGroup parent, @LayoutRes int layout ){
        super( LayoutInflater
                .from( parent.getContext() )
                .inflate( layout, parent, false ) );
        onBindView( itemView );
    }

    public abstract void onBindView( View view );

    public void onBind( I item ){
        this.item = item;
    }

    public I getItem(){
        return item;
    }

    public Context getContext(){
       return itemView.getContext();
    }

}
