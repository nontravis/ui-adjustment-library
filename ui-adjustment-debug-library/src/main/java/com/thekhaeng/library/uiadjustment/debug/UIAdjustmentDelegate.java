package com.thekhaeng.library.uiadjustment.debug;

import android.content.Context;

import com.thekhaeng.library.uiadjustment.core.UIAdjustmentInterface;
import com.thekhaeng.library.uiadjustment.debug.adapter.AdjustAdapter;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.BaseAdjustItem;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.BooleanAdjustment;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.ColorAdjustment;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.IntegerAdjustment;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.RangeFloatAdjustment;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.StringAdjustment;
import com.thekhaeng.library.uiadjustment.debug.adapter.model.AdjustColor;
import com.thekhaeng.library.uiadjustment.debug.adapter.model.AdjustInteger;
import com.thekhaeng.library.uiadjustment.debug.adapter.model.AdjustRangeFloat;
import com.thekhaeng.library.uiadjustment.debug.adapter.model.AdjustString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by「 The Khaeng 」on 20 Feb 2018 :)
 */

class UIAdjustmentDelegate{

    private final DefaultLocalStorage storage;
    private final UIAdjustmentInterface adjustInterface;
    private final Context context;
    private long delay = 0L;
    private boolean isUseLocalStorage = false;
    private boolean bindDataImmediately = false;

    UIAdjustmentDelegate( Context context, UIAdjustmentInterface adjustInterface ){
        this.context = context;
        this.storage = DefaultLocalStorage.getInstance( context );
        this.adjustInterface = adjustInterface;
    }

    void setDelay( long delay ){
        this.delay = delay;
    }

    void setUseLocalStorage( boolean useLocalStorage ){
        isUseLocalStorage = useLocalStorage;
    }

    void setBindDataImmediately( boolean bindDataImmediately ){
        this.bindDataImmediately = bindDataImmediately;
    }

    List<BaseAdjustItem> copyItemList( List<BaseAdjustItem> itemList ){
        List<BaseAdjustItem> copyItemList = new ArrayList<>();
        for( BaseAdjustItem item : itemList ){
            copyItemList.add( item.copy() );
        }
        return copyItemList;
    }

    List<BaseAdjustItem> getItemList( List<BaseAdjustItem> defaultItemList ){
        if( isUseLocalStorage ){
            for( BaseAdjustItem item : defaultItemList ){
                Object data;
                if( item.isCommon() ){
                    data = storage.load(
                            item.getId() + "",
                            item.getStorageClass() );
                }else{
                    data = storage.load(
                            getStorageKey( item.getId(), item.getType() ),
                            item.getStorageClass() );
                }

                if( data != null ){
                    item.selectValue( data );
                }
            }
        }
        return defaultItemList;

    }

    String getStorageKey( int id, int type ){
        return getClass().getSimpleName() + "_" + id + "_" + type;
    }


    void bindData( List<BaseAdjustItem> itemList ){
        if( itemList != null ){
            for( BaseAdjustItem item : itemList ){
                int id = item.getId();
                if( item instanceof BooleanAdjustment ){
                    boolean value = ( (BooleanAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        if( item.isCommon() ){
                            storage.save( id + "", value );
                        }else{
                            storage.save( getStorageKey( id, AdjustAdapter.BOOLEAN_ITEM ), value );
                        }
                    }

                    if( adjustInterface != null ){
                        adjustInterface.onBoolean( id, value );
                    }
                }else if( item instanceof ColorAdjustment ){
                    AdjustColor color = ( (ColorAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        if( item.isCommon() ){
                            storage.save( id + "", color );
                        }else{
                            storage.save( getStorageKey( id, AdjustAdapter.COLOR_ITEM ), color );
                        }
                    }

                    if( color != null && adjustInterface != null ){
                        adjustInterface.onColor( id, color.getColor( context ) );
                    }
                }else if( item instanceof IntegerAdjustment ){
                    AdjustInteger value = ( (IntegerAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        if( item.isCommon() ){
                            storage.save( id + "", value );
                        }else{
                            storage.save( getStorageKey( id, AdjustAdapter.INTEGER_ITEM ), value );
                        }
                    }
                    if( value != null && adjustInterface != null ){
                        adjustInterface.onInteger( id, value.getValue() );
                    }
                }else if( item instanceof RangeFloatAdjustment ){
                    AdjustRangeFloat value = ( (RangeFloatAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        if( item.isCommon() ){
                            storage.save( id + "", value );
                        }else{
                            storage.save( getStorageKey( id, AdjustAdapter.RANGE_FLOAT_ITEM ), value );
                        }
                    }
                    if( adjustInterface != null ){
                        adjustInterface.onRangeFloat( id, value.getCurrentValue() );
                    }
                }else if( item instanceof StringAdjustment ){
                    AdjustString value = ( (StringAdjustment) item ).getValue();
                    if( isUseLocalStorage ){
                        if( item.isCommon() ){
                            storage.save( id + "", value );
                        }else{
                            storage.save( getStorageKey( id, AdjustAdapter.STRING_ITEM ), value );
                        }
                    }

                    if( value != null && adjustInterface != null ){
                        adjustInterface.onString( id, value.getValue() );
                    }
                }
            }
        }
    }

    long getDelay(){
        return delay;
    }

    boolean isUseLocalStorage(){
        return isUseLocalStorage;
    }

    boolean isBindDataImmediately(){
        return bindDataImmediately;
    }
}
