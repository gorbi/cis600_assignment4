package com.nnataraj.assignment4;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

public class RecyclerViewActivity extends AppCompatActivity implements MovieItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MovieItemFragment fragment = new MovieItemFragment();
        transaction.replace(R.id.recycler_activity_main_container, fragment);
        transaction.commit();

    }

    @Override
    public void onListFragmentInteraction(Map<String, ?> item) {
        Log.d("Naga", "Clicked "+item.get("name"));
    }
}
