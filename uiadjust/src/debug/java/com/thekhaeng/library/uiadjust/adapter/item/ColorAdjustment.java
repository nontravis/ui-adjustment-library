package com.thekhaeng.library.uiadjust.adapter.item;

import android.os.Parcel;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.thekhaeng.library.uiadjust.adapter.AdjustAdapter;
import com.thekhaeng.library.uiadjust.adapter.model.AdjustColor;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public class ColorAdjustment extends BaseAdjustItem<AdjustColor>{

    private Map<String, AdjustColor> mapColor = new LinkedHashMap<>();

    public static ColorAdjustment create( @NonNull View view, String title, Map<String, AdjustColor> map ){
        return create( view.getId(), title, map );
    }

    public static ColorAdjustment create( @IdRes int id, String title, Map<String, AdjustColor> map ){
        if( map == null || map.isEmpty() ) return new ColorAdjustment( id );
        return new ColorAdjustment( id, title, map );
    }

    private ColorAdjustment( int id ){
        super( id, BaseAdjustItem.EMPTY, AdjustAdapter.COLOR_ITEM );
    }

    private ColorAdjustment( int id, String title, Map<String, AdjustColor> map ){
        super( id, title, AdjustAdapter.COLOR_ITEM );
        this.mapColor = map;
    }

    public void setMapColor( Map<String, AdjustColor> mapColor ){
        this.mapColor = mapColor;
    }

    public Map<String, AdjustColor> getMapColor(){
        return mapColor;
    }

    public AdjustColor getValue(){
        for( Map.Entry<String, AdjustColor> entry : this.mapColor.entrySet() ){
            AdjustColor value = entry.getValue();
            if( value.isSelected() ){
                return value;
            }
        }
        return null;
    }

    private void unSelectAll(){
        for( Map.Entry<String, AdjustColor> entry : this.mapColor.entrySet() ){
            entry.getValue().setSelected( false );
        }
    }

    @Override
    public Class<AdjustColor> getStorageClass(){
        return AdjustColor.class;
    }

    @Override
    public ColorAdjustment copy(){
        Map<String, AdjustColor> copyMapColor = new LinkedHashMap<>();
        for( Map.Entry<String, AdjustColor> entry : this.mapColor.entrySet() ){
            copyMapColor.put( entry.getKey(), entry.getValue().copy() );
        }
        return new ColorAdjustment(
                getId(),
                getTitle(),
                copyMapColor
        );
    }

    @Override
    public void selectValue( Object object ){
        if( object instanceof AdjustColor ){
            AdjustColor adjustColor = (AdjustColor) object;
            for( Map.Entry<String, AdjustColor> entry : this.mapColor.entrySet() ){
                if( entry.getValue().equals( adjustColor ) ){
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
        dest.writeInt( this.mapColor.size() );
        for( Map.Entry<String, AdjustColor> entry : this.mapColor.entrySet() ){
            dest.writeString( entry.getKey() );
            dest.writeParcelable( entry.getValue(), flags );
        }
    }

    protected ColorAdjustment( Parcel in ){
        super( in );
        int mapColorSize = in.readInt();
        this.mapColor = new HashMap<String, AdjustColor>( mapColorSize );
        for( int i = 0; i < mapColorSize; i++ ){
            String key = in.readString();
            AdjustColor value = in.readParcelable( AdjustColor.class.getClassLoader() );
            this.mapColor.put( key, value );
        }
    }

    public static final Creator<ColorAdjustment> CREATOR = new Creator<ColorAdjustment>(){
        @Override
        public ColorAdjustment createFromParcel( Parcel source ){
            return new ColorAdjustment( source );
        }

        @Override
        public ColorAdjustment[] newArray( int size ){
            return new ColorAdjustment[size];
        }
    };
}
