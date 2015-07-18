package com.example.ls;

import java.util.List;

import com.example.loksabha.R;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class CommitteeMeeting extends Activity {
	private TabHost tabhost;
	private TabSpec tabspec1;
	private TabSpec tabspec2;
	private LoadItems li;
	public CommitteeMeeting local;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comm_meetings);

		tabhost = (TabHost) findViewById(R.id.tabHost_meetings);
		tabhost.setup();

		tabspec1 = tabhost.newTabSpec("Tab 1");
		tabspec1.setContent(R.id.tab1_date_meetings);
		tabspec1.setIndicator("Date Wise");

		tabspec2 = tabhost.newTabSpec("Tab 2");
		tabspec2.setContent(R.id.tab2_com_meetings);
		tabspec2.setIndicator("Committee Wise");
		if (CompatibilityManager.isIceCreamSandwich()) {
			BackCompatibility();
		}

		tabhost.addTab(tabspec1);
		tabhost.addTab(tabspec2);
		tabhost.getChildAt(0).setBackgroundColor(Color.rgb(247, 195, 0));
		local = this;
		li = new LoadItems();
		// Toast.makeText(getBaseContext(), li.getDateWiseUrl(),
		// Toast.LENGTH_LONG).show();
		getDateWiseCommitteeMeetingFeeds(li.getDateWiseUrl());
		getCommitteeWiseFeeds(li.getCommitteeWiseUrl());
	}

	private void getCommitteeWiseFeeds(String committeeWiseUrl) {
		GetCommitteeWiseDataTask task = new GetCommitteeWiseDataTask();
		task.execute(committeeWiseUrl);
		Log.d("CommitteeWiseMeetings", Thread.currentThread().getName());
	}

	private class GetCommitteeWiseDataTask extends
			AsyncTask<String, Void, List<MeetingRssItem>> {

		@Override
		protected List<MeetingRssItem> doInBackground(String... urls) {
			// Debug the task thread name
			Log.d("CommitteeWiseMeetings", Thread.currentThread().getName());

			try {
				// Create RSS reader
				MeetingRssReader rssReader = new MeetingRssReader(urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("CommitteeWiseMeetings", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<MeetingRssItem> result) {
			// Get a ListView from main view
			ListView lv = (ListView) findViewById(R.id.lstCom_meetings);
			// Create a list adapter
			ArrayAdapter<MeetingRssItem> adapter = new ArrayAdapter<MeetingRssItem>(
					local, android.R.layout.simple_list_item_1, result);
			lv.setAdapter(adapter);
		}
	}

	private void getDateWiseCommitteeMeetingFeeds(String dateWiseUrl) {
		GetDateWiseDataTask task = new GetDateWiseDataTask();
		task.execute(dateWiseUrl);
		Log.d("DateWiseMeetings", Thread.currentThread().getName());
	}

	private class GetDateWiseDataTask extends
			AsyncTask<String, Void, List<MeetingRssItem>> {

		@Override
		protected List<MeetingRssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("DateWiseMeeings", Thread.currentThread().getName());

			try {
				// Create RSS reader
				MeetingRssReader rssReader = new MeetingRssReader(urls[0]);

				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				Log.e("DateWiseMeeings", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(List<MeetingRssItem> result) {
			// Get a ListView from main view
			ListView lv = (ListView) findViewById(R.id.lst_date_meetings);
			// Create a list adapter
			ArrayAdapter<MeetingRssItem> adapter = new ArrayAdapter<MeetingRssItem>(
					local, android.R.layout.simple_list_item_1, result);
			lv.setAdapter(adapter);

		}

	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void BackCompatibility() {
		ActionBar abc = getActionBar();
		abc.setHomeButtonEnabled(true);
		abc.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_member_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		onBackPressed();
		return true;
	}

}
