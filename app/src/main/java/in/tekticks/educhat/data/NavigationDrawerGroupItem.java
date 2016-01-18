package in.tekticks.educhat.data;

import java.util.ArrayList;

import in.tekticks.educhat.data.NavigationDrawerChildItem;

/**
 * Created by Admin on 07-01-2016.
 */
public class NavigationDrawerGroupItem {

    private String Name;
    private int Image;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public ArrayList<NavigationDrawerChildItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<NavigationDrawerChildItem> items) {
        Items = items;
    }

    private ArrayList<NavigationDrawerChildItem> Items;
}
