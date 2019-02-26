package com.sunny.gitpulls.ui.splitview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.sunny.gitpulls.R;
import com.sunny.gitpulls.ui.HomeActivity;
import com.sunny.gitpulls.ui.prlists.PullRequestItemFragment;

public class SplitViewActivity extends AppCompatActivity implements FileDiffFragment.OnListFragmentInteractionListener{

    private static final String TAG = "SplitViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_split_view);
        Fragment fragment = FileDiffFragment.newInstance(1);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.diff_fragment_container,fragment).commit();
    }

    @Override
    public void onListFragmentInteraction(FilesDiffItem item) {
        Log.d(TAG,"SplitViewActivity click");
    }
}
