package com.thekhaeng.library.uiadjust.adapter.item;

import android.os.Parcel;
import android.support.annotation.IdRes;
import android.view.View;

import com.thekhaeng.library.uiadjust.adapter.AdjustAdapter;
import com.thekhaeng.library.uiadjust.adapter.model.AdjustInteger;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public class IntegerAdjustment extends BaseAdjustItem<AdjustInteger>{

    private Map<String, AdjustInteger> mapInteger = new HashMap<>();

    public static IntegerAdjustment create( View view, String title, Map<String, AdjustInteger> map ){
        return create(view.getId(), title, map);
    }

    public static IntegerAdjustment create( @IdRes int id, String title, Map<String, AdjustInteger> map ){
        if( map == null || map.isEmpty() ) return new IntegerAdjustment( id );
        return new IntegerAdjustment( id, title, map );
    }

    private IntegerAdjustment( int id ){
        super( id, BaseAdjustItem.EMPTY, AdjustAdapter.INTEGER_ITEM );
    }

    private IntegerAdjustment( int id, String title, Map<String, AdjustInteger> map ){
        super( id, title, AdjustAdapter.INTEGER_ITEM );
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
    public Class<AdjustInteger> getStorageClass(){
        return AdjustInteger.class;
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
                copyMapInteger
        );
    }

    @Override
    public void selectValue( Object object ){
        if( object instanceof AdjustInteger ){
            AdjustInteger adjustInteger = (AdjustInteger) object;
            for( Map.Entry<String, AdjustInteger> entry : this.mapInteger.entrySet() ){
                if( entry.getValue().equals(adjustInteger) ){
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
