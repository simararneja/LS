package com.example.ls;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class SQLListListener implements OnItemClickListener {
    // Our listener will contain a reference to the list of RSS Items
    // List item's reference
    List<SQLRssItem> listItems;
    // And a reference to a calling activity
    // Calling activity reference
    Activity activity;
    /** We will set those references in our constructor.*/
    public SQLListListener(List<SQLRssItem> ListItems, Activity Activity) {
        this.listItems = ListItems;
        this.activity  = Activity;
    }
 
    /** Start a browser with url from the rss item.*/
    @Override
	public void onItemClick(AdapterView parent, View view, int pos, long id) {
        // We create an Intent which is going to display data
        Intent i = new Intent(Intent.ACTION_VIEW);
        // We have to set data for our new Intent
        String URL = listItems.get(pos).getlink();
        if(URL.contains(".pdf"))
        	URL = LoadItems.getPDFReaderLink()+URL;
        i.setData(Uri.parse(URL));
        // And start activity with our Intent
        activity.startActivity(i);
    }
}