package com.thekhaeng.library.uiadjust.adapter.model;

import android.os.Parcel;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class AdjustString extends BaseAdjustModel{

    private String value;

    public AdjustString( String value ){
        super( false );
        this.value = value;
    }

    public AdjustString( String value, boolean select ){
        super( select );
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public AdjustString copy(){
        return new AdjustString( value, isSelected() );
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ) return true;
        if( !( o instanceof AdjustString ) ) return false;

        AdjustString that = (AdjustString) o;

        return getValue() != null ? getValue().equals( that.getValue() ) : that.getValue() == null;
    }

    @Override
    public int hashCode(){
        return getValue() != null ? getValue().hashCode() : 0;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeString( this.value );
    }

    protected AdjustString( Parcel in ){
        super( in );
        this.value = in.readString();
    }

    public static final Creator<AdjustString> CREATOR = new Creator<AdjustString>(){
        @Override
        public AdjustString createFromParcel( Parcel source ){
            return new AdjustString( source );
        }

        @Override
        public AdjustString[] newArray( int size ){
            return new AdjustString[size];
        }
    };
}
