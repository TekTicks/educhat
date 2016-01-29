package in.tekticks.educhat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

import in.tekticks.educhat.R;
import in.tekticks.educhat.adapters.ExpandedListAdapter;
import in.tekticks.educhat.adapters.ViewPagerAdapter;
import in.tekticks.educhat.data.NavigationDrawerChildItem;
import in.tekticks.educhat.data.NavigationDrawerGroupItem;
import in.tekticks.educhat.fragments.ActiveChatsFragment;
import in.tekticks.educhat.fragments.ContactsFragment;
import in.tekticks.educhat.utils.Constants;

public class MainActivity extends AppCompatActivity {
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private ExpandedListAdapter ExpAdapter;
    private ArrayList<NavigationDrawerGroupItem> ExpListItems;
    private ExpandableListView ExpandList;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    PopupWindow verifyPhonePopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ExpandList = (ExpandableListView) findViewById(R.id.expandedListView);

        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandedListAdapter(MainActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
        ExpandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (groupPosition == 1)
                    return false;
                else
                    return true;
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.main_page_view_pager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.main_page_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_drawer);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.setHomeAsUpIndicator(R.drawable.ic_navigation_drawer);


        mDrawerLayout.setDrawerListener(toggle);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ActiveChatsFragment(), "Active Chats");
        adapter.addFragment(new ContactsFragment(), "Contacts");
        mViewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:


                return true;
            case R.id.action_status:
                Intent shareStatusActivity = new Intent(this, ShareStatusActivity.class);
                startActivity(shareStatusActivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<NavigationDrawerGroupItem> SetStandardGroups() {
        ArrayList<NavigationDrawerGroupItem> groupItems = new ArrayList<NavigationDrawerGroupItem>();
        ArrayList<NavigationDrawerChildItem> childItems = new ArrayList<NavigationDrawerChildItem>();
        int menu_size = 5;
        int sub_menu = 4;

        for (int i = 0; i < sub_menu; i++) {
            NavigationDrawerChildItem navigationDrawerChildItem = new NavigationDrawerChildItem();
            navigationDrawerChildItem.setChild1(Constants.sub_name[i]);
            navigationDrawerChildItem.setChild2(Constants.sub_name_text[i]);
            childItems.add(navigationDrawerChildItem);
        }
        for (int i = 0; i < menu_size; i++) {
            if (i == 1) {
                NavigationDrawerGroupItem grp = new NavigationDrawerGroupItem();
                grp.setImage(Constants.images[i]);
                grp.setName(Constants.group_names[i]);
                grp.setItems(childItems);
                groupItems.add(grp);
            } else {
                NavigationDrawerGroupItem grp = new NavigationDrawerGroupItem();
                grp.setImage(Constants.images[i]);
                grp.setName(Constants.group_names[i]);
                groupItems.add(grp);
            }
        }
        return groupItems;
    }

}
