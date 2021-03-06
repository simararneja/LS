package com.example.ls;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class LoMListListener implements OnItemClickListener {
    // Our listener will contain a reference to the list of RSS Items
    // List item's reference
    List<LoMRssItem> listItems;
    // And a reference to a calling activity
    // Calling activity reference
    Activity activity;
    /** We will set those references in our constructor.*/
    public LoMListListener(List<LoMRssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
    }
    /** Start a browser with url from the rss item.*/
    @Override
	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		// TODO Auto-generated method stub
        //Intent i = new Intent(v.getContext(),MemberDetails.class);
    	Intent i = new Intent(v.getContext(),TabMemberDetails.class);
    	LoMRssItem lri = (LoMRssItem) adapter.getItemAtPosition(pos);
    	//Intent i = new Intent(v.getContext(),WebSite.class);
        //i.putExtra("MP_Code", listItems.get(pos).getMPCode());
    	i.putExtra("MP_Code", lri.getMPCode());
        activity.startActivity(i);
	}
}