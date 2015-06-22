package com.example.rs;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class StatesListListener implements OnItemClickListener {
    // Our listener will contain a reference to the list of RSS Items
    // List item's reference
    List<StatesRssItem> listItems;
    // And a reference to a calling activity
    // Calling activity reference
    Activity activity;
    /** We will set those references in our constructor.*/
    public StatesListListener(List<StatesRssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
    }
 
    /** Start a browser with url from the rss item.*/
    @Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
        // We create an Intent which is going to display data
        Intent i = new Intent(view.getContext(),LokSabhaMembers.class);
        // We have to set data for our new Intent
        StatesRssItem sr = (StatesRssItem)adapter.getItemAtPosition(pos);
        String[] values = new String[]{"memstate","s",sr.getstates()};
        i.putExtra("values", values);
        // And start activity with our Intent
        activity.startActivity(i);
    }
}