package com.thekhaeng.library.uiadjustment.debug.adapter.model;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class AdjustColor extends BaseAdjustModel{

    private String color = "";
    private int id = -1;

    private AdjustColor(){
        super(false);
    }

    public AdjustColor( @ColorRes int id){
        super(false);
        this.id = id;
    }

    public AdjustColor( @ColorRes int id, boolean selected ){
        super(selected);
        this.id = id;
    }

    public AdjustColor( String color){
        super(false);
        this.color = color;
    }

    public AdjustColor( String color, boolean selected ){
        super(selected);
        this.color = color;
    }

    public AdjustColor setColor( String color ){
        this.color = color;
        return this;
    }

    public AdjustColor setId( int id ){
        this.id = id;
        return this;
    }

    @Override
    public AdjustColor setSelected( boolean selected ){
        return (AdjustColor) super.setSelected( selected );
    }

    public int getId(){
        return id;
    }


    @ColorInt
    public int getColor( Context context ){
        if( isUseId() ){
            return ContextCompat.getColor( context, id );
        }else{
            return Color.parseColor( color );
        }
    }


    public AdjustColor copy(){
        return new AdjustColor()
                .setColor( color )
                .setId( id )
                .setSelected( isSelected() ) ;
    }



    public boolean isUseId(){
        return color == null;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ) return true;
        if( !( o instanceof AdjustColor ) ) return false;

        AdjustColor that = (AdjustColor) o;

        if( getId() != that.getId() ) return false;
        return color.equals( that.color );
    }

    @Override
    public int hashCode(){
        int result = color.hashCode();
        result = 31 * result + getId();
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeString( this.color );
        dest.writeInt( this.id );
    }

    protected AdjustColor( Parcel in ){
        super( in );
        this.color = in.readString();
        this.id = in.readInt();
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

