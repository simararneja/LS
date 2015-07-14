package com.example.ls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.loksabha.R;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class TodaysBirthday extends Activity {

    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_CONSTITUENCY = "artist";
    static final String KEY_PARTYNAME = "duration";
    static final String KEY_THUMB_URL = "thumb_url";
    static final String KEY_DOB = "dob";
    static final String KEY_EMAIL = "email";
    ListView list;
    LazyAdapter adapter;
    private TodaysBirthday local;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todays_birthday);
		local = this;
		if (CompatibilityManager.isIceCreamSandwich()) 
		{
			BackCompatibility();
		}
		
		GetTBFeeds("http://164.100.47.132/ipad_rssfeed/todaybdaylist.aspx");
		
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
		getMenuInflater().inflate(R.menu.todays_birthday, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {       
		onBackPressed();
	    return true;
    }
	
	
	public void GetTBFeeds(String feedURL)
	{
		GetTBRSSDataTask task = new GetTBRSSDataTask();
     	task.execute(feedURL);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	
	
	private class GetTBRSSDataTask extends AsyncTask<String, Void, List<TBRssItem> > {
	    @Override
	    protected List<TBRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("LoMRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            TBRssReader rssReader = new TBRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<TBRssItem> result) {
	        
	    	ArrayList<HashMap<String, String>> birthdayList = new ArrayList<HashMap<String, String>>();
	    	
	    	for (int i = 0; i < result.size(); i++) {
	            // creating new HashMap
	            HashMap<String, String> map = new HashMap<String, String>();
	            // adding each child node to HashMap key => value
	            if(result.get(i).getMemberName() != null){
		            map.put(KEY_ID, "1");
		            map.put(KEY_TITLE, result.get(i).getMemberName());
		            map.put(KEY_PARTYNAME, result.get(i).getPartyName());
		            map.put(KEY_CONSTITUENCY, result.get(i).getConstituencyAndState());
		            map.put(KEY_THUMB_URL, result.get(i).getPictureURL());
		            map.put(KEY_DOB, result.get(i).getDOB());
		            map.put(KEY_EMAIL, result.get(i).getEmailID() );
		            // adding HashList to ArrayList
		            birthdayList.add(map);
	            }
	        }
	 
	        list=(ListView)findViewById(R.id.lstBday);
	 
	        // Getting adapter by passing xml data ArrayList
	        adapter = new LazyAdapter(local, birthdayList);
	        list.setAdapter(adapter);
	 
	    }
	}
}
