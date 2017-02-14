package com.nnataraj.assignment4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MovieItemFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private static MovieData movieData = new MovieData();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MovieItemFragment newInstance() {
        return new MovieItemFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movieitem_list, container, false);

        // Set the adapter
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_activity_main_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        final MyMovieItemRecyclerViewAdapter itemRecyclerViewAdapter = new MyMovieItemRecyclerViewAdapter(mListener, movieData.getMoviesList());
        recyclerView.setAdapter(itemRecyclerViewAdapter);

        Button selectAll = (Button) view.findViewById(R.id.select_all_button);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = itemRecyclerViewAdapter.getItemCount();
                for (int i = 0; i < count; i++) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(i);
                    item.put("selection", true);
                }
                itemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        Button clearAll = (Button) view.findViewById(R.id.clear_all_button);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = itemRecyclerViewAdapter.getItemCount();
                for (int i = 0; i < count; i++) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(i);
                    item.put("selection", false);
                }
                itemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        Button delete = (Button) view.findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = itemRecyclerViewAdapter.getItemCount();
                for (int i = 0; i < count; i++) {
                    HashMap<String, Boolean> item = (HashMap<String, Boolean>) movieData.getItem(i);
                    if (item != null && item.get("selection")) {
                        movieData.removeItem(i);
                        itemRecyclerViewAdapter.notifyItemRemoved(i);
                    }
                }
            }
        });

        class MovieComparator implements Comparator {
            @Override
            public int compare(Object o1, Object o2) {
                Integer obj1Value = Integer.parseInt((String) ((HashMap) o1).get("year"));
                Integer obj2Value = Integer.parseInt((String) ((HashMap) o2).get("year"));
                return obj1Value.compareTo(obj2Value);
            }
        }

        Button sort = (Button) view.findViewById(R.id.sort_button);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(movieData.getMoviesList(), new MovieComparator());
                itemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Map<String, ?> item);

        void onItemSelected(Map<String, ?> item);
    }
}
