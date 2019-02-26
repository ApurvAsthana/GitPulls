package com.sunny.gitpulls.ui.splitview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunny.gitpulls.R;
import com.sunny.gitpulls.ui.splitview.FileDiffFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MyFileDiffRecyclerViewAdapter extends RecyclerView.Adapter<MyFileDiffRecyclerViewAdapter.ViewHolder> {

    private final List<FilesDiffItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyFileDiffRecyclerViewAdapter(List<FilesDiffItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_filediff, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.mItem = mValues.get(position);
        if(mValues.get(position).getFileName()==null) {
            int leftNum = mValues.get(position).getLineNumberFile1();
            int rightNum = mValues.get(position).getLineNumberFile2();
            String leftStr = mValues.get(position).getLineStringFile1();
            String rightStr = mValues.get(position).getLineStringFile2();
            if (leftStr!=null && leftStr.charAt(0)=='-'){
                holder.mleftStringView.setBackgroundColor(Color.rgb(255, 216, 209));
            }
            if(rightStr!=null && rightStr.charAt(0)=='+'){
                holder.mRightStringView.setBackgroundColor(Color.rgb(209, 255, 216));
            }
            holder.mleftNumView.setText(leftNum == -1 ? "" : leftNum + "");
            holder.mleftStringView.setText(leftStr == null ? "" : leftStr);
            holder.mRightNumView.setText(rightNum == -1 ? "" : rightNum + "");
            holder.mRightStringView.setText(rightStr == null ? "" : rightStr);
        }else{
            holder.fileHeader.setText(mValues.get(position).getFileName()==null?"":mValues.get(position).getFileName());
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mleftNumView;
        public final TextView mleftStringView;
        public final TextView mRightNumView;
        public final TextView mRightStringView;
        public final TextView fileHeader;
        public FilesDiffItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mleftNumView = (TextView) view.findViewById(R.id.left_num);
            mleftStringView = (TextView) view.findViewById(R.id.left_string);
            mRightNumView = (TextView) view.findViewById(R.id.right_num);
            mRightStringView = (TextView) view.findViewById(R.id.right_string);
            fileHeader = (TextView) view.findViewById(R.id.file_header);
        }
    }
}
