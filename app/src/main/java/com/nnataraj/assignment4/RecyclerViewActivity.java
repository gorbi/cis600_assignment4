package com.nnataraj.assignment4;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nnataraj.assignment4.dummy.DummyContent;

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
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
