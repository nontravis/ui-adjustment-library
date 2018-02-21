package com.thekhaeng.library.uiadjustment.debug.adapter.item;

import android.os.Parcel;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.thekhaeng.library.uiadjustment.debug.adapter.AdjustAdapter;
import com.thekhaeng.library.uiadjustment.debug.adapter.model.AdjustInteger;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class IntegerAdjustment extends BaseAdjustItem<Integer>{

    private Map<String, AdjustInteger> mapInteger = new HashMap<>();

    public static IntegerAdjustment create( @NonNull View view, String title, Map<String, AdjustInteger> map ){
        return create( view.getId(), title, map, false );
    }

    public static IntegerAdjustment create( @IdRes int id, String title, Map<String, AdjustInteger> map ){
        return create( id, title, map, false );
    }

    public static IntegerAdjustment create( @NonNull View view, String title, Map<String, AdjustInteger> map, boolean isCommon ){
        return create( view.getId(), title, map, isCommon );
    }

    public static IntegerAdjustment create( @IdRes int id, String title, Map<String, AdjustInteger> map, boolean isCommon ){
        if( map == null || map.isEmpty() ) return new IntegerAdjustment( id );
        return new IntegerAdjustment( id, title, map, isCommon );
    }

    private IntegerAdjustment( int id ){
        super( id, BaseAdjustItem.EMPTY, AdjustAdapter.INTEGER_ITEM, false );
    }

    private IntegerAdjustment( int id, String title, Map<String, AdjustInteger> map, boolean isCommon ){
        super( id, title, AdjustAdapter.INTEGER_ITEM, isCommon );
        this.mapInteger = map;
    }

    public void setMapInteger( Map<String, AdjustInteger> mapInteger ){
        this.mapInteger = mapInteger;
    }

    public Map<String, AdjustInteger> getMapInteger(){
        return mapInteger;
    }

    public int getCurrentIndex(){
        int index = 0;
        for( Map.Entry<String, AdjustInteger> entry : this.mapInteger.entrySet() ){
            AdjustInteger value = entry.getValue();
            if( value.isSelected() ){
                return index;
            }
            index++;
        }
        return 0;
    }

    @Nullable
    public AdjustInteger getValue(){
        for( Map.Entry<String, AdjustInteger> entry : this.mapInteger.entrySet() ){
            AdjustInteger value = entry.getValue();
            if( value.isSelected() ){
                return value;
            }
        }
        return null;
    }

    public void unSelectAll(){
        for( Map.Entry<String, AdjustInteger> entry : this.mapInteger.entrySet() ){
            entry.getValue().setSelected( false );
        }
    }

    @Override
    public Class<Integer> getStorageClass(){
        return Integer.class;
    }

    @Override
    public IntegerAdjustment copy(){
        Map<String, AdjustInteger> copyMapInteger = new LinkedHashMap<>();
        for( Map.Entry<String, AdjustInteger> entry : this.mapInteger.entrySet() ){
            copyMapInteger.put( entry.getKey(), entry.getValue().copy() );
        }
        return new IntegerAdjustment(
                getId(),
                getTitle(),
                copyMapInteger,
                isCommon()
        );
    }

    @Override
    public void selectValue( @NonNull Object object ){
        if( object instanceof Integer ){
            Integer adjustInteger = (Integer) object;
            for( Map.Entry<String, AdjustInteger> entry : this.mapInteger.entrySet() ){
                if( entry.getValue().getValue() == adjustInteger ){
                    unSelectAll();
                    entry.getValue().setSelected( true );
                    return;
                }
            }
        }
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeInt( this.mapInteger.size() );
        for( Map.Entry<String, AdjustInteger> entry : this.mapInteger.entrySet() ){
            dest.writeString( entry.getKey() );
            dest.writeParcelable( entry.getValue(), flags );
        }
    }

    protected IntegerAdjustment( Parcel in ){
        super( in );
        int mapIntegerSize = in.readInt();
        this.mapInteger = new HashMap<String, AdjustInteger>( mapIntegerSize );
        for( int i = 0; i < mapIntegerSize; i++ ){
            String key = in.readString();
            AdjustInteger value = in.readParcelable( AdjustInteger.class.getClassLoader() );
            this.mapInteger.put( key, value );
        }
    }

    public static final Creator<IntegerAdjustment> CREATOR = new Creator<IntegerAdjustment>(){
        @Override
        public IntegerAdjustment createFromParcel( Parcel source ){
            return new IntegerAdjustment( source );
        }

        @Override
        public IntegerAdjustment[] newArray( int size ){
            return new IntegerAdjustment[size];
        }
    };
}
