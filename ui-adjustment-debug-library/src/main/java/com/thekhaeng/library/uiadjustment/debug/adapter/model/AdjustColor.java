package com.thekhaeng.library.uiadjustment.debug.adapter.model;

import android.os.Parcel;
import android.support.annotation.ColorInt;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class AdjustColor extends BaseAdjustModel{


    private int color = -1;

    private AdjustColor(){
        super( false );
    }


    public AdjustColor( @ColorInt int color ){
        super( false );
        this.color = color;
    }

    public AdjustColor( @ColorInt int color, boolean selected ){
        super( selected );
        this.color = color;
    }

    public AdjustColor setColor(@ColorInt int color){
        this.color = color;
        return this;
    }

    public int getColor(){
        return color;
    }

    @Override
    public AdjustColor setSelected( boolean selected ){
        return (AdjustColor) super.setSelected( selected );
    }



    public AdjustColor copy(){
        return new AdjustColor()
                .setColor( color )
                .setSelected( isSelected() );
    }


    @Override
    public boolean equals( Object o ){
        if( this == o ) return true;
        if( !( o instanceof AdjustColor ) ) return false;

        AdjustColor that = (AdjustColor) o;

        return color == that.color;
    }

    @Override
    public int hashCode(){
        return color;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeInt( this.color );
    }

    protected AdjustColor( Parcel in ){
        super( in );
        this.color = in.readInt();
    }

    public static final Creator<AdjustColor> CREATOR = new Creator<AdjustColor>(){
        @Override
        public AdjustColor createFromParcel( Parcel source ){
            return new AdjustColor( source );
        }

        @Override
        public AdjustColor[] newArray( int size ){
            return new AdjustColor[size];
        }
    };
}

