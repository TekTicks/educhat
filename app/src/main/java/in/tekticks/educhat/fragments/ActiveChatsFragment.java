package in.tekticks.educhat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.tekticks.educhat.R;
import in.tekticks.educhat.adapters.ActiveChatsRecyclerViewAdapter;
import in.tekticks.educhat.data.ActiveChatsRowData;
import in.tekticks.educhat.utils.Constants;
import in.tekticks.educhat.utils.DividerItemDecoration;

public class ActiveChatsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ActiveChatsRecyclerViewAdapter mActiveChatsRecyclerViewAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.active_chats_fragments, container, false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.active_chats_recycler_view);
        layoutManager=new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mActiveChatsRecyclerViewAdapter=new ActiveChatsRecyclerViewAdapter(getDataSet(),getActivity());
        mActiveChatsRecyclerViewAdapter.setOnItemClickListener(new ActiveChatsRecyclerViewAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.v(Constants.LOG, "Clicked on :" + position);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        mRecyclerView.setAdapter(mActiveChatsRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ArrayList<ActiveChatsRowData> getDataSet(){
        ArrayList results=new ArrayList<ActiveChatsRowData>();
        for (int i=0;i<3;i++){
            ActiveChatsRowData mChatsRowData=new ActiveChatsRowData(
                    "http://www.lerendoseren.nl/assets/no_profile-e7da098727586478701d3f16def38b13.jpg"
                    ,"Will smith","Hey how are u?","Today","4");
            results.add(i,mChatsRowData);
        }
        return results;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
