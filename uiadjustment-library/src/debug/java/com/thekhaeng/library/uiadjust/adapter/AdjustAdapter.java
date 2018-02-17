package com.thekhaeng.library.uiadjust.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.thekhaeng.library.uiadjust.adapter.holder.BaseAdjustViewHolder;
import com.thekhaeng.library.uiadjust.adapter.holder.BooleanAdjustHolder;
import com.thekhaeng.library.uiadjust.adapter.holder.ColorAdjustHolder;
import com.thekhaeng.library.uiadjust.adapter.holder.IntegerAdjustHolder;
import com.thekhaeng.library.uiadjust.adapter.holder.RangeFloatAdjustHolder;
import com.thekhaeng.library.uiadjust.adapter.holder.StringAdjustHolder;
import com.thekhaeng.library.uiadjust.adapter.item.BaseAdjustItem;
import com.thekhaeng.library.uiadjust.adapter.item.BooleanAdjustment;
import com.thekhaeng.library.uiadjust.adapter.item.ColorAdjustment;
import com.thekhaeng.library.uiadjust.adapter.item.IntegerAdjustment;
import com.thekhaeng.library.uiadjust.adapter.item.RangeFloatAdjustment;
import com.thekhaeng.library.uiadjust.adapter.item.StringAdjustment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by「 The Khaeng 」on 15 Feb 2018 :)
 */

public class AdjustAdapter extends RecyclerView.Adapter<BaseAdjustViewHolder>{

    public static final int BOOLEAN_ITEM = 1;
    public static final int COLOR_ITEM = 2;
    public static final int INTEGER_ITEM = 3;
    public static final int RANGE_FLOAT_ITEM = 4;
    public static final int STRING_ITEM = 5;

    private List<BaseAdjustItem> itemList = new ArrayList<>();


    public void setItemList( List<BaseAdjustItem> itemList ){
        if( itemList == null || itemList.isEmpty() ) return;
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public void updateItemList( List<BaseAdjustItem> itemList ){
        for( int i = 0; i < itemList.size(); i++ ){
            this.itemList.set( i, itemList.get( i ) );
            notifyItemChanged( i );
        }
    }


    public List<BaseAdjustItem> getItemList(){
        return itemList;
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }

    @Override
    public int getItemViewType( int position ){
        return itemList.get( position ).getType();
    }

    @Override
    public BaseAdjustViewHolder onCreateViewHolder( ViewGroup parent, int viewType ){
        if( viewType == BOOLEAN_ITEM ){
            return new BooleanAdjustHolder( parent );
        }else if( viewType == COLOR_ITEM ){
            return new ColorAdjustHolder( parent );
        }else if( viewType == INTEGER_ITEM ){
            return new IntegerAdjustHolder( parent );
        }else if( viewType == RANGE_FLOAT_ITEM ){
            return new RangeFloatAdjustHolder( parent );
        }else if( viewType == STRING_ITEM ){
            return new StringAdjustHolder( parent );
        }
        return null;

    }

    @Override
    public void onBindViewHolder( BaseAdjustViewHolder vh, int position ){
        BaseAdjustItem i = itemList.get( position );
        if( getItemViewType( position ) == BOOLEAN_ITEM ){
            BooleanAdjustHolder holder = (BooleanAdjustHolder) vh;
            BooleanAdjustment item = (BooleanAdjustment) i;
            holder.onBind( item );
        }else if( getItemViewType( position ) == COLOR_ITEM ){
            ColorAdjustHolder holder = (ColorAdjustHolder) vh;
            ColorAdjustment item = (ColorAdjustment) i;
            holder.onBind( item );
        }else if( getItemViewType( position ) == INTEGER_ITEM ){
            IntegerAdjustHolder holder = (IntegerAdjustHolder) vh;
            IntegerAdjustment item = (IntegerAdjustment) i;
            holder.onBind( item );
        }else if( getItemViewType( position ) == RANGE_FLOAT_ITEM ){
            RangeFloatAdjustHolder holder = (RangeFloatAdjustHolder) vh;
            RangeFloatAdjustment item = (RangeFloatAdjustment) i;
            holder.onBind( item );
        }else if( getItemViewType( position ) == STRING_ITEM ){
            StringAdjustHolder holder = (StringAdjustHolder) vh;
            StringAdjustment item = (StringAdjustment) i;
            holder.onBind( item );
        }
    }


}
