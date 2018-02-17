package com.thekhaeng.library.uiadjust.adapter.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public abstract class BaseAdjustModel implements Parcelable{

    private boolean selected = false;

    public BaseAdjustModel( boolean selected ){
        this.selected = selected;
    }

    public boolean isSelected(){
        return selected;
    }

    public BaseAdjustModel setSelected( boolean selected ){
        this.selected = selected;
        return this;
    }


    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        dest.writeByte( this.selected ? (byte) 1 : (byte) 0 );
    }

    protected BaseAdjustModel( Parcel in ){
        this.selected = in.readByte() != 0;
    }

}

