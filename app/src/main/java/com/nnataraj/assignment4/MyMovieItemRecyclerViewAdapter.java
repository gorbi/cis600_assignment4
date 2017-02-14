package com.nnataraj.assignment4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nnataraj.assignment4.MovieItemFragment.OnListFragmentInteractionListener;

import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a movie item and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMovieItemRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieItemRecyclerViewAdapter.ViewHolder> {

    private final MovieData movieData;
    private final OnListFragmentInteractionListener mListener;

    public MyMovieItemRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
        movieData = new MovieData();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movieitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = movieData.getItem(position);
        holder.mIdView.setText((String)movieData.getItem(position).get("name"));
        holder.mContentView.setText((String)movieData.getItem(position).get("year"));

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
        return movieData.getSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Map<String, ?> mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
