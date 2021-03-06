package com.sunny.gitpulls.ui.prlists;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sunny.gitpulls.R;
import com.sunny.gitpulls.ui.prlists.PullRequestListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PullRequestItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_JSON_ARRAY_STRING = "json-array";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private String jsonArrayString="";
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PullRequestItemFragment() {
    }

    public static PullRequestItemFragment newInstance(int columnCount,String jsonArrayString) {
        PullRequestItemFragment fragment = new PullRequestItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(ARG_JSON_ARRAY_STRING,jsonArrayString);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            jsonArrayString = getArguments().getString(ARG_JSON_ARRAY_STRING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pullrequestitem_list, container, false);
        ArrayList<PullRequestListItem> items = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonArrayString);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                items.add(new PullRequestListItem(object.getInt("id"),object.getString("title"),object.getString("url"),object.getInt("number")));
            }
        } catch (JSONException e) {
            Toast.makeText(getContext(),"Error while parsing json",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyPullRequestItemRecyclerViewAdapter(items, mListener));
        }
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

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(PullRequestListItem item);
    }
}
