package com.thekhaeng.library.uiadjust.adapter.model;

import android.os.Parcel;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public class AdjustInteger extends BaseAdjustModel{

    private int value = 0;

    public AdjustInteger( int value ){
        super( false );
        this.value = value;
    }

    public AdjustInteger( int value, boolean select ){
        super( select );
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public AdjustInteger copy(){
        return new AdjustInteger( value, isSelected() );
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ) return true;
        if( !( o instanceof AdjustInteger ) ) return false;

        AdjustInteger that = (AdjustInteger) o;

        return getValue() == that.getValue();
    }

    @Override
    public int hashCode(){
        return getValue();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeInt( this.value );
    }

    protected AdjustInteger( Parcel in ){
        super( in );
        this.value = in.readInt();
    }

    public static final Creator<AdjustInteger> CREATOR = new Creator<AdjustInteger>(){
        @Override
        public AdjustInteger createFromParcel( Parcel source ){
            return new AdjustInteger( source );
        }

        @Override
        public AdjustInteger[] newArray( int size ){
            return new AdjustInteger[size];
        }
    };
}
