package com.sunny.gitpulls.ui.prlists;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sunny.gitpulls.R;
import com.sunny.gitpulls.ui.HomeActivity;
import com.sunny.gitpulls.utils.EndpointDetails;

public class PullReqsListActivity extends AppCompatActivity implements PullRequestItemFragment.OnListFragmentInteractionListener{

    private Context mContext;
    private static final String TAG = "PullReqsListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"PullReqsListActivity onCreate");
        //getActionBar().setTitle(EndpointDetails.getOwner()+"/"+EndpointDetails.getRepo()+" List of open Pull Requests");
        setContentView(R.layout.activity_pull_reqs_list);
        mContext = this;
        String msg = HomeActivity.responseFromApi;
        Fragment fragment = PullRequestItemFragment.newInstance(1,msg);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.list_frag_container,fragment).commit();
    }

    @Override
    public void onListFragmentInteraction(PullRequestListItem item) {
        Toast.makeText(mContext,item.number+" Clicked",Toast.LENGTH_SHORT).show();
    }
}
