package com.example.rs;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class PartyListListener implements OnItemClickListener {
    // Our listener will contain a reference to the list of RSS Items
    // List item's reference
    List<PartyRssItem> listItems;
    // And a reference to a calling activity
    // Calling activity reference
    Activity activity;
    /** We will set those references in our constructor.*/
    public PartyListListener(List<PartyRssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
    }
 
    /** Start a browser with url from the rss item.*/
    @Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
        // We create an Intent which is going to display data
        Intent i = new Intent(view.getContext(),RSMembers.class);
        // We have to set data for our new Intent
        PartyRssItem sr = (PartyRssItem)adapter.getItemAtPosition(pos);
        String[] values = new String[]{"memparty","p",sr.getParty()};
        i.putExtra("values", values);
        // And start activity with our Intent
        activity.startActivity(i);
    }
}