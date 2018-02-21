package com.thekhaeng.library.uiadjustment.debug.adapter.holder;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thekhaeng.library.uiadjustment.debug.R;
import com.thekhaeng.library.uiadjustment.debug.adapter.item.ColorAdjustment;
import com.thekhaeng.library.uiadjustment.debug.adapter.layout.CenterLayoutManager;
import com.thekhaeng.library.uiadjustment.debug.adapter.model.AdjustColor;
import com.thekhaeng.library.uiadjustment.debug.widget.SingleColorDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by The Khaeng on 15 Feb 2018 :)
 */

public class ColorAdjustHolder
        extends BaseAdjustViewHolder<ColorAdjustment>{

    private AppCompatTextView tvCommon;
    private AppCompatTextView tvTitle;
    private ColorAdapter adapter;
    private RecyclerView rvColor;


    public ColorAdjustHolder( ViewGroup parent ){
        super( parent, R.layout.library_holder_adjust_color );
    }

    @Override
    public void onBindView( View view ){
        tvCommon = view.findViewById( R.id.library_holder_tv_common );
        tvTitle = view.findViewById( R.id.library_holder_tv_title );
        rvColor = view.findViewById( R.id.library_holder_rv_color );
        rvColor.setLayoutManager( new CenterLayoutManager( getContext(), LinearLayoutManager.HORIZONTAL, false ) );
        adapter = new ColorAdapter( view.getContext() );
        rvColor.setAdapter( adapter );
    }

    @Override
    public void onBind( ColorAdjustment item ){
        super.onBind( item );
        if( item.isCommon() ){
            tvCommon.setVisibility( View.VISIBLE );
        }else{
            tvCommon.setVisibility( View.GONE );
        }
        List<AdjustColor> colorList = new ArrayList<>();
        for( Map.Entry<String, AdjustColor> entry : item.getMapColor().entrySet() ){
            colorList.add( entry.getValue() );
        }
        tvTitle.setText( item.getTitle() );
        adapter.setColorList( colorList );
        rvColor.scrollToPosition( adapter.getCheckPosition() );
    }



    /* =========================== ColorAdapter ====================================================== */
    private static class ColorAdapter extends RecyclerView.Adapter<ColorViewHolder>{
        private List<AdjustColor> colorList = new ArrayList<>();
        private Context context;

        public ColorAdapter( Context context ){
            this.context = context;
        }

        public void setColorList( List<AdjustColor> colorList ){
            this.colorList = colorList;
            notifyDataSetChanged();
        }

        void selectColor( @ColorInt int color ){
            for( AdjustColor item : colorList ){
                item.setSelected( item.getColor( ) == color );
            }
            notifyDataSetChanged();
        }

        @Override
        public ColorViewHolder onCreateViewHolder( final ViewGroup parent, int viewType ){
            final ColorViewHolder viewHolder = new ColorViewHolder( parent );
            viewHolder.setOnClickColorViewHolder( setOnClickColorViewHolderListener() );
            return viewHolder;
        }

        @Override
        public void onBindViewHolder( ColorViewHolder holder, int position ){
            holder.setAdjustColor( colorList.get( position ) );
        }

        @Override
        public int getItemCount(){
            return colorList.size();
        }

        List<AdjustColor> getColorList(){
            return colorList;
        }

        int getCheckPosition(){
            for( int i = 0; i < getColorList().size(); i++ ){
                if( getColorList().get( i ).isSelected() ){
                    return i;
                }
            }
            return -1;
        }

        @NonNull
        private ColorViewHolder.OnClickColorViewHolder setOnClickColorViewHolderListener(){
            return new ColorViewHolder.OnClickColorViewHolder(){
                @Override
                public void onClick( ColorViewHolder vh, int color ){
                    selectColor( color );
                }
            };
        }


    }

    /* =========================== Holder ======================================================= */
    private static class ColorViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final SingleColorDrawable drawable;
        private AdjustColor color;

        interface OnClickColorViewHolder{
            void onClick( ColorViewHolder vh, @ColorInt int color );
        }

        ColorViewHolder( ViewGroup parent ){
            super( LayoutInflater.from( parent.getContext() )
                    .inflate( R.layout.library_holder_widget_color, parent, false )
            );
            this.imageView = (ImageView) itemView;
            this.drawable = new SingleColorDrawable( parent.getContext() );
            this.imageView.setImageDrawable( this.drawable );

        }

        void setAdjustColor( final AdjustColor colorItem ){
            this.color = colorItem;
            drawable.setColorItem( itemView.getContext(), colorItem );
            imageView.invalidate();
        }

        void setOnClickColorViewHolder( final OnClickColorViewHolder listener ){
            imageView.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick( View v ){
                    if( listener != null ){
                        listener.onClick( ColorViewHolder.this, color.getColor() );
                    }
                }
            } );
        }

    }
}
