package com.example.mymoequest.adapter;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.example.mymoequest.R;
import com.example.mymoequest.adapter.base.AbsRecyclerViewAdapter;
import com.example.mymoequest.entity.gank.GankMeizi;
import com.example.mymoequest.widget.RatioImageView;

import java.util.ArrayList;
import java.util.List;


public class GankMeiziAdapter extends AbsRecyclerViewAdapter
{

    private List<GankMeizi> meizis = new ArrayList<>();


    public GankMeiziAdapter(RecyclerView recyclerView, List<GankMeizi> meizis)
    {

        super(recyclerView);
        this.meizis = meizis;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.card_item_meizi, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {

        if (holder instanceof ItemViewHolder)
        {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mTextView.setText(meizis.get(position).getDesc());
            Glide.with(getContext())
                    .load(meizis.get(position).getUrl())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder_image)
                    .into(itemViewHolder.ratioImageView)
                    .getSize(new SizeReadyCallback()
                    {

                        @Override
                        public void onSizeReady(int width, int height)
                        {

                            if (!itemViewHolder.item.isShown())
                            {
                                itemViewHolder.item.setVisibility(View.VISIBLE);
                            }
                        }
                    });

            itemViewHolder.ratioImageView.setTag(R.string.app_name, meizis.get(position).getUrl());
            ViewCompat.setTransitionName(itemViewHolder.ratioImageView, meizis.get(position).getUrl());
        }


        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {

        return meizis == null ? 0 : meizis.size();
    }

    public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder
    {

        public RatioImageView ratioImageView;

        public TextView mTextView;

        public View item;

        public ItemViewHolder(View itemView)
        {

            super(itemView);
            item = itemView;
            ratioImageView = $(R.id.item_img);
            mTextView = $(R.id.item_title);
            ratioImageView.setOriginalSize(50, 50);
        }
    }
}
