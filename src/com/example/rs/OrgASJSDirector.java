package com.example.rs;

import java.util.List;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class OrgASJSDirector extends Activity {

	public OrgASJSDirector local;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_org_asjsdirector);
		if (CompatibilityManager.isIceCreamSandwich()) 
		{
			BackCompatibility();
		}
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();

		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("AS - JS");

		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("Directors");
		spec2.setContent(R.id.tab2);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.getChildAt(0).setBackgroundColor(Color.rgb(247, 195, 0));
		local = this;
		LoadItems li = new LoadItems();
		GetFeeds(li.GetASJSUrl());
		GetDirFeeds(li.GetDirectorsUrl());
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void BackCompatibility()
	{
		ActionBar abc = getActionBar();
		abc.setHomeButtonEnabled(true);
		abc.setDisplayHomeAsUpEnabled(true);
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {       
		onBackPressed();
	    return true;
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.org_asjsdirector, menu);
		return true;
	}
	public void GetFeeds(String link)
	{
		GetRSSDataTask task = new GetRSSDataTask();
     	task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	public void GetDirFeeds(String link)
	{
		GetRSSDirDataTask task = new GetRSSDirDataTask();
     	task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	private class GetRSSDataTask extends AsyncTask<String, Void, List<OrgRssItem> > {
	    @Override
	    protected List<OrgRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("RssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            OrgRssReader rssReader = new OrgRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<OrgRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.lstASJS);
	        // Create a list adapter
	        final ArrayAdapter<OrgRssItem> adapter = new ArrayAdapter<OrgRssItem>(local,android.R.layout.simple_list_item_1, result);
	        //final customlistadpater_feeds listadap = new customlistadpater_feeds(local , R.layout.customlistadapter_feeds , result);
	        // Set list adapter for the ListView
	        lv.setAdapter(adapter);
	        //lv.setAdapter(listadap);
	        EditText inputSearch = (EditText)findViewById(R.id.txtSearchASJS);
	        inputSearch.addTextChangedListener(new TextWatcher() {
	            
	            @Override
	            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
	                // When user changed the Text
	                adapter.getFilter().filter(cs);
	            	//listadap.getFilter().filter(cs);
	            }
	             
	            @Override
	            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
	                    int arg3) {
	                // TODO Auto-generated method stub
	                 
	            }
	             
	            @Override
	            public void afterTextChanged(Editable arg0) {
	                // TODO Auto-generated method stub                          
	            }
	        });
	        // Set list view item click listener
	        //lv.setOnItemClickListener(new ListListener(result, local));
	        //HideMenu();
	        
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
	private class GetRSSDirDataTask extends AsyncTask<String, Void, List<OrgRssItem> > {
	    @Override
	    protected List<OrgRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("RssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            OrgRssReader rssReader = new OrgRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<OrgRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.lstDirectors);
	        // Create a list adapter
	        final ArrayAdapter<OrgRssItem> adapter = new ArrayAdapter<OrgRssItem>(local,android.R.layout.simple_list_item_1, result);
	        // Set list adapter for the ListView
	        lv.setAdapter(adapter);
	        EditText inputSearch = (EditText)findViewById(R.id.txtSearchDirector);
	        inputSearch.addTextChangedListener(new TextWatcher() {
	            
	            @Override
	            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
	                // When user changed the Text
	                adapter.getFilter().filter(cs);   
	            }
	             
	            @Override
	            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
	                    int arg3) {
	                // TODO Auto-generated method stub
	                 
	            }
	             
	            @Override
	            public void afterTextChanged(Editable arg0) {
	                // TODO Auto-generated method stub                          
	            }
	        });
	        // Set list view item click listener
	        //lv.setOnItemClickListener(new ListListener(result, local));
	        //HideMenu();
	        
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
}
