package com.greenlionsoft.pollution.madrid.ui.listadapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenlionsoft.pollution.madrid.R;

import java.util.ArrayList;

public class PollutantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NAME = 0;
    private static final int TYPE_CONTENT = 1;

    private Context mContext;
    private String[] mPollutantsNames;
    private String[] mPollutantsDescriptions;
    private String[] mPollutantsEffects;
    private ArrayList<RowWrapper> mRowWrappers;
    private LinearLayoutManager mLinearLayoutManager;


    public PollutantsAdapter(Context context, LinearLayoutManager linearLayoutManager) {
        this.mContext = context;

        mLinearLayoutManager = linearLayoutManager;

        mPollutantsNames = mContext.getResources().getStringArray(R.array.pollutants_names);
        mPollutantsDescriptions = mContext.getResources().getStringArray(R.array.pollutants_descriptions);
        mPollutantsEffects = mContext.getResources().getStringArray(R.array.pollutants_effects);

        mRowWrappers = new ArrayList<>();

        for (int i = 0; i < mPollutantsNames.length; i++) {
            mRowWrappers.add(new RowWrapper(TYPE_NAME, i));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        if (viewType == TYPE_NAME) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_pollutant_name, parent, false);
            return new PollutantNameItemHolder(view);

        } else if (viewType == TYPE_CONTENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_pollutant_content, parent, false);
            return new PollutantContentItemHolder(view);

        } else {
            throw new IllegalStateException("Incorrect viewType found");
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof PollutantNameItemHolder) {

            final PollutantNameItemHolder viewHolder = (PollutantNameItemHolder) holder;
            viewHolder.name.setText(mPollutantsNames[mRowWrappers.get(holder.getAdapterPosition()).pollutantIndex]);
            setColorBackgroundOfNameRow(viewHolder.name, holder.getAdapterPosition());

            viewHolder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.getAdapterPosition() == (mRowWrappers.size() - 1) || mRowWrappers.get(holder.getAdapterPosition() + 1).rowType == TYPE_NAME) {
                        //add content row for this pollutant
                        mRowWrappers.add(holder.getAdapterPosition() + 1, new RowWrapper(TYPE_CONTENT, mRowWrappers.get(holder.getAdapterPosition()).pollutantIndex));
                        notifyItemInserted(holder.getAdapterPosition() + 1);
                        mLinearLayoutManager.scrollToPositionWithOffset(holder.getAdapterPosition(), 0);
                    } else {
                        //remove content row for this pollutant
                        mRowWrappers.remove(holder.getAdapterPosition() + 1);
                        notifyItemRemoved(holder.getAdapterPosition() + 1);
                    }

                    setColorBackgroundOfNameRow(viewHolder.name, holder.getAdapterPosition());
                }
            });

        } else if (holder instanceof PollutantContentItemHolder) {
            PollutantContentItemHolder viewHolder = (PollutantContentItemHolder) holder;

            viewHolder.description.setText(mPollutantsDescriptions[mRowWrappers.get(holder.getAdapterPosition()).pollutantIndex]);
            viewHolder.effects.setText(mPollutantsEffects[mRowWrappers.get(holder.getAdapterPosition()).pollutantIndex]);
        }
    }

    @Override
    public int getItemCount() {
        return mRowWrappers.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mRowWrappers.get(position).rowType;
    }

    private void setColorBackgroundOfNameRow(TextView textView, int position) {

        if (position == (mRowWrappers.size() - 1) || mRowWrappers.get(position + 1).rowType == TYPE_NAME) {
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.mp_grey));
        } else {
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        }

    }

    static class PollutantNameItemHolder extends RecyclerView.ViewHolder {

        TextView name;

        public PollutantNameItemHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    static class PollutantContentItemHolder extends RecyclerView.ViewHolder {

        TextView description;
        TextView effects;

        public PollutantContentItemHolder(View itemView) {
            super(itemView);

            description = (TextView) itemView.findViewById(R.id.tv_description_content);
            effects = (TextView) itemView.findViewById(R.id.tv_effects_content);
        }
    }

    static class RowWrapper {

        int rowType;
        int pollutantIndex;

        public RowWrapper(int rowType, int pollutantIndex) {
            this.rowType = rowType;
            this.pollutantIndex = pollutantIndex;
        }
    }

}
