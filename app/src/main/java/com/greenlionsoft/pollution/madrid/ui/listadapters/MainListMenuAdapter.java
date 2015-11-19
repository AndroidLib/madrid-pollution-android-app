package com.greenlionsoft.pollution.madrid.ui.listadapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.greenlionsoft.pollution.madrid.R;

import mainlistmenu.MainListMenuPresenter;

public class MainListMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private MainListMenuPresenter mPresenter;
    private Context mContext;

    private int[] mImages = new int[]{
            R.drawable.menu_city,
//            R.drawable.menu_region,
            R.drawable.menu_regulations,
            R.drawable.menu_pollutants
    };

    private int[] mTitles = new int[]{
            R.string.title_menu_madrid_city,
//            R.string.title_menu_madrid_region,
            R.string.title_menu_regulations,
            R.string.title_menu_pollutants
    };

    private int[] mIcons = new int[]{
            R.drawable.ic_location_on_white_24dp,
//            R.drawable.ic_location_on_white_24dp,
            R.drawable.ic_regulations_white_24dp,
            R.drawable.ic_pollutants_white_24dp
    };

    public MainListMenuAdapter(MainListMenuPresenter presenter, Context context){
        this.mPresenter = presenter;
        this.mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_main_menu_list, parent, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new MainListMenuItemHolder(view);
        } else {
            return new MainListMenuItemHolder(MaterialRippleLayout.on(view)
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(mContext.getResources().getColor(R.color.mp_grey_light))
                .rippleHover(true)
                .create());
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final MainListMenuItemHolder viewHolder = (MainListMenuItemHolder) holder;

        viewHolder.mTitleTv.setText(mContext.getString(mTitles[holder.getAdapterPosition()]));
        viewHolder.mIconIv.setImageResource(mIcons[holder.getAdapterPosition()]);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Drawable image = mContext.getDrawable(mImages[holder.getAdapterPosition()]);

            RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(mContext.getResources().getColor(R.color.mp_grey_light)), image, null);

            viewHolder.mImageView.setImageDrawable(rippleDrawable);


        } else {
            viewHolder.mImageView.setImageResource(mImages[holder.getAdapterPosition()]);
        }



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHandler(viewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImages.length;
    }

    private void onClickHandler(int position){

        switch (position){

            case 0:
                mPresenter.onMadridCityMapPressed();
                break;
//            case 1:
//                mPresenter.onMadridRegionMapPressed();
//                break;
            case 1:
                mPresenter.onRegulationsPressed();
                break;
            case 2:
                mPresenter.onPollutantsPressed();
                break;
        }

    }


    public static class MainListMenuItemHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        ImageView mIconIv;
        TextView mTitleTv;

        public MainListMenuItemHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.iv_menu_image);
            mIconIv = (ImageView) itemView.findViewById(R.id.iv_icon);
            mTitleTv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

}
