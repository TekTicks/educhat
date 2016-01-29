package in.tekticks.educhat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import in.tekticks.educhat.R;
import in.tekticks.educhat.data.ContactsRowData;

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactsRowDataHolder> {

    private Context mContext;
    private ArrayList<ContactsRowData> mContactsRowDatas;
    private static ClickListener clickListener;

    public ContactsRecyclerViewAdapter(Context mContext, ArrayList<ContactsRowData> mContactsRowDatas) {
        this.mContext = mContext;
        this.mContactsRowDatas = mContactsRowDatas;
    }

    @Override
    public ContactsRowDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_recyclerview_row_layout, parent, false);
        ContactsRowDataHolder mContactsRowDataHolder = new ContactsRowDataHolder(view);
        return mContactsRowDataHolder;
    }

    @Override
    public void onBindViewHolder(ContactsRowDataHolder holder, int position) {
        holder.contactName.setText(mContactsRowDatas.get(position).getContactName());
        Picasso.with(mContext).load(mContactsRowDatas.get(position).getImageUrl()).into(holder.profilePic);
    }

    @Override
    public int getItemCount() {
        return mContactsRowDatas.size();
    }

    public void addItem(ContactsRowData mContactsRowData, int index) {
        mContactsRowDatas.add(mContactsRowData);
        notifyItemInserted(index);
    }


    public static class ContactsRowDataHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private CircleImageView profilePic;
        private TextView contactName;

        public ContactsRowDataHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            profilePic = (CircleImageView) itemView.findViewById(R.id.contacts_recycler_view_profile_pic);
            contactName = (TextView) itemView.findViewById(R.id.contacts_recycler_view_contact_name);
        }


        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ContactsRecyclerViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }


}
