package com.example.rs;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ComMainListListener implements OnItemClickListener {
    // Our listener will contain a reference to the list of RSS Items
    // List item's reference
    // And a reference to a calling activity
    // Calling activity reference
    Activity activity;
    /** We will set those references in our constructor.*/
    public ComMainListListener( Activity anActivity) {
        activity  = anActivity;
    }
 
    /** Start a browser with url from the rss item.*/
    @Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
        // We create an Intent which is going to display data
        Intent i = new Intent(view.getContext(),LokSabhaMembers.class);
        // We have to set data for our new Intent
        LoadItems li = new LoadItems();
        String[] values = new String[]{"comDet",String.valueOf(pos),li.comShortNames[pos]};
        i.putExtra("values", values);
        // And start activity with our Intent
        activity.startActivity(i);
    }
}