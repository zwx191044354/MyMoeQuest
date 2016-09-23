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
import com.example.mymoequest.entity.meizitu.MeiziTu;
import com.example.mymoequest.widget.RatioImageView;

import java.util.ArrayList;
import java.util.List;


public class MeiziTuAdapter extends AbsRecyclerViewAdapter
{

    private List<MeiziTu> meiziList = new ArrayList<>();

    public MeiziTuAdapter(RecyclerView recyclerView, List<MeiziTu> meiziList)
    {

        super(recyclerView);
        this.meiziList = meiziList;
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
            itemViewHolder.mTextView.setText(meiziList.get(position).getTitle());
            Glide.with(getContext())
                    .load(meiziList.get(position).getImageurl())
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

            itemViewHolder.ratioImageView.setTag(R.string.app_name, meiziList.get(position).getImageurl());
            ViewCompat.setTransitionName(itemViewHolder.ratioImageView, meiziList.get(position).getImageurl());
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {

        return meiziList.size();
    }


    public class ItemViewHolder extends ClickableViewHolder
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
