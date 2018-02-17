package com.thekhaeng.library.uiadjust.adapter.model;

import android.os.Parcel;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public class AdjustRangeFloat extends BaseAdjustModel{

    private float minValue;
    private float maxValue;
    private float increment;
    private float initialValue;
    private float currentValue;

    public AdjustRangeFloat( float minValue,
                             float maxValue,
                             float increment,
                             float initialValue ){
        super( false );
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.increment = increment;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
    }


    public float getMinValue(){
        return minValue;
    }

    public void setMinValue( float minValue ){
        this.minValue = minValue;
    }

    public float getMaxValue(){
        return maxValue;
    }

    public void setMaxValue( float maxValue ){
        this.maxValue = maxValue;
    }

    public float getIncrement(){
        return increment;
    }

    public float getCurrentValue(){
        return currentValue;
    }

    public void setIncrement( float increment ){
        this.increment = increment;
    }

    public float getInitialValue(){
        return initialValue;
    }

    public void setInitialValue( float initialValue ){
        this.initialValue = initialValue;
    }

    public void setCurrentValue( float currentValue ){
        this.currentValue = currentValue;
    }

    public AdjustRangeFloat copy(){
        return new AdjustRangeFloat(
                minValue,
                maxValue,
                increment,
                initialValue );
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeFloat( this.minValue );
        dest.writeFloat( this.maxValue );
        dest.writeFloat( this.increment );
        dest.writeFloat( this.initialValue );
        dest.writeFloat( this.currentValue );
    }

    protected AdjustRangeFloat( Parcel in ){
        super( in );
        this.minValue = in.readFloat();
        this.maxValue = in.readFloat();
        this.increment = in.readFloat();
        this.initialValue = in.readFloat();
        this.currentValue = in.readFloat();
    }

    public static final Creator<AdjustRangeFloat> CREATOR = new Creator<AdjustRangeFloat>(){
        @Override
        public AdjustRangeFloat createFromParcel( Parcel source ){
            return new AdjustRangeFloat( source );
        }

        @Override
        public AdjustRangeFloat[] newArray( int size ){
            return new AdjustRangeFloat[size];
        }
    };
}
