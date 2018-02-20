package com.thekhaeng.library.uiadjustment.debug.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.thekhaeng.library.uiadjustment.debug.adapter.AdjustAdapter;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class BooleanAdjustment extends BaseAdjustItem<Boolean>{

    private boolean value = false;

    public static BooleanAdjustment create( @NonNull View view, String title, boolean value ){
        return create( view.getId(), title, value, false );
    }

    public static BooleanAdjustment create( @IdRes int id, String title, boolean value ){
        return create( id, title, value, false );
    }

    public static BooleanAdjustment create( @NonNull View view, String title, boolean value, boolean isCommon ){
        return create( view.getId(), title, value, isCommon );
    }

    public static BooleanAdjustment create( @IdRes int id, String title, boolean value, boolean isCommon ){
        return new BooleanAdjustment( id, title, value, isCommon );
    }


    private BooleanAdjustment( int id, String title, boolean value, boolean isCommon ){
        super( id, title, AdjustAdapter.BOOLEAN_ITEM, isCommon );
        this.value = value;
    }

    public void setValue( boolean value ){
        this.value = value;
    }

    public boolean getValue(){
        return value;
    }

    @Override
    public Class<Boolean> getStorageClass(){
        return Boolean.class;
    }

    @Override
    public BooleanAdjustment copy(){
        return new BooleanAdjustment(
                getId(),
                getTitle(),
                this.value,
                isCommon()
        );
    }

    @Override
    public void selectValue( Object object ){
        if( object instanceof Boolean ){
            setValue( (Boolean) object );
        }
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeByte( this.value ? (byte) 1 : (byte) 0 );
    }

    protected BooleanAdjustment( Parcel in ){
        super( in );
        this.value = in.readByte() != 0;
    }

    public static final Parcelable.Creator<BooleanAdjustment> CREATOR = new Parcelable.Creator<BooleanAdjustment>(){
        @Override
        public BooleanAdjustment createFromParcel( Parcel source ){
            return new BooleanAdjustment( source );
        }

        @Override
        public BooleanAdjustment[] newArray( int size ){
            return new BooleanAdjustment[size];
        }
    };
}
