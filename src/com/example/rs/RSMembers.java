package com.example.rs;

import java.util.List;

import android.R.integer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class RSMembers extends Activity {
	private Intent i;
	private RSMembers local;
	private String menutype;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rsmembers);
		local = this;
		
		if (CompatibilityManager.isIceCreamSandwich()) 
		{
			BackCompatibility();
		}
		i = getIntent();
		Bundle values = i.getExtras();
		String[] strvalues = values.getStringArray("values");
		LoadItems li = new LoadItems();
		ListView lv = (ListView)findViewById(R.id.listFeeds);
		String urlstart = li.urlStart;
		menutype = "n";
		TextView tv = (TextView)findViewById(R.id.product_label);
		if(strvalues[0].equalsIgnoreCase("members"))
		{
			if(strvalues[1].equalsIgnoreCase("a"))
				GetLoMFeeds(urlstart+"all_members.aspx");
			else if(strvalues[1].equalsIgnoreCase("n"))
			{
				GetLoMFeeds(urlstart+"nominatedMemberList.aspx");
				tv.setText("Nominated Members");
			}
			else if(strvalues[1].equalsIgnoreCase("c"))
			{
				GetLoMFeeds(urlstart+"ConcilOfMinisters.aspx");
				tv.setText("Council Of Ministers");
			}
			else if(strvalues[1].equalsIgnoreCase("w"))
				{GetLoMFeeds(urlstart+"WomenMembersList.aspx");
				tv.setText("Women Members");
				}
			else if(strvalues[1].equalsIgnoreCase("b"))
			{
				GetLoMFeeds(urlstart+"janmonth.aspx?monthname=january");
				menutype = "b";
			}
			else if(strvalues[1].equalsIgnoreCase("p"))
			{	GetLoMFeeds(urlstart+"DetailPartyWiseList.aspx?partyID=23");
				menutype = "p";
			}
			else if(strvalues[1].equalsIgnoreCase("s"))
			{	
				GetLoMFeeds(urlstart+"DetailstateWiseList.aspx?StateName=Andhra Pradesh");
				menutype = "s";
			}
		}
		else if(strvalues[0].equalsIgnoreCase("states"))
		{
			tv.setText("State wise Member List");
			GetStatesFeeds(urlstart+"stateWiseList.aspx");
		}
		else if(strvalues[0].equalsIgnoreCase("party"))
		{
			tv.setText("Party wise Member List");
			GetPartyFeeds(urlstart+"PartyWiseList.aspx");
		}
		else if(strvalues[0].equalsIgnoreCase("bday"))
		{
			tv.setText("Birthday wise Member List");
			ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,li.months);
			lv.setAdapter(aa);
			lv.setOnItemClickListener(new BdayListListener(local));
		}
		else if(strvalues[0].equalsIgnoreCase("memstate"))
		{
			GetLoMFeeds(urlstart+"DetailstateWiseList.aspx?StateName="+strvalues[2]);
		}
		else if(strvalues[0].equalsIgnoreCase("memparty"))
		{
			GetLoMFeeds(urlstart+"DetailPartyWiseList.aspx?partyID="+strvalues[2]);
		}
		else if(strvalues[0].equalsIgnoreCase("membday"))
		{
			//Toast.makeText(this, urlstart+"MonthwiseMembersBday.aspx?monthname="+strvalues[2] , Toast.LENGTH_LONG).show();
			GetBLoMFeeds(urlstart+"MonthwiseMembersBday.aspx?monthname="+strvalues[2]);
		}
		else if(strvalues[0].equalsIgnoreCase("org"))
		{
			//secretariat
			
		}
		else if(strvalues[0].equalsIgnoreCase("comMain"))
		{
			tv.setText("Committees");
			ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,li.comtypes);
			lv.setAdapter(aa);
			lv.setOnItemClickListener(new ComMainListListener(local));
		}
		else if(strvalues[0].equalsIgnoreCase("comDet"))
		{
			tv.setText(li.comtypes[Integer.parseInt(strvalues[1])]);
			GetCommMem(li.urlStart + "CommitteeMembershipall.aspx?comm="+strvalues[2]);
		}
		else if(strvalues[0].equalsIgnoreCase("comName"))
		{
			tv.setText(strvalues[1]);
			
		}
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
		getMenuInflater().inflate(R.menu.rsmembers, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {       
		onBackPressed();
	    return true;
    }
	public void GetLoMFeeds(String link)
	{
		GetLoMRSSDataTask task = new GetLoMRSSDataTask();
     	task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	public void GetStatesFeeds(String link)
	{
		GetStatesRSSDataTask task = new GetStatesRSSDataTask();
     	task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	public void GetPartyFeeds(String link)
	{
		GetPartyRSSDataTask task = new GetPartyRSSDataTask();
     	task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	public void GetBLoMFeeds(String link)
	{
		GetBLoMRSSDataTask task = new GetBLoMRSSDataTask();
     	task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	public void GetCommMem(String link)
	{
		GetCommMemRSSDataTask task = new GetCommMemRSSDataTask();
     	task.execute(link);
        Log.d("RssReader", Thread.currentThread().getName());
	}
	private class GetLoMRSSDataTask extends AsyncTask<String, Void, List<LoMRssItem> > {
	    @Override
	    protected List<LoMRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("LoMRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            LoMRssReader rssReader = new LoMRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<LoMRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.listFeeds);
	                     
	        // Create a list adapter
	        final ArrayAdapter<LoMRssItem> adapter = new ArrayAdapter<LoMRssItem>(local,android.R.layout.simple_list_item_1, result);
	        // Set list adapter for the ListView
	        lv.setAdapter(adapter);
	        EditText inputSearch = (EditText)findViewById(R.id.txtSearchList);
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
	        lv.setOnItemClickListener(new LoMListListener(result, local));
	        
	        //HideMenu();
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
	
	private class GetStatesRSSDataTask extends AsyncTask<String, Void, List<StatesRssItem> > {
	    @Override
	    protected List<StatesRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("StatesRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            StatesRssReader rssReader = new StatesRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<StatesRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.listFeeds);
	                     
	        // Create a list adapter
	        final ArrayAdapter<StatesRssItem> adapter = new ArrayAdapter<StatesRssItem>(local,android.R.layout.simple_list_item_1, result);
	        // Set list adapter for the ListView
	        lv.setAdapter(adapter);
	        EditText inputSearch = (EditText)findViewById(R.id.txtSearchList);
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
	        lv.setOnItemClickListener(new StatesListListener(result, local));
	        
	        //HideMenu();
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
	
	private class GetPartyRSSDataTask extends AsyncTask<String, Void, List<PartyRssItem> > {
	    @Override
	    protected List<PartyRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("PartyRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            PartyRssReader rssReader = new PartyRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<PartyRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.listFeeds);
	                     
	        // Create a list adapter
	        final ArrayAdapter<PartyRssItem> adapter = new ArrayAdapter<PartyRssItem>(local,android.R.layout.simple_list_item_1, result);
	        // Set list adapter for the ListView
	        lv.setAdapter(adapter);
	        EditText inputSearch = (EditText)findViewById(R.id.txtSearchList);
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
	        lv.setOnItemClickListener(new PartyListListener(result, local));
	        
	        //HideMenu();
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
	private class GetBLoMRSSDataTask extends AsyncTask<String, Void, List<BLoMRssItem> > {
	    @Override
	    protected List<BLoMRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("BLoMRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	            BLoMRssReader rssReader = new BLoMRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<BLoMRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.listFeeds);
	                     
	        // Create a list adapter
	        final ArrayAdapter<BLoMRssItem> adapter = new ArrayAdapter<BLoMRssItem>(local,android.R.layout.simple_list_item_1, result);
	        // Set list adapter for the ListView
	        lv.setAdapter(adapter);
	        EditText inputSearch = (EditText)findViewById(R.id.txtSearchList);
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
	        lv.setOnItemClickListener(new BLoMListListener(result, local));
	        
	        //HideMenu();
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
	
	
	private class GetCommMemRSSDataTask extends AsyncTask<String, Void, List<CommMemRssItem> > {
	    @Override
	    protected List<CommMemRssItem> doInBackground(String... urls) {
	         
	        // Debug the task thread name
	        Log.d("BLoMRssReader", Thread.currentThread().getName());
	         
	        try {
	            // Create RSS reader
	        	CommMemRssReader rssReader = new CommMemRssReader(urls[0]);
	         
	            // Parse RSS, get items
	            return rssReader.getItems();
	         
	        } catch (Exception e) {
	            Log.e("RssReader", e.getMessage());
	        }
	         
	        return null;
	    }
	     
	    @Override
	    protected void onPostExecute(List<CommMemRssItem> result) {
	        // Get a ListView from main view
	        ListView lv = (ListView)findViewById(R.id.listFeeds);
	                     
	        // Create a list adapter
	        final ArrayAdapter<CommMemRssItem> adapter = new ArrayAdapter<CommMemRssItem>(local,android.R.layout.simple_list_item_1, result);
	        // Set list adapter for the ListView
	        lv.setAdapter(adapter);
	        EditText inputSearch = (EditText)findViewById(R.id.txtSearchList);
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
	        lv.setOnItemClickListener(new CommMemListListener(result, local));
	        
	        //HideMenu();
	    	//Home hm = new Home();
	    	//hm.SetItems(result);
	    }
	}
}
