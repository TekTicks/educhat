package in.tekticks.educhat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.tekticks.educhat.R;
import in.tekticks.educhat.data.ActiveChatsRowData;

public class ActiveChatsRecyclerViewAdapter extends RecyclerView.Adapter<ActiveChatsRecyclerViewAdapter.ActiveChatsRowDataHolder> {

    private Context context;
    private static ClickListener clickListener;
    public ActiveChatsRecyclerViewAdapter(ArrayList<ActiveChatsRowData> activeChatsRowDataList,Context context) {
        this.activeChatsRowDataList = activeChatsRowDataList;
        this.context=context;
    }

    private ArrayList<ActiveChatsRowData> activeChatsRowDataList;


    @Override
    public ActiveChatsRowDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.active_chats_recyclerview_row_layout,parent,false);
        ActiveChatsRowDataHolder mActiveChatsRowDataHolder=new ActiveChatsRowDataHolder(view);
        return mActiveChatsRowDataHolder;
    }

    @Override
    public void onBindViewHolder(ActiveChatsRowDataHolder holder, int position) {
        holder.contactMessage.setText(activeChatsRowDataList.get(position).getContactMessage());
        holder.contactName.setText(activeChatsRowDataList.get(position).getContactName());
        holder.chatNoNotification.setText(activeChatsRowDataList.get(position).getChatNoNotification());
        holder.chatDate.setText(activeChatsRowDataList.get(position).getChatDate());
        Picasso.with(context).load(activeChatsRowDataList.get(position).getProfileImageUrl()).into(holder.profilePic);
    }

    public void addItem(ActiveChatsRowData activeChatsRowData,int index){
        activeChatsRowDataList.add(activeChatsRowData);
        notifyItemInserted(index);
    }

    @Override
    public int getItemCount() {
        return activeChatsRowDataList.size();
    }


    public static class ActiveChatsRowDataHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        private CircleImageView profilePic;
        private TextView contactName,contactMessage,chatDate,chatNoNotification;


        public ActiveChatsRowDataHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            profilePic=(CircleImageView)itemView.findViewById(R.id.profile_image);
            contactMessage=(TextView)itemView.findViewById(R.id.contact_message);
            contactName=(TextView)itemView.findViewById(R.id.contact_name);
            chatDate=(TextView)itemView.findViewById(R.id.chat_date);
            chatNoNotification=(TextView)itemView.findViewById(R.id.chat_no_notification);
        }


        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(),v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(),v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ActiveChatsRecyclerViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

}
