package com.nnataraj.assignment4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class RecyclerViewActivity extends AppCompatActivity implements MovieItemFragment.OnListFragmentInteractionListener {

    MovieItemFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new MovieItemFragment();
        transaction.replace(R.id.recycler_activity_main_container, fragment);
        transaction.commit();

    }

    @Override
    public void onClick(Map<String, ?> item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = DetailViewFragment.newInstance((HashMap<String, ?>) item);
        transaction.replace(R.id.recycler_activity_main_container, fragment);
        transaction.addToBackStack("store");
        transaction.commit();

    }

    @Override
    public void onLongClick(int position) {
        fragment.cloneMovie(position);
    }

    @Override
    public void onItemSelected(Map<String, ?> item) {
        Map i = (Map) item;
        i.put("selection",!((boolean)(Object)item.get("selection")));
    }
}
