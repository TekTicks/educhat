package in.tekticks.educhat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.tekticks.educhat.R;
import in.tekticks.educhat.adapters.ContactsRecyclerViewAdapter;
import in.tekticks.educhat.data.ContactsRowData;
import in.tekticks.educhat.utils.DividerItemDecoration;


public class ContactsFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ContactsRecyclerViewAdapter mContactsRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.active_chats_fragments, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.active_chats_recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mContactsRecyclerViewAdapter = new ContactsRecyclerViewAdapter(getActivity(), getDataSet());
        mContactsRecyclerViewAdapter.setOnItemClickListener(new ContactsRecyclerViewAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        mRecyclerView.setAdapter(mContactsRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ArrayList<ContactsRowData> getDataSet() {
        ArrayList results = new ArrayList<ContactsRowData>();
        for (int i = 0; i < 3; i++) {
            ContactsRowData rowData = new ContactsRowData("Vishnu Nemlawala", "http://www.lerendoseren.nl/assets/no_profile-e7da098727586478701d3f16def38b13.jpg");
            results.add(i, rowData);
        }
        return results;
    }

}
