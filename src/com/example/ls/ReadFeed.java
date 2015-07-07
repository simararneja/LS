package com.example.ls;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class ReadFeed extends Activity {
	public int itemid;
	private ReadFeed local;
	ReadFeed(int item)
	{
		this.itemid = item;
	}
	public void GetFeeds()
	{
		GetRSSDataTask task = new GetRSSDataTask();
        
        // Start download RSS task
        task.execute( "http://164.100.47.5/IPADFeeds/Bulletin1.aspx");
         
        // Debug the thread name
        Log.d("RssReader", Thread.currentThread().getName());
	}
	public List<RssItem> ReadFeeds()
	{
		try {
            // Create RSS reader
            RssReader rssReader = new RssReader("http://164.100.47.5/IPADFeeds/Bulletin1.aspx");
         
            // Parse RSS, get items
            List<RssItem> ri = rssReader.getItems();
            return ri;
         
        } catch (Exception e) {
            return null;
        }
	}
	private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem> > {
	    @Override
	    protected List<RssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("RssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            RssReader rssReader = new RssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<RssItem> result) {
	        // Get a ListView from main view
	        //ListView itcItems = (ListView)findViewById(R.id.listView1);
	                     
	        // Create a list adapter
	        //ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local,android.R.layout.simple_list_item_1, result);
	        // Set list adapter for the ListView
	        //itcItems.setAdapter(adapter);
	                     
	        // Set list view item click listener
	        //itcItems.setOnItemClickListener(new ListListener(result, local));
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
}
