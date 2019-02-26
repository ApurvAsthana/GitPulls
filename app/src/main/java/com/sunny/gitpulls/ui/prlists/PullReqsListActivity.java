package com.sunny.gitpulls.ui.prlists;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sunny.gitpulls.R;
import com.sunny.gitpulls.communication.MakeGetRequest;
import com.sunny.gitpulls.communication.OnHttpConnListener;
import com.sunny.gitpulls.ui.HomeActivity;
import com.sunny.gitpulls.ui.splitview.SplitViewActivity;
import com.sunny.gitpulls.utils.EndpointDetails;

import java.net.HttpURLConnection;

public class PullReqsListActivity extends AppCompatActivity implements PullRequestItemFragment.OnListFragmentInteractionListener,OnHttpConnListener{

    private PullReqsListActivity mActivity;
    private static final String TAG = "PullReqsListActivity";
    public static String responseFromGetApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"PullReqsListActivity onCreate");
        //getActionBar().setTitle(EndpointDetails.getOwner()+"/"+EndpointDetails.getRepo()+" List of open Pull Requests");
        setContentView(R.layout.activity_pull_reqs_list);
        mActivity = this;
        String msg = HomeActivity.responseFromApi;
        Fragment fragment = PullRequestItemFragment.newInstance(1,msg);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.list_frag_container,fragment).commit();
    }

    @Override
    public void onListFragmentInteraction(PullRequestListItem item) {
        Toast.makeText(mActivity,item.number+" Clicked",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"List Fragment interaction PullReqsListActivity");
        new MakeGetRequest(item.getUrl()+"/files",mActivity).execute();
    }

    @Override
    public void onUpdate(final String msg, int mesCode) {
        if(mesCode!= HttpURLConnection.HTTP_OK) {
            Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
        }else{
            Log.d(TAG,"Response received----------");
            Log.d(TAG,"Result is : "+msg);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    responseFromGetApi = msg;
                    Intent i = new Intent();
                    i.setClass(mActivity, SplitViewActivity.class);
                    startActivity(i);
                    mActivity.finish();
                }
            });
        }
    }
}
