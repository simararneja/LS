package com.example.ls;

import java.util.List;

import com.example.loksabha.R;

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

public class OrganisationASJSDirector extends Activity {

	public OrganisationASJSDirector local;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_org_asjsdirector);
		if (CompatibilityManager.isIceCreamSandwich()) 
		{
			BackCompatibility();
		}
		
		LoadItems li = new LoadItems();
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();
		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("SCCY-AS - JS");
		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("Directors");
		spec2.setContent(R.id.tab2);
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.getChildAt(0).setBackgroundColor(Color.rgb(247, 195, 0));
		local = this;
		
		getASJSFeeds(li.getASJSUrl());
		getDirectorFeeds(li.getDirectorsUrl());
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
	public void getASJSFeeds(String link)
	{
		GetASJSRSSDataTask task = new GetASJSRSSDataTask();
     	task.execute(link);
        Log.d("ASJSReader", Thread.currentThread().getName());
	}
	public void getDirectorFeeds(String link)
	{
		GetRSSDirectorDataTask task = new GetRSSDirectorDataTask();
     	task.execute(link);
        Log.d("DirectorRssReader", Thread.currentThread().getName());
	}
	private class GetASJSRSSDataTask extends AsyncTask<String, Void, List<OrgASJSRssItem> > {
	    @Override
	    protected List<OrgASJSRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("ASJSAsyncask", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            OrgASJSRssReader rssReader = new OrgASJSRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("ASJSAsyncask", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<OrgASJSRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.lstASJS);
	        // Create a list adapter
	        final ArrayAdapter<OrgASJSRssItem> adapter = new ArrayAdapter<OrgASJSRssItem>(local,android.R.layout.simple_list_item_1, result);
	        lv.setAdapter(adapter);
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
	    }
	}
	private class GetRSSDirectorDataTask extends AsyncTask<String, Void, List<OrgASJSRssItem> > {
	    @Override
	    protected List<OrgASJSRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("RSSDirectorDataTask", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            OrgASJSRssReader rssReader = new OrgASJSRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RSSDirectorDataTask", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<OrgASJSRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.lstDirectors);
	        // Create a list adapter
	        final ArrayAdapter<OrgASJSRssItem> adapter = new ArrayAdapter<OrgASJSRssItem>(local,android.R.layout.simple_list_item_1, result);
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
	        
	    }
	}
}
