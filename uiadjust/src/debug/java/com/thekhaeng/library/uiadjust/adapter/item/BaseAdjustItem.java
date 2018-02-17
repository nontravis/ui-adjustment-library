package com.thekhaeng.library.uiadjust.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public abstract class BaseAdjustItem<T> implements Parcelable{

    public static final String EMPTY = "EMPTY";
    private String title;
    private int type = -1;
    private int id = -1;

    public BaseAdjustItem( @NonNull View view, String title, int type ){
        this( view.getId(), title, type );
    }

    public BaseAdjustItem( int id, String title, int type ){
        this.title = title;
        this.type = type;
        this.id = id;
    }

    public abstract Class<T> getStorageClass();

    public abstract BaseAdjustItem copy();

    public abstract void selectValue(@NonNull Object object );

    public void setTitle( String title ){
        this.title = title;
    }

    public void setType( int type ){
        this.type = type;
    }

    public void setId( int id ){
        this.id = id;
    }

    public int getType(){
        return type;
    }

    public String getTitle(){
        return title;
    }

    public int getId(){
        return id;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        dest.writeString( this.title );
        dest.writeInt( this.type );
        dest.writeInt( this.id );
    }

    protected BaseAdjustItem( Parcel in ){
        this.title = in.readString();
        this.type = in.readInt();
        this.id = in.readInt();
    }

}
