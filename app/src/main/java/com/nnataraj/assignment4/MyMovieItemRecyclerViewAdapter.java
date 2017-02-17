package com.nnataraj.assignment4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nnataraj.assignment4.MovieItemFragment.OnListFragmentInteractionListener;

import java.util.List;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a movie item and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMovieItemRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieItemRecyclerViewAdapter.ViewHolder> {

    private List<Map<String, ?>> movieData;
    private final OnListFragmentInteractionListener mListener;

    public MyMovieItemRecyclerViewAdapter(OnListFragmentInteractionListener listener, List<Map<String, ?>> movieData) {
        mListener = listener;
        this.movieData = movieData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movieitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = movieData.get(position);
        holder.mTitle.setText((String) holder.mItem.get("name"));
        holder.mDescription.setText((String) holder.mItem.get("description"));
        holder.mIcon.setImageResource((int) (Object) holder.mItem.get("image"));
        holder.mCheckBox.setChecked((boolean) (Object) holder.mItem.get("selection"));
        holder.mYear.setText((String) holder.mItem.get("year"));
        holder.mRatingBar.setRating(((float) (double) (Object) holder.mItem.get("rating")) / 2f);

        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemSelected(holder.mItem);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onLongClick(position);
                }
                return true;
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDescription;
        public final ImageView mIcon;
        public final CheckBox mCheckBox;
        public final TextView mYear;
        public final RatingBar mRatingBar;
        public Map<String, ?> mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.title);
            mDescription = (TextView) view.findViewById(R.id.description);
            mIcon = (ImageView) view.findViewById(R.id.movie_icon);
            mCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
            mYear = (TextView) view.findViewById(R.id.year);
            mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        }
    }
}
