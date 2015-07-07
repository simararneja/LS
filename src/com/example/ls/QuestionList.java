package com.example.ls;

import java.util.List;

import com.example.rs.R;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class QuestionList extends Activity {

	private QuestionList local;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_list);
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();
		if (CompatibilityManager.isIceCreamSandwich()) 
		{
			BackCompatibility();
		}
		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Starred Questions");

		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("Un-Starred Questions");
		spec2.setContent(R.id.tab2);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.getChildAt(0).setBackgroundColor(Color.rgb(247, 195, 0));
		local = this;
		LoadItems li = new LoadItems();
		GetSQLFeeds(li.GetStarredQuestionsURL());
		GetUQLFeeds(li.GetUnStarredQuestionsURL());
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void BackCompatibility()
	{
		ActionBar abc = getActionBar();
		abc.setHomeButtonEnabled(true);
		abc.setDisplayHomeAsUpEnabled(true);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_list, menu);
		return true;
	}
	public void GetSQLFeeds(String link)
	{
		GetSQLRSSDataTask task = new GetSQLRSSDataTask();
     	task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {       
		onBackPressed();
	    return true;
    }
	public void GetUQLFeeds(String link)
	{
		GetUQLRSSDataTask task = new GetUQLRSSDataTask();
     	task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	private class GetSQLRSSDataTask extends AsyncTask<String, Void, List<SQLRssItem> > {
	    @Override
	    protected List<SQLRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("LoMRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            SQLRssReader rssReader = new SQLRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<SQLRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.lstStarredQues);
	                     
	        // Create a list adapter
	        ArrayAdapter<SQLRssItem> adapter = new ArrayAdapter<SQLRssItem>(local,android.R.layout.simple_list_item_1, result);
	        // Set list adapter for the ListView
	        lv.setAdapter(adapter);
	                     
	        // Set list view item click listener
	        lv.setOnItemClickListener(new SQLListListener(result, local));
	        
	        //HideMenu();
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
	private class GetUQLRSSDataTask extends AsyncTask<String, Void, List<UQLRssItem> > {
	    @Override
	    protected List<UQLRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("LoMRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            UQLRssReader rssReader = new UQLRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<UQLRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.lstUnStarredQues);
	                     
	        // Create a list adapter
	        ArrayAdapter<UQLRssItem> adapter = new ArrayAdapter<UQLRssItem>(local,android.R.layout.simple_list_item_1, result);
	        // Set list adapter for the ListView
	        lv.setAdapter(adapter);
	                     
	        // Set list view item click listener
	        lv.setOnItemClickListener(new UQLListListener(result, local));
	        
	        //HideMenu();
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
	
}
