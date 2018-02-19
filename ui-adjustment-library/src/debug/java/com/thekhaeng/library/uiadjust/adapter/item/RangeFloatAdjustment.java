package com.thekhaeng.library.uiadjust.adapter.item;

import android.os.Parcel;
import android.support.annotation.IdRes;
import android.view.View;

import com.thekhaeng.library.uiadjust.adapter.AdjustAdapter;
import com.thekhaeng.library.uiadjust.adapter.model.AdjustRangeFloat;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class RangeFloatAdjustment extends BaseAdjustItem<AdjustRangeFloat>{

    private AdjustRangeFloat rangeFloat;

    public static RangeFloatAdjustment create( View view,
                                               String title,
                                               AdjustRangeFloat rangeFloat ){
        return new RangeFloatAdjustment( view.getId(), title, rangeFloat );
    }

    public static RangeFloatAdjustment create( @IdRes int id,
                                               String title,
                                               AdjustRangeFloat rangeFloat ){
        return new RangeFloatAdjustment( id, title, rangeFloat );
    }


    private RangeFloatAdjustment( int id,
                                  String title,
                                  AdjustRangeFloat rangeFloat ){
        super( id, title, AdjustAdapter.RANGE_FLOAT_ITEM );
        this.rangeFloat = rangeFloat;
    }

    public AdjustRangeFloat getValue(){
        return rangeFloat;
    }


    public void setRangeFloat( AdjustRangeFloat rangeFloat ){
        this.rangeFloat = rangeFloat;
    }

    @Override
    public RangeFloatAdjustment copy(){
        return new RangeFloatAdjustment(
                getId(),
                getTitle(),
                rangeFloat.copy() );
    }

    @Override
    public void selectValue( Object object ){
        if( object instanceof AdjustRangeFloat ){
            setRangeFloat( (AdjustRangeFloat) object );
        }
    }

    @Override
    public Class<AdjustRangeFloat> getStorageClass(){
        return AdjustRangeFloat.class;
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

    public static final Creator<RangeFloatAdjustment> CREATOR = new Creator<RangeFloatAdjustment>(){
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
