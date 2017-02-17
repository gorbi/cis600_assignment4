package com.nnataraj.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FrontPageActivity extends AppCompatActivity {

    int mCurCheckPosition = 0;

    private static Fragment getFragment(int mCurCheckPosition) {
        switch (mCurCheckPosition) {
            case 1:
                return new AboutMeFragment();
            default:
                return new FrontPageActivityFragment();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice");
        } else {
            mCurCheckPosition = 0;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, getFragment(mCurCheckPosition))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_front_page, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_about_me:
                onClickAboutMe(null);
                return true;
            case R.id.action_task_1:
                onClickTask1(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickAboutMe(View view) {
        mCurCheckPosition = 1;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, getFragment(mCurCheckPosition))
                .addToBackStack("store")
                .commit();
    }

    public void onClickTask1(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mCurCheckPosition = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, getFragment(mCurCheckPosition))
                .commit();
    }
}
