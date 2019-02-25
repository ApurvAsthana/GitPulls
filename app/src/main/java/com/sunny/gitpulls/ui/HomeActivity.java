package com.sunny.gitpulls.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sunny.gitpulls.R;
import com.sunny.gitpulls.communication.MakeGetRequest;
import com.sunny.gitpulls.communication.OnHttpConnListener;
import com.sunny.gitpulls.utils.EndpointDetails;

import java.net.HttpURLConnection;

public class HomeActivity extends AppCompatActivity implements OnHttpConnListener{

    private static final String TAG = "HomeActivity";
    Button fetchButton;
    EditText ownerName,repoName;
    HomeActivity activity;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = this;
        fetchButton = (Button) findViewById(R.id.fetch_repos);
        ownerName = (EditText) findViewById(R.id.owner_name);
        repoName = (EditText) findViewById(R.id.repo_name);
        progressBar = (ProgressBar) findViewById(R.id.loading_blocker);
        fetchButton.setOnClickListener(fetchListener);
    }

    private View.OnClickListener fetchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String owner = ownerName.getText().toString();
            String repo = repoName.getText().toString();
            if(owner.isEmpty() || repo.isEmpty()){
                Toast.makeText(activity,"Either or both of owner, repo values empty, using default(facebook/react)",Toast.LENGTH_SHORT).show();
            }else{
                EndpointDetails.setOwner(owner);
                EndpointDetails.setRepo(repo);
            }
            fetchButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            new MakeGetRequest(EndpointDetails.getRequestEndpoint(),activity).execute();
        }
    };

    @Override
    public void onUpdate(String msg, int mesCode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                fetchButton.setVisibility(View.VISIBLE);
            }
        });
        if(mesCode!= HttpURLConnection.HTTP_OK) {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        }else{
            Log.d(TAG,"Response received----------");
            Log.d(TAG,"Result is : "+msg);
        }
    }
}
