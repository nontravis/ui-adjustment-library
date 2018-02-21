package com.thekhaeng.library.uiadjustment.debug.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.thekhaeng.library.uiadjustment.debug.adapter.AdjustAdapter;
import com.thekhaeng.library.uiadjustment.debug.adapter.model.AdjustRangeFloat;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class RangeFloatAdjustment extends BaseAdjustItem<Float>{

    private AdjustRangeFloat rangeFloat;

    public static RangeFloatAdjustment create( @NonNull View view,
                                               String title,
                                               AdjustRangeFloat rangeFloat ){
        return create( view.getId(), title, rangeFloat, false );
    }

    public static RangeFloatAdjustment create( @IdRes int id,
                                               String title,
                                               AdjustRangeFloat rangeFloat ){
        return create( id, title, rangeFloat, false );
    }

    public static RangeFloatAdjustment create( @NonNull View view,
                                               String title,
                                               AdjustRangeFloat rangeFloat,
                                               boolean isCommon ){
        return create( view.getId(), title, rangeFloat, isCommon );
    }

    public static RangeFloatAdjustment create( @IdRes int id,
                                               String title,
                                               AdjustRangeFloat rangeFloat,
                                               boolean isCommon ){
        return new RangeFloatAdjustment( id, title, rangeFloat, isCommon );
    }


    private RangeFloatAdjustment( int id,
                                  String title,
                                  AdjustRangeFloat rangeFloat,
                                  boolean isCommon ){
        super( id, title, AdjustAdapter.RANGE_FLOAT_ITEM, isCommon );
        this.rangeFloat = rangeFloat;
    }

    public AdjustRangeFloat getValue(){
        return rangeFloat;
    }


    public void setCurrentValue( Float currentValue ){
        this.rangeFloat.setCurrentValue( currentValue );
    }

    @Override
    public RangeFloatAdjustment copy(){
        return new RangeFloatAdjustment(
                getId(),
                getTitle(),
                rangeFloat.copy(),
                isCommon());
    }

    @Override
    public void selectValue( @NonNull Object object ){
        if( object instanceof Float ){
            setCurrentValue( (Float) object );
        }
    }

    @Override
    public Class<Float> getStorageClass(){
        return Float.class;
    }


    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeParcelable( this.rangeFloat, flags );
    }

    protected RangeFloatAdjustment( Parcel in ){
        super( in );
        this.rangeFloat = in.readParcelable( AdjustRangeFloat.class.getClassLoader() );
    }

    public static final Parcelable.Creator<RangeFloatAdjustment> CREATOR = new Parcelable.Creator<RangeFloatAdjustment>(){
        @Override
        public RangeFloatAdjustment createFromParcel( Parcel source ){
            return new RangeFloatAdjustment( source );
        }

        @Override
        public RangeFloatAdjustment[] newArray( int size ){
            return new RangeFloatAdjustment[size];
        }
    };
}
