package in.tekticks.educhat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.tekticks.educhat.R;
import in.tekticks.educhat.data.NavigationDrawerChildItem;
import in.tekticks.educhat.data.NavigationDrawerGroupItem;


public class ExpandedListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<NavigationDrawerGroupItem> navigationDrawerGroupItems;


    public ExpandedListAdapter(Context context, ArrayList<NavigationDrawerGroupItem> navigationDrawerGroupItems) {
        this.context = context;
        this.navigationDrawerGroupItems = navigationDrawerGroupItems;
    }

    @Override
    public int getGroupCount() {
        return navigationDrawerGroupItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<NavigationDrawerChildItem> chList = navigationDrawerGroupItems.get(groupPosition).getItems();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return navigationDrawerGroupItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<NavigationDrawerChildItem> chList = navigationDrawerGroupItems.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ExpandableListView view=(ExpandableListView)parent;

        NavigationDrawerGroupItem navigationDrawerGroupItem = (NavigationDrawerGroupItem) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.menu_name);
        ImageView iv = (ImageView) convertView.findViewById(R.id.icon_menu);
        tv.setText(navigationDrawerGroupItem.getName());
        iv.setImageResource(navigationDrawerGroupItem.getImage());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        NavigationDrawerChildItem navigationDrawerChildItem = (NavigationDrawerChildItem) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }
        TextView child1 = (TextView) convertView.findViewById(R.id.child1);
        TextView child2 = (TextView) convertView.findViewById(R.id.child2);
        child1.setText(navigationDrawerChildItem.getChild1());
        child2.setText(navigationDrawerChildItem.getChild2());
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
