package com.thekhaeng.library.uiadjustment.debug.adapter.item;

import android.os.Parcel;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.thekhaeng.library.uiadjustment.debug.adapter.AdjustAdapter;
import com.thekhaeng.library.uiadjustment.debug.adapter.model.AdjustString;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class StringAdjustment extends BaseAdjustItem<AdjustString>{

    private Map<String, AdjustString> mapString = new HashMap<>();

    public static StringAdjustment create( @NonNull View view,
                                           String title,
                                           Map<String, AdjustString> map ){
        return create(view.getId(), title, map, false);
    }

    public static StringAdjustment create( @IdRes int id,
                                           String title,
                                           Map<String, AdjustString> map ){
        return create(id, title, map, false);

    }

    public static StringAdjustment create( @NonNull View view,
                                           String title,
                                           Map<String, AdjustString> map,
                                           boolean isCommon){
        return create(view.getId(), title, map, isCommon);
    }

    public static StringAdjustment create( @IdRes int id,
                                           String title,
                                           Map<String, AdjustString> map,
                                           boolean isCommon){
        if( map == null || map.isEmpty() ) return new StringAdjustment( id );

        return new StringAdjustment( id, title, map, isCommon );
    }

    private StringAdjustment( int id ){
        super( id, BaseAdjustItem.EMPTY, AdjustAdapter.STRING_ITEM, false );
    }

    private StringAdjustment( int id,
                              String title,
                              Map<String, AdjustString> map,
                              boolean isCommon){
        super( id, title, AdjustAdapter.STRING_ITEM, isCommon );
        this.mapString = map;
    }

    public void setMapString( Map<String, AdjustString> mapString ){
        this.mapString = mapString;
    }

    public Map<String, AdjustString> getMapString(){
        return mapString;
    }

    public int getCurrentIndex(){
        int index = 0;
        for( Map.Entry<String, AdjustString> entry : this.mapString.entrySet() ){
            AdjustString value = entry.getValue();
            if( value.isSelected() ){
                return index;
            }
            index++;
        }
        return 0;
    }

    public AdjustString getValue(){
        for( Map.Entry<String, AdjustString> entry : this.mapString.entrySet() ){
            AdjustString value = entry.getValue();
            if( value.isSelected() ){
                return value;
            }
        }
        return null;
    }


    public void unSelectAll(){
        for( Map.Entry<String, AdjustString> entry : this.mapString.entrySet() ){
            entry.getValue().setSelected( false );
        }
    }

    @Override
    public Class<AdjustString> getStorageClass(){
        return AdjustString.class;
    }

    @Override
    public StringAdjustment copy(){
        Map<String, AdjustString> copyMapString = new LinkedHashMap<>();
        for( Map.Entry<String, AdjustString> entry : this.mapString.entrySet() ){
            copyMapString.put( entry.getKey(), entry.getValue().copy() );
        }
        return new StringAdjustment(
                getId(),
                getTitle(),
                copyMapString,
                isCommon()
        );
    }

    @Override
    public void selectValue( Object object ){
        if( object instanceof AdjustString ){
            AdjustString adjustString = (AdjustString) object;
            for( Map.Entry<String, AdjustString> entry : this.mapString.entrySet() ){
                if( entry.getValue().equals( adjustString ) ){
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
        dest.writeInt( this.mapString.size() );
        for( Map.Entry<String, AdjustString> entry : this.mapString.entrySet() ){
            dest.writeString( entry.getKey() );
            dest.writeParcelable( entry.getValue(), flags );
        }
    }

    protected StringAdjustment( Parcel in ){
        super( in );
        int mapStringSize = in.readInt();
        this.mapString = new HashMap<String, AdjustString>( mapStringSize );
        for( int i = 0; i < mapStringSize; i++ ){
            String key = in.readString();
            AdjustString value = in.readParcelable( AdjustString.class.getClassLoader() );
            this.mapString.put( key, value );
        }
    }

    public static final Creator<StringAdjustment> CREATOR = new Creator<StringAdjustment>(){
        @Override
        public StringAdjustment createFromParcel( Parcel source ){
            return new StringAdjustment( source );
        }

        @Override
        public StringAdjustment[] newArray( int size ){
            return new StringAdjustment[size];
        }
    };
}
